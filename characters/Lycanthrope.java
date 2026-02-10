package lsg.characters;
import lsg.weapons.*;
public class Lycanthrope extends Monster {
    public Lycanthrope(){
        super("Lycanthrope");
        this.setLife(10);
        this.setStamina(10);
        this.setWeapon(new Claw());
        this.skinThickness = 30;
    }

}
