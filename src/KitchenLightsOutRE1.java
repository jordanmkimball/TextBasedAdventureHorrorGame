public class KitchenLightsOutRE1 extends GameEvent{
    //This event triggers the Kitchen Lights to go out. The player then starts a MultipleChoice to find the light switch.
    private LightsOutChoices lightsOutChoices;
    private int turnCountWhenCompleted;

    //CONSTRUCTOR
    public KitchenLightsOutRE1(int priority, Player player1, LightsOutChoices lightsOutChoices){
        super(priority, player1);
        this.lightsOutChoices = lightsOutChoices;
        turnCountWhenCompleted = -1;
    }

    //METHODS

    //The event will trigger when the player has been in the Kitchen for 7 room turns.
    public boolean getTriggerStatus(){
        Room currentLocation = getPlayer().getLocation();
        boolean triggered = false;
        if(currentLocation instanceof Kitchen){
            int roomTurnCount = getPlayer().getRoomTurnCounter();
            if(roomTurnCount > 7){
                triggered = true;
            }
        }
        return triggered;
    }

    //The event will trigger the LightsOutChoices MultipleChoice Object that will lead the player through a series of choices to find the light switch.
    public String getTriggerMsg(){
        //Begin running the lights out Multiple choice
        this.setCompletedStatus(true);
        this.lightsOutChoices.runMultipleChoice();
        turnCountWhenCompleted = getPlayer().getTurnCounter();
        String response = "You are relieved to see the lights flicker back on. You take a quick look around your surroundings.\n\n";
        String lookAtKitchen = getPlayer().look(getPlayer().getLocation());
        return response + lookAtKitchen;
    }

    public int getTurnCountWhenCompleted(){
        return this.turnCountWhenCompleted;
    }
}
