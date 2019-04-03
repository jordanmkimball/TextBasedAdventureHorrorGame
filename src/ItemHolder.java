//This class is different from ItemContainer in that it is for items that are only ever Open and unlocked.
abstract class ItemHolder extends ItemContainer {

    //CONSTRUCTOR
    public ItemHolder(String name, Player player1){
        super(name, player1);
    }

    //OVERWRITTEN METHODS FROM ItemContainer

    //Provides the logic which determines what happens when the player attempts to open the ItemContainer
    public String attemptOpen(){
        return "The " + this.quote() + " is not something that can be opened.";
    }

    //Provides the logic which determines what happens when a player attempts to close an ItemContainer
    public String attemptClose(){
        return "The " + this.quote() + " is not something that can be closed.";
    }

    //ItemHolders don't return a string for inside description by default.
    public String getInsideDescription(){
        return "";
    }


}
