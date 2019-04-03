import java.util.*;

public class Environment {
    private Player player1;
    private int lastTurn;
    private ArrayList<NPC> npcList;

    public Environment(Player player1){
        this.player1 = player1;
        this.lastTurn = 0;
        npcList = new ArrayList<>();
    }

    //BEHAVIOR

    //Returns a String if there is an eventResponse after the players last input, and a response to that input. Empty otherwise.
    //TO-DO: Consider changing the way Npc Actions are done. Perhaps more than one npc action could show a response on screen.
    public String getEnvironmentResponse(){
        String environmentResponse = "";
        GameEvent eventToRun;
        //Exit early if we don't get response approval
        if(!getResponseApproval()){
            this.lastTurn = player1.getTurnCounter();
            return "";
        }
        NpcEvent npcEventToRun = getNpcEventToRun();
        GameEvent roomEventToRun = getRoomEventToRun();
        NPC npcActionToRun = getNpcActionToRun();
        //Get priorityValues for each of the possible events/actions that might produce a response.
        int npcEventPriority = (npcEventToRun != null) ? npcEventToRun.getPriority() : 100;
        int roomEventPriority = (roomEventToRun != null) ? roomEventToRun.getPriority() : 100;
        int npcActionPriority = (npcActionToRun != null) ? npcActionToRun.getPriority() : 100;
        //Compare the priority of the npcEvent and the roomEvent to see which one becomes the overall eventToRun
        eventToRun = (npcEventPriority > roomEventPriority) ? roomEventToRun : npcEventToRun;
        //Compare the priority of the npcActionToRun and the overall eventToRun to see which one will run
        int eventToRunPriority = (eventToRun != null) ? eventToRun.getPriority() : 100;
        if(eventToRunPriority > npcActionPriority){
            environmentResponse = (npcActionToRun != null) ? npcActionToRun.performAction() : "";
        }
        else{
            environmentResponse = (eventToRun != null) ? eventToRun.getTriggerMsg() : "";
        }
        //Update lastTurn with the current turn.
        this.lastTurn = player1.getTurnCounter();
        return environmentResponse;
    }

    //Determines if any response should be given by Environment based on the players last input.
    //If the input resulted in a turn increase return true. Otherwise return false
    private boolean getResponseApproval(){
        boolean approval = false;
        //There needs to be a difference greater than 1 between the players current turn and the players last turn.
        //We don't want events to occur if players used incorrect syntax on inputParser ect or did something that didn't move up the turnCounter.
        int difference = this.player1.getTurnCounter() - this.lastTurn;
        if(difference > 0){
            approval = true;
        }
        return approval;
    }

    //Returns the NpcEvent with the highest priority (lowest value) of uncompleted, triggered events.
    private NpcEvent getNpcEventToRun(){
        NpcEvent eventToRun = null;
        //For each NPC in this Environments npcList.
        for(NPC npc : this.npcList){
            //And For each NpcEvent in the npc npcEventList
            for (NpcEvent event : npc.getNpcEvents()){
                //If the event has not yet been completed and is being triggered
                if(!event.getCompletedStatus() && event.getTriggerStatus()){
                    //If eventToRun is null. The triggered event becomes the new eventToRun. Stays the same otherwise.
                    eventToRun = (eventToRun == null) ? event : eventToRun;
                    //If there is more than one event that is triggered, then the one with the highest priority (lowest Priority value) becomes the new eventToRun.
                    eventToRun = (eventToRun.getPriority() > event.getPriority()) ? event : eventToRun;
                }
            }
        }
        //The triggered, uncompleted, NpcEvent with the highest priority is automatically run.
        return eventToRun;
    }

    //Returns the NPC with the lowest priority value among Active NPCs whose actions are currently being triggered.
    private NPC getNpcActionToRun(){
        NPC npcToRun = null;
        for(NPC npc : this.npcList){
            //NPC must be in an active state, and the player must have triggered one of their actions.
            if(npc.getActiveStatus() && npc.getActionStatus()){
                //If npcToRun is null, npc becomes the new value of npcToRun. Otherwise if npc has a higher priority (lower Value) then npc replaces npcToRun
                npcToRun = (npcToRun == null) ? npc : npcToRun;
                npcToRun = (npcToRun.getPriority() > npc.getPriority()) ? npc : npcToRun;
            }
        }
        return npcToRun;
    }

    //Returns the currentRooms roomEvent with the lowest priority value, among events uncompleted, triggered events.
    private GameEvent getRoomEventToRun(){
        GameEvent eventToRun = null;
        Room room = this.player1.getLocation();
        for(GameEvent event : room.getRoomEvents()){
            if(!event.getCompletedStatus() && event.getTriggerStatus()){
                //If event to run is null, event becomes new eventToRun value.
                eventToRun = (eventToRun == null) ? event : eventToRun;
                //The event with the highest priority (lowest value) is the new eventToRun.
                eventToRun = (eventToRun.getPriority() > event.getPriority()) ? event : eventToRun;
            }
        }
        return eventToRun;
    }

    public ArrayList<NPC> getNpcList(){
        return this.npcList;
    }

    public void addNpc(NPC npc){
        this.npcList.add(npc);
    }
}



