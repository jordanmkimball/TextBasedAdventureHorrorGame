public class Chest extends ItemContainer{
    private Key key;

    //CONSTRUCTORS
    public Chest(String name, Player playerName, boolean open, boolean locked, Key key){
        super(name, playerName, open, locked);
        this.key = key;
    }


    //METHODS

    public boolean attemptTake(){
        return false;
    }

    public String takeMessage(boolean attemptTake){
        return "The " + this.quote() + " is too large and smelly to take with you.";
    }


    public String attemptOpen(){
        if(!this.getOpenStatus()){
            this.setOpenStatus(true);
            String s = "You open the " + this.quote() +".";
            s += "\n" + this.look();
            return s;
        }
        return "The " + this.quote() + " is already open.";
    }

    public String attemptClose(){
        if(this.getOpenStatus()){
            this.setOpenStatus(false);
            return "You close the " + this.quote() + ". It is nice not to have to look at the trash that was inside anymore.";
        }
        else{
            return "The " + this.quote() + " is already closed.";
        }
    }

    public String look(){
        //Chest is closed
        if(!this.getOpenStatus()){
            return "A " + this.quote() +". It is closed but not locked.";
        }
        //Chest is open, and the contents have not yet been taken.
        if(this.getStoredItems().size() > 0){
            return "Inside the open " + this.quote()+ " you can see some miscellaneous trash, a few crunched up cans of Coke Zero, and a " + this.key.quote() + ".";
        }
        //Chest is open, but the contents have been taken.
        return "Inside the open " + this.quote()+ " you can see some miscellaneous trash and a few crunched up cans of Coke Zero.";

    }

    public String examine(){
        //Chest is closed
        if(!this.getOpenStatus()){
            return "You investigate the " + this.quote() + ".\nIt is covered in dust like everything else in this room.\nThere is a bad but mild smell coming from the chest. It isn't locked and you could easily open it.";
        }
        //Chest is open, and the contents have not yet been taken.
        if(this.getStoredItems().size() > 0){
            return "You investigate the open " + this.quote() + ".\nThe top of the chest is covered in dust like everything else in the room.\nIt looks like it was used as a place to store trash, and a small " + this.key.quote() + ". The " + this.key.quote() +" is worth taking.";
        }
        //Chest is open, but the contents have been taken.
        return "You investigate the open " + this.quote() +".\nThe top of the chest is covered in dust like everything else in the room.\nAfter thoroughly searching through the trash, you determine that there is nothing else worth taking left in the chest.";
    }


    public String getInsideDescription(){
        return "";
    }
}

