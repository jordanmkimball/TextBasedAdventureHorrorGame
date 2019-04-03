public class Key extends Item{
    private String changeKeyLook;
    private String changeKeyExamine;

    //CONSTRUCTORS

    public Key(String name, Player playerName){
        super(name, playerName);
    }

    //METHODS

    public String takeMessage(boolean attemptTake){
        return "You pick up the " + this.quote() + " and put it in your inventory.";
    }

    public boolean attemptTake(){
        return true;
    }

    public String look(){
        return "A plain looking " + this.quote() + ".";
    }

    public String examine(){
        return "This " + this.quote() + " probably unlocks a room in the house somewhere.";
    }
}
