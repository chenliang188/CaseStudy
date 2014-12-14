package threads2;
 
public class Main {
 
    public static void main(String[] args) {
        SyncObject syncObj = new SyncObject();
        for (int i = 1; i < 4; i++) {
            new Thread(new PrintNumberRunnable(syncObj, i - 1, "线程" + i))
                    .start();
        }
    }
}