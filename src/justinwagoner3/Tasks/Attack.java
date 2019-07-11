package Tasks;

import org.powerbot.script.*;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import java.util.concurrent.Callable;
import static utils.Monsters.COW_ID;
import static utils.Monsters.HILL_GIANT_ID;
import static utils.Areas.HILL_GIANT_AREA;
import static utils.Food.LOBSTER_ID;


//TODO needs to turn camera while in combat if no other monsters are visible
//TODO turn the above in a Task - "CheckTurnCameraNextMonster"
//TODO got caught when i leveled up, had an enemy targeted (player would turn toward moving enemy) could activate attack
//TODO can you messaged to set a flag for activating attack when clicking on enemy already in combat
//TODO could maybe just create an area of where the monsters are and always keep the camera pointed to a center point, thay way monsters are always in view
public class Attack extends Task{

    private static int MONSTER_ID[];
    private static int FOOD_ID;


    public Attack(ClientContext ctx) {
        super(ctx);
        MONSTER_ID = HILL_GIANT_ID;
        FOOD_ID = LOBSTER_ID;
    }


    @Override
    public boolean activate() {
        if(inventory.select().id(FOOD_ID).count() < 1)
            return false;
        if(players.within(HILL_GIANT_AREA).select().size() >= 7)
            return false;

        if(!HILL_GIANT_AREA.contains(players.local()))
            return false;
        if(players.local().interacting().healthBarVisible() && players.local().interacting().healthPercent() == 0){
            System.out.println("Thinks enemy health bar is  visible and not == 0 ");
            return true;
        }
        if(!players.local().interacting().valid() && players.local().animation() == -1){
        //if(!players.local().healthBarVisible() && !players.local().inMotion()){
            Condition.sleep(Random.nextInt(362,484));
            if(!players.local().healthBarVisible() && !players.local().inMotion()) {
                System.out.println("Thinks my health bar is not visisble and not in motion");
                return true;
            }
        }
        return false;
        //return (players.local().interacting().healthBarVisible() && players.local().interacting().healthPercent() == 0) || !players.local().healthBarVisible();
    }

    @Override
    public boolean execute() {
        Condition.sleep(Random.nextInt(243,320));
        // TODO modify filter to first look for nearest that is current in viewpoint
        Npc monsterToKill = npcs.select().id(MONSTER_ID).select(new Filter<Npc>() {
            @Override
            public boolean accept(Npc npc) {
                if(!npc.interacting().valid()){ // if the npc is not interacting
                    if(npc.healthBarVisible()){ // if the health bar is visible
                        return false; // dont add to the list
                    }
                    else{
                        return true;
                    }
                }
                return false;
            }
            @Override
            public boolean test(Npc npc) {
                return false; // TODO not sure if this should be true or false?
            }
        }).nearest().poll(); // query NPCs on a new query cache, filter only the right monster IDs, sort from neareat to furtherst, select the top one because it will be the nearest
        if(!monsterToKill.inViewport())
            camera.turnTo(monsterToKill, Random.nextInt(5,10));
        monsterToKill.interact("Attack");
        //TODO if statement: no need for this wait if the click did not register as "Red"
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !players.local().inMotion();
            }
        },100, 200); // check every 200 ms, wait a max of 200 x 200
        Condition.sleep(Random.nextInt(624,1230));
        monsterToKill = npcs.select().id(MONSTER_ID).select(new Filter<Npc>() {
            @Override
            public boolean accept(Npc npc) {
                if(!npc.interacting().valid()){ // if the npc is not interacting
                    if(npc.healthBarVisible()){ // if the health bar is visible
                        return false; // dont add to the list
                    }
                    else{
                        return true;
                    }
                }
                return false;
            }
            @Override
            public boolean test(Npc npc) {
                return false;
            }
        }).nearest().limit(1,1).poll(); // query NPCs on a new query cache, filter only the right monster IDs, sort from neareat to furtherst, select the top one because it will be the nearest
        if(!monsterToKill.inViewport())
            camera.turnTo(monsterToKill, Random.nextInt(5,10));
        return false;
    }
}
