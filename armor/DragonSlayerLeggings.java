package lsg.armor;

import lsg.bags.Collectible;

public class DragonSlayerLeggings extends ArmorItem implements Collectible{
    public DragonSlayerLeggings(){
         super("Dragon Slayer Leggings", 10.2f);
    }
   
    

@Override

public int getWeight(){
    return 3;
}
}
class Main {
    public static void main(String[] args) {
        System.out.println(new DragonSlayerLeggings());
    }
}