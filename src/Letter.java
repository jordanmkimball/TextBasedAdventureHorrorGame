public class Letter extends Item implements ReadableItem, Edible{
    private DollLetterNE1 event;
    //CONSTRUCTORS
    public Letter(String name, Player playerName, DollLetterNE1 event){
        super(name, playerName);
        this.event = event;
    }

    //METHODS
    public boolean attemptTake(){
        return true;
    }

    //Looks at the letter
    public String look(){
        return "A neat handwritten Letter that appears to be addressed to you.";
    }

    //Player attempts to attemptEat the letter
    public String attemptEat(){
        return "You have no desire to try and eat the " + this.quote() + ".";
    }

    public String examine(){
        String letterWriting = "\nYou read the Letter\n\nDear " + getPlayer().getName() + ",\n\nWelcome to my humble home. If you are reading this it means that despite my repeated warnings, you decided to explore the old house anyway.\nAlthough I commend your bravery, I fear that this house will likely become your grave as has been the case with many of the others who came before you.\nBe wary of the other things living in this house, and try your best to stay alive.";
        //If the DollLetterEvent has been marked as complete. The letter will have additional text appended to it.
        if(event.getCompletedStatus()){
            String dollMessage = "\nSHES GONNA KILL YOU SHES GONNA KILL YOU SHES GONNA KILL YOU SHES GONNA KILL YOU. BUT I CAN HELP YOU. SHE NEVER HURTS HER FRIENDS WITH BUTTON EYES.\nThe last part of the message is in a different handwriting than the first part of the message.";
            letterWriting = letterWriting + dollMessage;
        }
        return letterWriting;
    }

    //Reading the letter
    public String read(){
        return this.examine();
    }
}

