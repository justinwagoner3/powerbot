package scripts;

import Tasks.*;
import Tasks.Traversal.BankToHillGiants;
import Tasks.Traversal.HillGiantsToBank;
import Tasks.Traversal.TraverseAtoB;
import org.powerbot.script.*;
import org.powerbot.script.rt4.ClientContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

//TODO the problem is the areas im using, they are overlapping

@Script.Manifest(name="Hill Giant Killer", description="Kills Hill Giants in Edgeville Dungeon", properties = "author=justinwagoner3; topic=1352224; client=4;")

public class HillGiantKiller extends PollingScript<ClientContext> implements PaintListener, MessageListener {

    private ArrayList<Task> taskList;
    public int xpGained;
    //public TraverseAtoB traverseHillGiantsToBank;
    //public TraverseAtoB traverseBankToHillGiants;


    public HillGiantKiller(){
        taskList = new ArrayList<Task>();
        //traverseHillGiantsToBank = new TraverseAtoB(ctx,HILL_GIANTS_TO_VARROACK_WEST_BANK,HILL_GIANTS_TO_VARROACK_WEST_BANK,((ctx.inventory.select().id(FOOD_ID).count() < 1) && (!VARROCK_WEST_BANK_AREA.contains(ctx.players.local()))), false);
        //traverseBankToHillGiants = new TraverseAtoB(ctx,HILL_GIANTS_TO_VARROACK_WEST_BANK,HILL_GIANTS_TO_VARROACK_WEST_BANK,(ctx.npcs.select().id(MONSTER_ID).isEmpty() && ctx.inventory.select().id(FOOD_ID).count() > 1), true);
        //traverseBankToHillGiants = new TraverseAtoB(ctx,HILL_GIANTS_TO_VARROACK_WEST_BANK,HILL_GIANTS_TO_VARROACK_WEST_BANK,ctx.inventory.select().id(FOOD_ID).count() > 1, true); // one argument still doesnt work
        //traverseBankToHillGiants = new TraverseAtoB(ctx,HILL_GIANTS_TO_VARROACK_WEST_BANK,HILL_GIANTS_TO_VARROACK_WEST_BANK, evaluateCondition(), true);

    }


    public void start(){
        System.out.println("Starting");
        // TODO add back in Hop Worlds
        taskList.addAll(Arrays.asList(new HopWorlds(ctx, 7), new Attack(ctx), new Heal(ctx), new RunActiveChecker(ctx), new AntiBan(ctx), new BankingWithdraw(ctx), new HillGiantsToBank(ctx), new BankToHillGiants(ctx)));
    }

    public void stop(){
        System.out.println("Stopping");
    }

    public void poll(){
        for (Task task : taskList) {
            if (task.activate())
                task.execute();
        }

    }

    @Override
    public void messaged(MessageEvent messageEvent) {

    }

    @Override
    public void repaint(Graphics graphics) {

    }
}
