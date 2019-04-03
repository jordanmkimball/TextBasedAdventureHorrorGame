import java.util.*;

abstract class ItemContainer extends Item implements Lockable, AcceptsItem{
    //The default state for containers is to be closed but not locked
    private boolean open;
    private boolean locked;
    private Item acceptItem;
    private ArrayList<Item> storedItems;

    //CONSTRUCTORS
    public ItemContainer(String name, Player playerName){
        super(name, playerName);
        this.storedItems = new ArrayList<>();
        this.open = true;
        this.locked = false;
    }

    public ItemContainer(String name, Player playerName, boolean open, boolean locked){
        super(name, playerName);
        this.storedItems = new ArrayList<>();
        this.open = open;
        this.locked = locked;
        if(this.open && this.locked){
            throw new IllegalArgumentException("Error: Item containers cannot be both open and locked!");
        }
    }

    public ItemContainer(String name, Player playerName, boolean open, boolean locked, Item acceptsItem){
        super(name, playerName);
        this.storedItems = new ArrayList<>();
        this.open = open;
        this.locked = locked;
        if(this.open && this.locked){
            throw new IllegalArgumentException("Item containers cannot be both open and locked!");
        }
        this.acceptItem = acceptsItem;
    }

    //METHODS

    //Returns the "open" status
    public boolean getOpenStatus(){
        return this.open;
    }

    //Returns the "locked" status
    public boolean getLockedStatus(){
        return this.locked;
    }

    //Sets the "open" status
    public void setOpenStatus(boolean open){
        this.open = open;
    }

    //Sets the "Locked" status
    public void setLockedStatus(boolean locked){
        this.locked = locked;
    }

    //Returns the acceptItem
    public Item getAcceptItem(){
        return this.acceptItem;
    }

    //Sets the acceptItem
    public void setAcceptItem(Item acceptItem){
        this.acceptItem = acceptItem;
    }

    //Returns the linkedList of stored Items
    public ArrayList<Item> getStoredItems(){
        return this.storedItems;
    }

    //Adds an item to the list of storedItems
    public void addStoredItem(Item e){
        storedItems.add(e);
    }

    //Removes an item from the list of storedItems
    public void removeStoredItem(Item e){
        storedItems.remove(e);
    }

    //Provides the logic which determines what happens when the player attempts to open the ItemContainer
    public String attemptOpen(){
        String openResponse;
        if(this.locked){
            openResponse = "The " + this.quote() + " is locked.";
        }
        else if(this.open){
            openResponse = "The " + this.quote() + " is already open.";
        }
        else{
            openResponse = "You open the " + this.quote() + ".\n";
            this.open = true;
            //Append a description of the inside of the itemContainer to your open response.
            openResponse += getInsideDescription();
        }
        return openResponse;
    }

    //Provides the logic which determines what happens when a player attempts to close an ItemContainer
    public String attemptClose(){
        String closeResponse;
        if(!this.open){
            closeResponse = "The " + this.quote() + " is already closed.";
        }
        else{
            closeResponse = "You close the " + this.quote() + ".";
            this.open = false;
        }
        return closeResponse;
    }

    //ABSTRACT METHODS

    //Returns a description of the inside of an ItemContainer if open. Returns empty string if closed.
    abstract String getInsideDescription();
}




