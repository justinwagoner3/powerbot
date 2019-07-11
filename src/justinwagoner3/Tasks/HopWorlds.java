package Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.World;
import java.util.concurrent.Callable;
import static utils.Areas.HILL_GIANT_AREA;
import static utils.Monsters.HILL_GIANT_ID;
import static utils.Food.LOBSTER_ID;

public class HopWorlds extends Task{

    private int numPlayersToHopWorld;
    private static int MONSTER_ID[];
    private static int FOOD_ID;

    public HopWorlds(ClientContext ctx, int numPlayersToHopWorld){
        super(ctx);
        this.numPlayersToHopWorld = numPlayersToHopWorld;
        MONSTER_ID = HILL_GIANT_ID;
        FOOD_ID = LOBSTER_ID;
    }
    @Override
    public boolean activate() {
        return players.within(HILL_GIANT_AREA).select().size() >= numPlayersToHopWorld && inventory.select().id(FOOD_ID).count() > 1 && !npcs.select().id(MONSTER_ID).isEmpty();
    }

    @Override
    public boolean execute() {
        System.out.println("Thinks should hop world");
        // wait until finish combat
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !players.local().interacting().valid() && !players.local().healthBarVisible()
                        && players.local().animation() == -1 && !players.local().inMotion();
            }
        },1000,1000);
        System.out.println("all conditions met");
        Condition.sleep(5000);
        worlds.open();
        World joiningWorld = worlds.select().types(World.Type.FREE).joinable().population(600).shuffle().peek();
        //while(!joiningWorld.hop())
        if(!joiningWorld.hop()){
            Condition.sleep(Random.nextInt(1320,1864));
            joiningWorld.hop();
        }

        Condition.sleep(Random.nextInt(6408,6984));
        // need to return back to my backpack after checking the number of players in this world
        if(players.within(HILL_GIANT_AREA).select().size() < numPlayersToHopWorld){
            if(!game.tab(Game.Tab.INVENTORY))
                game.tab(Game.Tab.INVENTORY);
        }

        return false;
    }
}
