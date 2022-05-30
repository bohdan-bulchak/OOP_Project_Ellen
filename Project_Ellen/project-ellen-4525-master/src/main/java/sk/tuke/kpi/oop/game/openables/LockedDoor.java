package sk.tuke.kpi.oop.game.openables;


public class LockedDoor extends Door{
    private boolean isLocked;

    public LockedDoor() {
        super();
        isLocked = true;
    }

    public void unlock() {
        this.open();
        isLocked = false;
    }

    public void lock() {
        this.close();
        isLocked = true;
    }


    public boolean isLocked() {
        return isLocked;
    }

}
