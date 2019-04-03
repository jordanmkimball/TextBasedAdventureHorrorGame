public interface Lockable {
    //Returns the "locked" Status of a Lockable
    boolean getLockedStatus();

    //Sets the "locked" Status of a Lockable
    void setLockedStatus(boolean locked);

    //Called when a player tries to open a Lockable
    String attemptOpen();

    //Called when a player tries to close a lockable
    String attemptClose();

    //Gets the name of a lockable
    String getName();
}
