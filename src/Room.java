
import java.util.*;
abstract class Room extends Interactive{
    private ArrayList<Item> roomItems;
    private ArrayList<GameEvent> roomEvents;
    private Room northExit;
    private Room eastExit;
    private Room southExit;
    private Room westExit;
    private Door northDoor;
    private Door eastDoor;
    private Door southDoor;
    private Door westDoor;
    private int visitNumber; //The number of times a Room has been visited


    //CONSTRUCTORS

    public Room(String name, Player player){
        super(name, player);
        this.roomItems = new ArrayList<Item>();
        this.visitNumber = 0;
        this.roomEvents = new ArrayList<>();
    }

    //BEHAVIOR

    //Returns the roomItems
    public ArrayList<Item> getRoomItems(){
        return this.roomItems;
    }

    //Returns roomEvents
    public ArrayList<GameEvent> getRoomEvents(){
        return this.roomEvents;
    }

    //Returns the northExit
    public Room getNorthExit(){
        return this.northExit;
    }

    //Returns the eastExit
    public Room getEastExit(){
        return this.eastExit;
    }

    //Returns the southExit
    public Room getSouthExit(){
        return this.southExit;
    }

    //Returns the westExit
    public Room getWestExit(){
        return this.westExit;
    }

    //Returns the North Door
    public Door getNorthDoor(){
        return this.northDoor;
    }

    //Returns the the East Door
    public Door getEastDoor(){
        return this.eastDoor;
    }

    //Returns the South Door
    public Door getSouthDoor(){
        return this.southDoor;
    }

    //Returns the West Door
    public Door getWestDoor(){
        return this.westDoor;
    }

    //Sets a Room to be the northExit
    public void setNorthExit(Room newExit){
        this.northExit = newExit;
    }

    //Sets a Room to be the eastExit
    public void setEastExit(Room newExit){
        this.eastExit = newExit;
    }

    //Sets a Room to be the southExit
    public void setSouthExit(Room newExit){
        this.southExit = newExit;
    }

    //Sets a Room to be the westExit
    public void setWestExit(Room newExit){
        this.westExit = newExit;
    }

    //Sets a Door to be the northDoor, and place it in the room if it is not already in the room
    public void setNorthDoor(Door door){
        //Set door as the northDoor
        this.northDoor = door;
        addDoorToRoomHandler(door);
    }

    //Sets a Door to be the eastDoor
    public void setEastDoor(Door door){
        //Set door as the northDoor
        this.eastDoor = door;
        addDoorToRoomHandler(door);
    }

    //Sets a Door to be the southDoor
    public void setSouthDoor(Door door){
        this.southDoor = door;
        addDoorToRoomHandler(door);
    }

    //Sets a Door to be the westDoor
    public void setWestDoor(Door door){
        this.westDoor = door;
        addDoorToRoomHandler(door);
    }

    //Determines if door should be added to room or not based on whether it has already been added to room.
    private void addDoorToRoomHandler(Door door){
        boolean alreadyInRoom = false;
        //If the door has not already been placed in the room. Place it in the room.
        for(Item item : this.roomItems){
            if(door.equals(item)){
                alreadyInRoom = true;
            }
        }
        if(!alreadyInRoom){
            this.roomItems.add(door);
        }
    }

    //Gets the number of times a room has been visited
    public int getVisitNumber(){
        return this.visitNumber;
    }

    //Increases by 1 the number of times a room has been visited
    public void addVisit(){
        this.visitNumber++;
    }

    //Add an Item to the Room
    public void addRoomItem(Item e){
        this.roomItems.add(e);
    }

    //Adds an ItemContainer to the room

    /*public void addRoomItem(ItemContainer e){
        this.roomContainers.add(e);
    }*/

    //Adds a gameEvent to roomEvents
    public void addGameEvent(GameEvent e){
        this.roomEvents.add(e);
    }

    //Remove an item from the room
    public void removeRoomItem(Item e){
        for(int i = 0; i < roomItems.size(); i++){
            if(roomItems.get(i).equals(e)){
                roomItems.remove(i);
                break;
            }
        }
    }


    //Remove a GameEvent from the room
    public void removeGameEvent(GameEvent e){
        for(GameEvent event : this.roomEvents){
            if(event.equals(e)){
                roomEvents.remove(e);
            }
        }
    }


    //Returns a string response when a player wants to look to see what is to the North
    public String lookNorth(){
        if(this.getNorthExit() != null){
            if(this.getNorthDoor() != null){
                //northDoor and northExit both exist, but the room has never been visited
                if(this.getNorthExit().getVisitNumber() == 0){
                    return "To the North you see a " + getNorthDoor().quote() + ". You are not sure where it leads.";
                }
                //northDoor and northExit both exist. We know the name of the room beyond the door.
                else{
                    return "To the North you see a " + getNorthDoor().quote() + ". It leads to the " + this.getNorthExit().quote() + ".";
                }
            }
            //NorthExit exists but not northDoor. It hasn't been visited before.
            if(this.getNorthExit().getVisitNumber() == 0 && this.getNorthDoor() == null){
                return "You are not sure where going north will lead you.";
            }
            //NorthExit exists but not northDoor. We know the name of the next room.
            else if(this.getNorthExit().getVisitNumber() > 0 && this.getNorthDoor() == null){
                return "Going north leads to the " + this.getNorthExit().quote() + ".";
            }
        }
        //Door exists but not exit.
        if(this.getNorthDoor() != null && this.getNorthExit() == null){
            return "To the North you see a " + getNorthDoor().quote() + ". You are not sure where it leads.";
        }
        //Both door and exit don't exist
        return "There is a wall blocking your path from going North.";
    }


    public String lookEast(){
        if(this.getEastExit() != null){
            if(this.getEastDoor() != null){
                //The exit and door both exist, but the room has never been visited
                if(this.getEastExit().getVisitNumber() == 0){
                    return "To the East you see a " + getEastDoor().quote() + ". You are not sure where it leads.";
                }
                //The exit and door both exist. We know the name of the room beyond the door.
                else{
                    return "To the East you see a " + getEastDoor().quote() + ". It leads to the " + this.getEastExit().quote() + ".";
                }
            }
            //The exit exists but not the door. It hasn't been visited before.
            if(this.getEastExit().getVisitNumber() == 0 && this.getEastDoor() == null){
                return "You are not sure where going East will lead you.";
            }
            //The Exit exists but not the door. We know the name of the next room.
            else if(this.getEastExit().getVisitNumber() > 0 && this.getEastDoor() == null){
                return "Going East leads to the " + this.getEastExit().quote() + ".";
            }
        }
        //Door exists but not exit.
        if(this.getEastDoor() != null && this.getEastExit() == null){
            return "To the East you see a " + getEastDoor().quote() + ". You are not sure where it leads.";
        }
        //Both door and exit don't exist
        return "There is a wall blocking your path from going East.";
    }

    public String lookSouth(){
        if(this.getSouthExit() != null){
            if(this.getSouthDoor() != null){
                //The exit and door both exist, but the room has never been visited
                if(this.getSouthExit().getVisitNumber() == 0){
                    return "To the South you see a " + getSouthDoor().quote() + ". You are not sure where it leads.";
                }
                //The exit and door both exist. We know the name of the room beyond the door.
                else{
                    return "To the South you see a " + getSouthDoor().quote() + ". It leads to the " + this.getSouthExit().quote() + ".";
                }
            }
            //The exit exists but not the door. It hasn't been visited before.
            if(this.getSouthExit().getVisitNumber() == 0 && this.getSouthDoor() == null){
                return "You are not sure where going South will lead you.";
            }
            //The Exit exists but not the door. We know the name of the next room.
            else if(this.getSouthExit().getVisitNumber() > 0 && getSouthDoor() == null){
                return "Going South leads to the " + this.getSouthExit().quote() + ".";
            }
        }
        //Door exists but not exit.
        if(this.getSouthDoor() != null && this.getSouthExit() == null){
            return "To the South you see a " + getSouthDoor().quote() + ". You are not sure where it leads.";
        }
        //Both door and exit don't exist
        return "There is a wall blocking your path from going South.";
    }


    public String lookWest(){
        if(this.getWestExit() != null){
            if(this.getWestDoor() != null){
                //The exit and door both exist, but the room has never been visited
                if(this.getWestExit().getVisitNumber() == 0){
                    return "To the West you see a " + getWestDoor().quote() + ". You are not sure where it leads.";
                }
                //The exit and door both exist. We know the name of the room beyond the door.
                else{
                    return "To the West you see a " + getWestDoor().quote() + ". It leads to the " + this.getWestExit().quote() + ".";
                }
            }
            //The exit exists but not the door. It hasn't been visited before.
            if(this.getWestExit().getVisitNumber() == 0 && this.getWestDoor() == null){
                return "You are not sure where going West will lead you.";
            }
            //The Exit exists but not the door. We know the name of the next room.
            else if(this.getWestExit().getVisitNumber() > 0 && this.getWestDoor() == null){
                return "Going West leads to the " + this.getWestExit().quote() + ".";
            }
        }
        //Door exists but not exit.
        if(this.getWestDoor() != null && this.getWestExit() == null){
            return "To the West you see a " + getWestDoor().quote() + ". You are not sure where it leads.";
        }
        //Both door and exit don't exist
        return "There is a wall blocking your path from going West.";

    }

    //Provides the logic which determines what happens when a player tries to go North
    public String attemptNorth(){
        //If there is no exit to the North or no door assigned to the North
        if(this.getNorthExit() == null || this.getNorthDoor() == null){
            return "You can't go that way. There is a wall blocking your path.";
        }
        else if(this.getNorthDoor().getLockedStatus()){
            return "The " + this.getNorthDoor().quote() + " is locked. You can't get through.";
        }
        else{
            String transition = "You proceed North through the " + this.getNorthDoor().quote() + ".";
            String changeLocation = getPlayer().changeLocation(getNorthExit());
            return roomTransitionMsg(transition, changeLocation);
        }
    }

    //Provides the logic which determines what happens when a player tries to go East
    public String attemptEast(){
        //If there is no exit to the east or no door assigned to the east
        if(this.getEastExit() == null || this.getEastDoor() == null){
            return "You can't go that way. There is a wall blocking your path.";
        }
        else if(this.getEastDoor().getLockedStatus()){
            return "The " + this.getEastDoor().quote() + " is locked. You can't get through.";
        }
        else{
            String transition = "You proceed East through the " + this.getEastDoor().quote() + ".";
            String changeLocation = getPlayer().changeLocation(getEastExit());
            return roomTransitionMsg(transition, changeLocation);
        }
    }

    //Provides the logic which determines what happens when a player tries to go South
    public String attemptSouth(){
        //If there is no exit to the South or no door assigned to the South
        if(this.getSouthExit() == null || this.getSouthDoor() == null){
            return "You can't go that way. There is a wall blocking your path.";
        }
        else if(this.getSouthDoor().getLockedStatus()){
            return "The " + this.getSouthDoor().quote() + " is locked. You can't get through.";
        }
        else{
            String transition = "You proceed South through the " + this.getSouthDoor().quote() + ".";
            String changeLocation = getPlayer().changeLocation(getSouthExit());
            return roomTransitionMsg(transition, changeLocation);
        }
    }

    //Provides the logic which determines what happens when a player tries to go West
    public String attemptWest(){
        //If there is no exit to the West or no door assigned to the West
        if(this.getWestExit() == null || this.getWestDoor() == null){
            return "You can't go that way. There is a wall blocking your path.";
        }
        else if(this.getWestDoor().getLockedStatus()){
            return "The " + this.getWestDoor().quote() + " is locked. You can't get through.";
        }
        else{
            String transition = "You proceed West through the " + this.getWestDoor().quote() + ".";
            String changeLocation = getPlayer().changeLocation(getWestExit());
            return roomTransitionMsg(transition, changeLocation);
        }
    }

    public String roomTransitionMsg(String transition, String changeLocation){
        return transition + "\n" + changeLocation;
    }

    //Provides an introduction to the room you are currently in. Used to help build out a String to return for look();
    public String getRoomIntro(){
        return "You are currently in the " + this.quote() + ".\n";
    }

    public String getNpcDescriptions(){
        String npcDescriptions = "";
        ArrayList<NPC> NpcList = this.getPlayer().getEnvironment().getNpcList();
        for(NPC npc : NpcList){
            if(npc.getLocation() != null && npc.getLocation().equals(this)){
                //When the room level description isn't empty and
                npcDescriptions += (!npc.getRoomLevelDescription().isEmpty()) ? npc.getRoomLevelDescription() + "\n" : "";
            }
        }
        return npcDescriptions;
    }

    //Provides all the available exits (and which rooms they lead to) Used to help build out the return String for look() and examine();
    public String getRoomExits(){
        String NorthExitStr = (northDoor != null) ? "There is a " + this.getNorthDoor().quote() + " to the North.\n" : "";
        //String northExitAppend = (northDoor != null) ? " You are not sure where it leads.\n" : "";
        String EastExitStr = (eastDoor != null) ? "There is a " + this.getEastDoor().quote() + " to the East.\n" : "";
        //String eastExitAppend = (eastDoor != null) ? " You are not sure where it leads.\n" : "";
        String SouthExitStr = (southDoor != null) ? "There is a " + this.getSouthDoor().quote() + " to the South.\n" : "";
        //String southExitAppend = (southDoor != null) ? " You are not sure where it leads.\n" : "";
        String WestExitStr = (westDoor != null) ? "There is a " + this.getWestDoor().quote() + " to the West.\n" : "";
        //String westExitAppend = (westDoor != null) ? " You are not sure where it leads.\n" : "";
        NorthExitStr = (this.northExit != null && this.northExit.getVisitNumber() != 0) ? NorthExitStr.substring(0, NorthExitStr.length() -2) + " which leads to the " + this.getNorthExit().quote() + ".\n" : NorthExitStr; //+ northExitAppend;
        EastExitStr = (this.eastExit != null && this.eastExit.getVisitNumber() != 0) ? EastExitStr.substring(0, EastExitStr.length() -2) + " which leads to the " + this.getEastExit().quote() + ".\n" : EastExitStr; //+ eastExitAppend;
        SouthExitStr = (this.southExit != null && this.southExit.getVisitNumber() != 0) ? SouthExitStr.substring(0, SouthExitStr.length() -2) + " which leads to the " + this.getSouthExit().quote() + ".\n" : SouthExitStr;// + southExitAppend;
        WestExitStr = (this.westExit != null && this.westExit.getVisitNumber() != 0) ? WestExitStr.substring(0, WestExitStr.length() -2) + " which leads to the " + this.getWestExit().quote() + ".\n" : WestExitStr;// + westExitAppend;
        String roomExits = NorthExitStr + EastExitStr + SouthExitStr + WestExitStr;
        roomExits = roomExits.substring(0, roomExits.length() -1); //Removes the last \n
        return roomExits;
    }


    //ABSTRACT METHODS

    abstract String look();

    abstract String examine();

}
