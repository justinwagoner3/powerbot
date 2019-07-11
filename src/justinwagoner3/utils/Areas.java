package utils;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;

public class Areas {
    public Areas(){}

    public static final Area BARB_OUTPOST_FISH_AREA = new Area(new Tile(3115, 3437, 0), new Tile(3098, 3417, 0));
    public static final Area HILL_GIANT_AREA = new Area(new Tile(3127,9855,0), new Tile(3095,9855,0), new Tile(3095,9825,0), new Tile(3127,9825,0));
    public static final Area HILL_GIANT_SHED_AREA = new Area(new Tile(3113, 3453, 0), new Tile(3117, 3450, 0));
    public static final Area HILL_GIANT_SHED_FRONT_AREA = new Area(new Tile(3122, 3446, 0), new Tile(3113, 3450, 0));
    public static final Area VARROCK_WEST_BANK_AREA = new Area(new Tile(3179,3447,0), new Tile(3179,3432,0), new Tile(3186,3432,0), new Tile(3186,3447));
}
