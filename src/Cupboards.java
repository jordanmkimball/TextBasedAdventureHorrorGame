public class Cupboards extends ItemContainer {
    private MouseTrap mouseTrap;

    //CONSTRUCTOR
    public Cupboards(String name, Player player1, boolean open, boolean locked, MouseTrap mouseTrap){
        super(name, player1, open, locked);
        this.mouseTrap = mouseTrap;
        this.addStoredItem(mouseTrap);
    }

    //BEHAVIOR

    public boolean attemptTake(){
        return false;
    }

    public String takeMessage(){
        return "The " + this.quote() + " is attached firmly to the wall.";
    }

    public String look(){
        String description;
        String inside = getInsideDescription();
        if(!this.getOpenStatus()){
            description = "A series of old wooden " + this.quote() + ". They usually hold Kitchen supplies inside.";
        }
        //If the Cupboards are open and the mousetrap is still in there.
        else if(this.getOpenStatus() && this.getStoredItems().size() > 0){
            description = "A series of opened old wooden Cupboards.\n";
        }
        //If the Cupboards are open, and the mouseTrap has been taken.
        else{
            description = "A series of opened old wooden Cupboards.\n";
        }
        return description + inside;
    }

    public String examine(){
        String intro = "You examine the " + this.quote() + " more closely.\n";
        String inside = getInsideDescription();
        String feelings;
        if(!this.getOpenStatus()){
            feelings = "The cupboards are unlocked and you could open them to search for useful items.";
        }
        //If the Cupboards are open and the mousetrap is still in there.
        else if(this.getOpenStatus() && this.getStoredItems().size() > 0){
            feelings = "\nJudging from the rodent you saw earlier, this place could really use a few mousetraps.";
        }
        //If the Cupboards are open, and the mouseTrap has been taken.
        else{
            feelings = "\nNone of this is stuff you want to take.";
        }
        return intro + inside + feelings;
    }

    //Provides the logic which determines what happens when the player attempts to open the ItemContainer
    public String attemptOpen(){
        String openResponse;
        if(this.getLockedStatus()){
            openResponse = "The " + this.quote() + " are locked.";
        }
        else if(this.getOpenStatus()){
            openResponse = "The " + this.quote() + " are already open.";
        }
        else{
            openResponse = "You open the " + this.quote() + ".\n";
            this.setOpenStatus(true);
            openResponse += getInsideDescription();

        }
        return openResponse;
    }

    //Provides the logic which determines what happens when a player attempts to close an ItemContainer
    public String attemptClose(){
        String closeResponse;
        if(!this.getOpenStatus()){
            closeResponse = "The " + this.quote() + " are already closed.";
        }
        else{
            closeResponse = "You close the " + this.quote() + ".";
            this.setOpenStatus(false);
        }
        return closeResponse;
    }

    //Returns the description of the inside of the cupboard
    public String getInsideDescription(){
        String inside = "";
        if(this.getOpenStatus() && this.getStoredItems().size() > 0 ){
            inside = "Inside you find a bunch of broken plates, various rusty kitchen utensils, and a " + this.mouseTrap.quote() + ".";
        }
        else if(this.getOpenStatus()){
            inside = "Inside you find a bunch of broken plates, and various rusty kitchen utensils.";
        }
        return inside;
    }
}
