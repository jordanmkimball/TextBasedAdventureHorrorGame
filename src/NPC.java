import java.util.*;
abstract class NPC extends Item {
    private Room location;
    private ArrayList<NpcEvent> npcEvents;
    private boolean active;
    private int priority;
    private boolean alive;

    //CONSTRUCTORS
    public NPC(String name, Player player1, int priority){
        super(name, player1);
        this.npcEvents = new ArrayList<>();
        this.active = false; //NPCs start out in an inactive state.
        this.priority = priority;
        this.alive = true;
    }


    //BEHAVIOR

    public Room getLocation(){
        return this.location;
    }

    public void changeLocation(Room newRoom){
        if(this.location != null){
            this.location.removeRoomItem(this);
        }
        this.location = newRoom;
        if(this.location != null){
            this.location.addRoomItem(this);
        }
    }

    //Returns the priority rank (lower = greater priority) for this NPC
    public int getPriority(){
        return this.priority;
    }

    //Sets the priority level of this NPC
    public void setPriority(int priority){
        this.priority = priority;
    }

    //Gets the alive status of the NPC
    public boolean getAlive(){
        return this.alive;
    }

    //Sets the alive status of the NPC
    public void setAlive(boolean alive){
        this.alive = alive;
    }

    //NPCs are by default not able to be taken.
    public boolean attemptTake(){
        return false;
    }

    //Default message when a player tries to take an NPC.
    public String takeMessage(boolean attemptTake){
        return "You are unable to put the " + this.quote() + " in your inventory";
    }

    //Returns the list of NpcEvents for this NPC.
    public ArrayList<NpcEvent> getNpcEvents(){
        return this.npcEvents;
    }

    //Adds an NpcEvent to this NPC. Also sets the NpcEvent NPC value as this NPC.
    public void addNpcEvent(NpcEvent npcEvent){
        this.npcEvents.add(npcEvent);
        npcEvent.setNPC(this);
    }

    //Returns whether the NPC is actively looking to take actions
    public boolean getActiveStatus(){
        return this.active;
    }

    //Sets an NPCs active movement status
    public void setActiveStatus(boolean active){
        this.active = active;
    }

    //Gives a generic roomLevelDescription for NPCs to append to room look() and room examine() responses when the NPC is in the room with you
    public String getRoomLevelDescription(){
        return "The " + this.quote() + " is in this room with you.";
    }

    //The NPC moves North if the path is not blocked.
    public boolean goNorth(){
        boolean success = false;
        //If the Exit exists, and the door exists, and the door is not locked. Move the NPC to the NorthExit Room
        if(this.location.getNorthExit() != null && this.location.getNorthDoor() != null && !this.location.getNorthDoor().getLockedStatus()){
            this.changeLocation(this.location.getNorthExit());
            success = true;
        }
        return success;
    }

    //The NPC moves East if the path is not blocked.
    public boolean goEast(){
        boolean success = false;
        //If the Exit exists, and the door exists, and the door is not locked. Move the NPC to the EastExit Room
        if(this.location.getEastExit() != null && this.location.getEastDoor() != null && !this.location.getEastDoor().getLockedStatus()){
            this.changeLocation(this.location.getEastExit());
            success = true;
        }
        return success;
    }

    //The NPC moves South if the path is not blocked.
    public boolean goSouth(){
        boolean success = false;
        //If the Exit exists, and the door exists, and the door is not locked. Move the NPC to the SouthExit Room
        if(this.location.getSouthExit() != null && this.location.getSouthDoor() != null && !this.location.getSouthDoor().getLockedStatus()){
            this.changeLocation(this.location.getSouthExit());
            success = true;
        }
        return success;
    }

    //The NPC moves West if the path is not blocked
    public boolean goWest(){
        boolean success = false;
        //If the Exit exists, and the door exists, and the door is not locked. Move the NPC to the WestExit Room
        if(this.location.getWestExit() != null && this.location.getWestDoor() != null && !this.location.getWestDoor().getLockedStatus()){
            this.changeLocation(this.location.getWestExit());
            success = true;
        }
        return success;
    }

    //The NPC moves North and ignores any barriers.
    public boolean goNorthIB(){
        boolean success = false;
        //If the Exit exists and the Door exists, move the NPC to the NorthExit Room
        if(this.location.getNorthExit() != null && this.location.getNorthDoor() != null){
            this.changeLocation(this.location.getNorthExit());
            success = true;
        }
        return success;
    }

    //The NPC moves East and ignores any barriers.
    public boolean goEastIB(){
        boolean success = false;
        //If the Exit exists, and the door exists, move the NPC.
        if(this.location.getEastExit() != null && this.location.getEastDoor() != null){
            this.changeLocation(this.location.getEastExit());
            success = true;
        }
        return success;
    }

    //The NPC moves South and ignores any barriers.
    public boolean goSouthIB(){
        boolean success = false;
        //If the Exit exists, and the door exists, Move the NPC to the SouthExit Room
        if(this.location.getSouthExit() != null && this.location.getSouthDoor() != null){
            this.changeLocation(this.location.getSouthExit());
            success = true;
        }
        return success;
    }

    //The NPC moves West if the path is not blocked
    public boolean goWestIB(){
        boolean success = false;
        //If the Exit exists, and the door exists, and the door is not locked. Move the NPC to the WestExit Room
        if(this.location.getWestExit() != null && this.location.getWestDoor() != null){
            this.changeLocation(this.location.getWestExit());
            success = true;
        }
        return success;
    }

    //Kills the NPC off and switches their status to inactive.
    public void kill(){
        this.alive = false;
        this.active = false;
    }





    //ABSTRACT METHODS

    //Returns a boolean representing whether an NPC performs a general Action.
    abstract boolean getActionStatus();


    //The NPC performs its general action. Returns a String if the player is able to see or hear results from this action. Empty otherwise.
    abstract String performAction();

}
