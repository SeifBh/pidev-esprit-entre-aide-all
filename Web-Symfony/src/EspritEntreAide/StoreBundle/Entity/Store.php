<?php

namespace EspritEntreAide\StoreBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Store
 *
 * @ORM\Table(name="store")
 * @ORM\Entity(repositoryClass="EspritEntreAide\StoreBundle\Repository\StoreRepository")
 */
class Store
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @ORM\OneToOne(targetEntity="EspritEntreAide\UserBundle\Entity\User",inversedBy="store")
     */
    private $user;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=255, nullable=true)
     */
    private $etat;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_store", type="string", length=255, nullable=true)
     */
    private $nomStore;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set idUser
     *
     * @param integer $idUser
     *
     * @return Store
     */
    public function setIdUser($idUser)
    {
        $this->idUser = $idUser;

        return $this;
    }

    /**
     * Get idUser
     *
     * @return int
     */
    public function getIdUser()
    {
        return $this->idUser;
    }

    /**
     * Set etat
     *
     * @param string $etat
     *
     * @return Store
     */
    public function setEtat($etat)
    {
        $this->etat = $etat;

        return $this;
    }

    /**
     * Get etat
     *
     * @return string
     */
    public function getEtat()
    {
        return $this->etat;
    }

    /**
     * @return string
     */
    public function getNomStore()
    {
        return $this->nomStore;
    }

    /**
     * @param string $nomStore
     */
    public function setNomStore($nomStore)
    {
        $this->nomStore = $nomStore;
    }



    /**
     * Set user
     *
     * @param \EspritEntreAide\UserBundle\Entity\User $user
     *
     * @return Store
     */
    public function setUser(\EspritEntreAide\UserBundle\Entity\User $user = null)
    {
        $this->user = $user;
    
        return $this;
    }

    /**
     * Get user
     *
     * @return \EspritEntreAide\UserBundle\Entity\User
     */
    public function getUser()
    {
        return $this->user;
    }
}
