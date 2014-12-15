package thecodesample.threads2;
 
public class SyncObject {
 
    private int currTurnIndex = 0;
 
    public int getCurrTurnIndex() {
        return currTurnIndex;
    }
 
    public void setCurrTurnIndex(int currTurnIndex) {
        this.currTurnIndex = currTurnIndex;
    }
 
    public void nextTurn() {
        setCurrTurnIndex(getCurrTurnIndex() + 1);
    }
     
    public boolean isNotCompleted() {
        return getCurrTurnIndex() < 15;
    }
}