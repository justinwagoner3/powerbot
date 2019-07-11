package Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.Item;

import static utils.Food.LOBSTER_ID;

public class Heal extends Task{

    private static int FOOD_ID;


    public Heal(ClientContext ctx) {
        super(ctx);
        FOOD_ID = LOBSTER_ID;
    }


    @Override
    public boolean activate() {
        return combat.healthPercent() < (Random.nextInt(9,43));
    }

    @Override
    public boolean execute() {
        if(!game.tab().equals(Game.Tab.INVENTORY))
            game.tab(Game.Tab.INVENTORY);
        Item foodToEat = inventory.select().id(FOOD_ID).poll(); // what poll does is takes the top result found in the inventory; what select does is clear the previous result and start fresh (every time it will forget and do another search so it doesnt give old result)
        System.out.println("Eating at health: " + combat.health());
        foodToEat.interact("Eat");
        Condition.sleep(Random.nextInt(654, 784));
        return false;
    }
}
