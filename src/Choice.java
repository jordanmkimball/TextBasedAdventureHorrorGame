public class Choice {
    private int numOfBranches;
    private int choiceNumber;
    private String branches;
    private String selectA;
    private String selectB;
    private String selectC;
    private String selectD;
    public static int choiceCount = 0;


    //CONSTRUCTORS
    public Choice(String branches, String selectA, String selectB){
        this.numOfBranches = 2;
        this.branches = branches;
        this.selectA = selectA;
        this.selectB = selectB;
        choiceCount++;
        this.choiceNumber = choiceCount;
    }

    public Choice(String branches, String selectA, String selectB, String selectC){
        this.numOfBranches = 3;
        this.branches = branches;
        this.selectA = selectA;
        this.selectB = selectB;
        this.selectC = selectC;
        choiceCount++;
        this.choiceNumber = choiceCount;
    }

    public Choice(String branches, String selectA, String selectB, String selectC, String selectD){
        this.numOfBranches = 4;
        this.branches = branches;
        this.selectA = selectA;
        this.selectB = selectB;
        this.selectC = selectC;
        this.selectD = selectD;
        choiceCount++;
        this.choiceNumber = choiceCount;
    }


    //METHODS

    public int getNumOfBranches(){
        return this.numOfBranches;
    }

    public String getBranches(){
        return this.branches;
    }

    public String getSelectA(){
        return this.selectA;
    }

    public String getSelectB(){
        return this.selectB;
    }

    public String getSelectC(){
        return this.selectC;
    }

    public String getSelectD(){
        return this.selectD;
    }

    public boolean equals(Choice B){
        if(this.choiceNumber == B.choiceNumber){
            return true;
        }
        return false;
    }


}
