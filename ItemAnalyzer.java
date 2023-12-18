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
    for(Item item : items){
      
      String vraitem = item.getTitre();
      listitre += vraitem;
    }
    listitre = listitre.replace(" ", "");
    return  (int) Math.ceil(listitre.length()/146);
  }

  public ArrayList<Double> getMoyenne_Prix_Etat() {
    // code here
    return null;
  }

  public Double getPart_Livre_Audio() {

    // code here
    return null;
  }
}
