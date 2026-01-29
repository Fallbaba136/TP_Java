package lsg.armor;

public class DragonSlayerLeggings extends ArmorItem{
    public DragonSlayerLeggings(){
         super("Dragon Slayer Leggings", 10.2f);
    }
   
    
}

class Main {
    public static void main(String[] args) {
        ArmorItem A1 = new DragonSlayerLeggings();
        System.out.println(A1);
    }
}