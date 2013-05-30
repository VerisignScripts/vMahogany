package org.aidan.scripts.Mahogany.util;


import org.powerbot.core.Bot;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.client.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Aid
 * Date: 29/05/13
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
public class Variables {

    public static int sticks = 6306;
    public static int tree = 70076;
    public static  int logs[] = {6332, 15290, 6333, 15289};
    public static Tile startTile = new Tile(2812, 3077, 0);
    public static Tile bankTile = new Tile(2783, 3094, 0);
    public static Tile treeGateTile = new Tile(2817, 3083,0);
    public static Tile bankGateTile = new Tile(2816, 3083, 0);
    public static MyTilePath pathToBank = new MyTilePath (new Tile[] {new Tile(2811, 3079, 0), new Tile(2806, 3079, 0), new Tile(2801, 3080, 0),
            new Tile(2799, 3085, 0), new Tile(2795, 3088, 0), new Tile(2790, 3089, 0),
            new Tile(2785, 3092, 0), new Tile(2780, 3093, 0) });
    public static MyTilePath pathToTrees = new MyTilePath(new Tile[] { new Tile(2783, 3093, 0), new Tile(2790, 3090, 0),
            new Tile(2795, 3085, 0),
            new Tile(2801, 3081, 0), new Tile(2808, 3079, 0), new Tile(2815, 3082, 0) });
    public static Area gateArea = new Area(new Tile[] { new Tile(2815, 3089, 0), new Tile(2815, 3077, 0) });
    public static Area treeArea = new Area(new Tile[] { new Tile(2827, 3090, 0), new Tile(2817, 3090, 0), new Tile(2817, 3076, 0),
            new Tile(2828, 3076, 0), new Tile(2829, 3089, 0) });
    public static Area outArea = new Area(new Tile[] { new Tile(2816, 3074, 0), new Tile(2816, 3096, 0), new Tile(2800, 3103, 0),
            new Tile(2765, 3102, 0), new Tile(2770, 3074, 0), new Tile(2816, 3076, 0) });


}
