package lsg.characters;

import lsg.characters.Character;
import lsg.characters.Hero;
import lsg.characters.Monster;
import lsg.weapons.*;
import lsg.armor.*;
import lsg.helper.*;
import lsg.buffs.*;
import lsg.buffs.rings.*;
//class Hero
public class Hero extends Character
{
    public static final int MAX_ARMOR_PIECES = 3;
    //Tableau d'armure
    ArmorItem[] armor = new ArmorItem[MAX_ARMOR_PIECES];

    
    // 1.3 constructeurs 
    public Hero(String name)  { 
        super(name);
    }

    public Hero(){
        name = "Gregoonitoor";
    }

    Ring[] rings = new Ring[2];

    public Ring[] getRing(){
        return rings;
    }

    public void setRing(Ring ring, int slot) {
    if (slot < 1 || slot > 2) {
        return;
    }

    int index = slot - 1;

    // 1) détacher l’ancienne bague si elle existe
    if (rings[index] != null) {
        rings[index].setHero(null);
    }

    // 2) si on veut retirer la bague → vider le slot et terminer
    if (ring == null) {
        rings[index] = null;
        return;
    }

    // 3) attacher la nouvelle bague au héros
    ring.setHero(this);

    // 4) mettre la bague dans le slot
    rings[index] = ring;
}


    //Methodes 
    public int attackWith(Weapon weapon)
    {
        if(weapon.isBroken()) return 0;
        else
        {
            Dice d = new Dice(101);
           float precision = (float)d.roll() / 100;
            int amplitude = weapon.getMaxDamage() - weapon.getMinDamage();
            int bonus = Math.round(amplitude * precision);
            int degat = weapon.getMinDamage() + bonus;
            return degat;
        }

    }

    public void setArmoItem(ArmorItem piece_armure, int slot){
        if(slot < 1 || slot > MAX_ARMOR_PIECES)
        {
            return;
        }
        else{
                armor[slot-1] = piece_armure;
        } 
    }

    public float getTotalArmor(){
        float compteur = 0;
         for(int i = 0; i < armor.length; i++){
            ArmorItem a = armor[i];
            if(a != null){
                compteur += armor[i].getArmorValue();
            }
         }
         return compteur;
    }

    public String armorToString(){

    String s1 = (armor[0] != null) ? armor[0].toString() : "empty";
    String s2 = (armor[1] != null) ? armor[1].toString() : "empty";
    String s3 = (armor[2] != null) ? armor[2].toString() : "empty";

    return String.format(
        "ARMOR   1:%s   2:%s   3:%s TOTAL:%.2f",
        s1,
        s2,
        s3,
        getTotalArmor()
    );
}

    public ArmorItem[] getArmorItems(){

        // compter les piéces équipées
        int count = 0;
        for(int i = 0; i < armor.length; i++)
        {
            ArmorItem a = armor[i];
            if(a != null){ count++;}
        }

        // crée le tableau résultat à la bonne taille
        ArmorItem[] armure = new ArmorItem[count];

        int index = 0;
        for(int i = 0; i < armor.length; i++){
            if (armor[i] != null) {
                armure[index] = armor[i];
                index++;
            }
        }
        return armure;
    }

    @Override
    public float computeProtection(){
        return getTotalArmor();
    }

    public float computeBuff(){
        float compteur = 0;
        for(int i = 0; i < rings.length; i++){
            if (rings[i] != null) {
                compteur += rings[i].computeBuffValue();
            }
        }
        return compteur;
    }




     public static void main(String[] args){
         System.out.println(new Hero("xx").armorToString());
    }
}

