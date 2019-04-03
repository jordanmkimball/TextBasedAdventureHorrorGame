public class MetalVent extends Item{

    public MetalVent(String name, Player player1){
        super(name, player1);
    }

    public boolean attemptTake(){
        return false;
    }

    public String look(){
        return "A large " + this.quote() + " that has been placed high up on the wall.";
    }

    public String examine(){
        String intro = examineIntro();
        String feelings = "The vent is too high up for you to reach. It is large though. It looks like an entire person could fit inside it.";
        return intro + feelings;
    }
}
