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

        // Titre sans espace avant 

        //*Character a = titre.charAt(1);        
        //while(a==(' ')){
         // titre = titre.substring(1,titre.length());
         // a = titre.charAt(1);
        //}
        //titre = titre.substring(1,titre.length());
        //

        //Auteur

        int balise_A=pageSource.indexOf("product__manufacturer",fin_A)+"product__manufacturer".length()+2;
        int debut_A=pageSource.indexOf(">", balise_A);
        fin_A=pageSource.indexOf("<", debut_A);
        
        String auteur = pageSource.substring(debut_A+1, fin_A);

        //Etat

        int debut_E=pageSource.indexOf("product__condition",fin_E)+"product__condition".length()+2;
        fin_E=pageSource.indexOf("<", debut_E);

        String etat=pageSource.substring(debut_E,fin_E);

        //Prix
        int debut_P=0; //comme on a deux debut different, celui ci sera le debut final
        int debut_P1=pageSource.indexOf("product__price",fin_P)+"product__price".length()+2; //debut possilble de prix 1
        int debut_P2=pageSource.indexOf("--sale",fin_P)+"--sale".length()+2; //debut possible de prix 2
        int teste=debut_P1+1; //(javais pas d'idée de nom de variable), c'est pour avoir l'idx du caractere juste apres le debut_1
        String cs1=pageSource.substring(debut_P1, teste); //sert a avoir le caractere juste apres en string
        int idxcs1=pageSource.indexOf(cs1); //jPour avoir l'idx du string que je viens de créer, si je met teste ça marche pas
        Character charcs1=pageSource.charAt(idxcs1); //je le transforme en caractere pour pouvoir l'analyser 

        
        if(Character.isDigit(charcs1)){ //ici je vérifie que me caractere est un chiffre, le premier chiffre du prix 
           debut_P=debut_P1;//si c'est un chiffre alors j'ai le bon début
        }
        else {
          debut_P=debut_P2;
          //Sinon c'est l'autre
        }

      fin_P=pageSource.indexOf("&",debut_P); 
      String ps=pageSource.substring(debut_P, fin_P);
      Double prix= Double.parseDouble(ps.replace(',', '.')); //le changer de String a Double en remplacer la ',' par '.'

        //Audi ou Physique

        int debut_Au=pageSource.indexOf("product__type",fin_P)+"product__type".length()+2;
        fin_P=pageSource.indexOf("<", debut_Au);

        String audio=pageSource.substring(debut_Au,fin_P);
        





        
        
        


      // add code here
      /* Idee :
      etat après <div class="product__condition"> 
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
        item.setPrix(prix);
        item.setEtat(etat);
        item.setPhysique_Audio(audio);
        itemList.add(item);

        if (i==100){          // A changer quand on a recup toutes les données 
          // on a fini d'extraire les item
          break;
          }
        i++;
        }
        
      return itemList;
    }
    
  }
