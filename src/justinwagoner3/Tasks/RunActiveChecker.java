package Tasks;

import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

public class RunActiveChecker extends Task{

    public RunActiveChecker(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !movement.running() && movement.energyLevel() > Random.nextInt(10,90);
    }

    @Override
    public boolean execute() {
        return widgets.component(160,24).click();
    }
}
