package threads1;
 
public class PrintNumberRunnable1 implements Runnable {
 
    private SyncObject syncObj = null;
 
    private String name = null;
 
    public PrintNumberRunnable1(SyncObject syncObj, String name) {
        this.syncObj = syncObj;
        this.name = name;
    }
 
    @Override
    public void run() {
        while (syncObj.isNotCompleted()) {
            synchronized(syncObj){
                if (syncObj.isFirstThreadTurn()) {
                    printNumbers(syncObj.getCurrTurnIndex());
                    syncObj.nextTurn();
                    syncObj.notifyAll();
                     
                }else
                {
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
 
    public SyncObject getSyncObj() {
        return syncObj;
    }
 
    public void setSyncObj(SyncObject syncObj) {
        this.syncObj = syncObj;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
     
    private void printNumbers(int startNumber){
        for(int i = startNumber*5+1; i< startNumber*5+6;i++){
            System.out.println(name +" : " +i);
        }
        System.out.println();
    }
 
}