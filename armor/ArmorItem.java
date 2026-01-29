package lsg.armor;

public class ArmorItem {
    // attributs
    protected String name;
    protected float armorValue;

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
