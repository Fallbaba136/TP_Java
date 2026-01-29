package lsg.characters;
import java.util.Locale;




abstract class Character {
    protected String name;          // nom du Character 
    protected int life ;           // nombre de point de vie 
    protected int maxLife ;       // nombre maximal de point de vie 
    protected int stamina ;      // la force restante
    protected int maxStamina ;  // force maximal


// 1.2 setters / getters : Accesseurs
    public String getName() { return name; }
    public void setName(String name){  this.name = name;}

    public int getLife() {return life;} 
    public void setLife(int life){this.life = life; }

    public int getMAxLife(){  return maxLife;}
    public void setMaxLife(int maxLife){ this.maxLife = maxLife; }

    public int getStamina(){ return stamina;}
     public void setStamina(int stamina){ this.stamina = stamina;}

    public int getMaxStamina(){  return maxStamina;}
    public void setMaxStamina(int maxStamina){  this.maxStamina = maxStamina;}

    // 1.3 constructeurs 
    public Character(String name)  { this.name = name;}
    public Character() {name = "Gregooninator";}

// methodes
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
    public String toAlive() {
        return String.format(
        "[%-10s] %-20s LIFE: %-5d STAMINA: %-5d",
        getClass().getSimpleName(),
        name,
        life,
        stamina
    );
    }

    public Boolean isAlive()
    {
        return life > 0;
    }
    public String Alive_Dead(){
        if (isAlive()) {
            return "ALIVE";
        }
        return "DEAD";
    }

    /*On peut pas déclarée une méthode abstraite dans une classe qui n'est pas elle même abstraite*/

    /*L'erreur est :*/
    /*Les classes concrètes n'implémentent pas toutes les méthodes abstraites héritée */
    abstract float computeProtection();
    abstract int getHitWith(int value);
}
