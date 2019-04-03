public class FlatMan extends NPC implements Talking {
    public FlatMan(String name, Player player1, int priority){
        super(name, player1, priority);
        player1.getEnvironment().addNpc(this);
    }


    //The FlatMan is an inactive NPC.
    public boolean getActionStatus(){
        return false;
    }

    public String performAction(){
        return "";
    }

    public String look(){
        return "He would look like a normal middle aged white male except for his lack of eyes, and the fact that he is is literally as thin as a piece of paper.";
    }

    public String examine(){
        String examineIntro = this.examineIntro();
        String description = "He is literally a " + this.quote() + ". He is so thin that he comes across as nearly 2-dimensional. He could slide under any door, or slip through any window crack.\n";
        String feelings = "No where is safe from him. Luckily he doesn't appear to hold any hostility towards you... yet.";
        return examineIntro + description + feelings;
    }


    //Determines the appropriate response when a player tries to talk with an NPC
    public String talk(){
        TalkFlatMan talkFlatMan = new TalkFlatMan(this.getPlayer(), this);
        talkFlatMan.runMultipleChoice();
        getPlayer().setContinueGame(false);
        return "CONGRATULATIONS ON BEATING THE GAME.";
    }
}
