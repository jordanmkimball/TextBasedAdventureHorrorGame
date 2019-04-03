import java.util.ArrayList;

public class MouseTrap extends ItemHolder{
    private SmallMeat smallMeat;
    private boolean baited;
    private boolean placed;

    //CONSTRUCTOR
    public MouseTrap(String name, Player player1, SmallMeat smallMeat){
        super(name, player1);
        baited = false;
        this.smallMeat = smallMeat;
        this.placed = false;
    }

    //BEHAVIOR

    //Returns true is the mouseTrap is baited
    public boolean isBaited(){
        return this.baited;
    }

    //Returns true if the mouseTrap is placed
    public boolean isPlaced(){
        return this.placed;
    }

    //Sets the value for placed
    public void setPlaced(boolean placed){
        this.placed = placed;
    }

    public boolean attemptTake(){
        boolean result = false;
        //If the MouseTrap hasn't been baited yet, you can pick it up.
        if(!baited){
            result = true;
        }
        return result;
    }

    public String takeMessage(boolean take){
        String res = "";
        //Player takes the MouseTrap
        if(take){
            //The MouseTrap has been placed.
            if(this.placed){
                res = "You carefully trigger the " + this.quote() + " without hurting yourself so it is reset to its unarmed state.\n";
                res += "You then take the " + this.quote() + " and put it in your inventory.";
                this.placed = false;
            }
            //The MouseTrap is still in the Cupboards.
            else{
                res = "You then take the " + this.quote() + " and put it in your inventory.";
            }
        }
        else{
            //The Mouse exists, but is dead.
            if(this.getRat() != null && !this.getRat().getAlive()){
                res = "The " + this.getRat().quote() + " is dead now so you don't feel like the " + this.quote() + " will be useful to you.";
            }
            //The Mouse has not yet been killed.
            else{
                res = "You feel pretty confident about the placement of the " + this.quote() + " and you don't want to move it.";
            }
        }
        return res;
    }

    public String look(){
        String response = "One of those little wooden " + this.quote() + " that snaps the mouses neck when it triggers the trap.";
        String keyDescription = "";
        Mouse mouse = (Mouse) this.getRat();
        //The Mouse is alive
        if(mouse.getAlive()){
            if(this.baited){
                response += "\nYou have baited the trap with the " + this.smallMeat.quote() + ".";
            }
            //If the mouseHole exists and the Trap has been placed.
            if(this.isPlaced() && this.getMouseHole() != null){
                response += "\nThe trap has been primed and placed near the " + this.getMouseHole().quote() + ".";
            }
        }
        //The Mouse is dead
        else{
            response = "A wooden " + this.quote() + ". There is a dead " + this.getRat().quote() + " in the trap.";
            //The Mouse in the Trap is dead
            if(mouse.getYellowKey() != null){
                keyDescription = "\n You see a shiny " + mouse.getYellowKey().quote() + " looped around its tail.";
            }
        }
        return response;
    }

    public String examine(){
        String intro = examineIntro();
        String description = "It is one of those mousetraps that has a spot for bait.\n";
        String mouseKillMethod = "When a rodent triggers the trap by taking the bait, the metal part will swing around and break the mouses neck.\n";
        String keyDescription = "";
        String feelings = "The trap looks like it is in perfect working order, unlike most other things in this Kitchen.";
        Mouse mouse = (Mouse) this.getRat();
        //The Mouse is alive
        if(mouse.getAlive()){
            if(this.baited){
                description = "You have baited the " + this.quote() + " with the " + this.smallMeat.quote() + ".\n";
            }
            //If the trap has been placed and the mouseHole and mouse both exist.
            if(this.placed && this.getMouseHole() != null && this.getRat() != null){
                description = description.substring(0, description.length() -2) + " and it has been primed and placed near the " + this.getMouseHole().quote() + ".\n";
                feelings = "It would probably be best to wait in another room so the " + this.getRat().quote() + " isn't afraid to come out of his hole.";
            }
        }
        else{
            if(!mouse.getAlive()){
                description = "The " + this.quote() + " was quite effective at taking care of the " + mouse.quote() + ". It is now dead inside of the trap.\n";
                feelings = "";
                mouseKillMethod = "";
                if(mouse.getYellowKey() != null){
                    keyDescription = "You see a shiny " + mouse.getYellowKey().quote() + " looped around its tail. It is worth taking the " + mouse.getYellowKey().quote() + ".\n";
                }
            }
        }
        //If the mouse is Dead.
        return intro + description + keyDescription + mouseKillMethod + feelings;
    }


    public String attemptUse(Item inventoryItem){
        String useResult = "Nothing happens";
        if(inventoryItem.equals(smallMeat)){
            if(this.isInInventory()){
                //Case where the smallMeat has already been used to bait the MouseTrap.
                if(this.baited){
                    useResult = "The " + this.smallMeat.quote() + " has already been used to bait the " + this.quote() + ".";
                }
                //SmallMeat has not been used to bait the MouseTrap.
                else{
                    this.baited = true;
                    //We remove the SmallMeat from player inventory
                    this.getPlayer().getInventory().remove(inventoryItem);
                    //We add the SmallMeat to MouseTrap ItemStorage
                    this.getStoredItems().add(smallMeat);
                    smallMeat.setBaitedStatus(true);
                    useResult = "You place the " + inventoryItem.quote() + " on the bait section of the " + this.quote() + ".";
                }

            }
            else{
                useResult = "The " + this.quote() + " must be in your inventory for you to do that.";
            }
        }
        return useResult;
    }

    //A description that will automatically be added to Kitchen.look() response if the MouseTrap is in RoomItems
    public String getRoomLevelDescription(){
        String description = "";
        Mouse mouse = (Mouse) this.getRat();
        //Description if the trap has been baited, placed, and the Mouse exists and is alive
        if(this.placed && this.isBaited() && getRat() != null && getRat().getAlive()){
            description = "A " + this.quote() + " has been placed near the " + this.getMouseHole().quote() + " and baited with the " + this.smallMeat.quote() + ".\n";
        }
        //Description if the trap has NOT been baited, but has been placed, and the Mouse exists and is alive.
        else if(this.placed && getRat() != null && getRat().getAlive()){
            description = "A " + this.quote() + " has been placed near the " + this.getMouseHole().quote() + ".";
        }
        //The Mouse is dead inside the trap
        else if(!mouse.getAlive()){
            description = "A " + this.quote() + " has been placed near the " + this.getMouseHole().quote() + " and baited with the " + this.smallMeat.quote() + ".\n";
            description += "There is a dead " + mouse.quote() + " inside the trap.";
            //If the key also exists
            if(mouse.getYellowKey() != null){
                description += " You notice that there is something shiny looped around the tail of the " + mouse.quote() + ".";
            }
        }
        return (!description.isEmpty()) ? description + "\n" : "";
    }

    //PRIVATE HELPER METHODS
    private Item getMouseHole(){
        //Find out if Small Hole is in the Room
        ArrayList<Item> roomItems = this.getPlayer().getLocation().getRoomItems();
        Item mouseHole = null;
        for(Item item : roomItems){
            if(item instanceof MouseHole){
                mouseHole = item;
            }
        }
        return mouseHole;
    }

    private NPC getRat(){
        ArrayList<NPC> NpcList = this.getPlayer().getEnvironment().getNpcList();
        NPC rat = null;
        for(NPC npc : NpcList){
            if(npc instanceof Mouse){
                rat = (Mouse) npc;
            }
        }
        return rat;
    }
}
