import java.util.*;
public class Player {
    //Fields
    private ArrayList<Item> inventory;
    private String name;
    private Room currentLocation;
    private boolean alive;
    private boolean continueGame;
    private int turnCounter;
    private int actionCounter;
    private int roomTurnCounter;
    private Environment environment;
    private String lastAction;

    //Player Constructor
    public Player(String name){
        this.name = name;
        this.alive = true;
        this.continueGame = true;
        this.inventory = new ArrayList<Item>();
        this.turnCounter = 0;
        this.actionCounter = 0;
        this.roomTurnCounter = 0;
        this.lastAction = "";
    }

    //Behaviors

    //Returns the player's name
    public String getName(){
        return name;
    }

    //Returns the current location of the player as an Object
    public Room getLocation() {
        return this.currentLocation;
    }

    //Returns the players alive status
    public boolean getAliveStatus(){
        return this.alive;
    }

    //Returns the players continueGame Status
    public boolean getContinueGame(){
        return this.continueGame;
    }

    //Returns the players current inventory
    public ArrayList<Item> getInventory(){
        return this.inventory;
    }

    //Returns the players turn counter (Increases whenever the player makes their turn)
    public int getTurnCounter(){
        return this.turnCounter;
    }

    //Returns the players action counter (Increases whenever the player makes an action)
    public int getActionCounter(){
        return this.actionCounter;
    }

    //Returns the players room turn counter (Increases whenever turn counter goes up) (Resets when entering a new room)
    public int getRoomTurnCounter(){
        return this.roomTurnCounter;
    }

    //Returns the players last action as a String
    public String getLastAction(){
        return this.lastAction;
    }

    //Returns the players Environment
    public Environment getEnvironment(){
        return this.environment;
    }

    //Sets the players Environment
    public void setEnvironment(Environment env){
        this.environment = env;
    }

    //Increases the turnCounter and roomTurnCounter by 1
    private void increaseTurnCounter(){
        turnCounter++;
        roomTurnCounter++;
    }

    //Increases turnCounter, roomTurnCounter, and actionTurnCounter by 1
    public void increaseActionCounter(){
        this.increaseTurnCounter();
        actionCounter++;
    }


    //Adds an item to the players inventory (Different from Taking an Item)
    public void addToInventory(Item e){
        inventory.add(e);
    }

    //Changes the players current location, and looks at the new location. Reset the roomTurnCounter.
    public String changeLocation(Room newLocation) {
        this.roomTurnCounter = 0;
        this.currentLocation = newLocation;
        newLocation.addVisit();
        return this.currentLocation.look();
    }

    //Changes the players alive Status
    public void setAliveStatus(boolean alive){
        this.alive = alive;
    }

    //Changes the players continueGame status
    public void setContinueGame(boolean continueGame){
        this.continueGame = continueGame;
    }

    //Player attempts to go North
    public String goNorth(){
        this.lastAction = "north";
        this.increaseActionCounter();
        return currentLocation.attemptNorth();
    }

    //Player attempts to go East
    public String goEast(){
        this.lastAction = "east";
        this.increaseActionCounter();
        return currentLocation.attemptEast();
    }

    //Player attempts to go South
    public String goSouth(){
        this.lastAction = "south";
        this.increaseActionCounter();
        return currentLocation.attemptSouth();
    }

    //Player attempts to go West
    public String goWest() {
        this.lastAction = "west";
        this.increaseActionCounter();
        return currentLocation.attemptWest();
    }

    //The player looks at any interactive
    public String look(Interactive e){
        this.lastAction = "look " + e.getName();
        this.increaseTurnCounter();
        return e.look();
    }

    //The player looks to the north in the current Room
    public String lookNorth(){
        this.lastAction = "look north";
        this.increaseTurnCounter();
        return this.currentLocation.lookNorth();
    }

    //The player looks to the east in the current Room
    public String lookEast(){
        this.lastAction = "look east";
        this.increaseTurnCounter();
        return this.currentLocation.lookEast();
    }

    //The player looks to the south in the current Room
    public String lookSouth(){
        this.lastAction = "look south";
        this.increaseTurnCounter();
        return this.currentLocation.lookSouth();
    }

    //The player looks West in the current Room
    public String lookWest(){
        this.lastAction = "look west";
        this.increaseTurnCounter();
        return this.currentLocation.lookWest();
    }

    //The player examines an Interactive. Results in a long form description of the Interactive.
    public String examineInteractive(Interactive e){
        this.lastAction = "examine " + e.getName();
        this.increaseActionCounter();
        return e.examine();
    }

    //The Player attempts to take an item. If the item can be taken, it is added to the players inventory.
    public String takeItem(Item e){
        this.lastAction = "take " + e.getName();
        boolean successful = e.attemptTake();
        if(successful){
            //If the Item is an NPC its location must be reset to null, and it must be removed from the room.
            if(e instanceof NPC){
                NPC npc = (NPC) e;
                npc.changeLocation(null);
                this.getLocation().removeRoomItem(e);
            }
            //The Item is just a regular room Item
            else{
                this.getLocation().removeRoomItem(e);
            }
            //A successful take counts as an action, while an unsuccessful take does not.
            this.actionCounter++;
            inventory.add(e);
        }
        this.increaseTurnCounter();
        return e.takeMessage(successful);
    }

    //The Player attempts to take an item that is in an ItemContainer
    public String takeItem(ItemContainer c, Item e){
        this.lastAction = "take " + e.getName() + " " + c.getName();
        boolean successful = e.attemptTake();
        if(successful){
            //If take is successful add item to inventory and remove item from room. Success counts as an action.
            this.actionCounter++;
            inventory.add(e);
            c.removeStoredItem(e);
        }
        this.increaseTurnCounter();
        return e.takeMessage(successful);
    }

    //The Player attempts to use one of their inventoryItems on another item
    public String use(Item inventoryItem, Item otherItem){
        this.lastAction = "use " + inventoryItem.getName() + " " + otherItem.getName();
        this.increaseActionCounter();
        return otherItem.attemptUse(inventoryItem);
    }

    //The Player attempts to open a Lockable. (ItemContainer, Door, ect..)
    public String open(Lockable lockable){
        this.lastAction = "open " + lockable.getName();
        this.increaseActionCounter();
        return lockable.attemptOpen();
    }


    //The Player attempts to close an Lockable. (ItemContainer, Door, ect..)
    public String close(Lockable lockable){
        this.lastAction = "close " + lockable.getName();
        this.increaseActionCounter();
        return lockable.attemptClose();
    }

    //The player waits for a turn
    public String waitTurn(){
        this.lastAction = "wait";
        this.increaseActionCounter();
        return "You wait around and do nothing for a little bit.";
    }

    //The player talks to the Talking interactive
    public String talkTo(Talking talking){
        return talking.talk();
    }

    //The player reads a ReadableItem
    public String read(ReadableItem readableItem){
        return readableItem.read();
    }

    //The player attempt to eat an Edible Item
    public String eat(Edible edible){
        return edible.attemptEat();
    }

    //Prints the players current inventory:
    public String printInventory(){
        this.lastAction = "inventory";
        this.increaseTurnCounter();
        String s = "Items in your inventory: ";
        for(Item item: this.inventory) {
            s += item.quote() + "  ";
        }
        return s;
    }
}


