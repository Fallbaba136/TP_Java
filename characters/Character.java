package lsg.characters;
import java.util.Locale;

import lsg.helper.Dice;
import lsg.weapons.ShotGun;
import lsg.weapons.Weapon;

import java.math.*;
import lsg.consumables.Consumable;
import lsg.consumables.drinks.*;
import lsg.consumables.food.*;
import lsg.consumables.repair.*;
import lsg.armor.DragonSlayerLeggings;
import lsg.armor.RingedKnightArmor;
import lsg.bags.*;



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


    public static final String LIFE_STAT_STRING = "LIFE";
    public static final String STAM_STAT_STRING = "STAMINA";
    public static final String PROT_STAT_STRING = "PROTECTION";
    public static final String BUFF_STAT_STRING = "BUFF";


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

    //=======================
    //New Attribut Consumable
    //=======================

    protected Consumable consumable;

    public Consumable getConsumable(){return consumable;}
    public void setConsumable(Consumable consumable){this.consumable = consumable;}
    
    public void consume(){
        use(this.consumable);
    }

    //=================
    //New Attribut Bag
    //=================
    protected Bag bag;

    public void pickUp(Collectible item){
            bag.push(item);
    }

    public Collectible pullOut(Collectible item){
        return bag.pop(item);
    }

    public void printBag(){
        Collectible gun = new ShotGun();
        Collectible leggings = new DragonSlayerLeggings();
        Collectible ringed = new RingedKnightArmor();
        bag.push(gun);
        bag.push(leggings);
        bag.push(ringed);
        System.out.println("Sac 1 :");
        System.out.println(bag);
        for(Collectible c: bag.getItems()){
        System.out.println(c + " [ " + c.getWeight() + " kg]");
    }
    }

    public int getBagCapacity(){
        return bag.getCapacity();
    }
    public int getBagWeight(){
        return bag.getWeight();
    }
    public Collectible[] getBagItems(){
        return bag.getItems();
    }

    public Bag setBag(Bag bagSecond){
        Bag.transfer(bag, bagSecond);
        this.bag = bagSecond;
        /**
         * getClass().getSimpleName() retourne le nom de la classe fille 
         * réelle, pas celui de la classe parente !
         */
        System.out.println(getName()+ " changes " + 
            bag.getClass().getSimpleName() + " for " +
                 bagSecond.getClass().getSimpleName());
                 return bag;
    }

    public void equip(Weapon weapon){
        if (bag.contains(weapon)) {
            setWeapon(weapon);
            bag.pop(weapon);
            System.out.println(getName() + " pulls " + weapon + " and equips it !");
        }
}

    public void equip(Consumable consumable){
        if (bag.contains(consumable)) {
            setConsumable(consumable);
            bag.pop(consumable);
            System.out.println(getName() + " pulls " + consumable + " and equips it !");
        }
    }

    

    // -----------------------------------------
    //      CONSTRUCTEURS
    // -----------------------------------------
    public Character(String name)  {
        this.name = name; 
        this.maxLife = 100;      // Initialisation dans la classe parente
        this.maxStamina = 100;
        this.life = maxLife;
        this.stamina = 50;
        this.bag = new SmallBag(10);
        }
    public Character() {
       this("Gregooninator");
    }

    // -----------------------------------------
    //      Methode
    // -----------------------------------------
    
private void drink(Drink soda) {
    int pointGagne = soda.use();
    stamina = Math.min(stamina + pointGagne, maxStamina);
}
private void eat(Food food) {
    int pointGagne = food.use();
    life = Math.min(life + pointGagne, maxLife);
}


    public void use(Consumable consumable){
        if (consumable instanceof Drink) { // appelle des fontions
             drink((Drink) consumable); // Conversion de consumable (casting)
        }else if (consumable instanceof Food) {
           eat((Food) consumable);
        }
        else if (consumable instanceof RepairKit) {
            repairWeaponWith((RepairKit) consumable);
        }
    }

   private void repairWeaponWith(RepairKit kit)
    { 
        if(this.weapon != null)
            {   
                weapon.setMinDamage(0);
                weapon.setMaxDamage(0);
                weapon.setStamCost(1000);
                weapon.setDurability(99);
                if(kit.getCapacity() > 0){
                 this.weapon.repairWith(kit);
                System.out.println(this.name + " Repairs " + weapon.getName() + " ( " + "min:" + weapon.getMinDamage() + " max:" + weapon.getMaxDamage() + " stam:"+ weapon.getStamCost() + " dur:" + weapon.getDurability() +" ) " + " with " + kit);
                }
            }

    }

    //-------------------------
    // Fast recharging
    //-------------------------

    private <T extends Consumable> T fastUseFirst(Class<T> type){ // retourne un methode de type T
        for(Collectible item: bag.getItems()){
            if (type.isInstance(item)) {
                T consumable = type.cast(item); // cast: On converti item(Collectible) en T(Drink, Food, etc ..)
                consumable.use();
                return consumable;
            }
        }
        return null;
    }

    public Drink fastDrink(){
        Drink bu = fastUseFirst(Drink.class);
         if (bu != null) {
            System.out.println(this.name + "drinks Fast :\n");
            System.out.println(this.name + " drink " + bu.toString());
            System.out.println(this.name + " pulls out " + bu.toString());
        }else{
            System.out.println("Pas de boisson dans le sac");
        }
        return bu;
    }

    public Food fastEat(){
        Food eat = fastUseFirst(Food.class);
        if (eat != null) {
            System.out.println(this.name + "eats Fast :\n");
            System.out.println(this.name + " eats " + eat.toString());
            System.out.println(this.name + " pulls out " + eat.toString());
        }else{
            System.out.println("Pas de nourriture dans le sac");
        }
        return eat;
    }

    public RepairKit fasRepairKit(){
        RepairKit kit = fastUseFirst(RepairKit.class);
         if (kit != null) {
            System.out.println(this.name + "repair Fast :\n");
            System.out.println(this.name + " repair " + kit.toString());
        }else{
            System.out.println("Pas de reparation de l'arme");
        }
        return kit;
    }


@Override
    public String toString() {
    return String.format(
        Locale.US,
        "[%-10s] %-20s %s: %-5d %s: %-5d %s: %.2f (%s), %s: %f",
        getClass().getSimpleName(),
        name,
       LIFE_STAT_STRING, life,
        STAM_STAT_STRING, stamina,
        PROT_STAT_STRING, computeProtection(),
        Alive_Dead(),
        BUFF_STAT_STRING, computeBuff()
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

       public static void main(String[] args) {
        Character dragon = new Hero("Gregooninator");
        Whisky whisky = new Whisky("12 years old Oban", 150, null);
        Hamburger hamburger = new Hamburger("Uncle Greg's spicy Maroilles burger", 40, null);
        System.out.println(dragon.getName() + "\t drinks" + "\t" +  whisky);
        System.out.println();
         System.out.println(dragon.getName() + "\t eats" + "\t" +  hamburger);
         System.out.println();
         Weapon weapon = new Weapon("Grosse arme", 0, 0, 1000, 99);
         dragon.setWeapon(weapon);
         RepairKit kit = new RepairKit();
         dragon.repairWeaponWith(kit);
       }
}
