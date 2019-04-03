public class DollGiggleNE3 extends NpcEvent {
    private DollKnifeNE2 dollKnifeNE2;

    public DollGiggleNE3(int priority, Player player1, DollKnifeNE2 dollKnifeNE2){
        super(priority, player1);
        this.dollKnifeNE2 = dollKnifeNE2;
    }

    public boolean getTriggerStatus(){
        boolean triggered = false;
        //Early exit if the dollKnife event has not yet been completed.
        if(!dollKnifeNE2.getCompletedStatus()){
            return false;
        }
        //The player must be in the dusty room.
        Room playerLocation = getPlayer().getLocation();
        if(playerLocation instanceof DustyRoom && getPlayer().getRoomTurnCounter() > 1){
            triggered = true;
        }
        return triggered;
    }

    public String getTriggerMsg(){
        this.setCompletedStatus(true);
        return "You hear \"giggling\" coming from the " + this.getNPC().getLocation().quote() + " to the East.";
    }
}
