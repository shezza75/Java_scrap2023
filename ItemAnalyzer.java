package fr.pantheonsorbonne.ufr27.miashs.poo;

import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.text.DecimalFormat;
import java.util.ArrayList;

public final class ItemAnalyzer {
  private ArrayList<Item> items = new ArrayList<>();

  public ItemAnalyzer(ArrayList<Item> items) {
    this.items=items;
  }

  public Double getEcart_Type_Prix() {
    Double vraitem = 0.;
    double moyenne = 0.;
    double variance = 0.;
    for(Item item : items){
      vraitem =  item.getPrix();
  
      moyenne += vraitem ;
      
    }
    
    moyenne = (moyenne-vraitem)/146;
    moyenne = Math.round(moyenne*100.0)/100.0;
    //System.out.println("c'est la moyenne apres div "+moyenne);
    for(Item item : items){
      variance +=Math.pow((item.getPrix()-moyenne),2)/146;
    }
    //System.out.println(moyenne);
    //System.out.println(variance);
    Double ecartype = Math.sqrt(variance);
    //System.out.println(ecartype);
    //DecimalFormat decimalFormat = new DecimalFormat("#.##");
    //double ecartype_arr = Double.parseDouble(decimalFormat.format(ecartype));
    double ecartype_arr = Math.round(ecartype*100.0)/100.0;
    
    return ecartype_arr;
  }

  public Integer getAuteurs_Differents() {
    int c=0;
    String listAuteur ="";
    for(Item item : items){
      String vraitem = item.getAuteur();
      if(listAuteur.indexOf(vraitem)==-1){
        listAuteur+=vraitem;
        c++;
      }
    }
    return c;
  }

  public Integer getNb_Lettres_Moyens_Arr() {
    String listitre = "";
    String alphabet="abcdefghijklmnopqrstuvwxyzéèàâêôûîäëïöüABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ÄËÜÏÖÂÊÛÎÔ";
    for(Item item : items){
      
      String vraitem = item.getTitre();
      for(int i=0; i<vraitem.length();i++){
        Character teste = vraitem.charAt(i);
        int lettre=alphabet.indexOf(teste);
        if(lettre!=-1){
        String lString = Character.toString(teste);
        listitre+=lString;
        }
      }
       
    }
    listitre = listitre.replace(" ", "");
    return  (int) Math.ceil(listitre.length()/146);
  }

  public ArrayList<Double> getMoyenne_Prix_Etat() {
    int NbLivreN=0;
    int NbLivreTBE=0;
    int NbLivreO=0;

    double SumpN=0;
    double SumpTBE=0;
    double SumpO=0;
    for(Item item : items){
      String vraitem = item.getEtat();
      Double vraiprix=item.getPrix();
      
      if(!(vraitem.indexOf("Comme neuf - À offrir")==-1)){
        NbLivreN+=1;
        SumpN += vraiprix;
      }
      if(!(vraitem.indexOf("Très bon état - À offrir")==-1)){
        NbLivreTBE+=1;
        SumpTBE += vraiprix;
      }
      if(!(vraitem.indexOf("D'occasion - bon état")==-1)){
        NbLivreO+=1;
        SumpO += vraiprix;
      }

  }
  ArrayList<Double> MoyenneEtat= new ArrayList<>();
  Double moyN= SumpN/NbLivreN;
  Double moyTBE= SumpTBE/NbLivreTBE;
  Double moyO= SumpO/NbLivreO;
  moyN = Math.round(moyN*100.0)/100.0;
  moyO = Math.round(moyO*100.0)/100.0;
  moyTBE = Math.round(moyTBE*100.0)/100.0;
  
  MoyenneEtat.add(moyN);
  MoyenneEtat.add(moyTBE);
  MoyenneEtat.add(moyO);

    return MoyenneEtat;
  }

  public Double getPart_Livre_Audio() {

    Double nb_Audio=0.0;
    Double tot = 0.0;
    for(Item item : items){
      String vraitem = item.getPhysique_Audio();
      tot++;
      
       if(vraitem.indexOf("Livre audio")!=-1){
        nb_Audio++;
       }
    }
    Double part_Audio = nb_Audio/tot;
    part_Audio = Math.round(part_Audio*100.0)/100.0;
    return part_Audio;
  }
}
