package Tasks.Traversal;

import Tasks.Task;
import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.TilePath;
import java.util.concurrent.Callable;
import static utils.Traversal.HILL_GIANTS_TO_VARROCK_WEST_BANK;
import static utils.Areas.HILL_GIANT_SHED_AREA;
import static utils.Food.LOBSTER_ID;
import static utils.Monsters.HILL_GIANT_ID;


public class BankToHillGiants extends Task {

    private static int FOOD_ID;
    private static int MONSTER_ID[];

    // TODO switch this to an array once there's more than just a few
    public TilePath pathToWalk;

    public BankToHillGiants(ClientContext ctx) {
        super(ctx);
        FOOD_ID = LOBSTER_ID;
        MONSTER_ID = HILL_GIANT_ID;
    }

    @Override
    public boolean activate() {
        return (npcs.select().id(MONSTER_ID).isEmpty() && inventory.select().id(FOOD_ID).count() > 1);
    }

    @Override
    public boolean execute() {
        System.out.println("About to traverse" + Random.nextDouble());
        ChooseWalkingPaths();
        System.out.println("Reverse");
        GameObject door = objects.select().id(1804).peek();
        if(door.inViewport() && !HILL_GIANT_SHED_AREA.contains(players.local())) {
            door.interact("Open", "Door");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return HILL_GIANT_SHED_AREA.contains(players.local());
                }
            },100, Random.nextInt(30,50));
        }
        else if(HILL_GIANT_SHED_AREA.contains(players.local())){
            GameObject trapDoor = objects.select().id(17384).peek();
            trapDoor.interact("Climb-down");
            Condition.sleep(Random.nextInt(786,946));
        }
        else
            pathToWalk.traverse();
        return true;
    }


    private void ChooseWalkingPaths(){

        switch(Random.nextInt(1,2)){
            case 1:
                pathToWalk = movement.newTilePath(HILL_GIANTS_TO_VARROCK_WEST_BANK).randomize(1,1).reverse(); // TODO unsure on how much i can randomize, maybe make smaller steps in between with higher randomization, could randomize more with more paths and constantly switching paths to find a valid next step
                break;
            case 2:
                pathToWalk = movement.newTilePath(HILL_GIANTS_TO_VARROCK_WEST_BANK).randomize(1,1).reverse(); // TODO unsure on how much i can randomize, maybe make smaller steps in between with higher randomization, could randomize more with more paths and constantly switching paths to find a valid next step
                break;
            default:
                break;
        }
    }

}

