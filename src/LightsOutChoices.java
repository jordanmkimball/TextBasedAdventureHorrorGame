import java.util.*;
public class LightsOutChoices extends MultipleChoice{
    private Choice choice1;
    private Choice choice2A;
    private Choice choice3B;
    private Choice choice3C;
    private boolean usedNorthSwitch;


    //CONSTRUCTOR
    public LightsOutChoices(Player player1){
        super(player1);
        usedNorthSwitch = true;

        //Create the initial prompt
        String initialPrompt = "You hear the sound of a switch flipping in the room. The room suddenly goes dark. Pitch dark. So dark that you can't even see your own hand if you put it in front of your face.\n";
        initialPrompt += "Someone or something has turned out the lights. You feel like there is a presence watching you from the darkness.\n";
        initialPrompt += "You accidentally hit your head on the Kitchen Cupboards as you stumble about in the room. Using the cupboards to orient yourself, you determine that you must currently be facing South.\n";
        initialPrompt += "You need to either find a light switch to put the lights back on, or get out of the room. What will you do?\n";
        setInitialPrompt(initialPrompt);

        //START OF CHOICE 1
        String c1SA = "A.) Search the North wall for a light switch.\n";
        String c1SB = "B.) Search the East wall for a light switch.\n";
        String c1SC = "C.) Search the South wall for a light switch.\n";
        String c1SD = "D.) Search the West wall for a light switch.\n";

        String c1presentBranches = c1SA + c1SB + c1SC + c1SD;

        String c1SelectA = "Would you like to search to the left or right of the door?\n";
        String c1SelectB = "You feel all around the Eastern Wall, but you don't find a light switch, and the door is locked so you can't get out.\nWhich wall would you like to search next?\n";
        String c1SelectC = "The wall is gross and moist as you feel along it with your hand. You search thoroughly, but don't find a light switch.\nWhich wall would you like to search next?\n";
        String c1SelectD = "You feel around the Western Wall of the room for some time, before you finally You manage to find a light switch. You quickly flip the switch.";

        this.choice1 = new Choice(c1presentBranches, c1SelectA, c1SelectB, c1SelectC, c1SelectD);
        setCurrentChoice(choice1);

        //START OF CHOICE 2A (Tried to search the North Wall)
        String c2aSA = "A.) Search to the left of the door.\n";
        String c2aSB = "B.) Search to the right of the door.\n";
        String c2aSC = "C.) Open the \"Red Door\" and leave the room.\n";

        String c2APresentBranches = c2aSA + c2aSB + c2aSC;

        String c2ASelectA = "You find the light switch to the left of the door and flip it back on."; //Success Case
        String c2ASelectB = "You don't find the light switch to the right of the door. Do you want to search left of the door or somewhere else?\n";
        String c2ASelectC = "You try to open the door on the North wall to get out of the room, and get back to the light. But it won't budge! Its not even locked.\n";
        c2ASelectC += "Its like there is someone or something on the other side of the door keeping you from escaping. There is something seriously wrong with this house.\n";
        c2ASelectC += "It would be best to leave the door alone for right now. You could try searching to the left or right of the door for a light switch or searching somewhere else. What do you want to do?\n";

        this.choice2A = new Choice(c2APresentBranches, c2ASelectA, c2ASelectB, c2ASelectC);

        //START OF CHOICE 3B (Just went Right of the door)
        String c3bSA = "A.) Search to the left of the door.\n";
        String c3bSB = "B.) Search somewhere else.\n";
        String c3bSC = "C.) Attempt to open the door.\n";

        String c3BPresentBranches = c3bSA + c3bSB + c3bSC;

        String c3BSelectA = "You find the light switch to the left of the door and flip it back on."; //Success Case
        String c3BSelectB = "You find your way back to the center of the Kitchen. You can't see anything in the room. You either need to find an exit or find a light switch. What do you want to do?\n";
        c3BSelectB += "Which wall would you like to search for the light switch?\n";
        String c3BSelectC = c2ASelectC;

        this.choice3B = new Choice(c3BPresentBranches, c3BSelectA, c3BSelectB, c3BSelectC);

        //START OF CHOICE 3C (Just tried to go through the door)
        String c3cSA = "A.) Search to the left of the door.\n";
        String c3cSB = "B.) Search to the right of the door.\n";
        String c3cSC = "C.) Search somewhere else.\n";

        String c3CBranches = c3cSA + c3cSB + c3cSC;

        String c3CSelectA = c3BSelectA; //Find the light switch left of door.
        String c3CSelectB = c2ASelectB; //Search to the right of the door.
        String c3CSelectC = c3BSelectB; //Back to center of kitchen.

        this.choice3C = new Choice(c3CBranches, c3CSelectA, c3CSelectB, c3CSelectC);
    }


    //This method returns a response String if the player made an input that corresponded with one of the options.
    public String optionLogic(String input){
        String response = null;
        Choice currentChoice = this.getCurrentChoice();
        if(currentChoice.equals(choice1)){
            if(input.equals("a")){
                response = choice1.getSelectA();
                this.setCurrentChoice(choice2A);
            }
            else if(input.equals("b")){
                response = choice1.getSelectB();
            }
            else if(input.equals("c")){
                response = choice1.getSelectC();
            }
            else if(input.equals("d")){
                response = choice1.getSelectD();
                //YOU WOULD PROBABLY ACTUALLY TURN THE LIGHT SWITCH ON HERE
                setContinueRunningChoices(false); //Success
                this.usedNorthSwitch = false; //They used the Western Wall switch.
            }
        }
        //CHOICE2A (Just tried searching the north wall)
        else if(currentChoice.equals(choice2A)){
            if(input.equals("a")){
                response = choice2A.getSelectA();
                //YOU WOULD PROBABLY ACTUALLY WANT TO TURN THE LIGHT SWITCH ON HERE
                setContinueRunningChoices(false); //Success
            }
            else if(input.equals("b")){
                response = choice2A.getSelectB();
                setCurrentChoice(choice3B);
            }
            else if(input.equals("c")){
                response = choice2A.getSelectC();
                setCurrentChoice(choice3C);
            }
        }
        //CHOICE3B (Just tried searching to the Right of the door)
        else if(currentChoice.equals(choice3B)){
            if(input.equals("a")){
                response = choice3B.getSelectA();
                //YOU WOULD PROBABLY ACTUALLY WANT TO TURN THE LIGHT SWITCH ON HERE
                setContinueRunningChoices(false); //Success
            }
            else if(input.equals("b")){
                response = choice3B.getSelectB();
                setCurrentChoice(choice1);
            }
            else if(input.equals("c")){
                response = choice3B.getSelectC();
                setCurrentChoice(choice3C);
            }
        }
        //CHOICE3C (Just tried going through the door)
        if(currentChoice.equals(choice3C)){
            if(input.equals("a")){
                response = choice3C.getSelectA();
                //YOU WOULD PROBABLY ACTUALLY WANT TO TURN THE LIGHT SWITCH ON HERE
                setContinueRunningChoices(false); //Success
            }
            else if(input.equals("b")){
                response = choice3C.getSelectB();
                setCurrentChoice(choice3B);
            }
            else if(input.equals("c")){
                response = choice3C.getSelectC();
                setCurrentChoice(choice1);
            }
        }
        return response;
    }

    //Returns the value for usedNorthSwitch
    public boolean getUsedNorthSwitch(){
        return this.usedNorthSwitch;
    }
}
