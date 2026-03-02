package lsg.consumables.food;

import lsg.consumables.Consumable;
import lsg.consumables.food.Americain;
import lsg.characters.Character;
public class Americain extends Food{
    public Americain(String name, int capacity, String stat){
        super(name, capacity, Character.LIFE_STAT_STRING);
    }

     @Override
    public String toString(){
        return String.format("%s [%d life point(s)]", getName(), getCapacity());
    }

    public static void main(String[] args) {
        Americain americain = new Americain("Friterie 2000's Best of the Best", 40, null);
        System.out.println(americain);
    }
}