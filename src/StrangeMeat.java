public class StrangeMeat extends Item implements Edible{
    private SmallMeat smallMeat;
    private boolean takenSmallMeat;

    //CONSTRUCTOR
    public StrangeMeat(String name, Player player1, SmallMeat smallMeat){
        super(name, player1);
        this.smallMeat = smallMeat;
        this.takenSmallMeat = false;
    }

    //BEHAVIOR

    public boolean attemptTake(){
        return false;
    }

    public String takeMessage(boolean attemptTake){
        return "There must be at least 40 pounds of meat in there. You don't want to take it with you.";
    }

    public String look(){
        return "A large " + this.quote() + ". You can see ribs poking out at weird angles. It looks heavy.";
    }

    public String examine(){
        String intro = examineIntro();
        String description = "It is large. Probably about 40 pounds. You can see ribs poking out at weird angles.\n";
        String feelings = "You aren't sure what animal it is from. It looks sort of like beef, but the bones don't look quite right.\n";
        String grossness = "The meat is directly touching the moldy bottom of the refrigerator.";
        return intro + description + feelings + grossness;
    }

    //The player attempts to attemptEat the Strange Meat
    public String attemptEat(){
        return "You don't want to try eating the " + this.quote() + " because it is uncooked, and you aren't even sure what animal it is from.";
    }

    //When the player uses a Knife on the StrangeMeat. Return a small cut of the meat.
    public String attemptUse(Item inventoryItem){
        String useResponse = "Nothing happens.";
        String placeInInventory = "";
        //Case where the player uses a knife on the StrangeMeat, and has not yet received smallMeat.
        if(inventoryItem instanceof Knife && !this.takenSmallMeat){
            useResponse = "You use the " + inventoryItem.quote() + " to carefully slice a small piece of the " + this.quote() + " off.\n";
            placeInInventory = "You take the " + this.smallMeat.quote() + " and put it in your inventory.";
            getPlayer().addToInventory(this.smallMeat);
            this.takenSmallMeat = true;
        }
        //Case where the player uses a knife on the strangeMeat, but has already received smallMeat.
        else if(inventoryItem instanceof Knife){
            useResponse = "You have already cut a small piece of the meat off. You don't see how taking more would do you any good.";
        }
        //Case where player tries to use their key on the strangeMeat.
        else if(inventoryItem instanceof Key){
            useResponse = "Strangely enough, slamming your " + inventoryItem.quote() + " repeatedly into the " + this.quote() + " doesn't yield any results.\n";
            useResponse += "You think this house might be starting to get to you.";
        }
        return useResponse + placeInInventory;
    }

}
