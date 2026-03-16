package lsg.bags;

import java.util.HashSet;

import lsg.armor.DragonSlayerLeggings;
import lsg.armor.RingedKnightArmor;
import lsg.bags.*;
import lsg.consumables.Consumable;
import lsg.weapons.*;
import lsg.buffs.*;


public class Bag {

    //Attributs
    private int capacity;
    private int weight;
    private HashSet<Collectible> items = new HashSet<>();

    //Constructeur 
    public Bag(int capacity){
        this.capacity = capacity;
    }

    //Accessibilités
    public int getCapacity(){return capacity;}
    public int getWeight(){return weight;}

    //Methodes
    public void push(Collectible item){
        int disponible = capacity - weight;
        if (disponible >= item.getWeight()) {
            items.add(item);
            weight += item.getWeight();
        }
    }

    public Collectible pop(Collectible item){
        if (items.contains(item)) { // si item est de dans 
            items.remove(item);
            return item;
        }
        return null;
    }

    public boolean contains(Collectible item){
        return (items.contains(item));
    }

    public Collectible[] getItems(){
        int i = 0;
        Collectible[]  tab = new Collectible[items.size()];
        for(Collectible c: items){
           tab[i] = c;
           i++;
        }
        return tab;
    }

    public static void transfer(Bag from, Bag into){
        //Création d'une item de copie 
        Collectible[] copie = from.getItems();

           for(Collectible c: copie){
                into.push(c);
           }
    }

    @Override
    public String toString(){
        return String.format("Bag [ %d items %d/%d kg ] \n ",
         items.size(),
         weight,
         capacity
        ); }

public static void main(String[] args) {
    Bag bag = new SmallBag(10);
    Collectible gun = new ShotGun();
    Collectible leggings = new DragonSlayerLeggings();
    Collectible ringed = new RingedKnightArmor();

    bag.push(gun);
    bag.push(leggings);
    bag.push(ringed);
    System.out.println("Sac 1 :");
    System.out.println(bag);
    for(Collectible c: bag.items){
        System.out.println(c + " [ " + c.getWeight() + " kg]");
    }

    Bag bag2 = new Bag(5);
    System.out.println("\nSac 2 :");
    System.out.println(bag2);
    for(Collectible c: bag2.items){
        System.out.println(c + " [ " + c.getWeight() + " kg]");
    }

    System.out.println("\nSac 2 après transfers :");
    Bag.transfer(bag, bag2);
    System.out.println(bag2);
    for(Collectible c: bag.items){
        System.out.println(c + " [ " + c.getWeight() + " kg]");
    }

    System.out.println("\nSac 1 après transfert : ");
    System.out.println(bag);

    /* 
    bag.pop(leggings);
    System.out.println(" \n Apres suppresion : " + bag);
     for(Collectible c: bag.items){
        System.out.println(c + " [ " + c.getWeight() + " kg]");
    }
        */
}

}