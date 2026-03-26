package lsg.consumables;

import lsg.bags.Collectible;

public class Consumable implements Collectible{

    // Attributs
    protected String name;
    protected int capacity;
    protected String stat;

    // constructeur

    public Consumable(String name, int capacity, String stat){
        this.name = name;
        this.capacity = capacity;
        this.stat = stat;
    }

    // accessibilité

    public String getName() { return name;}
    public int getCapacity() {return capacity;}
    public String getStat() {return stat;}

    public void setCapacity(int capacity){ this.capacity = capacity;}

    //Methode
   public int use(){
    int total = capacity;
    capacity = 0;
    return total;
}

    @Override
    public String toString(){
        return String.format("%s[%d stamina point(s)]",
        getName(),
        getCapacity()
        );
    }

    public int getWeight(){
        return 1;
    }

    public static void main(String[] args) {
        Consumable Cons = new Consumable("Hot Coffee", 10, null);
        System.out.println(Cons);
    }
}