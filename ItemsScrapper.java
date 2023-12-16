package fr.pantheonsorbonne.ufr27.miashs.poo;

import java.lang.String;
import java.sql.Array;
import java.util.ArrayList;





public final class ItemsScrapper {
      
      ArrayList<Item> parseSource(String pageSource) {
      ArrayList<Item> itemList = new ArrayList<>();

      
      int i = 0; 
      int fin_T = 0;
      int fin_A =  0;
      int fin_E = 0;
      int fin_P=0;
      while (true) {
        
        // Titre 

        int debut_T=pageSource.indexOf("product__title",fin_T)+"product__title".length()+2;
        int option1_T=pageSource.indexOf("(", debut_T);
        int option2_T=pageSource.indexOf("<", debut_T);
        if((option1_T != -1) && (option1_T<option2_T)){
          fin_T=option1_T;
        }
        else{
          fin_T=option2_T;
        }
        String titre = pageSource.substring(debut_T, fin_T);

        //Auteur

        int balise_A=pageSource.indexOf("product__manufacturer",fin_A)+"product__manufacturer".length()+2;
        int debut_A=pageSource.indexOf(">", balise_A);
        fin_A=pageSource.indexOf("<", debut_A);
        
        String auteur = pageSource.substring(debut_A+1, fin_A);

        //Etat

        int debut_E=pageSource.indexOf("product__condition",fin_E)+"product__condition".length()+2;
        fin_E=pageSource.indexOf("<", debut_E);

        String etat=pageSource.substring(debut_E,fin_E);

        // Physique_Audio

        int debut_P=pageSource.indexOf("product__type",fin_P)+"product__type".length()+2;
        fin_P=pageSource.indexOf("<", debut_P);

        String audio=pageSource.substring(debut_P,fin_P);

        





        
        
      /* Idee : 
      Physique_Audio après <span class="product__type">
      Prix après <span class="product__price">
      
      Je ne sais pas si on aura d'autre info, comment ne pas recuperer que la première donnée ?
      --> Il faut spécifier a quel item nous avons à faire

      Si on veut se placer on peut faire un comptage de substring
      faire une boucle pour faire un substring i fois pour être bien placé



      */
        
        
        
        Item item = new Item();
        item.setTitre(titre);
        item.setAuteur(auteur);
        item.setPrix(null);
        item.setEtat(etat);
        item.setPhysique_Audio(audio);
        itemList.add(item);

        if (i==146){          // A changer quand on a recup toutes les données 
          // on a fini d'extraire les item
          break;
          }
        i++;
        }
        
      return itemList;
    }
    
  }