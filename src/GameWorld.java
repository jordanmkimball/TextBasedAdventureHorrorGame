public class GameWorld {
    private Player player1;
    private Environment environment;
    private Room startLocation;

    //CONSTRUCTOR
    public GameWorld(Player player1){
        this.player1 = player1;
        //Then we create the Environment
        this.environment = new Environment(player1);

        //Add the environment to the player so we can access it from all objects
        player1.setEnvironment(environment);

        //Then we create relevant events that may influence items in the first room.
        DollLetterNE1 dollLetterNE1 = new DollLetterNE1(player1, 2);

        //Now we create all of the items that will go in the first room
        Letter letter = new Letter("Letter", player1, dollLetterNE1);
        Buttons buttons = new Buttons("Buttons", player1);
        Table table = new Table("Wooden Table", player1, letter, buttons);
        Door blueDoor = new Door("Blue Door", player1, true);
        Door redDoor = new Door("Red Door", player1, true); //TO-DO: change to true after testing.
        //ItemContainers and their Items
        Key blueKey = new Key("Blue Key", player1);
        Chest dustyChest = new Chest("Wooden Chest", player1, false, false, blueKey);
        blueDoor.setAcceptItem(blueKey);
        //Add Item to ItemContainer
        dustyChest.addStoredItem(blueKey);

        //Now we create the first room
        DustyRoom dustyRoom = new DustyRoom("Dusty Room", player1, table, letter, dustyChest);
        dustyRoom.setEastDoor(blueDoor);
        dustyRoom.setSouthDoor(redDoor);


        //Then we create the Items/Events for the next Room we will create
        BloodyMessage bloodyMessage = new BloodyMessage("disturbing message", player1);
        Chair blackChair = new Chair("Black Chair", player1);
        MetalVent metalVent = new MetalVent("Metal Vent", player1);
        //Creating Events and MultipleChoice related to the Creepy Doll NPC
        LightsOutChoices lightsOutChoices = new LightsOutChoices(player1);
        DollKnifeNE2 dollKnifeNE2 = new DollKnifeNE2(0, player1);
        DollGiggleNE3 dollGiggleNE3 = new DollGiggleNE3(1, player1, dollKnifeNE2);
        CreepyDoll creepyDoll = new CreepyDoll("Creepy Doll", player1, 0, dollLetterNE1, dollKnifeNE2, dollGiggleNE3);
        DollPlacementRE dollPlacementRE = new DollPlacementRE(0, player1, creepyDoll);


        //We create the next room, and attach its doors.
        BloodyWallRoom darkRoom = new BloodyWallRoom("Dark Room", player1, dollPlacementRE, bloodyMessage, blackChair, metalVent);
        darkRoom.setWestDoor(blueDoor);

        //Connect the two rooms
        dustyRoom.setEastExit(darkRoom);
        darkRoom.setWestExit(dustyRoom);

        //We create the items that will go in ItemContainers for the Third Room (Kitchen)
        SmallMeat smallMeat = new SmallMeat("Piece of Meat", player1);
        MouseTrap mouseTrap = new MouseTrap("Mouse Trap", player1, smallMeat);
        StrangeMeat strangeMeat = new StrangeMeat("Slab of Meat", player1, smallMeat);
        //Then we create the ItemContainers and put appropriate Items inside of them
        Cupboards cupboards = new Cupboards("Cupboards", player1, false, false, mouseTrap);
        Refrigerator fridge = new Refrigerator("Fridge", player1, false, false, strangeMeat);
        //Then we create the standalone Items that will go in the Kitchen
        Counter counter = new Counter("Counter", player1);
        Knife knife = new Knife("Knife", player1);
        MouseHole mouseHole = new MouseHole("Small Hole", player1, mouseTrap);
        Key yellowKey = new Key("Yellow Key", player1);
        Door yellowDoor = new Door("Yellow Door", player1, true, yellowKey);
        //We add appropriate events to the Kitchen
        KitchenLightsOutRE1 kitchenLightsOutRE1 = new KitchenLightsOutRE1(3, player1, lightsOutChoices);


        //We create the new room (Kitchen)
        Kitchen kitchen = new Kitchen("Kitchen", player1, counter, cupboards, knife, fridge, mouseHole, kitchenLightsOutRE1);
        kitchen.setNorthDoor(redDoor);
        kitchen.setEastDoor(yellowDoor);
        //Events an Items associated with Mouse NPC
        MouseDeathNE1 mouseDeathNE1 = new MouseDeathNE1(3, player1, mouseTrap, yellowKey);
        //We create a Mouse NPC that will go in the room.
        Mouse mouse = new Mouse("Brown Mouse", player1, 4, kitchen, mouseHole, mouseDeathNE1);

        //We connect it to the other rooms
        kitchen.setNorthExit(dustyRoom);
        dustyRoom.setSouthExit(kitchen);

        //We create all the items that will go into the final "Empty Room"
        FlatMan flatMan = new FlatMan("Flat Man", player1, 5);
        FlatManPlacementRE flatManPlacementRE = new FlatManPlacementRE(0, player1, metalVent, flatMan);
        //We create the "Empty Room"
        EmptyRoom emptyRoom = new EmptyRoom("Final Room", player1, metalVent, flatManPlacementRE);
        emptyRoom.setWestDoor(yellowDoor);


        //We connect it to other rooms
        kitchen.setEastExit(emptyRoom);
        emptyRoom.setWestExit(kitchen);

        //Finally we add the player to the First Room in the house.
        player1.changeLocation(dustyRoom);
        this.startLocation = dustyRoom;

    }

    //METHODS

    //Returns the value for player1
    public Player getPlayer(){
        return this.player1;
    }

    //Returns the value for the Environment
    public Environment getEnvironment(){
        return this.environment;
    }

    //Returns the value for the Start Location
    public Room getStartLocation(){
        return this.startLocation;
    }


}
