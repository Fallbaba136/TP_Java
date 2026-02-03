package lsg.armor;

public class ArmorItem {
    // attributs
    protected String name; //nom de la pièce
    protected float armorValue; // valeur d'armure de l'item

    // Constructeur
    public ArmorItem(String name, float armorValue){
        this.name = name;
        this.armorValue = armorValue;
    }

    // Accesseurs
    public String getName(){return name;}
    public float getArmorValue(){return armorValue;}

    // Methode
    public String toString(){
        return String.format("%s(%.1f)",
         name, 
         armorValue);
    }
}
