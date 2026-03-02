package lsg.consumables.food;

import lsg.consumables.Consumable;
import lsg.consumables.drinks.Coffee;
import lsg.characters.Character;

public class Food extends Consumable{
    public Food(String name, int capacity, String stat){
        super(name, capacity, Character.LIFE_STAT_STRING);
    }

     @Override
    public String toString(){
        return String.format("%s [%d life point(s)]", getName(), getCapacity());
    }

    public static void main(String[] args) {
       
        Food food = new Food("Unlce Greg's spicy Maroilles burger", 40, null);
        System.out.println(food);
    }
}