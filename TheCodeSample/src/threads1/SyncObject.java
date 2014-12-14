package threads1;
 
public class SyncObject {
 
    private int currTurnIndex = 0;
 
    public boolean isFirstThreadTurn() {
        return currTurnIndex % 3 == 0;
    }
 
    public boolean isSecondThreadTurn() {
        return currTurnIndex % 3 == 1;
    }
 
    public boolean isThirdThreadTurn() {
        return currTurnIndex % 3 == 2;
    }
 
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