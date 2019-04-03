import java.util.*;

public class MainGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputParser inputParser = new InputParser();
        System.out.println("This is a short text based adventure horror game:\n");
        System.out.println(inputParser.keyWordHandler() + "\n"); //Print a list of the games keywords.

        //Create the Player and the GameWorld. Retrieve the environment and startLocation from GameWorld
        Player player1 = new Player("Caroline");
        GameWorld gameWorld = new GameWorld(player1);
        Environment environment = gameWorld.getEnvironment();
        Room startLocation = gameWorld.getStartLocation();

        //Start the game by giving a description of the current Room
        System.out.println(startLocation.look());


        //RUNNING THE ACTUAL GAME
        while(player1.getContinueGame()){
            String userInput = scanner.nextLine();
            //Print the direct response to the players input
            System.out.println(inputParser.parseInput(userInput, player1));
            String environmentResponse = environment.getEnvironmentResponse();
            //If there is an environment response this turn based on GameEvents or NPC actions, print this response.
            if(!environmentResponse.isEmpty()){
                environmentResponse = "\n" + environmentResponse;
                System.out.println(environmentResponse);
            }
            //If the Player dies, print Game Over.
            if(!player1.getAliveStatus()){
                System.out.println("\nGAME OVER");
                player1.setContinueGame(false);
            }
        }
    }
}

