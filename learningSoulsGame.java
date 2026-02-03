package lsg;
import java.util.Scanner;

import lsg.characters.Character;
import lsg.characters.Hero;
import lsg.characters.Monster;
import lsg.weapons.*; 
// class learningSoulsGame
class learningSoulsGame{

      Hero hero;
      Monster monster;

      private void init(){
            hero = new Hero();
            hero.setWeapon(new Sword());

            monster = new Monster();
            monster.setWeapon(new Claw());
}
      private void play_v1(){
            init();
            fight1v1();
      }

      private void fight1v1(){

            refresh();

            Character agressor = hero;
            Character target = monster;
            String action = null; 
            int attack, hit;
            Character tmp;
            while(hero.isAlive() && monster.isAlive()){
                  System.out.println();
                  System.out.println("Hit enter key for next move >");
                  Scanner scanner = new Scanner(System.in);
                  action = scanner.nextLine();

                  attack = agressor.attack();
                  hit = target.getHitWith(attack);
                  System.out.printf("%s attacks %s with %s (ATTACK:%d | DMG : %d)", agressor.getName(), target.getName(), agressor.getWeapon().getName(), attack, hit);

                  System.out.println();
                  refresh();

                  tmp = agressor;
                  agressor = target;
                  target = tmp;
            }

            Character winner = (hero.isAlive()) ? hero : monster;
            System.out.println();
            System.out.println("---" + winner.getName() + "WIN !!! ---");
      }

      private void refresh(){
            hero.printStats();
            monster.printStats();
      }
      public static void main(String[] args) {
            new learningSoulsGame().play_v1();
      }
}

