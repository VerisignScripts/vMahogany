package org.aidan.scripts.Mahogany.HardwoodGrove.util;


import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;




/**
 * Created with IntelliJ IDEA.
 * User: Aid
 * Date: 29/05/13
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
public class Variables {

    public static Timer t = new Timer(3200);
    public static final int STICKS = 6306;
    public static final int TREE = 70076;
    public static final int LOGS[] = {6332, 15290, 6333, 15289};
    public static final Tile SAFE_TILE = new Tile(2815, 3091, 0);
    public static final Tile BANK_TILE = new Tile(2781, 3095, 0);
    public static final MyTilePath PATH_TO_BANK = new MyTilePath (new Tile[] {new Tile(2815, 3086, 0), new Tile(2814, 3091, 0), new Tile(2809, 3093, 0),
            new Tile(2804, 3093, 0), new Tile(2799, 3092, 0), new Tile(2794, 3092, 0),
            new Tile(2789, 3092, 0), new Tile(2784, 3093, 0), new Tile(2781, 3095, 0) });
    public static final MyTilePath PATH_TO_TREES = new MyTilePath(new Tile[] { new Tile(2783, 3093, 0), new Tile(2790, 3090, 0),
            new Tile(2795, 3085, 0),
            new Tile(2801, 3081, 0), new Tile(2808, 3079, 0), new Tile(2815, 3082, 0) });
    public static final Area TREE_AREA = new Area(new Tile[] { new Tile(2817, 3090, 0), new Tile(2817, 3076, 0), new Tile(2828, 3076, 0),
            new Tile(2828, 3089, 0), new Tile(2827, 3090, 0) });
    public static final Area OUT_AREA = new Area(new Tile[] { new Tile(2815, 3085, 0), new Tile(2815, 3081, 0), new Tile(2814, 3082, 0),
            new Tile(2814, 3085, 0) });


}
