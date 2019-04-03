public class Knife extends Item {

    //CONSTRUCTOR
    public Knife(String name, Player player1){
        super(name, player1);
    }

    //BEHAVIOR
    public boolean attemptTake(){
        return true;
    }

    public String look(){
        return "A sharp " + this.quote() + ".";
    }

    public String examine(){
        String intro = "You examine the " + this.quote() + ".\n";
        String feelings = "It is quite sharp. It could be used to cut things.";
        return intro + feelings;
    }

}
