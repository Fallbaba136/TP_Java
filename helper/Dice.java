package lsg.helper;
import java.util.Random;
public class Dice
{
    private int faces;
    private Random random;

    public Dice(int x)
    {
        this.faces = x;
        this.random = new Random();
    }
    public int roll()
    {
        return random.nextInt(faces);
    }
    public static void main(String[] args)
    {
        Dice d = new Dice(50);
        int min = 49;
        int max = 0;

        for(int i = 0; i < 500; i++)
        {
           int val = d.roll();
           if(val < min) min = val;
           if(val > max) max = val;
        }
        System.out.println(" Min : " + min);
        System.out.println(" Max : " + max);
    }
}
