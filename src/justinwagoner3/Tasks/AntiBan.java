package Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.*;

public class AntiBan extends Task{


    public AntiBan(ClientContext ctx){
        super(ctx);
    }

    @Override
    public boolean activate(){
        return (Random.nextInt(1,5000) == 1);
    }

    @Override
    public boolean execute(){
        System.out.println("activate antiban");
        if(game.tab(Game.Tab.STATS)) {
            switch(combat.style()){
                case ACCURATE:
                    widgets.component(320,1).hover();
                    Condition.sleep(Random.nextInt(1254,1982));
                    break;
                case AGGRESSIVE:
                    widgets.component(320,2).hover();
                    Condition.sleep(Random.nextInt(1254,1982));
                    break;
                case DEFENSIVE:
                    widgets.component(320,3).hover();
                    Condition.sleep(Random.nextInt(1254,1982));
                    break;
                default:
                    widgets.component(320,9).hover();
                    Condition.sleep(Random.nextInt(1254,1982));
                    break;
            }
        }
        if(!game.tab(Game.Tab.INVENTORY))
            game.tab(Game.Tab.INVENTORY);
        widgets.component(122,1).hover();
        return true;
    }
}
