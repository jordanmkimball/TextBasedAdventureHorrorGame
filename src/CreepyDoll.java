import java.util.ArrayList;

public class CreepyDoll extends NPC {
    private DollLetterNE1 dollLetterNE1;
    private DollKnifeNE2 dollKnifeNE2;
    private DollGiggleNE3 dollGiggleNE3;

    //CONSTRUCTOR
    public CreepyDoll(String name, Player player1, int priority, DollLetterNE1 dollLetterNE1, DollKnifeNE2 dollKnifeNE2, DollGiggleNE3 dollGiggleNE3){
        super(name, player1, priority);
        this.dollLetterNE1 = dollLetterNE1;
        this.addNpcEvent(dollLetterNE1);
        this.dollKnifeNE2 = dollKnifeNE2;
        this.addNpcEvent(dollKnifeNE2);
        this.dollGiggleNE3 = dollGiggleNE3;
        this.addNpcEvent(dollGiggleNE3);
    }

    //BEHAVIORS

    public boolean attemptTake(){
        boolean ableToBeTaken = true;
        if(this.dollKnifeNE2.getCompletedStatus()){
            ableToBeTaken = false;
        }
        return ableToBeTaken;
    }

    //Taking the Creepy Doll unlocks the red door.
    public String takeMessage(boolean attemptTake){
        String returnMessage = "";
        //If Doll is able to be taken.
        if(attemptTake){
            String response = "You take the " + this.quote() + " and put it in the backpack where you store your Inventory.";
            //Unlock the Dusty Room South Door
            getPlayer().getLocation().getWestExit().getSouthDoor().setLockedStatus(false);
            String doorUnlock = "\n\nYou hear the faint clicking sound come from the direction of the " + getPlayer().getLocation().getWestExit().quote() + ".";
            returnMessage = response + doorUnlock;
        }
        else{
            returnMessage = "You can tell that the " + this.quote() + " doesn't want to be taken right now.";
        }
        return returnMessage;
    }

    //Looking at the doll. Description changes based on which NpcEvents have been completed.
    public String look(){
        String description = "A small " + this.quote() + " with black button eyes, grey curly hair, and a black frilly dress.";
        //If the first NPC event has been completed. The doll is smiling. Otherwise the doll is frowning.
        if(this.dollLetterNE1.getCompletedStatus()){
            description += "\nThe doll is smiling.";
        }
        else{
            description += "\nThe doll is frowning.";
        }
        return description;
    }

    public String examine(){
        String description = "You examine the " + this.quote() + " more closely. The doll smells strangely of copper, and one of its button eyes is starting to come undone.\nThe doll has a tag on its leg that has the words \"take me\" written on it.";
        if(this.dollLetterNE1.getCompletedStatus()){
            description += "\nYou are pretty sure that the " + this.quote() + " wasn't smiling when you picked it up...";
        }
        return description;
    }

    public boolean getActionStatus(){
        boolean performingActions = false;
        if(this.dollKnifeNE2.getCompletedStatus() && getPlayer().getLocation().equals(this.getLocation())){
            performingActions = true;
        }
        return performingActions;
    }

    //The Creepy Doll will kill the player once it starts performing actions.
    public String performAction(){
        getPlayer().setAliveStatus(false);
        DollDeathChoices dollDeathChoices = new DollDeathChoices(getPlayer(), this.getChair(), this);
        dollDeathChoices.runMultipleChoice();
        String response = "You struggle until the very end as the " + this.quote() + " slowly moves the tip of the bloody knife towards your left eye.";
        return response;
    }

    //Returns the DollKnifeNE2 Event
    public DollKnifeNE2 getDollKnifeNE2(){
        return dollKnifeNE2;
    }

    private Item getChair(){
        Item chair = null;
        ArrayList<Item> roomItems = getPlayer().getLocation().getRoomItems();
        for(Item item : roomItems){
            if(item instanceof Chair){
                chair = item;
            }
        }
        return chair;
    }
}
