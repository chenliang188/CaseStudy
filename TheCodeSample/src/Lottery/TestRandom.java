package Lottery;
import java.util.Random;

class TestRandom
{
    public static void main(String[] args)
    {
        Random random = new Random();
        int red = 0;
        int bulle = 0;
        StringBuffer sb = new StringBuffer();
        while (true)
        {
            for (int i = 0; i < 6; i++)
            {
                red = Math.abs(random.nextInt()) % 33;
                sb.append(red+" ");
                //System.out.println();
            } 
            bulle = Math.abs(random.nextInt()) % 16;
            sb.append(bulle);
            System.out.println(sb.toString());
            sb = new StringBuffer();
        }

    }
}