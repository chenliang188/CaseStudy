public class TTw implements Runnable
{
    int b = 100;
    
    public void m1() throws Exception
    {
        synchronized (this)
        {
            //Thread.sleep(2000);
            b = 1000;
            Thread.sleep(5000);
            System.out.println("b = " + b);
        }
    }
    
    public synchronized void m2() throws Exception
    {
        //Thread.sleep(2500);
        //b = 2000;
        System.out.println(b);
    }
    
    public void run()
    {
        try
        {
            m1();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception
    {
        TTw tt = new TTw();
        Thread t = new Thread(tt);
        t.start();
        
        tt.m2();
        //System.out.println(tt.b);
    }
}