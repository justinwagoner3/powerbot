package Tasks;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;
import utils.Areas;
import static utils.Food.LOBSTER_ID;

import static utils.Areas.VARROCK_WEST_BANK_AREA;

public class BankingWithdraw extends Task {

    private static int FOOD_ID;

    public BankingWithdraw(ClientContext ctx){
        super(ctx);
        FOOD_ID = LOBSTER_ID;
    }


    @Override
    public boolean activate() {
        return VARROCK_WEST_BANK_AREA.getCentralTile().distanceTo(players.local()) < 15 && inventory.select().id(FOOD_ID).count() < 1;
    }

    @Override
    public boolean execute() {
        System.out.println("Banking withdraw");
        if (!bank.opened())
            bank.open();
        bank.withdraw(FOOD_ID, Bank.Amount.ALL);
        //bank.close();
        return true;
    }
}
