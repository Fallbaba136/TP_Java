package lsg.armor;

public class DragonSlayerLeggings extends ArmorItem{
    public DragonSlayerLeggings(){
         super("Dragon Slayer Leggings", 10.2f);
    }
   
    
}

class Main {
    public static void main(String[] args) {
        System.out.println(new DragonSlayerLeggings());
    }
}