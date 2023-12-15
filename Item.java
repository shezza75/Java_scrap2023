package fr.pantheonsorbonne.ufr27.miashs.poo;

import java.lang.Double;
import java.lang.String;

public final class Item {
  private String Titre;

  private String Auteur;

  private Double Prix;

  private String Etat;

  private String Physique_Audio;

  public String getTitre() {
    return this.Titre;
  }

  public void setTitre(String Titre) {
    this.Titre=Titre;
  }

  public String getAuteur() {
    return this.Auteur;
  }

  public void setAuteur(String Auteur) {
    this.Auteur=Auteur;
  }

  public Double getPrix() {
    return this.Prix;
  }

  public void setPrix(Double Prix) {
    this.Prix=Prix;
  }

  public String getEtat() {
    return this.Etat;
  }

  public void setEtat(String Etat) {
    this.Etat=Etat;
  }

  public String getPhysique_Audio() {
    return this.Physique_Audio;
  }

  public void setPhysique_Audio(String Physique_Audio) {
    this.Physique_Audio=Physique_Audio;
  }
}
