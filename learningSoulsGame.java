package lsg;

import java.util.Random;
import java.util.Scanner;

import lsg.armor.*;
import lsg.buffs.rings.RingOfDeath;
import lsg.buffs.rings.RingOfSwords;
import lsg.buffs.talismans.Talisman;
import lsg.characters.Character;
import lsg.characters.Hero;
import lsg.characters.Lycanthrope;
import lsg.characters.Monster;
import lsg.weapons.Claw;
import lsg.weapons.Sword;
import java.util.random.*;
import lsg.buffs.*;
import lsg.buffs.rings.*;

public class LearningSoulsGame{
	
	Scanner scanner = new Scanner(System.in) ;
	
	Hero hero ;
	Monster monster;
	Monster monster2;

	

	private void init(){
		hero = new Hero() ;
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

	private void play_v1(){
		init() ;
		fight1v1() ;
		
	}
	private void play_v2(){
		init() ;
		fight1v2() ;
		
	}


	private void fight1v1(){

		
		refresh();
		
		Character agressor = hero ;
		Character target = monster ;
		String action = null ; // TODO sera effectivement utilise dans une autre version
		int attack, hit ;
		Character tmp ;
		while(hero.isAlive() && monster.isAlive()){ // ATTENTION : boucle infinie si 0 stamina...
			
			System.out.println();
			
			System.out.println("Hit enter key for next move > ");
			action = scanner.nextLine() ;
			
			attack = agressor.attack() ;
			hit = target.getHitWith(attack);
			System.out.printf("%s attacks %s with %s (ATTACK:%d | DMG : %d)", agressor.getName(), target.getName(), agressor.getWeapon().getName(), attack, hit
        );
			
			System.out.println();
			refresh();
			
			tmp = agressor ;
			agressor = target ;
			target = tmp ;
			
		}
		
		Character winner = (hero.isAlive()) ? hero : monster ;
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

	
	private void refresh(){
		hero.printStats();
		monster.printStats();
	}

	public static void main(String[] args) {
		new LearningSoulsGame().play_v1(); ;

	}

}
