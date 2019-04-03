import java.util.ArrayList;

public class DustyRoom extends Room {
    //STATES
    private Table table;
    private Letter letter;
    private Chest chest;
    private Door eastDoor;
    private Door southDoor;

    //CONSTRUCTORS
    public DustyRoom(String name, Player player1){
        super(name, player1);
    }

    public DustyRoom(String name, Player player1, Table table, Letter letter, Chest chest){
        super(name, player1);
        //Add items
        this.table = table;
        this.addRoomItem(table);
        this.letter = letter;
        //Add ItemContainers
        this.chest = chest;
        this.addRoomItem(chest);
    }


    //METHODS

    public String attemptNorth(){
        return "There is no exit to the North. Only a " + this.chest.quote() + " that rests against the grimy wall.";
    }


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

    public String attemptSouth(){
        //If there is no exit to the South or no door assigned to the South
        if(this.getSouthDoor() == null){
            return "You can't go that way. There is a wall blocking your path.";
        }
        //If the door is Locked
        else if(this.getSouthDoor().getLockedStatus()){
            return "You attempt to open the " + this.getSouthDoor().quote() + " to the South but it is locked.";
        }
        //If the door is unlocked
        else{
            String transition = "You proceed South through the " + this.getSouthDoor().quote() + ".";
            String changeLocation = getPlayer().changeLocation(getSouthExit());
            return roomTransitionMsg(transition, changeLocation);
        }
    }

    //Returns the response if the player attempts to go West.
    public String attemptWest(){
        return "You can't go that way. There is a wall blocking your path.";
    }

    //Room description changes based on whether the letter has been taken by the player or not.
    public String look(){
        //If the Letter is on the Table.
        String description = "";
        if(this.table.hasLetter()){
            description = "You are standing in the middle of a " + this.quote() + " that is empty except for a " + this.table.quote() + " in the center of the room, and a " + this.chest.quote() + " touching the north wall of the room.\nThere is a " + this.letter.quote() + " resting on top of the " + this.table.quote()+".\nThere is a " + getEastDoor().quote() + " to the East, and a " + getSouthDoor().quote() + " to the South.";
        }
        else{
            description = "You are standing in the middle of a " + this.quote() + " that is empty except for a \"Wooden Table\" in the center of the room, and a \"Wooden Chest\" touching the north wall of the room.\n" + "There is a " + getEastDoor().quote() + " to the East, and a " + getSouthDoor().quote() + " to the South.";
        }
        if(getDollKnifeNE2() != null && getDollKnifeNE2().getCompletedStatus()){
            description += "\nYou notice that the room looks slightly different from when you first entered it.";
        }
        return description;
    }

    public String examine(){
        return "You examine the " + this.quote() + " closer. The dust on the floor, and the grime on the walls indicate that no one has been here in quite some time.";
    }

    //Returns the Table
    public Table getTable(){
        return this.table;
    }

    public DollKnifeNE2 getDollKnifeNE2(){
        ArrayList<NPC> NpcList = getPlayer().getEnvironment().getNpcList();
        CreepyDoll creepyDoll = null;
        DollKnifeNE2 dollKnifeNE2 = null;
        for(NPC npc : NpcList){
            if(npc instanceof CreepyDoll){
                creepyDoll = (CreepyDoll) npc;
            }
        }
        dollKnifeNE2 = (creepyDoll != null) ? creepyDoll.getDollKnifeNE2() : null;
        return dollKnifeNE2;
    }
}
