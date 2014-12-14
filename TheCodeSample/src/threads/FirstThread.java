package threads;


public class FirstThread extends Thread
{
    int[] s;
    public FirstThread(int[] temp)
    {
        s = temp;
    }

    @Override
    public void run()
    {
        System.out.println("First:"+ print());
        this.interrupt();
    }
    
    private String print()
    {
        String result = "";
        for (int i : s)
        {
            result = result + String.valueOf(i);
        }
        return result;
    }
}
