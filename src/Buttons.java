public class Buttons extends Item {

    //CONSTRUCTOR
    public Buttons(String name, Player player1){
        super(name, player1);
    }

    //METHODS

    //Player doesn't want to take the buttons
    public boolean attemptTake(){
        return false;
    }

    public String takeMessage(boolean attemptTake){
        return "You don't think you would have any use for the " + this.quote() + ".";
    }

    public String look(){
        return "There are at least 13 buttons of various sizes and colors on this table. The kind of buttons would be used in clothing.";
    }

    public String examine(){
        String intro = "You examine the " + this.quote() + ".\n";
        String feelings = "There is nothing unusual about them. You don't feel like they would be useful to you.";
        return intro + feelings;
    }

}
