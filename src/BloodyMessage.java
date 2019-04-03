public class BloodyMessage extends Item implements ReadableItem, Edible {

    //Constructor
    public BloodyMessage(String name, Player player1){
        super(name, player1);
    }

    public boolean attemptTake(){
        return false;
    }

    public String takeMessage(boolean attemptTake){
        return "You don't want to touch it.";
    }

    public String look(){
        return "A " + this.quote() +" written in blood adorns the North Wall, it says 'Beware the Beast'.";
    }

    public String read(){
        String intro = "You read the " + this.quote() + ".\n";
        String message = "It says \'Beware the beast\'. It looks like it was written in blood.";
        return intro + message;
    }

    public String attemptEat(){
        return "The very thought of trying to eat the dried blood off of the wall makes you want to vomit.";
    }

    public String examine(){
        String quickLook =  "A " + this.quote() + " written in blood smears the North Wall, it says 'Beware the Beast'.\n";
        String moreInfo = "The blood is dry, and starting to crack. Whoever wrote the message didn't do so recently.\n";
        String thoughts = "You wonder what beast they were referring to.";
        return quickLook + moreInfo + thoughts;
    }
}
