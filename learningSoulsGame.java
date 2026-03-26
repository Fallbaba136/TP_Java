package lsg;

import java.util.Random;
import java.util.Scanner;

import lsg.armor.*;
import lsg.buffs.talismans.Talisman;
import lsg.characters.Character;
import lsg.characters.Hero;
import lsg.characters.Lycanthrope;
import lsg.characters.Monster;
//import java.util.Random;
import lsg.buffs.*;
import lsg.buffs.rings.*;
import lsg.weapons.*;
import lsg.consumables.*;
import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Drink;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.Americain;
import lsg.consumables.food.Food;
import lsg.consumables.food.Hamburger;
import lsg.consumables.repair.RepairKit;
import lsg.bags.*;

public class LearningSoulsGame{
	
	Scanner scanner = new Scanner(System.in) ;
	public static final String GOSSE_ARME =  "Grosse Arme";
	Hero hero ;
	Monster monster;
	Monster monster2;

	

	private void init(){
		hero = new Hero() ;
        Consumable hamburger = new Hamburger("Uncle Greg's spicy Maroilles burger", 40, null);
        hero.setConsumable(hamburger);
		hero.setLife(50);
		hero.setStamina(50);
		hero.setWeapon(new Sword());
		
		
		ArmorItem black = new BlackWitchVeil();
		ArmorItem dragon = new DragonSlayerLeggings();
		ArmorItem ring = new RingedKnightArmor();

        Ring RingOfSwords = new RingOfSwords();
        Ring RingOfDeath = new RingOfDeath();

        hero.setRing(RingOfDeath, 2);
       
        hero.setRing(RingOfSwords, 1);
        

		hero.setArmoItem(dragon, 0);
		hero.setArmoItem(black, 1);
		hero.setArmoItem(ring, 2);
		

		monster = new Lycanthrope();
        Talisman MoonStone = new lsg.buffs.talismans.MoonStone();
        monster.setTalisman(MoonStone);
        
	}

    public void title(){
        System.out.println("\t ======================= \t");
      System.out.println("# \t THE LEARNING SOULS GAME \t \n");
      System.out.println("\t ======================= \t");
    }

	private void play_v1(){
		init() ;
		fight1v1() ;
		
	}
	private void play_v2(){
		init() ;
		fight1v2() ;
		
	}

    private void createEchaustedHero(){
        Hero hero = new Hero("Gregooninator");
        hero.setLife(1);
        hero.setStamina(0);
        Weapon weapon = new Weapon(GOSSE_ARME, 0, 0, 1000, 99);
        System.out.println("Create exhausted hero : ");
        System.out.println(hero);
        //System.out.println(weapon);
    }
    
private void aTable() {
    MenuBestOfV4 menu = new MenuBestOfV4();
    Hero hero = new Hero("Gregoonianator"); 
     hero.setLife(100);      
    hero.setStamina(50); 
    
    System.out.println(hero + "\n");
    
    for (Consumable c : menu) {
        String action = (c instanceof Food) ? "eats" : "drinks";
        System.out.println(hero.getName() + " " + action + " " + c);
        hero.use(c);  
        System.out.println(hero);
        System.out.println("Après utilisation : " + c + "\n");
    }
}


private void fight1v1() {
    refresh();
    
    Character agressor = hero;
    Character target = monster;
    int attack, hit;
    Character tmp;
    
    while(hero.isAlive() && monster.isAlive()) {
        System.out.println();
        
        // DÉBUT des modifications
        if (agressor == hero) {  // Tour du héros
            int choix;
            do {
                System.out.println("Choix : 1 - Attaquer | 2 - Consommer");
                System.out.print("> ");
                choix = scanner.nextInt();
            } while (choix != 1 && choix != 2);
            
            if (choix == 1) {
                attack = agressor.attack();
            } else {
                agressor.consume();
                attack = 0;  // Pas d'attaque ce tour
            }
        } else {  // Tour du monstre (automatique)
            attack = agressor.attack();
        }
        // FIN des modifications
        
        hit = target.getHitWith(attack);
        System.out.printf("%s attacks %s with %s (ATTACK:%d | DMG : %d)",
            agressor.getName(), target.getName(),
            agressor.getWeapon().getName(), attack, hit);
        
        System.out.println();
        refresh();
        
        tmp = agressor;
        agressor = target;
        target = tmp;
    }
    
    Character winner = (hero.isAlive()) ? hero : monster;
    System.out.println();
    System.out.println("--- " + winner.getName() + " WINS !!! ---");
}

	private void fight1v2(){
    refresh();
    Character agressor = hero;
    Character target1 = monster;
    Character target2 = monster2;

    String action = null;
    int attack;
    int hit;
    Character tmp;

    Random rand = new Random();
    Character targetChoice = rand.nextBoolean() ? target1 : target2;

    // ===== CORRIGÉ : gérer aussi le cas targetChoice == target2 (sinon 50% du temps rien) =====
    if (targetChoice == target1) {

        while (hero.isAlive() && monster.isAlive()) {
            System.out.println();
            System.out.println("Hit enter key for next move > "); // ===== CORRIGÉ : prompt manquant =====
            action = scanner.nextLine();

            attack = agressor.attack();
            hit = target1.getHitWith(attack);

            System.out.printf("%s attacks %s with %s (ATTACK:%d | DMG : %d)",
                    agressor.getName(), target1.getName(), agressor.getWeapon().getName(), attack, hit);

            System.out.println();
            refresh();

            tmp = agressor;
            agressor = target1;
            target1 = tmp;
        }

        // ===== CORRIGÉ : winner doit correspondre au duel fait (hero vs monster) =====
        Character winner = (hero.isAlive()) ? hero : monster;

        if (winner == hero) {

            // ===== CORRIGÉ : remettre le bon agressor avant le 2e duel (sinon peut être un monstre) =====
            agressor = hero; // CORRIGÉ

            while (hero.isAlive() && monster2.isAlive()) {
                System.out.println();
                System.out.println("Hit enter key for next move > "); // ===== CORRIGÉ : prompt manquant =====
                action = scanner.nextLine();

                attack = agressor.attack();
                hit = target2.getHitWith(attack);

                System.out.printf("%s attacks %s with %s (ATTACK:%d | DMG : %d)",
                        agressor.getName(), target2.getName(), agressor.getWeapon().getName(), attack, hit);

                System.out.println();
                refresh();

                tmp = agressor;
                agressor = target2;
                target2 = tmp;
            }

            // ===== CORRIGÉ : afficher le vainqueur du 2e duel =====
            Character winner2 = (hero.isAlive()) ? hero : monster2; // CORRIGÉ
            System.out.println();
            System.out.println("--- " + winner2.getName() + " WINS !!! ---"); // CORRIGÉ

        } else {
            // ===== CORRIGÉ : si le héro perd contre monster, afficher le gagnant =====
            System.out.println();
            System.out.println("--- " + winner.getName() + " WINS !!! ---"); // CORRIGÉ
        }

    } else { 
        // ===== CORRIGÉ : branche manquante (si targetChoice == target2) =====

        while (hero.isAlive() && monster2.isAlive()) { // CORRIGÉ : duel direct vs monster2
            System.out.println();
            System.out.println("Hit enter key for next move > "); // CORRIGÉ
            action = scanner.nextLine();

            attack = agressor.attack();
            hit = target2.getHitWith(attack);

            System.out.printf("%s attacks %s with %s (ATTACK:%d | DMG : %d)",
                    agressor.getName(), target2.getName(), agressor.getWeapon().getName(), attack, hit);

            System.out.println();
            refresh();

            tmp = agressor;
            agressor = target2;
            target2 = tmp;
        }

        // CORRIGÉ : winner du duel hero vs monster2
        Character winner = (hero.isAlive()) ? hero : monster2;

        if (winner == hero) {

            agressor = hero; // CORRIGÉ : reset agressor pour le duel suivant

            // ensuite duel vs monster (target1)
            while (hero.isAlive() && monster.isAlive()) {
                System.out.println();
                System.out.println("Hit enter key for next move > "); // CORRIGÉ
                action = scanner.nextLine();

                attack = agressor.attack();
                hit = target1.getHitWith(attack);

                System.out.printf("%s attacks %s with %s (ATTACK:%d | DMG : %d)",
                        agressor.getName(), target1.getName(), agressor.getWeapon().getName(), attack, hit);

                System.out.println();
                refresh();

                tmp = agressor;
                agressor = target1;
                target1 = tmp;
            }

            Character winner2 = (hero.isAlive()) ? hero : monster; // CORRIGÉ
            System.out.println();
            System.out.println("--- " + winner2.getName() + " WINS !!! ---"); // CORRIGÉ

        } else {
            System.out.println();
            System.out.println("--- " + winner.getName() + " WINS !!! ---"); // CORRIGÉ
        }
    }
}

private void testBag(){
    Hero hero = new Hero("Gregooninator");
    hero.setLife(1);
    hero.setStamina(0);

    System.out.println(hero + "\n");

    ArmorItem blackWith = new BlackWitchVeil();
    ArmorItem dragonSlayer = new DragonSlayerLeggings();
    ArmorItem ringedKnight = new RingedKnightArmor();
    Weapon gun = new ShotGun();

    dragonSlayer.setArmorValue(10.2f);
    ringedKnight.setArmorValue(14.99f);
    gun.setMinDamage(6);
    gun.setDurability(100);
    gun.setStamCost(5);
    gun.setMaxDamage(20);

    System.out.println(hero.getName() + " picks up " + dragonSlayer);
    System.out.println(hero.getName() + " picks up " + ringedKnight);
    System.out.println(hero.getName() + " picks up " + gun + "\n");

    Bag smallbag = new SmallBag(10);
    smallbag.push(gun);
    smallbag.push(dragonSlayer);
    smallbag.push(ringedKnight); 

    System.out.println("Bag : " + smallbag);

   for(Collectible item: smallbag.getItems()){
     System.out.println("\u2219 " + item + " [ " + item.getWeight() + " kg]");
   }
   System.out.println();
   
   System.out.println(hero.getName() + "changes SmallBag for MediumBag \n");

   Bag mediumbag = new MediumBag(40);
   smallbag.transfer(smallbag, mediumbag);
   System.out.println("Bag : " + mediumbag);
    for(Collectible item: mediumbag.getItems()){
     System.out.println("\u2219 " + item + " [ " + item.getWeight() + " kg]");
   }
   System.out.println();

    Consumable coffee = new Coffee("Hot Grandmother Coffee", 10, null);
    Consumable hamburger = new Hamburger("Uncle Greg's spicy Maroilles burger", 40, null);
    Consumable wh = new Whisky("12 years old Oban", 150, null);

    RepairKit kit = new RepairKit();
    RepairKit kit2 = new RepairKit();
    kit.setCapacity(10);
    kit2.setCapacity(10);

    System.out.println(hero.getName() + " picks up " + coffee.toString());
    System.out.println(hero.getName() + " picks up " + hamburger.toString());
    System.out.println(hero.getName() + " picks up " + wh.toString());
    System.out.println(hero.getName() + " picks up Repair Kit " + kit.toString());
    System.out.println(hero.getName() + " picks up Repair Kit " + kit2.toString());
    System.out.println();

    mediumbag.push(coffee);
    mediumbag.push(kit);
    mediumbag.push(hamburger);
    mediumbag.push(wh);
    mediumbag.push(kit2);
    hero.setBag(mediumbag);
    System.out.println("BAG : " + mediumbag);
     for(Collectible item: mediumbag.getItems()){
     System.out.println("\u2219 " + item + " [ " + item.getWeight() + " kg]");
   }
   System.out.println();

   System.out.println("---AVANT---");
   System.out.println(hero);
   hero.printArmor();

   Weapon weapon = new Weapon(GOSSE_ARME, 0, 0, 1000, 99);
   System.out.println(weapon);
   System.out.println();

  for(Collectible item: mediumbag.getItems()){
     System.out.println("\u2219 " + item + " [ " + item.getWeight() + " kg]");
   }
   System.out.println();

   System.out.println("---ACTION--");
   hero.fastDrink();
   System.out.println();
   hero.fastEat();
   System.out.println();
   hero.fasRepairKit();
   System.out.println();

   System.out.println("--Apres--");
   System.out.println(hero);
   hero.printArmor();
   System.out.println(weapon);

   System.out.println("BAG : " + mediumbag);
     for(Collectible item: mediumbag.getItems()){
     System.out.println( "\u2219 " + item + " [ " + item.getWeight() + " kg]");
   }


}

	
	private void refresh(){
		hero.printStats();
		monster.printStats();
       System.out.println(hero.getWeapon());
       System.out.println(hero.getConsumable());
	}

	public static void main(String[] args) {
    LearningSoulsGame game = new LearningSoulsGame();
    game.title();
    game.init();    
    game.testBag();
}
}
