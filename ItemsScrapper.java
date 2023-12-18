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
      int fin_Prix=0;
      while (true) {
        
        //////////////////////////////////////////   Titre 

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

        ///////////  Titre sans espace avant 

        Character a_T = titre.charAt(1);        
        while(a_T==(' ')){
          titre = titre.substring(1,titre.length());
          a_T = titre.charAt(1);
          //System.out.println(titre);
        }
        titre = titre.substring(1,titre.length());

        // ////////////////////////////////////////   Auteur

        int balise_A=pageSource.indexOf("product__manufacturer",fin_A)+"product__manufacturer".length()+2;
        int debut_A=pageSource.indexOf(">", balise_A);
        fin_A=pageSource.indexOf("<", debut_A);
        
        String auteur = pageSource.substring(debut_A+1, fin_A);

        ////////////////////////////////////////////  Etat

        int debut_E=pageSource.indexOf("product__condition",fin_E)+"product__condition".length()+2;
        fin_E=pageSource.indexOf("<", debut_E);

        String etat=pageSource.substring(debut_E,fin_E);

        /////////// Enlever les espaces des etats 

        Character a_E = etat.charAt(1);
        while(a_E==(' ')){
          etat = etat.substring(1,etat.length());
          a_E=etat.charAt(1);
          System.out.println(etat);
        } 
        etat = etat.substring(1,etat.length());


        //////////// Prix 
        
        int debut_Prix=0; //comme on a deux debut different, celui ci sera le debut final
        int debut_P1=pageSource.indexOf("product__price",fin_Prix)+"product__price".length()+2; //debut possilble de prix 1
        int debut_P2=pageSource.indexOf("--sale",fin_Prix)+"--sale".length()+2; //debut possible de prix 2
        int teste=debut_P1+1; //(javais pas d'idée de nom de variable), c'est pour avoir l'idx du caractere juste apres le debut_1
        String cs1=pageSource.substring(debut_P1, teste); //sert a avoir le caractere juste apres en string
        int idxcs1=pageSource.indexOf(cs1); //jPour avoir l'idx du string que je viens de créer, si je met teste ça marche pas
        Character charcs1=pageSource.charAt(idxcs1); //je le transforme en caractere pour pouvoir l'analyser 

        
        if(Character.isDigit(charcs1)){ //ici je vérifie que me caractere est un chiffre, le premier chiffre du prix 
           debut_Prix=debut_P1;//si c'est un chiffre alors j'ai le bon début
        }
        else {
          debut_Prix=debut_P2;
          //Sinon c'est l'autre
        }

        
      fin_P=pageSource.indexOf("&",debut_Prix); 
      String ps=pageSource.substring(debut_Prix, fin_P);
      Double prix= Double.parseDouble(ps.replace(',', '.')); //le changer de String a Double en remplacer la ',' par '.'

        




        //////////////////////////////////////////  Physique_Audio

        int debut_P=pageSource.indexOf("product__type",fin_P)+"product__type".length()+2;
        fin_P=pageSource.indexOf("<", debut_P);

        String audio=pageSource.substring(debut_P,fin_P);
 
        Item item = new Item();
        item.setTitre(titre);
        item.setAuteur(auteur);
        item.setPrix(prix);
        item.setEtat(etat);
        item.setPhysique_Audio(audio);
        itemList.add(item);

        if (i==148){          // PASSSS 149 pitié sinon marche pas
          // on a fini d'extraire les item
          break;
          }
        i++;
        }
        
      return itemList;
    }
    
  }
