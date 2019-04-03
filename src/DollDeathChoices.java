import java.util.ArrayList;
public class DollDeathChoices extends MultipleChoice {
    private Choice choice1;
    private Choice choice2;
    private Item chair;
    private Item doll;

    public DollDeathChoices(Player player1, Item chair, Item doll){
        super(player1);
        this.chair = chair;
        this.doll = doll;

        String initialPrompt = "You feel something strike hard against the back of your head, and you fall to the floor unconscious.\n\n";
        initialPrompt += "You aren't sure how long you stayed unconscious, but when you wake up you find yourself bound to the " + chair.quote() + " in the " + this.getPlayer().getLocation().quote() + ".\n";
        initialPrompt += "You feel a piercing pain in the area around your right eye. You don't appear to be able to open it, or see anything through it.\n";
        initialPrompt += "The pain is so great that it almost drowns out the dull pain you feel in the back of your head where you were struck.\n";
        initialPrompt += "You can either struggle against the ropes that are tying you up, or call for help. What do you want to do?\n";

        this.setInitialPrompt(initialPrompt);

        //START OF CHOICE1

        String c1a = "A). Try to break out of the ropes that are tying you to the " + chair.quote() + ".\n";
        String c1b = "B). Call for help.\n";

        String c1Branches = c1a + c1b;

        String c1SelectA = "You struggle against the ropes that bind you to the " + chair.quote() + " but it is in vain. You can't escape.\n";
        String c1SelectB = "You desperately try to cry out for help. You know it is probably hopeless. But you have to try to do something.\n";

        String followup = "You are surprised to hear a high pitched voice respond to you. It would almost sound feminine if it didn't have such an inhuman quality to it.\n";
        followup += "\"Why are you struggling so much? I'm only trying to help you\" it says. You can't pinpoint exactly where the voice is coming from.\n";

        this.choice1 = new Choice(c1Branches, c1SelectA + followup, c1SelectB + followup);
        this.setCurrentChoice(choice1);

        //START OF CHOICE2

        String c2a = "A). \"Why are you doing this to me?\"\n";
        String c2b = "B). \"What have you done to my eye?\"\n";
        String c2c = "C). \"What are you?\"\n";

        String c2Branches = c2a + c2b + c2c;

        String c2xxSelectA = "You hear a \"giggle\" in response to your question. \"I'm just trying to help you. What I'm doing isn't half as bad as what she would have done to you\" it says in a childlike voice that seems to echo from all around you.\n";
        c2xxSelectA += "You see the " + doll.quote() + " crawl out from beneath the " + chair.quote() + " and hop on your lap.\n";
        c2xxSelectA += "It's black frilly dress is now covered in blood, and so is the sharp knife that it is holding in its right hand.\n";
        String c2xxSelectB = "You hear a \"giggle\" in response to your question. You see the " + doll.quote() + " crawl out from beneath the " + chair.quote() + " and hop on your lap.\n";
        c2xxSelectB += "It's black frilly dress is now covered in blood, and so is the sharp knife that it is holding in its right hand. With its left hand it points to its black button eyes.\n";
        String c2xxSelectC = "You hear a \"giggle\" in response to your question. You see the " + doll.quote() + " crawl out from beneath the " + chair.quote() + " and hop on your lap.\n";
        c2xxSelectC += "It's black frilly dress is now covered in blood, and so is the sharp knife that it is holding in its right hand.\n";

        this.choice2 = new Choice(c2Branches, c2xxSelectA, c2xxSelectB, c2xxSelectC);
    }

    //The selection in Choice1 will always lead to Choice2. The selection in Choice2 will always end the MultipleChoice.
    public String optionLogic(String input){
        String response = null;
        if(this.getCurrentChoice().equals(this.choice1)){
            switch(input){
                case "a":
                    response = choice1.getSelectA();
                    this.setCurrentChoice(choice2);
                    break;
                case "b":
                    response = choice1.getSelectB();
                    this.setCurrentChoice(choice2);
                    break;
                case "c":
                    response = choice1.getSelectC();
                    this.setCurrentChoice(choice2);
                    break;
            }
        }
        else if(this.getCurrentChoice().equals(this.choice2)){
            switch(input){
                case "a":
                    response = choice2.getSelectA();
                    this.setContinueRunningChoices(false);
                    break;
                case "b":
                    response = choice2.getSelectB();
                    this.setContinueRunningChoices(false);
                    break;
                case "c":
                    response = choice2.getSelectC();
                    this.setContinueRunningChoices(false);
                    break;
            }
        }
        return response;
    }

}
