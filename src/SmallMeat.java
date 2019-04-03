public class SmallMeat extends Item implements Edible{
    private boolean baited;

    //CONSTRUCTOR
    public SmallMeat(String name, Player player1){
        super(name, player1);
        this.baited = false;
    }

    //BEHAVIORS

    public boolean attemptTake(){
        return false;
    }

    public String takeMessage(boolean attemptTake){
        return "You like it where it is now. You don't want to take it.";
    }

    public String look(){
        return "A " + this.quote() + " that you cut off from the strange meat in the refrigerator.";
    }

    public String attemptEat(){
        return "The " + this.quote() + " is raw, but even if it were cooked you would have to be pretty hungry to consider eating it.";
    }

    public String examine(){
        String intro = this.examineIntro();
        String description = "It feels like beef, but has a strange almost copper like scent to it.";
        String feelings = "\nYou wonder if it could be used for bait.";
        if(baited){
            feelings = "";
        }
        return intro + description + feelings;
    }

    public void setBaitedStatus(boolean baited){
        this.baited = baited;
    }

    public String attemptUse(Item invItem){
        String result = "Nothing happens.";
        if(invItem instanceof MouseTrap){
            result = getPlayer().use(this, invItem);
        }
        return result;
    }
}
