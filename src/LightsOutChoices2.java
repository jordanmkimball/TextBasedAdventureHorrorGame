public class LightsOutChoices2 extends MultipleChoice {
    private Choice choice1whichWall;
    private Choice choice2AxNorthWall;
    private Choice choice2DvWestSwitchFail;

    public LightsOutChoices2(Player player1){
        super(player1);

        //Create initial prompt.
        String initialPrompt = "You hear the familiar sound of a light switch flipping off in the room. What is going on? Why is this happening again?\n";
        initialPrompt += "You feel a dark presence in the room with you. You need to either find a way out of the room or flip the lights back on.\n";
        initialPrompt += "What would you like to do?\n";
        this.setInitialPrompt(initialPrompt);

        //START OF CHOICE 1 (Which Wall)
        String c1SA = "A.) Search the North wall for a light switch.\n";
        String c1SB = "B.) Search the East wall for a light switch.\n";
        String c1SC = "C.) Search the South wall for a light switch.\n";
        String c1SD = "D.) Search the West wall for a light switch.\n";

        String c1Branches = c1SA + c1SB + c1SC + c1SD;


        String c1SelectA = "You quickly find the light switch to the left of the North wall and and flip it back on... Nothing happens. You frantically flip the switch back and forth, but the lights do not come back on.\n";
        c1SelectA += "You hear a \"giggle\" come from directly behind you. Then the pitter patter of tiny feet... something else is in the room with you.\n";
        c1SelectA += "You try to open the North door to get out of the room, but there is something on the other side barring you from escaping! There must be another light switch around here somewhere.\n";

        String c1SelectB = "You feel all around the Eastern Wall, but you don't find a light switch, and the door is locked so you can't get out.\nWhich wall would you like to search next?\n";
        String c1SelectC = "The wall is gross and moist as you feel along it with your hand. You search thoroughly, but don't find a light switch.\nWhich wall would you like to search next?\n";

        String c1SelectD = "You quickly find the light switch along the Western wall and flip it back on... Nothing happens. You frantically flip the switch back and forth, but the lights do not come back on.\n";
        c1SelectD += "You hear a \"giggle\" come from directly behind you. Then the pitter patter of tiny feet... something else is in the room with you.\n";
        c1SelectD += "There must be another light switch around here somewhere.\n";


        this.choice1whichWall = new Choice(c1Branches, c1SelectA, c1SelectB, c1SelectC, c1SelectD);
        this.setCurrentChoice(choice1whichWall);

        //START OF CHOICE2A (Post North Wall failure)

        String c2AxxA = "A.) Search the East wall for a light switch.\n";
        String c2AxxB = "B.) Search the South wall for a light switch.\n";
        String c2AxxC = "C.) Search the West wall for a light switch.\n";

        String c2xxBranches = c2AxxA + c2AxxB + c2AxxC;

        String c2xxSelectAxEastWall = c1SelectB;
        String c2xxSelectBxSouthWall = c1SelectC;
        String c2xxSelectCxWestWall = "You feel around the Western Wall of the room for some time, before you finally You manage to find a light switch. You quickly flip the switch.\n";

        this.choice2AxNorthWall = new Choice(c2xxBranches, c2xxSelectAxEastWall, c2xxSelectBxSouthWall, c2xxSelectCxWestWall);

        //START OF CHOICE2D (Post West Wall failure)

        String c2DvvA = "A.) Search the North wall for a light switch.\n";
        String c2DvvB = "B.) Search the East wall for a light switch.\n";
        String c2DvvC = "C.) Search the South wall for a light switch.\n";

        String c2DvvBranches = c2DvvA + c2DvvB + c2DvvC;

        String c2DvvSelectA = "You search the North wall for a light switch. You find one to the left of the door and flip it on.";

        //Presents only the first three branches from the Choice1
        this.choice2DvWestSwitchFail = new Choice(c2DvvBranches, c2DvvSelectA , c1SelectB, c1SelectC);
    }


    //Provides the logic that determines where each player choice leads
    public String optionLogic(String input){
        String response = null;
        Choice currentChoice = this.getCurrentChoice();
        if(currentChoice.equals(choice1whichWall)){
            if(input.equals("a")){
                response = this.choice1whichWall.getSelectA();
                //The first Light Switch the player goes to will always fail.
                this.setCurrentChoice(choice2AxNorthWall);
            }
            else if(input.equals("b")){
                response = this.choice1whichWall.getSelectB();
            }
            else if(input.equals("c")){
                response = this.choice1whichWall.getSelectC();
            }
            else if(input.equals("d")){
                //The first Light Switch the player goes to will fail.
                response = this.choice1whichWall.getSelectD();
                this.setCurrentChoice(choice2DvWestSwitchFail);
            }
        }
        else if(currentChoice.equals(choice2AxNorthWall)){
            if(input.equals("a")){
                response = this.choice2AxNorthWall.getSelectA();
            }
            else if(input.equals("b")){
                response = this.choice2AxNorthWall.getSelectB();
            }
            else if(input.equals("c")){
                response = this.choice2AxNorthWall.getSelectC();
                this.setContinueRunningChoices(false); //Success!
            }
        }
        else if(currentChoice.equals(choice2DvWestSwitchFail)){
            if(input.equals("a")){
                response = this.choice2DvWestSwitchFail.getSelectA();
                this.setContinueRunningChoices(false); //Success!
            }
            else if(input.equals("b")){
                response = this.choice2DvWestSwitchFail.getSelectB();
            }
            else if(input.equals("c")){
                response = this.choice2DvWestSwitchFail.getSelectC();
            }
        }
        return response;
    }

}
