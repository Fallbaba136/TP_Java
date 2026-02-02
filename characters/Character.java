package lsg.characters;
import java.util.Locale;

import lsg.helper.Dice;
import lsg.weapons.Weapon;

import java.math.*;



abstract class Character {
    // Message possible pour l'affichage
    private static String MSG_ALIVE = "(ALIVE)";
    private static String MSG_DEAD = "(DEAD)";

    //champs protégés : accessibles dans les classes filles (Hero/Monster)
    protected String name;          // nom du personnage 
    protected int life ;           // Points vie actuels
    protected int maxLife ;       // points de vie maximal 
    protected int stamina ;      // Endurance actuelle pour attaquer
    protected int maxStamina ;  // endurance maximal

    // champs privée : accessible uniquement via getters/setters
    private Weapon weapon;      // arme équipée
    private Dice dice101 = new Dice(101);


// -----------------------------------------
//      GETTERS / SETTERS
// -----------------------------------------
    public String getName() { return name; }
    public void setName(String name){  this.name = name;}

    public int getLife() {return life;} 
    public void setLife(int life){this.life = life; }

    public int getMaxLife(){  return maxLife;}
    public void setMaxLife(int maxLife){ this.maxLife = maxLife; }

    public int getStamina(){ return stamina;}
     public void setStamina(int stamina){ this.stamina = stamina;}

    public int getMaxStamina(){  return maxStamina;}
    public void setMaxStamina(int maxStamina){  this.maxStamina = maxStamina;}

    public Weapon getWeapon(){return weapon;}
    public void setWeapon(Weapon weapon){this.weapon = weapon;}

    // -----------------------------------------
    //      CONSTRUCTEURS
    // -----------------------------------------
    public Character(String name)  { this.name = name;}
    public Character() {name = "Gregooninator";}


    // -----------------------------------------
    //      AFFICHAGE / ETAT
    // -----------------------------------------
@Override
    public String toString() {
    return String.format(
        Locale.US,
        "[%-10s] %-20s LIFE: %-5d STAMINA: %-5d PROTECTION: %.2f (%s)",
        getClass().getSimpleName(),
        name,
        life,
        stamina,
        computeProtection(),
        Alive_Dead()
    );
}
    // Si le personnage est encore en vie 
    public Boolean isAlive()
    {
        return life > 0;
    }

    // Renvoie mort ou vivant 
    public String Alive_Dead(){
        if (isAlive()) {
            return "ALIVE";
        }
        return "DEAD";
    }
    
    // Affiche l'objet en utilisant toString()
    public void printStats(){
    System.out.println(this);
    }

    // -----------------------------------------
    //      ATTAQUE
    // -----------------------------------------

    // Attaque avec l'arme équipée
    public int attack(){
        return attackWith(this.getWeapon());
    }

    
public int attackWith(Weapon weapon)
    {
        int min = weapon.getMinDamage();
        int max = weapon.getMaxDamage();
        int cost = weapon.getStamCost();
    
        int attack = 0;

        if(!weapon.isBroken()){
        attack = min + Math.round((max-min) * dice101.roll() / 100.f);
        int stam = getStamina();
        if (cost <=  stam) { // il y a assez stam pour lancer l'attaque
        setStamina(getStamina()-cost);}
        
        else
        {
           attack = Math.round(attack * ((float)stam / cost));
           setStamina(0);
        }
        weapon.use();
    }
    return attack;
    }

    /*On peut pas déclarée une méthode abstraite dans une classe qui n'est pas elle même abstraite*/

    /*L'erreur est :*/
    /*Les classes concrètes n'implémentent pas toutes les méthodes abstraites héritée */
    abstract float computeProtection();
    public int getHitWith(int value){
       int life = getLife();
       int dmg;
       dmg = (life > value) ? value : life;
       setLife(life - dmg);
       return dmg;
       }
}
