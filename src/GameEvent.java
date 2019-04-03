abstract class GameEvent {
    private Player player1;
    private int priority;
    private boolean completed;
    public static int mostPriority = 0;
    public static int leastPriority = 5;

    public GameEvent(int priority, Player player1){
        if(priority >= mostPriority && priority <= leastPriority){
            this.priority = priority;
        }
        else if(priority < mostPriority){
            this.priority = mostPriority;
        }
        else{
            this.priority = leastPriority;
        }
        this.player1 = player1;
        completed = false;
    }

    //Methods

    //Returns the events priority. Lower number means more priority.
    public int getPriority(){
        return this.priority;
    }

    //Sets the priority of an event.
    public void setPriority(int priority){
        if(priority >= mostPriority && priority <= leastPriority){
            this.priority = priority;
        }
        else if(priority < mostPriority){
            this.priority = mostPriority;
        }
        else{
            this.priority = leastPriority;
        }
    }

    //Returns the player.
    public Player getPlayer(){
        return this.player1;
    }

    //Returns the completed Status
    public boolean getCompletedStatus(){
        return this.completed;
    }

    //Sets the completed Status
    public void setCompletedStatus(boolean completed){
        this.completed = true;
    }

    //Abstract Methods

    //Provides the logic which determines if an event is triggered or not.
    abstract boolean getTriggerStatus();

    //Provides the response String which describes what happens in the event.
    abstract String getTriggerMsg();



}
