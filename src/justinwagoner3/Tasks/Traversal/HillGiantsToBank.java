package Tasks.Traversal;

import Tasks.Task;
import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.TilePath;
import static utils.Traversal.HILL_GIANTS_TO_VARROCK_WEST_BANK;
import static utils.Areas.HILL_GIANT_SHED_FRONT_AREA;
import static utils.Areas.HILL_GIANT_SHED_AREA;
import static utils.Areas.VARROCK_WEST_BANK_AREA;
import static utils.Food.LOBSTER_ID;
import static utils.Monsters.HILL_GIANT_ID;

import java.util.concurrent.Callable;


public class HillGiantsToBank extends Task {

    private static int FOOD_ID;
    private static int MONSTER_ID[];

    // TODO switch this to an array once there's more than just a few
    public TilePath pathToWalk;

    public HillGiantsToBank(ClientContext ctx) {
        super(ctx);
        FOOD_ID = LOBSTER_ID;
        MONSTER_ID = HILL_GIANT_ID;
    }

    @Override
    public boolean activate() {
        return inventory.select().id(FOOD_ID).count() < 1 && VARROCK_WEST_BANK_AREA.getCentralTile().distanceTo(players.local()) > 15;
    }

    @Override
    public boolean execute() {
        System.out.println("About to traverse" + Random.nextDouble());
        ChooseWalkingPaths();
        System.out.println("Not Reverse");
        if (!npcs.select().id(MONSTER_ID).isEmpty()) {
            GameObject ladder = objects.select().id(17385).nearest().peek();
            if (!ladder.inViewport())
                camera.turnTo(ladder);
            if (ladder.inViewport()) {
                if(!ladder.interact("Climb-up"))
                    pathToWalk.traverse();
                Condition.wait(new Callable<Boolean>() {

                    @Override
                    public Boolean call() throws Exception {
                        return HILL_GIANT_SHED_AREA.contains(players.local());
                    }
                }, 100, 30);
            }
            else
                pathToWalk.traverse();
        }
        else if (HILL_GIANT_SHED_AREA.contains(players.local())) {
            GameObject door = objects.select().id(1804).peek();
            if (door.inViewport()) {
                door.interact("Open");
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return HILL_GIANT_SHED_FRONT_AREA.contains(players.local());
                    }
                },100,40);
            }
        }
        else
            pathToWalk.traverse();
        return true;
    }

    private void ChooseWalkingPaths(){

        switch(Random.nextInt(1,2)){
            case 1:
                pathToWalk = movement.newTilePath(HILL_GIANTS_TO_VARROCK_WEST_BANK).randomize(1,1); // TODO unsure on how much i can randomize, maybe make smaller steps in between with higher randomization, could randomize more with more paths and constantly switching paths to find a valid next step
                break;
            case 2:
                pathToWalk = movement.newTilePath(HILL_GIANTS_TO_VARROCK_WEST_BANK).randomize(1,1); // TODO unsure on how much i can randomize, maybe make smaller steps in between with higher randomization, could randomize more with more paths and constantly switching paths to find a valid next step
                break;
            default:
                break;
        }
    }

}


