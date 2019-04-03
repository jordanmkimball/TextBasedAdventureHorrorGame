public class Refrigerator extends ItemContainer{
    private Item storedItem;

    //CONSTRUCTOR
    public Refrigerator(String name, Player player1, boolean open, boolean locked, Item storedItem){
        super(name, player1, locked, open);
        this.storedItem = storedItem;
        this.addStoredItem(storedItem);
    }

    //BEHAVIOR
    public boolean attemptTake(){
        return false;
    }

    public String takeMessage(){
        return "The " + this.quote() + " is too large for you to take.";
    }

    public String look(){
        String description;
        String inside = getInsideDescription();
        if(!this.getOpenStatus()){
            description = "A white " + this.quote() + " The fridge door is closed.";
        }
        //The fridge is open, and you can see its contents.
        else{
            description = "A white " + this.quote() + " The fridge door is open.\n";
            inside = "Inside you are see a bunch or rotting foods, expired items, and a strange " + this.storedItem.quote() + ".";
        }
        return description + inside;
    }

    public String examine(){
        String intro;
        String inside = getInsideDescription();
        String feelings;
        if(!this.getOpenStatus()){
            intro = this.examineIntro() + "The fridge door is closed.\n";
            feelings = "Based on the dirty state of the room you are doubtful that you will find anything to attemptEat inside.";
        }
        //The fridge is open, and you can see its contents.
        else{
            intro = this.examineIntro() + "The fridge door is open.\n";
            inside += "\nThere is brown speckled mold covering all the walls, and shelves of the fridge.\n";
            feelings = "The mystery meat looks relatively fresh in contrast to everything else in the fridge.";
        }
        return intro + inside + feelings;
    }

    public String getInsideDescription(){
        String inside = "";
        if(this.getOpenStatus()){
            inside = "Inside you are see a bunch or rotting foods, expired items, and a strange " + this.storedItem.quote() + ".";
        }
        return inside;
    }
}
