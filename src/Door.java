public class Door extends Item implements Lockable, AcceptsItem{
    private boolean locked;
    private Item acceptItem;

    //CONSTRUCTORS
    public Door(String name, Player playerName, boolean locked){
        super(name, playerName);
        this.locked = locked;
    }

    public Door(String name, Player playerName, boolean locked, Item acceptItem){
        super(name, playerName);
        this.locked = locked;
        this.acceptItem = acceptItem;
    }

    //METHODS

    //Get the locked status
    public boolean getLockedStatus(){
        return this.locked;
    }

    //Set Locked Status
    public void setLockedStatus(boolean locked){
        this.locked = locked;
    }

    //Returns the acceptItem
    public Item getAcceptItem(){
        return this.acceptItem;
    }

    //Sets the acceptItem
    public void setAcceptItem(Item acceptItem){
        this.acceptItem = acceptItem;
    }

    //Attempt to unlock the door
    public String attemptUse(Item e){
        if(!this.locked){
            return "The door is already unlocked.";
        }
        else if(this.getAcceptItem() != null && this.getAcceptItem().equals(e)){
            this.setLockedStatus(false);
            return "You unlock the door.";
        }
        else{
            return "You are unable to unlock the " + this.quote() + " with the " + e.quote() + ".";
        }
    }

    public boolean attemptTake(){
        return false;
    }

    public String takeMessage(boolean attemptTake){
        return "The "+this.quote()+" is attached firmly to its hinges. You doubt you would be able to carry it anyway.";
    }

    public String attemptOpen(){
        String printToPlayer = "";
        if(this.getLockedStatus()){
            printToPlayer = "The " + this.quote() + " is locked.";
        }
        else{
            if(this.getDoorDirection() != null){
                switch(getDoorDirection()){
                    case "north":
                        printToPlayer = this.getPlayer().goNorth();
                        break;
                    case "east":
                        printToPlayer = this.getPlayer().goEast();
                        break;
                    case "south":
                        printToPlayer = this.getPlayer().goSouth();
                        break;
                    case "west":
                        printToPlayer = this.getPlayer().goWest();
                        break;
                }
            }
        }
        return printToPlayer;
    }


    public String attemptClose(){
        return "The " + this.quote() + " is already closed.";
    }


    public String look(){
        if(this.locked){
            return "A " + this.quote() + ". It appears to be locked.";
        }
        else{
            return "A " + this.quote() +" door. It is unlocked.";
        }
    }

    public String examine(){
        if(this.locked){
            return "You examine the " + this.getName() + ".\nIt is old, but looks to be fairly sturdy. It is locked.\nThere is probably a key around somewhere that could open it.";
        }
        else{
            return "You examine the " + this.getName() + ".\nIt is old, but looks to be fairly sturdy. It is currently unlocked. You could go through it if you want.";
        }
    }


    public String getDoorDirection(){
        if(this.getPlayer().getLocation().getNorthDoor() != null){
            if(this.getPlayer().getLocation().getNorthDoor().getName().equals(this.getName())){
                return "north";
            }
        }
        if(this.getPlayer().getLocation().getEastDoor() != null){
            if(this.getPlayer().getLocation().getEastDoor().getName().equals(this.getName())){
                return "east";
            }
        }
        if(this.getPlayer().getLocation().getSouthDoor() != null){
            if(this.getPlayer().getLocation().getSouthDoor().getName().equals(this.getName())){
                return "south";
            }
        }
        if(this.getPlayer().getLocation().getWestDoor() != null){
            if(this.getPlayer().getLocation().getWestDoor().getName().equals(this.getName())){
                return "west";
            }
        }
        return null;
    }
}
