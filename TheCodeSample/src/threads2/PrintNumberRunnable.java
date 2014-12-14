package threads2;
 
public class PrintNumberRunnable implements Runnable {
 
    private SyncObject syncObj = null;
    private int num = 0;
    private String name;
 
    public PrintNumberRunnable(SyncObject syncObj, int num, String name) {
        this.syncObj = syncObj;
        this.num = num;
        this.name = name;
    }
 
    @Override
    public void run() {
        while (syncObj.isNotCompleted()) {
            synchronized (syncObj) {
                if (syncObj.getCurrTurnIndex() % 3 == num) {
                    printNumbers(syncObj.getCurrTurnIndex());
                    syncObj.nextTurn();
                    syncObj.notifyAll();
                } else {
                    try {
                        syncObj.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }
 
    private void printNumbers(int startNumber) {
        for (int i = startNumber * 5 + 1; i < startNumber * 5 + 6; i++) {
            System.out.println(name + " : " + i);
        }
        System.out.println();
    }
}