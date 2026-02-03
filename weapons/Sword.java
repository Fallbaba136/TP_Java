package lsg.weapons;

public class Sword extends Weapon {
    public Sword()
    {
        super("Basic Sword", 5, 10, 20, 100);
    }
   
    public static void main(String[] args) {
        System.out.println(new Sword()); 
    }
    
}
