package lsg.characters;
import lsg.weapons.*;
import lsg.helper.Dice;
import lsg.weapons.Weapon;
import java.math.*;

public class Monster extends Character{
    // Attributs
    public static int INSTANCES_COUNT = 0;
    private float skinThickness = 20;

    // accesseurs
    public float getSkinThickness(){return skinThickness;}
    public void setSkinThickness(float skinThickness) {this.skinThickness = skinThickness;}


  // 1.3 constructeur 
    public Monster(String name)  
    { 
    super(name);
    INSTANCES_COUNT++;
}

    public Monster()
    {
        INSTANCES_COUNT++;
        name = "Monster_" + INSTANCES_COUNT; // nom du hero 
        life = 10; // nombre de point de vie 
        maxLife = 10; // nombre maximal de point de vie 
        stamina = 10; // la force restante
        maxStamina = 10; // force maximal
    }


    @Override
     float computeProtection(){
        return skinThickness;
    }

  }
   
}
