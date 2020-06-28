/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import edu.pidev.entities.Commentaire;
import edu.pidev.entities.Publication;
import static edu.pidev.gui.Spotted.DetailsDescController.contentme;
import static edu.pidev.gui.Spotted.DetailsDescController.idme;
import static edu.pidev.gui.Spotted.ListPublicationController.a1;
import static edu.pidev.gui.Spotted.ListPublicationImageController.a1;
import edu.pidev.services.CrudCommentaire;
import edu.pidev.services.CrudPublication;
import edu.pidev.utils.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Seif BelHadjAli
 */
public class DImageController implements Initializable {

    public static int a1;

    Connection cnx = DataSource.getInstance().getConnection();

    CrudPublication cr = new CrudPublication();
    Publication c = new Publication();
    private Image img;
    @FXML
    private ImageView IMGC;
    private Label tags;
    private TableView<Commentaire> listcommentaire;

    public static int i;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnRetour;
    @FXML
    private TextArea txtComment;
    @FXML
    private Button btnComment;
    @FXML
    private VBox comVB;
    @FXML
    private Label labtags;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        a1 = ListPublicationImageController.a1;
        System.out.println(a1);
        CrudCommentaire annonce = new CrudCommentaire();
        ArrayList arrayList = (ArrayList) annonce.afficherCommentaire2(a1);
        ObservableList observableList = FXCollections.observableArrayList(arrayList);

        List<Commentaire> myList = new ArrayList<Commentaire>();

        try {
            String req = "SELECT * FROM commentaire where id_publication='" + a1 + "'";
            System.out.println(req);
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Commentaire c = new Commentaire(
                        rs1.getInt("id"),
                        rs1.getInt("id_user"),
                        rs1.getInt("id_publication"),
                        rs1.getDate("date_creation"),
                        rs1.getDate("date_modif"),
                        rs1.getString("content_comm"));
                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        comVB.getChildren().clear();
        GridPane pane = new GridPane();
        pane.setVgap(20);
        pane.setHgap(20);
        for (Commentaire c : myList) {

            pane.add(new Label(c.getContent_comm()), 1, 0);
            Button btn1 = new Button();
            btn1.setText("modifier");
            btn1.setOnAction((ActionEventn) -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifComment.fxml"));
                    Parent root;
                    idme = c.getId();
                    contentme = c.getContent_comm();
                    System.out.println("" + idme + "");
                    root = loader.load();
                    btn1.getScene().setRoot(root);
                    //JOptionPane.showMessageDialog(null, c.getContent_comm());
                } catch (IOException ex) {
                    Logger.getLogger(DetailsDescController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
            btn1.setId("" + c.getId() + "");
            Button btn2 = new Button();

            btn1.setText("modifier");
            btn2.setText("supprimer");
            btn2.setOnAction((ActionEventn) -> {
                idme = c.getId();
                CrudCommentaire myTool = new CrudCommentaire();
                myTool.supprimerCommentaire(c, idme);
                JOptionPane.showMessageDialog(null, "Commentaire supprimé avec succes");

            });
            Label lab = new Label("com ");
            lab.setText(c.getContent_comm());
            comVB.getChildren().add(lab);
            comVB.getChildren().add(btn1);
            comVB.getChildren().add(btn2);

        }

        // TODO
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        a1 = ListPublicationImageController.a1;
        CrudPublication myTool = new CrudPublication();
        Publication c = new Publication();
        try {
            String req = "SELECT * FROM publication where   id='" + a1 + "'";
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            if (rs1.next()) {
                c = new Publication(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getTimestamp(4), rs1.getDate(5), rs1.getString(6), rs1.getString("image"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(c.getImage());

        //   try {
        img = new Image(c.getImage().substring(4, c.getImage().length()));
        // } catch (FileNotFoundException ex) {
        //      Logger.getLogger(ListPublicationImageController.class.getName()).log(Level.SEVERE, null, ex);
        //  }  
        IMGC.setImage(img);

        System.out.println(a1);
    }

    private void callme() {
        a1 = ListPublicationImageController.a1;
        System.out.println(a1);
        CrudCommentaire annonce = new CrudCommentaire();
        ArrayList arrayList = (ArrayList) annonce.afficherCommentaire2(a1);
        ObservableList observableList = FXCollections.observableArrayList(arrayList);

        List<Commentaire> myList = new ArrayList<Commentaire>();

        try {
            String req = "SELECT * FROM commentaire where id_publication='" + a1 + "'";
            System.out.println(req);
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Commentaire c = new Commentaire(
                        rs1.getInt("id"),
                        rs1.getInt("id_user"),
                        rs1.getInt("id_publication"),
                        rs1.getDate("date_creation"),
                        rs1.getDate("date_modif"),
                        rs1.getString("content_comm"));
                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        comVB.getChildren().clear();
        GridPane pane = new GridPane();
        pane.setVgap(20);
        pane.setHgap(20);
        for (Commentaire c : myList) {

            pane.add(new Label(c.getContent_comm()), 1, 0);
            Button btn1 = new Button();
            btn1.setText("modifier");
            btn1.setOnAction((ActionEventn) -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifComment.fxml"));
                    Parent root;
                    idme = c.getId();
                    contentme = c.getContent_comm();
                    System.out.println("" + idme + "");
                    root = loader.load();
                    btn1.getScene().setRoot(root);
                    //JOptionPane.showMessageDialog(null, c.getContent_comm());
                } catch (IOException ex) {
                    Logger.getLogger(DetailsDescController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
            btn1.setId("" + c.getId() + "");
            Button btn2 = new Button();

            btn1.setText("modifier");
            btn2.setText("supprimer");
            btn2.setOnAction((ActionEventn) -> {
                idme = c.getId();
                CrudCommentaire myTool = new CrudCommentaire();
                myTool.supprimerCommentaire(c, idme);
                JOptionPane.showMessageDialog(null, "Commentaire supprimé avec succes");

            });
            Label lab = new Label("com ");
            lab.setText(c.getContent_comm());
            comVB.getChildren().add(lab);
            comVB.getChildren().add(btn1);
            comVB.getChildren().add(btn2);

        }

        // TODO
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        a1 = ListPublicationImageController.a1;
        CrudPublication myTool = new CrudPublication();
        Publication c = new Publication();
        try {
            String req = "SELECT * FROM publication where   id='" + a1 + "'";
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            if (rs1.next()) {
                c = new Publication(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getTimestamp(4), rs1.getDate(5), rs1.getString(6), rs1.getString("image"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(c.getImage());

        //   try {
        img = new Image(c.getImage().substring(4, c.getImage().length()));
        // } catch (FileNotFoundException ex) {
        //      Logger.getLogger(ListPublicationImageController.class.getName()).log(Level.SEVERE, null, ex);
        //  }  
        IMGC.setImage(img);

        System.out.println(a1);

    }

    @FXML
    private void btnModif(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./modifPublicationImage.fxml"));
        Parent root;
        root = loader.load();
        btnModif.getScene().setRoot(root);
        callme();
    }

    @FXML
    private void btnSupp_action(ActionEvent event) throws IOException {
        cr.supprimerPublication(c, a1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./ListPublicationImage.fxml"));
        Parent root;
        root = loader.load();
        btnSupp.getScene().setRoot(root);
        callme();

    }

    @FXML
    private void btnRetour_action(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./ListPublicationImage.fxml"));
        Parent root;
        root = loader.load();
        btnRetour.getScene().setRoot(root);
        callme();

    }

    @FXML
    private void btnComment(ActionEvent event) {
        CrudCommentaire cc = new CrudCommentaire();
        Commentaire cc1 = new Commentaire();
        cc.ajoutCommentaire(cc1, a1, txtComment.getText());
        JOptionPane.showMessageDialog(null, "Commentaire Ajoutee avec success");
        callme();

    }

}