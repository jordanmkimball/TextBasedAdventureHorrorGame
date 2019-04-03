abstract class Interactive {
    private String name;
    private Player player;
    private int uniqueID;
    public static int interactiveCount;

    public Interactive(String name, Player player){
        this.name = name;
        this.player = player;
        interactiveCount++;
        uniqueID = interactiveCount;
    }

    //BEHAVIOR

    //Returns the name of the Interactive
    public String getName(){
        return this.name;
    }

    //Returns the name but in quotes
    public String quote(){
        return "\"" + this.name + "\"";
    }

    //Returns the Player
    public Player getPlayer(){
        return this.player;
    }

    //Returns the uniqueID of the interactive
    public int getUniqueID(){
        return this.uniqueID;
    }

    //Returns the interactiveCount of the interactive
    public int getInteractiveCount(){
        return interactiveCount;
    }

    //Returns true if two Interactive objects have the same uniqueID
    public boolean equals(Interactive e){
        return this.uniqueID == e.uniqueID;
    }

    //ABSTRACT METHODS

    abstract String look();

    abstract String examine();


}
