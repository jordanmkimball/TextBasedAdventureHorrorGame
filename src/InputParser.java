//TO-DO: Consider merging the roomContainer and roomItems lists into a single list, then editing the handler methods in input parser, so they work with this new merge.
import java.util.ArrayList;

public class InputParser {
    //METHODS

    //Handles all player inputs
    public String parseInput(String input, Player player1) {
        input = input.trim().toLowerCase();
        String[] inputArr = input.split(" ");
        String firstWord = inputArr[0].toLowerCase();
        String printToPlayer = "";
        switch (firstWord) {
            case "go":
            case "move":
            case "walk":
            case "run":
            case "sprint":
            case "jog":
                printToPlayer = goHandler(input, inputArr, player1);
                break;
            case "north":
            case "n":
                printToPlayer = player1.goNorth();
                break;
            case "east":
            case "e":
                printToPlayer = player1.goEast();
                break;
            case "south":
            case "s":
                printToPlayer = player1.goSouth();
                break;
            case "west":
            case "w":
                printToPlayer = player1.goWest();
                break;
            case "\\exit":
                printToPlayer = "Game Ended";
                player1.setContinueGame(false);
                break;
            case "inventory":
            case "i":
                printToPlayer = player1.printInventory();
                break;
            case "look":
            case "glance":
                printToPlayer = lookHandler(input, inputArr, player1);
                break;
            case "examine":
            case "investigate":
            case "inspect":
                printToPlayer = examineHandler(input, inputArr, player1);
                break;
            case "take":
            case "get":
            case "pick":
            case "obtain":
            case "acquire":
                printToPlayer = takeHandler(input, player1);
                break;
            case "use":
                printToPlayer = useHandler(input, inputArr, player1);
                break;
            case "open":
                printToPlayer = openHandler(input, inputArr, player1);
                break;
            case "close":
                printToPlayer = closeHandler(input, inputArr, player1);
                break;
            case "talk": case "chat": case "speak": case "converse":
                printToPlayer = talkingHandler(input, inputArr, player1);
                break;
            case "read":
                printToPlayer = readHandler(input, inputArr, player1);
                break;
            case "eat": case "consume": case "devour":
                printToPlayer = eatHandler(input, inputArr, player1);
                break;
            case "wait":
                printToPlayer = player1.waitTurn();
                break;
            case "keywords":
            case "keyword":
            case "'keywords'":
            case "commands":
                printToPlayer = keyWordHandler();
                break;
            case "help":
            case "'help'":
                printToPlayer = helpHandler();
                break;
            default:
                printToPlayer = "All commands must start with a keyword:\nEnter 'keywords' or 'help' to learn more";
        }
        return printToPlayer;
    }


    //Handles all inputs with the keyword 'go'
    private String goHandler(String input, String[] inputArr, Player player1) {
        //Case where input is "Go" with no other words
        String printToPlayer = "";
        if (inputArr.length == 1) {
            printToPlayer = "I don't know where you are trying to go.";
        } else {
            //Handles go north, go to the east, go west.. ect.
            String direction = getContainsDirection(input);
            if (direction != null) {
                switch (direction) {
                    //If Go is immediately followed by a cardinal direction the player should go in that direction
                    case "north":
                        printToPlayer = player1.goNorth();
                        break;
                    case "east":
                        printToPlayer = player1.goEast();
                        break;
                    case "south":
                        printToPlayer = player1.goSouth();
                        break;
                    case "west":
                        printToPlayer = player1.goWest();
                        break;
                }
            }
            //Handles inputs like go to the dusty room, go dark room, ect...
            else {
                //Check to see if a connecting room was mentioned instead a direction
                Room connectedRoom = matchConnectedRoom(input, player1);
                String roomDirection = getRoomDirection(input, player1);
                if (connectedRoom != null && roomDirection != null) {
                    switch (roomDirection) {
                        case "north":
                            printToPlayer = player1.goNorth();
                            break;
                        case "east":
                            printToPlayer = player1.goEast();
                            break;
                        case "south":
                            printToPlayer = player1.goSouth();
                            break;
                        case "west":
                            printToPlayer = player1.goWest();
                            break;
                    }
                }
                //If the player tries to go to the room they are currently in.
                else {
                    Room currentRoom = matchThisRoom(input, player1);
                    if (currentRoom != null) {
                        printToPlayer = "You are already in " + currentRoom.quote() + ".";
                    }
                    //If the input doesn't know where the player wants to go.
                    else {
                        printToPlayer = "I am not sure where you are trying to go.";
                    }

                }
            }
        }
        return printToPlayer;
    }

    //Handles all inputs with the keyword 'look'
    private String lookHandler(String input, String[] inputArr, Player player1) {
        String printToPlayer = "";
        //Sees if they were trying to look at an available interactive
        Interactive match = matchAllInteractive(input, inputArr, player1);
        if (match != null) {
            printToPlayer = player1.look(match);
        } else {
            //Sees if they are were trying to look in a direction
            String direction = getContainsDirection(input);
            if (direction != null) {
                switch (direction) {
                    case "north":
                        printToPlayer = player1.lookNorth();
                        break;
                    case "east":
                        printToPlayer = player1.lookEast();
                        break;
                    case "south":
                        printToPlayer = player1.lookSouth();
                        break;
                    case "west":
                        printToPlayer = player1.lookWest();
                        break;
                }
            } else {
                if (connectedRoomLookHandler(input, player1).isEmpty()) {
                    printToPlayer = "I am not sure what you want to look at.";
                }
            }
        }
        return printToPlayer;
    }

    //Handles all inputs with the keyword 'examine'
    private String examineHandler(String input, String[] inputArr, Player player1) {
        String printToPlayer = "";
        Interactive match = matchAllInteractive(input, inputArr, player1);
        if (match != null) {
            printToPlayer = player1.examineInteractive(match);
        } else {
            //Sees if they are were trying to examine in a direction (same as if they were trying to look in a direction)
            String direction = getContainsDirection(input);
            if (direction != null) {
                switch (direction) {
                    case "north":
                        printToPlayer = player1.lookNorth();
                        break;
                    case "east":
                        printToPlayer = player1.lookEast();
                        break;
                    case "south":
                        printToPlayer = player1.lookSouth();
                        break;
                    case "west":
                        printToPlayer = player1.lookWest();
                        break;
                }
            } else {
                if (connectedRoomLookHandler(input, player1).isEmpty()) {
                    printToPlayer = "I am not sure what you want to examine.";
                }
            }
        }
        return printToPlayer;
    }


    //Handles inputs that contain the name of a connected room. Returns the text to PrintToPlayer if successful. Empty otherwise.
    private String connectedRoomLookHandler(String input, Player player1) {
        String PrintToPlayer = "";
        Room connectedRoom = matchConnectedRoom(input, player1);
        if (connectedRoom == null) {
            return "";
        }
        String direction = getRoomDirection(input, player1);
        if (direction != null) {
            switch (direction) {
                case "north":
                    PrintToPlayer = player1.lookNorth();
                    break;
                case "east":
                    PrintToPlayer = player1.lookEast();
                    break;
                case "south":
                    PrintToPlayer = player1.lookSouth();
                    break;
                case "west":
                    PrintToPlayer = player1.lookWest();
                    break;
            }
        }
        return PrintToPlayer;
    }

    //Handles all inputs with the Keyword 'take'
    private String takeHandler(String input, Player player1){
        //Check if input contains the name of an item in the room
        String printToPlayer = "";
        Item roomItemMatch = matchRoomItemsPlus(input, player1);
        ItemContainer matchedItemsContainer = getMatchedItemsContainer(input, player1.getLocation().getRoomItems());
        //If the roomItem has an associated roomContainer. Use the take method that uses both the Item and its container.
        if(roomItemMatch != null && matchedItemsContainer != null){
            printToPlayer = player1.takeItem(matchedItemsContainer, roomItemMatch);
        }
        //The roomItem existed, but had no associated ItemContainer. We use the method that uses just the roomItem.
        else if(roomItemMatch != null){
            printToPlayer = player1.takeItem(roomItemMatch);
        }
        //We print a custom fail message.
        else{
            printToPlayer = getTakeFailMsg(input, player1);
        }
        return printToPlayer;
    }

    //Prints a Custom Take failure message
    private String getTakeFailMsg(String input, Player player1) {
        //Check to see if Player tried to take the Room
        Room room = matchThisRoom(input, player1);
        if (room != null) {
            return "You can't take a room.";
        }
        //Check to see if Player tried to take an item from their inventory
        Item inventoryItem = matchInventoryItemsPlus(input, player1);
        if (inventoryItem != null) {
            return inventoryItem.quote() + " is already in your inventory.";
        }
        return "I'm not sure what you are trying to take.";
    }

    //Handles the responses for all inputs that start with the 'use' keyword
    private String useHandler(String input, String[] inputArr, Player player1) {
        String printToPlayer = "";
        //This first block of code handles "Use inventoryItem on otherItem" type strings
        if (input.contains("on")) {
            int splitIndex = input.lastIndexOf("on");
            String interactive1 = input.substring(0, splitIndex - 1); //The String before the 'on' keyword
            String interactive2 = input.substring(splitIndex + 2); //The String after the 'on' keyword
            Item inventoryItem = matchJustInventoryItems(interactive1, player1);
            if (inventoryItem == null) {
                printToPlayer = getUseErrMsg1(interactive1, player1);
            } else {
                Item otherItem = matchAllItems(interactive2, player1);
                if (otherItem != null) {
                    printToPlayer = player1.use(inventoryItem, otherItem);
                } else {
                    //The custom message if there was a problem identifying the interactive2 string
                    printToPlayer = getUseErr2Msg(interactive2, inventoryItem, player1);
                }
            }
        } else {
            //This is where an easy Use X: Syntax could be implemented
            printToPlayer = "'Use' command must use syntax:\nUse X on Y\nWhere X is an item in your inventory, and Y is any other item";
        }
        return printToPlayer;
    }

    //Returns an custom error message if the first interactive match is not in proper form. 'Use' keyword
    private String getUseErrMsg1(String interactive1, Player player1) {
        //This method doesn't get called unless interactive1 wasn't an inventoryItem
        //Check if the player tried to use a room Item not in inventory
        Item roomItem = matchRoomItemsPlus(interactive1, player1);
        if (roomItem != null) {
            return "You must have " + roomItem.quote() + " in your inventory to use it on another item.";
        }
        //Check if player tried to use the Room
        Room room = matchThisRoom(interactive1, player1);
        if (room != null) {
            return "You can't use rooms on other objects.";
        }
        //Check if player tried to use an Item that is in an ItemContainer in their inventory
        Item inventoryContainerItem = matchInventoryContainersItems(interactive1, player1);
        if(inventoryContainerItem != null){
            return "You must have " + inventoryContainerItem.quote() + " in your inventory to use it on another item.";
        }
        else {
            return "I'm not sure what Item you are trying to use.";
        }
    }

    //Returns a custom error message if the second interactive match is not in proper form 'use' keyword
    private String getUseErr2Msg(String interactive2, Item inventoryItem, Player player1) {
        Room room = matchThisRoom(interactive2, player1);
        if (room != null) {
            return "You can't use the " + inventoryItem.quote() + " on a room.";
        } else {
            return "I don't understand what you are trying to use " + inventoryItem.quote() + " on.";
        }
    }


    //Handles all inputs with the keyword 'Open'
    private String openHandler(String input, String[] inputArr, Player player1){
        String printToPlayer = "";
        Lockable lockable = null;
        Interactive interactive = matchAllInteractive(input, inputArr, player1);
        if(interactive != null){
            //Case where interactive match was the name of a Lockable.
            if(interactive instanceof Lockable){
                lockable = (Lockable) interactive;
                printToPlayer = player1.open(lockable);
            }
            //Case where interactive match was not the name of a Lockable.
            else{
                printToPlayer = interactive.quote() + " is not something that can be opened.";
            }
        }
        //Case where there was no interactive match
        else{
            printToPlayer = "I'm not sure what you are trying to open.";
        }
        return printToPlayer;
    }


    //Handles all inputs that start with the Keyword 'Close'
    private String closeHandler(String input, String[] inputArr, Player player1){
        String printToPlayer;
        Lockable lockable;
        Interactive interactive = matchAllInteractive(input, inputArr, player1);
        if(interactive != null){
            //Case where interactive match was the name of a Lockable.
            if(interactive instanceof Lockable){
                lockable = (Lockable) interactive;
                printToPlayer = player1.close(lockable);
            }
            //Case where interactive match was not the name of a Lockable.
            else{
                printToPlayer = interactive.quote() + " is not something that can be closed.";
            }
        }
        //Case where there was no interactive match
        else{
            printToPlayer = "I'm not sure what you are trying to close.";
        }
        return printToPlayer;
    }

    //Handles all inputs that start with the Keyword 'Talk'
    private String talkingHandler(String input, String[] inputArr, Player player1){
        String printToPlayer;
        Talking talking;
        Interactive interactive = matchAllInteractive(input, inputArr, player1);
        if(interactive != null){
            if(interactive instanceof Talking){
                talking = (Talking) interactive;
                printToPlayer = player1.talkTo(talking);
            }
            else{
                printToPlayer = "The " + interactive.quote() + " is not something that can be talked to.";
            }
        }
        else{
            printToPlayer = "I'm not sure what you are trying to talk to.";
        }
        return printToPlayer;
    }

    //Handles all inputs that start with the Keyword 'Read'
    private String readHandler(String input, String[] inputArr, Player player1){
        String printToPlayer;
        ReadableItem readableItem;
        Interactive interactive = matchAllInteractive(input, inputArr, player1);
        if(interactive != null){
            if(interactive instanceof ReadableItem){
                readableItem = (ReadableItem) interactive;
                printToPlayer = player1.read(readableItem);
            }
            else{
                printToPlayer = "The " + interactive.quote() + " is not something that can be read.";
            }
        }
        else{
            printToPlayer = "I'm not sure what you are trying to read.";
        }
        return printToPlayer;
    }

    //Handles all inputs that start with the Keyword 'Eat'
    private String eatHandler(String input, String[] inputArr, Player player1){
        String printToPlayer;
        Edible edible;
        Interactive interactive = matchAllInteractive(input, inputArr, player1);
        if(interactive != null){
            if(interactive instanceof Edible){
                edible = (Edible) interactive;
                printToPlayer = player1.eat(edible);
            }
            else{
                printToPlayer = "The " + interactive.quote() + " is not something that can be eaten.";
            }
        }
        else{
            printToPlayer = "I'm not sure what you are trying to attemptEat.";
        }
        return printToPlayer;
    }

    public String keyWordHandler(){
        String printToPlayer = "List of Commands\n";
        printToPlayer += "Keywords: go, North (n), South (s), East (e), West (w), Inventory (i), look, examine, take, 'use X on Y', open, close, talk, wait, read, eat, help, keywords, \\exit\n";
        printToPlayer += "Many of the common synonyms of keywords are also recognized. Type 'help' for a tutorial on how to use keywords, and play the game.";
        return printToPlayer;
    }

    //Handles any input that starts with help. Prints quick tips on how to play the game as well as all keywords and how to use them.
    private String helpHandler(){
        String intro = "______________________________________________________________________________________________________________________\n";
        String basicInformation = "GENERAL INFORMATION:\n\n";
        String tip1 = "1. All player inputs must start with a keyword.\n\n";
        String tip2 = "2. All objects that can be interacted with in a room are in double \"quotes\".\n\n";
        String tip3 = "3. When referencing an object you must type its name EXACTLY as it appears in the \"quotation marks\". For example if an object is named \"Wooden Table\".\ntyping 'examine wooden table' will be recognized by the text parser while 'examine table' will not be recognized.\n\n";
        String tip4 = "4. The text parser can only recognize one command at a time.\n\n";
        String tip5 = "5. The text parser is case insensitive: 'TAKE BLACK CHAIR' will be recognized as will 'TaKe blACK Chair'.\n\n";
        String tip6 = "6. It is ok to use filler words: 'look wooden table' will be recognized as will 'look at the glorious brown handcrafted wooden table that smells of rosemary'.\n\n";
        String tip7 = "7. Many common synonyms of keywords are also recognized. Recognized synonyms are listed in parenthesis next to the keyword below.\n";
        String divider = "----------------------------------------------------------------------------------------------------------------------\n";
        String keywordsIntro = "KEYWORDS AND HOW TO USE THEM:\n\n";
        String north = "North (n): The player attempts to move to a room North of their current location.\n\n";
        String east = "East (e): The player attempts to move to a room East of their current location.\n\n";
        String south = "South (s): The player attempts to move to a room South of their current location.\n\n";
        String west = "West (w): The player attempts to move to a room West of their current location.\n\n";
        String go = "Go (move/walk/run/jog/sprint): Syntax = 'Go location'. The location can be one of the cardinal directions (North/East/South/West) or a discovered room that is connected\nto the current room the player is in.\n\n";
        String inventory = "Inventory (i): Prints a list of the items in the players inventory.\n\n";
        String look = "Look (glance): Syntax = 'Look object' or 'look room' or 'look' or 'look north/east/south/west'. Returns a brief description of the object that the player is looking at.\nIf 'look room' or 'look' is entered the player will get a description of their current location. If 'look north/east/south/west' is entered the player will look in the specified direction.\n\n";
        String examine = "Examine (investigate/inspect): Syntax = 'examine object' or 'examine room' or 'examine'. The player investigates the referenced object.\nMay include additional details about the object, as well as the players feelings about the object. When using 'examine room' or 'examine' the player examines the room they are currently in.\n\n";
        String take = "Take (get/pick up/obtain/acquire): Syntax = 'take object'. The player attempts to take an object.\n\n";
        String use = "Use: Syntax = 'Use X on Y' where X is an Item in the players inventory, and Y is any item in the current room or the players inventory.\nThe player attempts to use an inventory item on another item.\n\n";
        String open = "Open: Syntax = 'open object' or 'open door'. The player attempts to open an object that can be opened. If the object is a door, the player attempts to open the door and walk through it.\n\n";
        String close = "Close: Syntax = 'close object'. The player attempts to close an object that can be opened and closed.\n\n";
        String talk = "Talk (chat/converse/speak): 'Talk NPC' The player starts a conversation with an NPC.\n\n";
        String wait = "Wait: Syntax = 'wait'. The player waits for some time. Allows one in game turn to pass.\n\n";
        String read = "Read: Syntax = 'read object'. The player attempts to read something that can be read.\n\n";
        String eat = "Eat (consume/devour): Syntax = 'eat object'. The player attempts to eat an edible object. Just because an object is edible doesn't mean your character will necessarily eat it.\n\n";
        String help = "help: Prints a list of instructions on how to play the game, as well as a list of all commands, their synonyms, and what they do.\n\n";
        String keywords = "Keywords (keyword/commands): Returns a list of all game keywords.\n\n";
        String exit = "\\exit: exits the game. Progress will NOT be saved.";
        return intro + basicInformation + tip1 + tip2 + tip3 + tip4 + tip5 + tip6 + tip7 + divider + keywordsIntro + north + east + south + west + go + inventory + look + examine + take + use + open + close + talk + wait + read + eat + help + keywords + exit;
    }

    //Returns an Interactive if input contains the name of an interactive in inventory, the room, or items in open containers
    private Interactive matchAllInteractive(String input, String[] inputArr, Player player1) {
        //TO-DO: This is pretty specific to Look and Examine.
        if (inputArr.length == 1) {
            return player1.getLocation();
        }
        //Look to see if input contains the name of the current room
        Room thisRoom = matchThisRoom(input, player1);
        if (thisRoom != null) {
            return thisRoom;
        }
        //Check if input contains name of any items in the room.
        Item roomItemMatch = matchRoomItemsPlus(input, player1);
        if (roomItemMatch != null) {
            return roomItemMatch;
        }
        //Check if input contains name that matches any items in inventory
        Item inventoryMatch = matchInventoryItemsPlus(input, player1);
        if (inventoryMatch != null) {
            return inventoryMatch;
        }
        return null;
    }

    //Returns an item if the input contains a name that matches with an item available for interaction
    private Item matchAllItems(String input, Player player1) {
        //Check if input contains name of any items in the room.
        Item roomItemMatch = matchRoomItemsPlus(input, player1);
        if (roomItemMatch != null) {
            return roomItemMatch;
        }
        //Check if input contains name that matches any items in inventory or Items in ItemContainers in Inventory
        Item inventoryMatch = matchInventoryItemsPlus(input, player1);
        if (inventoryMatch != null) {
            return inventoryMatch;
        }
        return null;
    }


    //Searches input for name matches within the given list of Items
    //Also checks if Item is an open ItemContainer. If so it recursively calls itself to check the Items in that ItemContainer for matches
    private Item matchItemsFromList(String input, ArrayList<Item> itemListArray) {
        Item match = null;
        ItemContainer container;
        for (Item item : itemListArray) {
            if (input.contains(item.getName().toLowerCase())) {
                match = item;
                break;
            }
            //Checking if Item is an open ItemContainer to check for Items within that ItemContainer.
            else if (item instanceof ItemContainer) {
                container = (ItemContainer) item;
                if (container.getOpenStatus()) {
                    Item potentialMatch = matchItemsFromList(input, container.getStoredItems());
                    if (potentialMatch != null) {
                        match = potentialMatch;
                        break;
                    }
                }
            }
        }
        return match;
    }

    //Returns an Item if input contains the name of an Item that IS STORED IN AN ItemContainer within the given itemListArray
    private Item matchContainersItemsFromList(String input, ArrayList<Item> itemListArray) {
        Item match = null;
        ItemContainer container;
        for (Item item : itemListArray) {
            if (item instanceof ItemContainer) {
                container = (ItemContainer) item;
                if (container.getOpenStatus()) {
                    Item potentialMatch = matchItemsFromList(input, container.getStoredItems());
                    if (potentialMatch != null) {
                        match = potentialMatch;
                        break;
                    }
                }
            }
        }
        return match;
    }

    //Returns an Item if input contains its name AND the Item is INSIDE an ItemContainer that is in the players Inventory
    private Item matchInventoryContainersItems(String input, Player player1) {
        ArrayList<Item> inventory = player1.getInventory();
        return matchContainersItemsFromList(input, inventory);
    }

    //Returns an Item if input contains its name AND the Item is INSIDE an ItemContainer that is in the current Room
    private Item matchRoomContainersItems(String input, Player player1){
        ArrayList<Item> roomItems = player1.getLocation().getRoomItems();
        return matchContainersItemsFromList(input, roomItems);
    }

    //Returns the ItemContainer of a roomContainer Item that has been matched

    //Returns the ItemContainer of an Item that has been matched
    private ItemContainer getMatchedItemsContainer(String input, ArrayList<Item> itemListArray) {
        ItemContainer container;
        ItemContainer matchesContainer = null;
        for (Item item : itemListArray) {
            if (item instanceof ItemContainer) {
                container = (ItemContainer) item;
                if (container.getOpenStatus()) {
                    Item potentialMatch = matchItemsFromList(input, container.getStoredItems());
                    if (potentialMatch != null) {
                        matchesContainer = container;
                        break;
                    }
                }
            }
        }
        return matchesContainer;
    }

    //

    //Returns an Item if input contains the name of an inventory Item, or an Item inside an ItemContainer in inventory
    private Item matchInventoryItemsPlus(String input, Player player1) {
        ArrayList<Item> inventory = player1.getInventory();
        return matchItemsFromList(input, inventory);
    }

    //Returns an Item if it matches the name of an Inventory Item, but NOT Items inside of ItemContainers
    private Item matchJustInventoryItems(String input, Player player1) {
        ArrayList<Item> inventory = player1.getInventory();
        Item match = null;
        for (Item item : inventory) {
            if (input.contains(item.getName().toLowerCase())) {
                match = item;
            }
        }
        return match;
    }


    //Returns an Item if it matches the name of a Room Item. (Redundantly checks to see if Item is an ItemContainer.)
    //TO-DO: The ItemContainer check is redundant if I maintain the separate list for ItemContainers.
    private Item matchRoomItemsPlus(String input, Player player1) {
        ArrayList<Item> roomItems = player1.getLocation().getRoomItems();
        return matchItemsFromList(input, roomItems);
    }

    private Item matchJustRoomItems(String input, Player player1){
        ArrayList<Item> roomItems = player1.getLocation().getRoomItems();
        Item match = null;
        for(Item item : roomItems) {
            if(input.contains(item.getName().toLowerCase())){
                match = item;
            }
        }
        return match;
    }


    //Used to see if input contains a name that matches any itemContainer.name in the currentLocation Room
    private ItemContainer matchJustRoomContainers(String input, Player player1){
        ArrayList<Item> roomItems = player1.getLocation().getRoomItems();
        ItemContainer container = null;
        for(Item item : roomItems){
            //If item is both an instance of itemContainer, and a
            if(item instanceof ItemContainer && input.contains(item.getName().toLowerCase())){
                container = (ItemContainer) item;
            }
        }
        return container;
    }


    //If input contains the name of the currentLocation. Return the current location.
    private Room matchThisRoom(String input, Player player1) {
        Room thisRoom = null;
        if (input.contains(player1.getLocation().getName().toLowerCase())) {
            thisRoom = player1.getLocation();
        } else if(input.contains("room")){
            thisRoom = player1.getLocation();
        }
        return thisRoom;
    }

    //If input contains the name of a connected and discovered room. Return that room
    private Room matchConnectedRoom(String input, Player player1) {
        //If the given exit isn't null
        if (player1.getLocation().getNorthExit() != null) {
            //If input contains the exit name, and the room has been visited before.
            if (input.contains(player1.getLocation().getNorthExit().getName().toLowerCase()) && player1.getLocation().getNorthExit().getVisitNumber() > 0) {
                return player1.getLocation().getNorthExit();
            }
        }
        if (player1.getLocation().getEastExit() != null) {
            if (input.contains(player1.getLocation().getEastExit().getName().toLowerCase()) && player1.getLocation().getEastExit().getVisitNumber() > 0) {
                return player1.getLocation().getEastExit();
            }
        }
        if (player1.getLocation().getSouthExit() != null) {
            if (input.contains(player1.getLocation().getSouthExit().getName().toLowerCase()) && player1.getLocation().getSouthExit().getVisitNumber() > 0) {
                return player1.getLocation().getEastExit();
            }
        }
        if (player1.getLocation().getWestExit() != null) {
            if (input.contains(player1.getLocation().getWestExit().getName().toLowerCase()) && player1.getLocation().getWestExit().getVisitNumber() > 0) {
                return player1.getLocation().getWestExit();
            }
        }
        return null;
    }

    //If input contains the name of a connected door. Return that door.
    private Door matchConnectedDoor(String input, Player player1) {
        //If the given exit isn't null
        if (player1.getLocation().getNorthDoor() != null) {
            //If input contains the exit name, and the room has been visited before.
            if (input.contains(player1.getLocation().getNorthDoor().getName().toLowerCase())) {
                return player1.getLocation().getNorthDoor();
            }
        }
        if (player1.getLocation().getEastDoor() != null) {
            if (input.contains(player1.getLocation().getEastDoor().getName().toLowerCase())) {
                return player1.getLocation().getEastDoor();
            }
        }
        if (player1.getLocation().getSouthDoor() != null) {
            if (input.contains(player1.getLocation().getSouthDoor().getName().toLowerCase())) {
                return player1.getLocation().getSouthDoor();
            }
        }
        if (player1.getLocation().getWestDoor() != null) {
            if (input.contains(player1.getLocation().getWestDoor().getName().toLowerCase())) {
                return player1.getLocation().getWestDoor();
            }
        }
        return null;
    }

    //Get the direction that you need to go to reach a connectedRoom
    private String getRoomDirection(String input, Player player1) {
        //If the given exit isn't null
        if (player1.getLocation().getNorthExit() != null) {
            //If input contains the exit name, and the room has been visited before.
            if (input.contains(player1.getLocation().getNorthExit().getName().toLowerCase()) && player1.getLocation().getNorthExit().getVisitNumber() > 0) {
                return "north";
            }
        }
        if (player1.getLocation().getEastExit() != null) {
            if (input.contains(player1.getLocation().getEastExit().getName().toLowerCase()) && player1.getLocation().getEastExit().getVisitNumber() > 0) {
                return "east";
            }
        }
        if (player1.getLocation().getSouthExit() != null) {
            if (input.contains(player1.getLocation().getSouthExit().getName().toLowerCase()) && player1.getLocation().getSouthExit().getVisitNumber() > 0) {
                return "south";
            }
        }
        if (player1.getLocation().getWestExit() != null) {
            if (input.contains(player1.getLocation().getWestExit().getName().toLowerCase()) && player1.getLocation().getWestExit().getVisitNumber() > 0) {
                return "west";
            }
        }
        return null;
    }



    //Takes an input string and checks if it contains a cardinal direction in the string
    private String getContainsDirection(String input) {
        if (input.contains("north")) {
            return "north";
        } else if (input.contains("east")) {
            return "east";
        } else if (input.contains("south")) {
            return "south";
        } else if (input.contains("west")) {
            return "west";
        } else {
            return null;
        }
    }


    //OPTION PARSER
    public String parseOptions(String input){
        input = input.toLowerCase().trim();
        String selection;
        switch(input){
            case "a" : case "a)" : case "a)." : case "1":
                selection = "a";
                break;
            case "b" : case "b)" : case "b)." : case "2":
                selection = "b";
                break;
            case "c" : case "c)" : case "c)." : case "3":
                selection = "c";
                break;
            case "d" : case "d)" : case "d)." : case "4":
                selection = "d";
                break;
            case "help":
                selection = "help";
                break;
            case "//exit":
                selection = "exit";
                break;
            default:
                selection = "null";
                break;
        }
        return selection;
    }
}