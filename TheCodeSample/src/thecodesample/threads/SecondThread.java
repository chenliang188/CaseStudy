package thecodesample.threads;


public class SecondThread extends Thread
{
    int[] s;
    public SecondThread(int[] temp)
    {
        s = temp;
    }
    
    @Override
    public void run()
    {
        System.out.println("Second:"+ print());
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
