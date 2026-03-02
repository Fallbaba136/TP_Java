package lsg.consumables.drinks;

public class Wine extends Drink{
    // constructeur
    public Wine(String name, int capacity, String stat){super(name, capacity, stat);}

     @Override
    public String toString(){
        return String.format("%s [%d stamina point(s)]", getName(), getCapacity());
    }

    public static void main(String[] args) {
        Wine wine = new Wine("Pomerol 2008", 30, null);
        System.out.println(wine);
    }
}