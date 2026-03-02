package lsg.consumables.food;

import lsg.consumables.Consumable;
import lsg.consumables.food.Hamburger;
import lsg.characters.Character;
public class Hamburger extends Food{
    public Hamburger(String name, int capacity, String stat){
        super(name, capacity, Character.LIFE_STAT_STRING);
    }

     @Override
    public String toString(){
        return String.format("%s [%d life point(s)]", getName(), getCapacity());
    }

    public static void main(String[] args) {
        Hamburger hamburger = new Hamburger("Uncle Greg's spicy Maroilles burger", 40, null);
        System.out.println(hamburger);
    }
}