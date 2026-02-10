package lsg.characters;
import java.util.Locale;

import lsg.helper.Dice;
import lsg.weapons.Weapon;

import java.math.*;



public abstract class Character {
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


    public int getMaxStamina(){  return maxStamina;}
    public void setMaxStamina(int maxStamina){  this.maxStamina = maxStamina;}

    public Weapon getWeapon(){return weapon;}
    public void setWeapon(Weapon weapon){this.weapon = weapon;}

    
    public int getStamina(){ return stamina;}
    public void setStamina(int stamina){ this.stamina = stamina;}

    // -----------------------------------------
    //      CONSTRUCTEURS
    // -----------------------------------------
    public Character(String name)  {
         this.name = name; 
        }
    public Character() {name = "Gregooninator";}


    // -----------------------------------------
    //      AFFICHAGE / ETAT
    // -----------------------------------------
@Override
    public String toString() {
    return String.format(
        Locale.US,
        "[%-10s] %-20s LIFE: %-5d STAMINA: %-5d PROTECTION: %.2f (%s), BUFF: %f",
        getClass().getSimpleName(),
        name,
        life,
        stamina,
        computeProtection(),
        Alive_Dead(),
        computeBuff()
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

 public int attackWith(Weapon weapon) {
    int min = weapon.getMinDamage();
    int max = weapon.getMaxDamage();
    int cost = weapon.getStamCost();

    int attack = 0;

    if (!weapon.isBroken()) {
        // 1) dégâts de base
        attack = min + Math.round((max - min) * dice101.roll() / 100.f);

        // 2) gestion stamina (ton ancien mécanisme)
        int stam = getStamina();
        if (cost <= stam) {
            setStamina(stam - cost);
        } else {
            attack = Math.round(attack * ((float) stam / cost));
            setStamina(0);
        }

        weapon.use();

        // 3) appliquer le buff APRÈS ajustement stamina
        float buffTotal = computeBuff(); // (ou le nom exact que tu as choisi)
        attack = Math.round(attack * (1f + buffTotal / 100f));
    }

    return attack;
}


    /*On peut pas déclarée une méthode abstraite dans une classe qui n'est pas elle même abstraite*/

    /*L'erreur est :*/
    /*Les classes concrètes n'implémentent pas toutes les méthodes abstraites héritée */
    //abstract float computeProtection();
    public int getHitWith(int value){
         int currentLife = getLife();

    // Sécurité : si déjà mort ou si dégâts <= 0
    if (currentLife <= 0 || value <= 0) {
        return 0;
    }
        float protection = computeProtection();
        if (protection >= 100f) {
            return 0;
        }
        // combien de pourcent passe ?
        float pourcentagePassant = 100f - protection;

        // convertion en facteur 

        float facteur = value * pourcentagePassant / 100f;

        // Appliqué aux degats
        int dommageReal = Math.round(facteur);
        
        //On ne peut pas prendre plus de vie qu'il n'en reste 
        dommageReal = Math.min(dommageReal, life);

        life -= dommageReal;

        return dommageReal;


    }

       public abstract float computeProtection();
       public abstract float computeBuff();
}
