public class FlatManPlacementRE extends GameEvent{
    private MetalVent metalVent;
    private FlatMan flatMan;

    //CONSTRUCTOR
    public FlatManPlacementRE(int priority, Player player1, MetalVent metalVent, FlatMan flatMan){
        super(priority, player1);
        this.metalVent = metalVent;
        this.flatMan = flatMan;
    }

    //METHODS

    public boolean getTriggerStatus(){
        boolean triggered = false;
        //This event triggers when the player is in the Empty Room and more than one turn has passed.
        if(this.getPlayer().getLocation() instanceof EmptyRoom && this.getPlayer().getRoomTurnCounter() > 1){
            triggered = true;
        }
        return triggered;
    }

    public String getTriggerMsg(){
        String response = "You watch as a paper thin man floats through the gaps in the " + this.metalVent.quote() + " and gently floats down into the center of the room.\n";
        response += "Where eyes would normally be located, there is only skin.\nThe " + this.flatMan.quote() + " is now in the room with you.";
        //Spawn the FlatMan in the Empty Room
        this.flatMan.changeLocation(this.getPlayer().getLocation());
        getPlayer().getEnvironment().addNpc(flatMan);
        this.setCompletedStatus(true);
        return response;
    }
}
