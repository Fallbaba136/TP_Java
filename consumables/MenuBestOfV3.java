package lsg.consumables;

import java.util.HashSet;
import java.util.Hashtable;

import lsg.consumables.drinks.*;
import lsg.consumables.food.*;

public class MenuBestOfV3 extends HashSet<Consumable> {

   // HashSet<Consumable> set = new HashSet<>(); // pas d'indice stockage par hachage

    /**
     * A propos de l'itération d'un Hashet :
     * Aucun ordre garantie
     * L'ordre peut changer
     * Performance d'itération
     * les itérateurs retournées sont fail-fast : si le set est modifier après la création de l'itérateur
     * Alternative ordonné : LinkedHasSet
     */
    
    public MenuBestOfV3(){
        add(new Hamburger("Uncle Greg's spicy Maroilles burger", 40, null));
        add(new Wine("Pomerol 2008", 30, null));
        add(new Americain("Friterie 2000's Best of the Best", 3000, null));
        add(new Coffee("Hot Grandmother Coffee", 10, null));
        add(new Whisky("12 years old Oban", 150, null));
    }
      public String toString(){
        String result = "MenuBestOfV3 : \n";
       for(Consumable c : this){ // Parcour possible pour un 
            result += c + "\n";
       }
       return result;
    }

    public static void main(String[] args) {
        MenuBestOfV3 menu = new MenuBestOfV3();
        System.out.println(menu);
    }
}