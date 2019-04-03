import java.util.*;
abstract class MultipleChoice {
    private InputParser inputParser;
    private Scanner scanner;
    private Player player1;
    private Environment environment;
    private Choice currentChoice;
    private String initialPrompt;
    private boolean increaseActionCounter;
    private boolean continueRunningChoices;


    //CONSTRUCTORS
    public MultipleChoice(Player player1){
        this.inputParser = new InputParser();
        this.scanner = new Scanner(System.in);
        this.player1 = player1;
        this.environment = player1.getEnvironment();
        this.increaseActionCounter = false;
        this.continueRunningChoices = true;
        this.initialPrompt = "";
    }

    public MultipleChoice(Player player1, boolean increaseActionCounter){
        this.inputParser = new InputParser();
        this.scanner = new Scanner(System.in);
        this.player1 = player1;
        this.environment = player1.getEnvironment();
        this.increaseActionCounter = increaseActionCounter;
        this.continueRunningChoices = true;
        this.initialPrompt = "";
    }


    //METHODS

    //Returns the current Choice that the player is facing
    public Choice getCurrentChoice(){
        return this.currentChoice;
    }

    //Set the current Choice that the player is facing
    public void setCurrentChoice(Choice newChoice){
        this.currentChoice = newChoice;
    }

    //Get initialPrompt
    public String getInitialPrompt(){
        return this.initialPrompt;
    }

    //Set initialPrompt
    public void setInitialPrompt(String initialPrompt){
        this.initialPrompt = initialPrompt;
    }

    //Returns the player
    public Player getPlayer(){
        return this.player1;
    }

    //Returns whether the MultipleChoice is to continue running.
    public boolean getContinueRunningChoices(){
        return this.continueRunningChoices;
    }

    //Sets the value of continueRunningChoices
    public void setContinueRunningChoices(boolean continueRunningChoices){
        this.continueRunningChoices = continueRunningChoices;
    }

    //Used to actually run the MultipleChoice object.
    public void runMultipleChoice(){
        //Print the initialPrompt
        System.out.println("\n" + this.initialPrompt);
        //Start a loop to handle the getting and printing of Choice responses, and move the player through the list of choices.
        while(this.continueRunningChoices){
            System.out.println(currentChoice.getBranches());
            String input = scanner.nextLine();
            input = inputParser.parseOptions(input);
            String response = optionLogic(input);
            if(response == null){
                String errorResponse = inputNotRecognizedHandler(input);
                System.out.println(errorResponse);
            }
            else{
                System.out.println(response);
                //If increaseActionCounter is true then we increase the action counter when the optionLogic response != null.
                if(increaseActionCounter){
                    player1.increaseActionCounter();
                    String environmentResponse = environment.getEnvironmentResponse();
                    if(!environmentResponse.isEmpty()){
                        environmentResponse = "\n" + environmentResponse;
                        System.out.println(environmentResponse);
                    }
                }
            }
        }
    }


    public String inputNotRecognizedHandler(String input){
        String response = null;
        if(input.equals("null")){
            return "I'm not sure what selection you were trying to make. Please type the letter of the action you want to take or type 'help' for more assistance.";
        }
        else if(input.equals("c") || input.equals("d")){
            response = "'" + input.toUpperCase() + "'" + " is not one of the available options.";
            if(this.currentChoice.getNumOfBranches() == 2){
                response += "Please type either 'A' or 'B' to make your selection.";
            }
            else if(this.currentChoice.getNumOfBranches() == 3){
                response += "Please type either 'A', 'B', or 'C' to make your selection";
            }
        }
        else if(input.equals("help")){
            //NOT IMPLEMENTED
            response = "PRINT A HELPFUL LITTLE TUTORIAL ON HOW TO USE MultipleChoice";
        }
        else if(input.equals("//exit")){
            response = "";
            this.continueRunningChoices = false;
            player1.setContinueGame(false);
        }
        else{
            response = "I'm not sure what selection you were trying to make. Please type the letter of the action you want to take or type 'help' for more assistance.";
        }
        return response;
    }




    //ABSTRACT METHODS

    //This method returns a response String if the player made an input that corresponded with one of the options.
    abstract String optionLogic(String input);
}
