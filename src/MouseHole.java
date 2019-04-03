public class MouseHole extends Item{
    private Mouse mouse;
    private MouseTrap mouseTrap;

    //CONSTRUCTOR
    public MouseHole(String name, Player player1, MouseTrap mouseTrap){
        super(name, player1);
        this.mouseTrap = mouseTrap;
    }

    //BEHAVIOR
    public boolean attemptTake(){
        return false;
    }

    public String look(){
        String response = "There is a " + this.quote() + " where the floor connects to the Eastern Wall.";
        String ratString = "";
        if(this.mouse != null && mouse.isInsideHole()){
            ratString = "\nThere is a " + this.mouse.quote() + " peeking its head out of the hole.";
        }
        return response + ratString;
    }

    public String examine(){
        String intro = examineIntro();
        String description = "It is located where the floor connects to the Eastern Wall.\n";
        String feelings = "The hole is large enough that a rodent could travel between this room and the room on the other side of the wall.";
        if(this.mouse != null && mouse.isInsideHole()){
            feelings = "A " + this.mouse.quote() + " is peeking its head out of the hole.\n";
            feelings += "It is probably looking to see if you are still in the room.";
        }
        return intro + description + feelings;
    }

    //Sets the Mouse value for the mouseHole
    public void setMouse(Mouse mouse){
        this.mouse = mouse;
    }

    public String attemptUse(Item invItem){
        String response = "Nothing happens.";
        if(invItem.equals(mouseTrap)){
            response = "You place the " + mouseTrap.quote() + " near the " + this.quote() + " and prime it for use.";
            getPlayer().getInventory().remove(invItem);
            getPlayer().getLocation().addRoomItem(invItem);
            mouseTrap.setPlaced(true);
            if(mouseTrap.isBaited()){
                response += "\nNow all you need to do is wait for the " + this.mouse.quote() + " to trigger it.";
            }
        }
        return response;
    }

    //Returns MouseTrap.
    public MouseTrap getMouseTrap(){
        return this.mouseTrap;
    }
}
