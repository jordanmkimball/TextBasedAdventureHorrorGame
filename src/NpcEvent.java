abstract class NpcEvent extends GameEvent {
    private NPC npc;

    //CONSTRUCTOR
    public NpcEvent(int priority, Player player1){
        super(priority, player1);
        this.npc = null;
    }

    //BEHAVIOR

    //Returns an NpcEvents NPC
    public NPC getNPC(){
        return this.npc;
    }

    //Sets an NpcEvents NPC
    public void setNPC(NPC npc){
        this.npc = npc;
    }
}
