package lsg.consumables;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;

import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.Americain;
import lsg.consumables.food.Hamburger;
import lsg.consumables.repair.*;

public class MenuBestOfV4 extends LinkedHashSet<Consumable>{
    /**
     * Conserve l'ordre
     * Pas de doublons
     * Un peu plus lent que Hashet
     */
    
     public MenuBestOfV4(){
        add(new Hamburger("Uncle Greg's spicy Maroilles burger", 40, null));
        add(new Wine("Pomerol 2008", 30, null));
        add(new Americain("Friterie 2000's Best of the Best", 3000, null));
        add(new Coffee("Hot Grandmother Coffee", 10, null));
        add(new Whisky("12 years old Oban", 150, null));
        add(new RepairKit());
    }
      public String toString(){
        String result = "MenuBestOfV4 : \n";
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