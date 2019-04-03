public class DollLetterNE1 extends NpcEvent{
    private int ActionCountWhenDollTaken;

    //CONSTRUCTOR
    public DollLetterNE1(Player player1, int priority){
        super(priority, player1);
        this.ActionCountWhenDollTaken = 0;
    }

    //BEHAVIOR
    public boolean getTriggerStatus(){
        boolean triggered = false;
        //The doll must be in inventory, and the player has taken three action turns since the NPC was put in inventory, and the letter is also in inventory
        if(hasTakenDoll() && (getPlayer().getActionCounter() - this.ActionCountWhenDollTaken) >= 3 && letterInInventory()){
            triggered = true;
        }
        return triggered;
    }

    public String getTriggerMsg(){
        this.setCompletedStatus(true);
        return "You hear some quiet scratching noises coming from the backpack where you store your inventory.";
    }

    public boolean hasTakenDoll(){
        boolean takenDoll = false;
        //First we check to see if the Doll is in the Players Inventory.
        for(Item item: getPlayer().getInventory()){
            if(this.getNPC() != null){
                if(this.getNPC().getName().equals(item.getName())){
                    takenDoll = true;
                    //If ActionCountWhenDollTaken hasn't had a real value assigned to it yet. Assign it a value.
                    if(this.ActionCountWhenDollTaken == 0){
                        this.ActionCountWhenDollTaken = this.getPlayer().getActionCounter();
                    }
                }
            }
        }
        return takenDoll;
    }

    public boolean letterInInventory(){
        boolean letterInInventory = false;
        for(Item item : this.getPlayer().getInventory()){
            if(item instanceof Letter){
               letterInInventory = true;
            }
        }
        return letterInInventory;
    }
}
