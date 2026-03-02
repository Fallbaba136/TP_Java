package lsg.consumables.drinks;

public class Whisky extends Drink{
    // constructeur
    public Whisky(String name, int capacity, String stat){super(name, capacity, stat);}

    @Override
    public String toString(){
        return String.format("%s [%d stamina point(s)]", getName(), getCapacity());
    }

    public static void main(String[] args) {
        Whisky wh = new Whisky("12 years old Oban", 150, null);
        System.out.println(wh);
    }
}