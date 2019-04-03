public class Counter extends Item{

    //CONSTRUCTOR
    public Counter(String name, Player player1){
        super(name, player1);
    }

    //BEHAVIOR
    public boolean attemptTake(){
        return false;
    }

    public String takeMessage(boolean attemptTake){
        return "It is attached to the floor.";
    }

    public String look(){
        return "A long dirty " + this.quote() + ".";
    }

    public String examine() {
        String intro = "You investigate the " + this.quote() + ".\n";
        String description = "The counter is long, and mostly barren. There is a bit of black mold growing around the edges.";
        return intro + description;
    }
}
