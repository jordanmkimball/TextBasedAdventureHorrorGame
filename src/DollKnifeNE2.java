import java.util.ArrayList;

//This event will only occur after KitchenLightsOutRE1 has triggered, and it causes the LightsOutChoices2 MultipleChoice sequence.
public class DollKnifeNE2 extends NpcEvent{
    private LightsOutChoices2 lightsOutChoices2;

    //CONSTRUCTOR
    public DollKnifeNE2(int priority, Player player1){
        super(priority, player1);
        this.lightsOutChoices2 = new LightsOutChoices2(player1);
    }

    //METHODS

    //This method checks if the event is being triggered or not.
    public boolean getTriggerStatus(){
        boolean triggered = false;
        Room currentLocation = this.getPlayer().getLocation();
        Kitchen kitchen;
        //Early exit if the Player isn't in the Kitchen.
        if(!(currentLocation instanceof Kitchen)){
            return false;
        }
        kitchen = (Kitchen) currentLocation;
        //Early exit if the KitchenLightsOutRE1 hasn't been completed yet.
        if(!kitchen.getKitchenLightsOutRE1().getCompletedStatus()){
            return false;
        }
        int turnNumWhenLightsOutDone = kitchen.getKitchenLightsOutRE1().getTurnCountWhenCompleted();
        int currentTurnCount = getPlayer().getTurnCounter();
        //Early exit if at least 5 turns haven't occurred since the KitchenLightsOutRE1 event happened.
        if(currentTurnCount - turnNumWhenLightsOutDone < 5){
            return false;
        }
        //Early exit if the yellow Door has been unlocked
        if(!currentLocation.getEastDoor().getLockedStatus()){
            return false;
        }

        //Search through players inventory to see what items they have.
        //They must have either obtained the piece of meat (meaning it is in their inventory) or they have baited the mouseTrap.
        boolean hasSmallMeat = false;
        MouseTrap mouseTrap = null;
        ArrayList<Item> inventory = getPlayer().getInventory();
        for(Item item : inventory){
            if(item instanceof SmallMeat){
                hasSmallMeat = true;
            }
            else if(item instanceof MouseTrap){
                mouseTrap = (MouseTrap) item;
            }
        }
        //Search through Room for the MouseTrap
        ArrayList<Item> roomItems = currentLocation.getRoomItems();
        for(Item item : roomItems){
            if(item instanceof MouseTrap){
                mouseTrap = (MouseTrap) item;
            }
        }
        if(hasSmallMeat || (mouseTrap != null && mouseTrap.isBaited())){
            triggered = true;
        }
        return triggered;
    }


    //The LightsOutChoices2 MultipleChoice runs, and the Doll takes the knife, moves to the dustyRoom, takes the buttons, moves to the Dark room and locks the door.
    public String getTriggerMsg() {
        this.setCompletedStatus(true);
        ArrayList<Item> inventory = getPlayer().getInventory();
        this.lightsOutChoices2.runMultipleChoice();
        inventory.removeIf(n -> (n instanceof Knife || n instanceof CreepyDoll));
        //Creepy Doll moves to the Dusty Room.
        this.getNPC().changeLocation(getPlayer().getLocation().getNorthExit().getEastExit());
        //Re-lock the Blue Door. This will help cue players in that maybe they shouldn't go through this door.
        this.getNPC().getLocation().getWestDoor().setLockedStatus(true);
        //Remove the buttons from the Dusty Room.
        DustyRoom dustyRoom = (DustyRoom) this.getNPC().getLocation().getWestExit();
        Table table = dustyRoom.getTable();
        Buttons buttons = table.getButtons();
        table.removeStoredItem(buttons);
        //Activate the Dolls General Behavior
        this.getNPC().setActiveStatus(true);
        String response = "You are relieved to see the lights flicker back on. However, you notice that your backpack is feeling a bit lighter than before...\n";
        response += "You take a quick look around your surroundings.\n\n";
        response += getPlayer().look(getPlayer().getLocation());
        return response;
    }
}
