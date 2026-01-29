package lsg;

import lsg.characters.*;
import lsg.weapons.*;
// class learningSoulsGame
class learningSoulsGame{
     public static void main(String args[])
    {
        

        for(int i = 0; i < 6; i++)
        {
          Hero hero = new Hero();
          Monster monster = new Monster();
          Sword  sword = new Sword();
          hero.setName("Rick");
          hero.setLife(100);
          hero.setStamina(50);

          monster.setName("Zombie");
          monster.setLife(10);
          monster.setStamina(10);

          System.out.println(hero);
          System.out.println(monster);
          System.out.println();

          System.out.println(String.format("!!! %s attack %s with ShotGun (%d) !!! ->  Effective DMG:",
           hero.getName(),
           monster.getName(),
           
        ));
        System.out.println();
        }

       
}
}