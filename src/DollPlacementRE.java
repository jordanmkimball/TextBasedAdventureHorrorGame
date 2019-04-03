public class DollPlacementRE extends GameEvent{
    private CreepyDoll doll;

    //CONSTRUCTOR
    public DollPlacementRE(int priority, Player player1, CreepyDoll doll){
        super(priority, player1);
        this.doll = doll;
    }

    //BEHAVIORS
    //Returns the CreepyDoll
    public CreepyDoll getDoll(){
        return this.doll;
    }

    //Triggers when player looks west.
    public boolean getTriggerStatus(){
        Player player1 = this.getPlayer();
        boolean triggered = false;
        if(player1.getLastAction().equals("look west")){
            triggered = true;
        }
        return triggered;
    }

    public String getTriggerMsg(){
        Room room = this.getPlayer().getLocation();
        room.addRoomItem(doll);
        getPlayer().getEnvironment().addNpc(doll);
        this.setCompletedStatus(true);
        return "You hear a giggle come from behind you.";
    }

    public String getLeavingMessage(){
        Room room = this.getPlayer().getLocation().getEastExit();
        room.addRoomItem(doll);
        getPlayer().getEnvironment().addNpc(doll);
        this.setCompletedStatus(true);
        return "You hear the faint sound of a giggle coming from the " + this.getPlayer().getLocation().getEastExit().quote() + ".";
    }


}
