public class BloodyWallRoom extends Room {
    private DollPlacementRE dollPlacementRE;
    private BloodyMessage bloodyMessage;
    private Chair chair;
    private MetalVent metalVent;

    //CONSTRUCTORS
    public BloodyWallRoom(String name, Player playerName, DollPlacementRE dollPlacementRE, BloodyMessage bloodyMessage, Chair chair, MetalVent metalVent){
        super(name, playerName);
        this.addGameEvent(dollPlacementRE);
        this.dollPlacementRE = dollPlacementRE;
        this.bloodyMessage = bloodyMessage;
        this.getRoomItems().add(bloodyMessage);
        this.chair = chair;
        this.getRoomItems().add(chair);
        this.metalVent = metalVent;
        this.getRoomItems().add(metalVent);

    }

    //METHODS

    public String attemptNorth(){
        return "There is a wall marked with blood blocking your exit to the North.";
    }

    public String attemptEast(){
        return "There is no exit to the East.";
    }

    public String attemptSouth(){
        return "There is no exit to the South.";
    }

    public String attemptWest(){
        //Provide the text that describes the player leaving the current room.
        String transition = "You exit through the " + this.getWestDoor().quote() + " to the West";
        //Change the location that the player is in
        String changeLocation = this.getPlayer().changeLocation(this.getWestExit());
        String roomTransitionMsg = roomTransitionMsg(transition, changeLocation);
        //If the dollPlacementRE hasn't been completed yet. Trigger a special message after player leaves the room.
        if(!this.dollPlacementRE.getCompletedStatus()){
            String dollMsg = this.dollPlacementRE.getLeavingMessage();
            roomTransitionMsg = roomTransitionMsg + "\n\n" + dollMsg;
        }
        return roomTransitionMsg;
    }

    public String look(){
        String intro = "You are in the " + this.quote() + ".\n";
        String briefDescription = "You can see a " + this.bloodyMessage.quote() + " on the Northern wall of the room, and there is a single " + this.chair.quote() + " resting against the Eastern wall of the room.\n";
        String metalVents = "There is a large " + this.metalVent.quote() + " high up on the Southern wall of the room.\n";
        String exits = "The only exit in the room is the " + this.getWestDoor().quote() + " to the West, where you just came from.";
        for(Item item : this.getRoomItems()){
            //The Doll is not added to the room description if the DollKnife event has occurred.
            if((item instanceof CreepyDoll) && !this.dollPlacementRE.getDoll().getDollKnifeNE2().getCompletedStatus()){
                briefDescription += "Sitting on top of the " + this.chair.quote() + " is a " + this.dollPlacementRE.getDoll().quote() + " with black button eyes. It wasn't there before.\n";
            }
        }
        //In the event that the second Creepy Doll event has been Completed.
        return intro + briefDescription + metalVents + exits;
    }

    public String examine(){
        String intro = "You examine the " + this.quote() + " closer. You notice that The floorboards are starting to rot.\n";
        String roomItems = "The room is quite empty except for the small " + this.chair.quote() + " that is sitting against the Western wall.\n";
        String feelingAboutRoom = "Something feels off about the room. You almost feel like you are being watched. You want to get out of here as soon as possible.";
        for(Item item : this.getRoomItems()){
            //The Doll is not added to the room description if the DollKnife event has occurred.
            if(item instanceof CreepyDoll && !this.dollPlacementRE.getDoll().getDollKnifeNE2().getCompletedStatus()){
                roomItems = "There is now a " + this.dollPlacementRE.getDoll().quote() + " sitting on the " + this.chair.quote() + ". it wasn't there before.\n";
                feelingAboutRoom = "You are starting to get the impression that this house might be haunted. It would be best to get out of here as soon as possible.";
            }
        }
        return intro + roomItems + feelingAboutRoom;
    }

    public String lookNorth(){
        return bloodyMessage.look();
    }
}
