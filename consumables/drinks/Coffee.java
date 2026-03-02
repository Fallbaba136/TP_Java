package lsg.consumables.drinks;

public class Coffee extends Drink{
    // constructeur
    public Coffee(String name, int capacity, String stat){super(name, capacity, stat);}

     @Override
    public String toString(){
        return String.format("%s [%d stamina point(s)]", getName(), getCapacity());
    }

    public static void main(String[] args) {
        Coffee coffee = new Coffee("Hot Grandmother Coffee", 10, null);
        System.out.println(coffee);
    }
}