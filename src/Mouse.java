public class Mouse extends NPC implements Edible{
    private boolean insideHole;
    private MouseHole smallHole;
    private Room kitchen;
    private MouseDeathNE1 mouseDeathNE1;

    //CONSTRUCTOR
    public Mouse(String name, Player player1, int priority, Room kitchen, MouseHole smallHole, MouseDeathNE1 mouseDeathNE1){
        super(name, player1, priority);
        this.kitchen = kitchen;
        this.changeLocation(kitchen);
        //Mouse Starts out as an Active NPC
        this.setActiveStatus(true);
        //Mouse is added to Environment NPCs
        this.getPlayer().getEnvironment().addNpc(this);
        //Mouse does not start inside the small hole
        this.insideHole = false;
        this.smallHole = smallHole;
        this.smallHole.setMouse(this);
        //Adding the mouseDeath event.
        this.mouseDeathNE1 = mouseDeathNE1;
        this.addNpcEvent(mouseDeathNE1);
        mouseDeathNE1.setMouse(this);
    }

    //BEHAVIOR

    public boolean isInsideHole(){
        return this.insideHole;
    }

    public boolean getActionStatus(){
        boolean actionOccurs = false;
        //The Mouse will move on its own every three turns if the player is in the Kitchen
        if(getPlayer().getLocation() instanceof Kitchen){
            if(getPlayer().getTurnCounter() % 3 == 0){
                actionOccurs = true;
            }
        }
        //The Mouse will move on its own every 2 turns if the player is NOT in the Kitchen
        else{
            if(getPlayer().getTurnCounter() % 2 == 0){
                actionOccurs = true;
            }
        }
        //If the Mouse is in the same location as the player it will automatically have an action.
        if(this.getPlayer().getLocation().equals(this.getLocation())){
            actionOccurs = true;
        }
        return actionOccurs;
    }


    //The rat has somewhat complex movement behavior that depends on where the player is and what the players last action was.
    public String performAction(){
        String action = "";
        boolean playerInSameRoom = this.getPlayer().getLocation().equals(this.getLocation());
        boolean playerJustEnteredRoom = this.getPlayer().getLastAction().equals("south");
        boolean ratInKitchen = this.getLocation().equals(kitchen);
        //Early exit if the player just entered the Room.
        if(playerJustEnteredRoom){
            return action;
        }
        //If the Mouse and the Player are in the same room, and the Mouse isn't inside the SmallHole.
        if(playerInSameRoom && !this.insideHole){
            //Then rats going to attempt to flee to the Room to the East. (The EmptyRoom)
            if(this.goEastIB()){
                action = "The " + this.quote() + " sees you and flees through the " + this.smallHole.quote() + ". As it runs away you think you see a flash of something shiny looped around its tail.\n";
                action += "The " + this.quote() + " is no longer in the " + getPlayer().getLocation().quote() + ".";
            }
        }
        //If the Mouse is not in the Kitchen.
        else if(!ratInKitchen){
            //Go West into the Kitchen.
            if(this.goWestIB()){
                this.insideHole = true;
            }
        }
        //The Mouse is in the Kitchen and is in his hole.
        else if(insideHole){
            action = ratInKitchenAndInHoleHandler(playerInSameRoom);
        }
        return action;
    }

    public String look(){
        String description;
        String keyDescription = "";
        if(this.getAlive()){
            //The Mouse is alive and inside his hiding hole.
            if(this.insideHole){
                description = "You can make out the yellow eyes of a " + this.quote() + " peaking out from the " + this.smallHole.quote() + ".";
            }
            else{
                description = "A large " + this.quote() + ". You see something yellow and shiny looped around its tail.";
            }
        }
        //Mouse is dead
        else{
            description = "The dead body of a large " + this.quote() + ". Its neck was broken by the " + this.mouseDeathNE1.getMouseTrap().quote() + ".";
            if(getYellowKey() != null){
                keyDescription = "\nThere is a " + this.getYellowKey().quote() + " looped around its tail.";
            }
        }
        return description + keyDescription;
    }

    public String examine(){
        String intro = examineIntro();
        String description;
        String keyDescription = "";
        String feelings = "";
        if(this.getAlive()){
            //The Mouse is alive and inside his hiding hole.
            if(this.insideHole){
                description = "You can just barely see the yellow eyes of the " + this.quote() + " peaking out from the " + this.smallHole.quote() + ".\n";
                feelings = "It looks like it is peaking to see if you are still in the room.";
            }
            else{
                description = "It is hard to get a good look it. You think you see something yellow and shiny looped around its tail.";
            }
        }
        else{
            description = "It is dead. It looks like the " + this.mouseDeathNE1.getMouseTrap().quote() + " did its job and broke its neck.";
            if(this.getYellowKey() != null){
                keyDescription = "\nThere appears to be a " + this.getYellowKey().quote() + " looped around its tail. You should probably take it.";
            }
        }
        return intro + description + keyDescription + feelings;
    }

    //If the player attempts to Eat the Mouse
    public String attemptEat(){
        String response = "";
        if(this.getAlive()){
            response = "You are not quite hungry enough to consider eating a live " + this.quote() + ".\n\n";
            response += "The " + this.quote() + " sees you looking at it and flees through the " + this.smallHole.quote() + ".";
            this.goEastIB(); //
        }
        else{
            response = "You would have to be pretty hungry to consider eating a dead mouse.";
        }
        return response;
    }

    //Mouse can't be taken
    public boolean attemptTake(){
        return false;
    }

    //Trying to take rat ends with rat running away.
    public String takeMessage(boolean attemptTake){
        String response;
        //Mouse is alive
        if(this.getAlive()){
            //Mouse isInside Hole
            if(this.insideHole){
                response = "The "+ this.quote() + " scampers through the " + this.smallHole.quote() + " to the room on the other side before you can even get close to it.";
                this.goEastIB();
            }
            //Mouse is just inside the room
            else{
                response = "You chase the " + this.quote() + " around the room, but are unable to catch it.\n";
                response += "It ends up fleeing through the " + this.smallHole.quote() + ".";
                this.goEastIB();
            }
        }
        //Mouse is dead
        else{
            response = "You don't want to take a dead " + this.quote() + " with you.";
        }
        return response;
    }

    //How the rat appears in the room will depend on whether the rat is inTheHole, dead, or just chilling in the room.
    public String getRoomLevelDescription(){
        String description = "There is a large " + this.quote() + " in the kitchen with you.";
        if(this.insideHole){
            description = "There is a " + this.quote() + " poking its head out of the " + this.smallHole.quote() + ".";
        }
        //The room level description for the Item handles this.
        else if(!this.getAlive()){
            description = "";
        }
        return description;
    }

    public String attemptUse(Item invItem){
        String response = "Nothing happens.";
        //Responses if the Mouse is alive.
        if(this.getAlive()){
            if(invItem instanceof Knife){
                response = "You attempt to chase the " + this.quote() + " down and stab it with your " + invItem.quote() + ".\n";
                response += "Unfortunately the mouse is easily able to outrun you and escapes through the " + this.smallHole.quote() + ".";
                this.goEastIB();
            }
            else if(invItem instanceof CreepyDoll){
                response = "You attempt to use the " + invItem.quote() + " to lure the " + this.quote() + " towards you.\n";
                response += "If anything the " + this.quote() + " moved even faster to escape through the " + this.smallHole.quote() + ".";
                this.goEastIB();
            }
            else if(invItem instanceof SmallMeat){
                response = "You attempt to use the " + invItem.quote() + " to lure the " + this.quote() + " towards you.\n";
                response += "The eyes of the " +  this.quote() + " seem transfixed on the meat for a moment. But it still flees through the " + this.smallHole.quote() + ".";
                this.goEastIB();
            }
            else if(invItem instanceof MouseTrap){
                response = "You attempt to throw the " + invItem.quote() + " at the " + this.quote() + ". It doesn't work.\n";
                response += "You should probably set it up instead of throwing it.";
                this.goEastIB();
            }
        }
        return response;
    }


    //PRIVATE METHODS

    //Determines the appropriate Action for all instances where the Mouse is in the Kitchen and in the Hole
    private String ratInKitchenAndInHoleHandler(boolean playerInSameRoom) {
        String action = "";
        if (playerInSameRoom) {
            action = lookedAtRatOrHoleResponse();
            //The Mouse moves to the EmptyRoom and he is no longer in the hole
            this.goEastIB();
            this.insideHole = false;
        } else {
            //The Mouse comes out of his hole.
            this.insideHole = false;
        }
        return action;
    }

    //Determines whether or not to give a response to the player when the rat flees through the hole.
    //Will return a string only if the player actually saw the rat peeking out from the hole.
    private String lookedAtRatOrHoleResponse(){
        String action = "";
        //ONLY returns a String if the player actually saw the Mouse peaking his head out of the Small Hole.
        String lastAction1 = "look " + this.getPlayer().getLocation().getName();
        String lastAction2 = "look " + this.smallHole.getName();
        String lastAction3 = "examine " + this.smallHole.getName();
        String lastAction4 = "look " + this.getName();
        String lastAction5 = "look " + this.getName();
        String LA = getPlayer().getLastAction();
        //If last action is equal to any of lastAction 1 through 5.
        if(LA.equals(lastAction1) || LA.equals(lastAction2) || LA.equals(lastAction3) || LA.equals(lastAction4) || LA.equals(lastAction5)){
            action = "The " + this.quote() + " sees you looking at it and skitters back through the " +  this.smallHole.quote() + ".\n";
        }
        return action;
    }

    //Returns a Key if a Key is in the room.
    public Item getYellowKey(){
        Item yellowKey = null;
        for(Item item : this.getLocation().getRoomItems()){
            if(item instanceof Key){
                yellowKey = item;
            }
        }
        return yellowKey;
    }
}

