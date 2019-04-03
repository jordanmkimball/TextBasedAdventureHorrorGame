public class Table extends ItemHolder {
    private Letter letter;
    private Buttons buttons;

    //CONSTRUCTORS
    public Table(String name, Player playerName, Letter letter, Buttons buttons){
        super(name, playerName);
        this.letter = letter;
        this.addStoredItem(letter);
        this.buttons = buttons;
        this.addStoredItem(buttons);

    }

    //METHODS
    public boolean attemptTake(){
        return false;
    }

    public String takeMessage(boolean attemptTake){
        return "The " + this.quote() + " is too big for you to carry.";
    }

    public String look(){
        boolean hasButtons = this.hasButtons();
        boolean hasLetter = this.hasButtons();
        String generalDescription = "A plain wooden table. Looks a little worn.\n";
        String tableTopDescription = getTableTopDescription();
        return generalDescription + tableTopDescription;
    }

    public String examine(){
        boolean hasButtons = this.hasButtons();
        boolean hasLetter = this.hasButtons();
        String generalFeelings =  "You inspect the table. Besides looking a bit worn, it appears to just be an ordinary " + this.quote() + ".\n";
        String tableTopDescription = getTableTopDescription();
        return generalFeelings + tableTopDescription;
    }

    //Returns true if the letter is still on top of the table.
    public boolean hasLetter(){
        boolean hasLetter = false;
        for(Item item : this.getStoredItems()) {
            if (item.equals(this.letter)) {
                hasLetter = true;
            }
        }
        return hasLetter;
    }

    //Returns true if the buttons are still on top of the table.
    public boolean hasButtons(){
        boolean hasButtons = false;
        for(Item item : this.getStoredItems()) {
            if (item.equals(this.buttons)) {
                hasButtons = true;
            }
        }
        return hasButtons;
    }

    //Returns the Buttons
    public Buttons getButtons(){
        return this.buttons;
    }


    public String getTableTopDescription(){
        boolean hasButtons = this.hasButtons();
        boolean hasLetter = this.hasLetter();
        String tableTopDescription = "";
        if(hasButtons && hasLetter){
            tableTopDescription = "On top of the table is a " + this.letter.quote() + " and an assortment of " + this.buttons.quote() + ".";
        }
        else if(hasButtons){
            tableTopDescription = "There is an assortment of different " + this.buttons.quote() + " on top of the " + this.quote() + ".";
        }
        else if(hasLetter){
            tableTopDescription = "There is a " + this.letter.quote() + " on top of the " + this.quote() + ". It looks like someone has removed the various " + this.buttons.quote() + " that used to rest on top of the table.";
        }
        else{
            tableTopDescription = "It looks like someone has removed the various " + this.buttons.quote() + " that used to rest on top of the table.";
        }
        return tableTopDescription;
    }
}
