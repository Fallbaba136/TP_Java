package lsg.consumables;

import lsg.consumables.drinks.*;
import lsg.consumables.food.*;

public class MenuBestOfV2 {

     //Attributs
    private Consumable[] menu = {
        new Wine("Pomerol 2008", 30, null),
        new  Hamburger("Uncle Greg's spicy Maroilles burger", 40, null),
        new Coffee("Hot Grandmother Coffee", 10, null),new Whisky("12 years old Oban", 150, null),
        new Americain("Friterie 2000's Best of the Best", 3000, null)
    };


    public String toString(){
        String result = "MenuBestOfV2 : \n";
       for(int i = 0 ; i < 5; i++){
        result += (i+1) + " : " + menu[i] + "\n";
       }
       return result;
    }
    
    public static void main(String[] args) {
        MenuBestOfV2 menu = new MenuBestOfV2();
        System.out.println(menu);
    }
}