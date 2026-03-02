package lsg.consumables.repair;
import lsg.consumables.*;
import lsg.weapons.*;

public class RepairKit extends Consumable {

    public RepairKit(){
        super("Repair Kit", 10, Weapon.DURABILITY_STAT_STRING);
    }

    @Override
    //Surcharge du méthode use
    public int use(){
      if (capacity > 0) {
        capacity -= 1;
        return 1;
      }
      return 0;
    }

    public String toString(){
        return String.format("%s [%d durability point(s)",
         getName(), 
         getCapacity()
        );
    }
}