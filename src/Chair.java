public class Chair extends Item{

    //Constructor
    public Chair(String name, Player player1){
        super(name, player1);
    }

    //BEHAVIORS

    public boolean attemptTake(){
        return false;
    }

    public String takeMessage(boolean attemptTake){
        return "You don't see what you would do with a " + this.quote();
    }

    public String look(){
        return "An old " + this.quote() + ".";
    }

    public String examine(){
        return "You investigate the " + this.quote() + ". There doesn't seem to be anything out of the ordinary with it.";
    }


}
