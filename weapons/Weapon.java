package lsg.weapons;
import lsg.helper.*;
import java.math.*;
//import lsg.characters.*;

public class Weapon 
{
    //attributs
    protected String name;
    protected int minDamage;
    protected int maxDamage;
    protected int stamCost;
    protected int durability;

    //constructeurs
    public Weapon(String name, int minDamage, int maxDamage, int stamCost, int durability)
    {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.stamCost = stamCost;
        this.durability = durability;
    }

//getters
    public String getName() {return name;}
    public int getMinDamage() {return minDamage;}
    public int getMaxDamage() {return maxDamage;}
    public int getStamCost() {return stamCost;}
    public int getDurability() {return durability;}
    private void setDurability(int durability) {this.durability = durability;}

    public void setMinDamage(int minDamage){this.minDamage = minDamage;}
    public void setMaxDamage(int maxDamage){this.maxDamage = maxDamage;}

    // les methodes 
    public void use() {durability -= 1;}

    public Boolean isBroken()
    {
        if (durability <= 0) return true;
        else return false;
    }
    public String toString()
    {
         return String.format(
        "[%-10s] %-20s min: %-5d max: : %-5d stam: %-5d dur: %-5d",
        getClass().getSimpleName(),
        name,
        minDamage,
        maxDamage,
        stamCost,
        durability
    );
    }

    //main
    public static void main(String[] args)
    {
        Weapon w = new Weapon("Basic Sword", 5, 10, 20, 100);
        w.minDamage = 5;
        w.maxDamage = 10;
        w.stamCost = 20;
        w.durability = 100;
       System.out.println(w);
    }

}
