package threads1;
 
public class Main {
 
    public static void main(String[] args) {
        SyncObject syncObj = new SyncObject();
        Thread t1 = new Thread(new PrintNumberRunnable1(syncObj, "线程1"));
        Thread t2 = new Thread(new PrintNumberRunnable2(syncObj, "线程2"));
        Thread t3 = new Thread(new PrintNumberRunnable3(syncObj, "线程3"));
        t1.start();
        t2.start();
        t3.start();
    }
 
}