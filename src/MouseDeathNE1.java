public class MouseDeathNE1 extends NpcEvent {
    private MouseTrap mouseTrap;
    private Mouse mouse;
    private Key yellowKey;

    //CONSTRUCTOR
    public MouseDeathNE1(int priority, Player player1, MouseTrap mouseTrap, Key yellowKey){
        super(priority, player1);
        this.mouseTrap = mouseTrap;
        this.yellowKey = yellowKey;
    }

    //BEHAVIOR

    public boolean getTriggerStatus(){
        boolean triggered = false;
        //Early Exit if Mouse hasn't yet been assigned.
        if(mouse == null){
            return triggered;
        }
        boolean mouseTrapInRoomWithRat = false;
        boolean mouseTrapBaitedAndPlaced = mouseTrap.isPlaced() && mouseTrap.isBaited();
        boolean ratInRoomWithPlayer = mouse.getLocation().equals(getPlayer().getLocation());
        //Find out if a MouseTrap is in the same Room as the Mouse.
        for(Item item : mouse.getLocation().getRoomItems()){
            if(item instanceof MouseTrap){
                mouseTrapInRoomWithRat = true;
            }
        }
        //RatDeath only occurs when 1.) the Mouse is NOT InsideHole 2.)Mouse is in the Room with the MouseTrap
        //3.) MouseTrap is baited and placed 4.) Mouse and Player are NOT in the same room
        if(!mouse.isInsideHole() && mouseTrapBaitedAndPlaced && mouseTrapInRoomWithRat && !ratInRoomWithPlayer){
            triggered = true;
        }
        return triggered;
    }

    public String getTriggerMsg(){
        String response = "";
        Room playerRoom = this.getPlayer().getLocation();
        Room ratRoom = mouse.getLocation();
        if(ratRoom.getNorthExit().equals(playerRoom) || ratRoom.getEastExit().equals(playerRoom)){
            response = "You hear a sudden metallic snapping sound come from the " + ratRoom.quote() + ".";
        }
        mouse.kill();
        //Spawn the yellowKey
        ratRoom.addRoomItem(yellowKey);
        this.setCompletedStatus(true);
        return response;
    }

    //Used so that when Mouse is constructed it will simultaneously give MouseDeathNE1 a mouse value
    public void setMouse(Mouse mouse){
        this.mouse = mouse;
    }

    //Used to get Mouse Trap from the event
    public MouseTrap getMouseTrap(){
        return this.mouseTrap;
    }
}
