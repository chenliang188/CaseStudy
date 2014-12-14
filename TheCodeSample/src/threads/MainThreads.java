package threads;

public class MainThreads
{  
    public static void main(String[] args)
    {
        int[] a1 = {1,2,3};
        int[] a2 = {4,5,6};
        FirstThread t1 = new FirstThread(a1);
        SecondThread t2 = new SecondThread(a2);
        t1.run();
        t2.run();
    }
}
