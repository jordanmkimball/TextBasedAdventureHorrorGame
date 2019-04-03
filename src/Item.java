import java.util.ArrayList;

abstract class Item extends Interactive{
    private int itemNumber;
    public static int itemCount;

    //CONSTRUCTORS
    public Item(String name, Player player){
        super(name, player);
        itemCount++;
        itemNumber = itemCount;
    }


    //BEHAVIORS

    //Gets the total count of how many different items have been created.
    public int getItemCount(){
        return itemCount;
    }

    //Returns an integer which represents that this is the nth item that has been generated.
    public int getItemNumber(){
        return this.itemNumber;
    }

    //Provides the logic that determines what happens when a Player tries to use one of their items on this item
    //This method contains a default message of "Nothing happens" unless it is Over-ridden in future sub-classes
    public String attemptUse(Item inventoryItem){
        return "Nothing happens";
    }

    public String takeMessage(boolean attemptTake){
        if(attemptTake){
            return "You take the " + this.quote() + " and put it in your inventory.";
        }
        else{
            return "You are unable to put " + this.quote() + " in your inventory.";
        }
    }

    public String examineIntro(){
        return "You examine the " + this.quote() + ".\n";
    }

    public boolean isInInventory(){
        boolean inInventory =  false;
        ArrayList<Item> playerInventory = this.getPlayer().getInventory();
        for(Item item : playerInventory){
            if(item.equals(this)){
                inInventory = true;
            }
        }
        return inInventory;
    }


    //ABSTRACT METHODS

    //Used for when a Player attempts to take an item. Provides the logic that determines whether or not the Player can add item to inventory or not
    abstract boolean attemptTake();

    //Used to print the text the Player will see when they take a quick look at an item.
    abstract String look();

    //Used to print the text the Player will see when they carefully examine an item.
    abstract String examine();

}
