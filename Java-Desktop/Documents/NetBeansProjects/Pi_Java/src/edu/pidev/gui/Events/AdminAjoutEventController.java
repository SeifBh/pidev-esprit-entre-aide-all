/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Events;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import edu.pidev.entities.Evenement;
import edu.pidev.entities.Utilisateur;
import edu.pidev.services.EvenementServices;
import edu.pidev.tests.PI_Main;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import static java.sql.Types.NULL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class AdminAjoutEventController implements Initializable , MapComponentInitializedListener {

    @FXML
    private AnchorPane acMain;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private ScrollPane leftSideBarScroolPan;
    @FXML
    private Button btnClub;
    @FXML
    private Button btnStore;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSpot;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnAnnonce;
    @FXML
    private BorderPane appContent;
    @FXML
    private AnchorPane acHead;
    @FXML
    private MenuButton mbtnUsrInfoBox;
    @FXML
    private MenuItem miPopOver;
    @FXML
    private Circle circleImgUsr;
    @FXML
    private Label lblUsrNamePopOver;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblRoleAs;
    @FXML
    private Hyperlink hlEditUpdateAccount;
    @FXML
    private Button btnLogOut;
    @FXML
    private Circle imgUsrTop;
    @FXML
    private Label lblUsrName;
    @FXML
    private ToggleButton sideMenuToogleBtn;
    @FXML
    private Label lblUserId;
    @FXML
    private StackPane acContent;
    @FXML
    private JFXButton hideMenu;
    @FXML
    private JFXButton showMenu;
    @FXML
    private TextField titreE;
    @FXML
    private TextArea descE;
    @FXML
    private DatePicker dateE;
    @FXML
    private Button ajouter;
    @FXML
    private Button annuler;
    @FXML
    private ComboBox<String> typeE;
    @FXML
    private ImageView imageEvent;
    @FXML
    private ImageView verif_titre;
    @FXML
    private ImageView verif_desc;
    @FXML
    private ImageView verif_date;
    @FXML
    private ImageView verif_type;
    @FXML
    private GoogleMapView mapView;
    @FXML
    private TextField ville;
    @FXML
    private ImageView verif_map;
    @FXML
    private TextArea adresse;
    @FXML
    private TextField longitude;
    @FXML
    private TextField latitude;
    @FXML
    private JFXTimePicker time;

    
      int c;
    File pDir;
    File pfile;
    String lien;
    int file = 0;
    String role;
    private GoogleMap map;
    private GeocodingService geocodingService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        typeE.getItems().addAll("Seminaire","Workshop","Culturel","Gaming","Coding","Sportif","Autre");
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("src/Media/Evenement" + c + ".jpg");
        lien = "Evenement" + c + ".jpg";
        role = "ADMIN";
        
    }    

   @FXML
    private void btnHomeOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Home.fxml"));
        Parent root;
        root = loader.load();
        btnHome.getScene().setRoot(root);
    }


    @FXML
    private void btnUserOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./GererUser.fxml"));
        Parent root;
        root = loader.load();
        btnUser.getScene().setRoot(root);

    }
    @FXML
    public void btnEvenement(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Events/AdminEventHome.fxml"));
        Parent root;
        root = loader.load();
        btnEvent.getScene().setRoot(root);
    }

    @FXML
    private void btnStoreOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Stores/AdminStoreHome.fxml"));
        Parent root;
        root = loader.load();
        btnStore.getScene().setRoot(root);
    }

    @FXML
    private void btnSettingsOnClick(ActionEvent event) {
        
    }


    @FXML
    private void hlUpdateAccount(ActionEvent event) {
    }

    @FXML
    private void btnLogOut(ActionEvent event) throws IOException {
        Utilisateur u = new Utilisateur();
        PI_Main.setUser(u);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Login.fxml"));
        Parent root;
        root = loader.load();
        
        btnLogOut.getScene().setRoot(root);
    }

    @FXML
    private void mbtnOnClick(ActionEvent event) {
    }

    @FXML
    private void sideMenuToogleBtnOnCLick(ActionEvent event) {
    }

    @FXML
    private void acMainOnMouseMove(MouseEvent event) {
    }

    @FXML
    private void btnClubOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Clubs/AdminClubHome.fxml"));
        Parent root;
        root = loader.load();
        btnClub.getScene().setRoot(root);
    }

    @FXML
    private void btnSpottedOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Spotted/AdminSpottedHome.fxml"));
        Parent root;
        root = loader.load();
        btnSpot.getScene().setRoot(root);
    }
@FXML
    private void btnAnnonceOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Annonces/AdminAnnonceHome.fxml"));
        Parent root;
        root = loader.load();
        btnUser.getScene().setRoot(root);
    }
    
    

    @FXML
    private void choisirImage(MouseEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            imageEvent.setImage(image);
        }
    }
    
    
    public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }
    
    
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
    
  
    
    @FXML
   private void ajouterEvenement(ActionEvent event) throws IOException, SQLException {
        
       
       Boolean test1, test2, test3, test4, test5;
        test1 = test2 = test3 = test4 = test5 = true;
        if (typeE.getValue()==null) {
            System.out.println("pas de categorie choisie");
            Image image1 = new Image("Media/no-icon.png");
            verif_type.setImage(image1);
            test1 = false;
        } else {
            Image image1 = new Image("Media/yes-icon.png");
            verif_type.setImage(image1);
        }
        if (titreE.getText().matches("")) {
            System.out.println("pas de nom");
            Image image2 = new Image("Media/no-icon.png");
            verif_titre.setImage(image2);
            test2 = false;
        } else {
            Image image1 = new Image("Media/yes-icon.png");
            verif_titre.setImage(image1);
        }
        if (descE.getText().matches("")) {
            System.out.println("pas de description");
            Image image3 = new Image("Media/no-icon.png");
            verif_desc.setImage(image3);
            test3 = false;
        } else {
            Image image1 = new Image("Media/yes-icon.png");
            verif_desc.setImage(image1);
        }
        
        if (dateE.getValue() == null) {
            System.out.println("pas de date insérée");
            Image image5 = new Image("Media/no-icon.png");
            verif_date.setImage(image5);
            test4 = false;
        } else {
            Image image1 = new Image("Media/yes-icon.png");
            verif_date.setImage(image1);
        }
        if (longitude.getText().matches("")) {
            System.out.println("pas de localisation");
            Image image7 = new Image("Media/no-icon.png");
            verif_map.setImage(image7);
            test3 = false;
        } else {
            Image image1 = new Image("Media/yes-icon.png");
            verif_map.setImage(image1);
        }
        
        
        System.out.println("tests ok");
        if (test1 && test2 && test3 && test4 && test5) {
            if (file == 0) {
                
                EvenementServices evenement = EvenementServices.getInstance();
                LocalTime t= time.getValue();
                LocalDate date = dateE.getValue();
                Date d = convertToDateViaSqlDate(date);
                if (date.isBefore(LocalDate.now())){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Vous ne pouvez pas choisir une date antérieure à la date du jour", ButtonType.OK);
                    alert.showAndWait();
                }else{
                Timestamp ts = new Timestamp(d.getYear(), d.getMonth(), date.getDayOfMonth(), t.getHour(), t.getMinute(), 00, 00);
                Evenement e = new 
                                Evenement(titreE.getText(),
                                        descE.getText(),
                                        ts,
                                        PI_Main.getUser().getId() ,
                                        role,
                                        1 ,
                                        "nophoto.png",
                                        typeE.getValue().toString(),
                                        "a:4:{s:3:\"lat\";s:"+latitude.getText().length()+":\""+latitude.getText()+"\";s:3:\"lng\";s:"+longitude.getText().length()+":\""+longitude.getText()+"\";s:4:\"city\";s:"+ville.getText().length()+":\""+ville.getText()+"\";s:7:\"address\";s:"+adresse.getText().length()+":\""+adresse.getText()+"\";}");

                
                 e.setEtat(0);
                
                evenement.ajouterEvenement(e);
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Evenenement ajouté avec succès, voulez vous ajouter un autre?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult().getText().equalsIgnoreCase("oui")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("./AdminAjoutEvent.fxml"));
                Parent root;
                root = loader.load();
                ajouter.getScene().setRoot(root);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("./AdminEventHome.fxml"));
                    Parent root;
                    root = loader.load();
                    ajouter.getScene().setRoot(root);
                }
                }          
            } else {
                copier(pfile, pDir);

                EvenementServices evenement = EvenementServices.getInstance();
                
                 LocalTime t= time.getValue();
                LocalDate date = dateE.getValue();
                Date d = convertToDateViaSqlDate(date);
                if (date.isBefore(LocalDate.now())){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Vous ne pouvez pas choisir une date antérieure à la date du jour", ButtonType.OK);
                    alert.showAndWait();
                }else{
                Timestamp ts = new Timestamp(d.getYear(), d.getMonth(), date.getDayOfMonth(), t.getHour(), t.getMinute(), 00, NULL);
                
                Evenement e = new Evenement(
                        titreE.getText(),
                        descE.getText(),
                        ts, 
                        PI_Main.getUser().getId() , 
                        role,
                        1 ,
                        lien,
                        typeE.getValue().toString(),
                        "a:4:{s:3:\"lat\";s:"+latitude.getText().length()+":\""+latitude.getText()+"\";s:3:\"lng\";s:"+longitude.getText().length()+":\""+longitude.getText()+"\";s:4:\"city\";s:"+ville.getText().length()+":\""+ville.getText()+"\";s:7:\"address\";s:"+adresse.getText().length()+":\""+adresse.getText()+"\";}");
                
                
                e.setEtat(0);
               
                evenement.ajouterEvenement(e);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Evenenement ajouté avec succès, voulez vous ajouter un autre?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult().getText().equalsIgnoreCase("oui")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("./AdminAjoutEvent.fxml"));
                Parent root;
                root = loader.load();
                ajouter.getScene().setRoot(root);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("./AdminEventHome.fxml"));
                    Parent root;
                    root = loader.load();
                    ajouter.getScene().setRoot(root);
                }
                
                }       
            }
        } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Champs non saisis ou invalides", ButtonType.OK);
                alert.showAndWait();
                
        }
       

    } 
   @FXML
   public void annuler(ActionEvent ev) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./AdminEventHome.fxml"));
        Parent root;
        root = loader.load();
        annuler.getScene().setRoot(root);
   }

    @Override
    public void mapInitialized() {
        
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        LatLong Location = new LatLong(36.8981646, 10.1876613);
        MarkerOptions markerOptions1 = new MarkerOptions();
        Marker m = new Marker(markerOptions1);
        System.out.println(Location);
        mapOptions.center(Location)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(13);
        map = mapView.createMap(mapOptions);
        
        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
            markerOptions1.position(event.getLatLong())
                    .visible(true)
                    ;
            m.setOptions(markerOptions1);
            map.addMarker(m);
            double longi = event.getLatLong().getLongitude();
            
            double lat = event.getLatLong().getLatitude();
            longitude.setText(Double.toString(longi));
            latitude.setText(Double.toString(lat));
            
           geocodingService.reverseGeocode(event.getLatLong().getLatitude(), event.getLatLong().getLongitude(), new GeocodingServiceCallback() {
                @Override
                public void geocodedResultsReceived(GeocodingResult[] grs, GeocoderStatus gs) {
                 String  address= grs[2].toString().substring(grs[2].toString().indexOf("Address: "), grs[2].toString().indexOf(", Tunisia"));
                 address=address.substring(address.indexOf(" ")+1, address.length());
                 ville.setText(address);
                    System.out.println(address);                
                }
            });
            
        });
        
        
    }

    

    
}