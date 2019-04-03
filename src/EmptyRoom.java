
public class EmptyRoom extends Room{
    private MetalVent metalVent;
    private FlatManPlacementRE flatManPlacementRE;

    //CONSTRUCTOR
    public EmptyRoom(String name, Player player1, MetalVent metalVent, FlatManPlacementRE flatManPlacementRE){
        super(name, player1);
        this.metalVent = metalVent;
        this.addRoomItem(metalVent);
        this.flatManPlacementRE = flatManPlacementRE;
        this.addGameEvent(flatManPlacementRE);
    }

    public String look(){
        String intro = getRoomIntro();
        String description = "It is a mostly empty room with hardwood floors, and a large " + this.metalVent.quote() + " located high up on the Northern Wall of the room.\n";
        String npcRoomLevelDescription = this.getNpcDescriptions();
        String exits = getRoomExits();
        return intro + description + npcRoomLevelDescription + exits;
    }

    public String examine(){
        String intro = "You investigate the " + this.quote() + " further.\n";
        String feelings = "The room is quite empty, but it is well lit. The floors look like they have been vacuumed recently.\n";
        String npcRoomLevelDescription = this.getNpcDescriptions();
        return intro + feelings + npcRoomLevelDescription;
    }
}
