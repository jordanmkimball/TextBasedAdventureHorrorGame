public class Kitchen extends Room {
    private Counter counter;
    private Cupboards cupboards;
    private Knife knife;
    private Refrigerator refrigerator;
    private MouseHole mouseHole;
    private KitchenLightsOutRE1 kitchenLightsOutRE1;

    //CONSTRUCTOR
    public Kitchen(String name, Player player1, Counter counter, Cupboards cupboards, Knife knife, Refrigerator refrigerator, MouseHole mouseHole, KitchenLightsOutRE1 kitchenLightsOutRE1){
        super(name, player1);
        this.counter = counter;
        this.addRoomItem(counter);
        this.cupboards = cupboards;
        this.addRoomItem(cupboards);
        this.knife = knife;
        this.addRoomItem(knife);
        this.refrigerator = refrigerator;
        this.addRoomItem(refrigerator);
        this.mouseHole = mouseHole;
        this.addRoomItem(mouseHole);
        //Add Room events to Room.
        this.kitchenLightsOutRE1 = kitchenLightsOutRE1;
        this.addGameEvent(kitchenLightsOutRE1);
    }

    public String look(){
        String intro = this.getRoomIntro();
        //String description = "In this room there is a " + this.refrigerator.quote() + ", some " + this.cupboards.quote() + " along the South wall, and a long " + this.counter.quote() + " in the center of the room.\n";
        String description = "There is a long " + this.counter.quote() + " in the center of the room, a " + this.refrigerator.quote() + " in the Southeast corner of the room, and some " + this.cupboards.quote() + " along the South wall of the room.\n";
        String knifeString = "";
        String smallHole = "There is a " + this.mouseHole.quote() + " on the Eastern Wall.\n";
        for(Item item : this.getRoomItems()){
            if(item instanceof Knife){
                knifeString =  "There is a " + this.knife.quote() + " resting on top of the " + this.counter.quote() + ".\n";
            }
        }
        String npcDescriptions = getNpcDescriptions();
        String mouseTrapDescribed = this.mouseHole.getMouseTrap().getRoomLevelDescription();
        String exits = this.getRoomExits();
        return intro + description + knifeString + smallHole + mouseTrapDescribed + npcDescriptions + exits;
    }

    public String examine(){
        return "The walls are grimy, and there is a lingering scent of rotting food in the air.";
    }

    //Custom response for if the player looks North in the Kitchen.
    public String lookNorth(){
        String response = "";
        if(this.getNorthDoor() != null && this.getNorthExit() != null){
            //northDoor and northExit both exist, but the room has never been visited
            if(this.getNorthExit().getVisitNumber() == 0){
                response = "To the North you see a " + getNorthDoor().quote() + ". You are not sure where it leads.\n";
                response += "You notice that there is a light switch to the left of the " + this.getNorthDoor().quote() + ".";
            }
            //northDoor and northExit both exist. We know the name of the room beyond the door.
            else{
                response = "To the North you see a " + getNorthDoor().quote() + ". It leads to the " + this.getNorthExit().quote() + ".\n";
                response += "You notice that there is a light switch to the left of the " + this.getNorthDoor().quote() + ".";
            }
        }
        return response;
    }

    //Custom response for if the player looks West in the Kitchen
    public String lookWest(){
        return "There is a wall to the West in the room. You notice that there is an awkwardly placed light switch in the center of that wall.\n";
    }

    //Returns KitchenLightsOutRE1
    public KitchenLightsOutRE1 getKitchenLightsOutRE1(){
        return this.kitchenLightsOutRE1;
    }
}
