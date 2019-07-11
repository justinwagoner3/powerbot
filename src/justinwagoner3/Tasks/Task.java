package Tasks;

import org.powerbot.script.rt4.ClientContext;

public abstract class Task extends ClientContext {
    public Task(ClientContext ctx) {
        super(ctx);
    }

    public abstract boolean activate();

    public abstract boolean execute();

}
