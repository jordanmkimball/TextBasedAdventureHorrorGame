public class TalkFlatMan extends MultipleChoice {
    private FlatMan flatMan;
    private Choice choice1xxClarify;
    private Choice choice2xEnjoyGame;

    public TalkFlatMan(Player player1, FlatMan flatMan){
        super(player1);
        this.flatMan = flatMan;

        //Set initialPrompt
        String initialGreeting = "You greet the " + flatMan.quote() + ".\n";
        String flatManResponse = "\"Aw, " + player1.getName() + ". Just the person I was looking to talk to. I came here to congratulate you on making it this far.\" he replies in a deep gruff voice.\n";
        flatManResponse += "\"Unfortunately this is where your story ends. At least as far as this house is concerned.\"\n";
        setInitialPrompt(initialGreeting + flatManResponse);

        //START OF CHOICE 1

        String c1SA = "A.) \"What do you mean by that?\"\n";
        String c1SB = "B.) \"I came all this way just for you to kill me?\"\n";

        String c1branches = c1SA + c1SB;

        String c1SelectA = "\"Oh just that you have reached the end of this game's content is all\"\n";
        String c1SelectB = "\"Kill you? Of course I'm not going to kill you. You've beaten the game! You are safe. There's no reason to fear for your characters life any longer.\"\n";
        String postResponse = "\"Now then, did you enjoy the game?\"\n";

        this.choice1xxClarify = new Choice(c1branches, c1SelectA + postResponse, c1SelectB + postResponse);
        this.setCurrentChoice(choice1xxClarify);

        //START OF CHOICE 2

        String c2SA = "A.) \"Yes\"\n";
        String c2SB = "B.) \"No\"\n";

        String c2branches = c2SA + c2SB;

        String c2SelectA = "\"I'm happy to hear that\" he says with a smile.\n";
        String c2SelectB = "\"I'm sorry to hear that\" he says with a downtrodden sigh. You can tell that he is disappointed with this news.\n";
        postResponse = "\"At any rate I certainly appreciate you taking the time to play the game.\"\n";

        this.choice2xEnjoyGame = new Choice(c2branches, c2SelectA + postResponse, c2SelectB + postResponse);
    }


    String optionLogic(String input) {
        String response = null;
        if(getCurrentChoice().equals(choice1xxClarify)){
            if(input.equals("a")){
                response = choice1xxClarify.getSelectA();
                setCurrentChoice(choice2xEnjoyGame);
            }
            else if(input.equals("b")){
                response = choice1xxClarify.getSelectB();
                setCurrentChoice(choice2xEnjoyGame);
            }
        }
        else if(getCurrentChoice().equals(choice2xEnjoyGame)){
            if(input.equals("a")){
                response = choice2xEnjoyGame.getSelectA();
                setContinueRunningChoices(false); //END OF CONVERSATION.
            }
            else if(input.equals("b")){
                response = choice2xEnjoyGame.getSelectB();
                setContinueRunningChoices(false); //END OF CONVERSATION.
            }
        }
        return response;
    }
}
