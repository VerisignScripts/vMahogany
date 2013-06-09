package org.aidan.scripts.JungleSpiders.util;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public class Vars {

    public static final MyTilePath pathToSpiders = new MyTilePath (new Tile[] { new Tile(2610, 3092, 0), new Tile(2606, 3095, 0), new Tile(2607, 3100, 0),
            new Tile(2612, 3101, 0), new Tile(2616, 3104, 0), new Tile(2621, 3105, 0),
            new Tile(2626, 3103, 0), new Tile(2626, 3098, 0), new Tile(2625, 3093, 0),
            new Tile(2627, 3088, 0), new Tile(2632, 3087, 0), new Tile(2637, 3088, 0),
            new Tile(2642, 3087, 0), new Tile(2644, 3092, 0), new Tile(2648, 3095, 0),
            new Tile(2651, 3099, 0), new Tile(2651, 3104, 0), new Tile(2652, 3109, 0),
            new Tile(2657, 3111, 0), new Tile(2662, 3111, 0), new Tile(2667, 3111, 0),
            new Tile(2672, 3111, 0), new Tile(2677, 3110, 0), new Tile(2680, 3106, 0),
            new Tile(2680, 3101, 0), new Tile(2677, 3097, 0), new Tile(2676, 3092, 0) });
    public static final MyTilePath pathToBank = new MyTilePath ( new Tile[] { new Tile(2671, 3089, 0), new Tile(2676, 3090, 0), new Tile(2677, 3095, 0),
            new Tile(2678, 3100, 0), new Tile(2680, 3105, 0), new Tile(2678, 3110, 0),
            new Tile(2673, 3110, 0), new Tile(2668, 3111, 0), new Tile(2663, 3111, 0),
            new Tile(2658, 3111, 0), new Tile(2653, 3111, 0), new Tile(2651, 3106, 0),
            new Tile(2651, 3101, 0), new Tile(2651, 3096, 0), new Tile(2647, 3093, 0),
            new Tile(2643, 3090, 0), new Tile(2639, 3087, 0), new Tile(2634, 3088, 0),
            new Tile(2629, 3089, 0), new Tile(2625, 3092, 0), new Tile(2623, 3097, 0),
            new Tile(2622, 3102, 0), new Tile(2618, 3105, 0), new Tile(2613, 3104, 0),
            new Tile(2609, 3101, 0), new Tile(2606, 3097, 0), new Tile(2607, 3092, 0),
            new Tile(2612, 3091, 0) });
    public static final Tile spiderTile =  new Tile(2671, 3088, 0);
    public static final Tile bankTile =  new Tile(2613, 3092, 0);
    public static final int lobster = 379;
    public static final int carcass = 6291;
    public static final Area combatArea = new Area(new Tile[] { new Tile(2676, 3086, 0), new Tile(2675, 3091, 0), new Tile(2669, 3091, 0),
            new Tile(2667, 3090, 0), new Tile(2667, 3086, 0), new Tile(2674, 3082, 0),
            new Tile(2676, 3085, 0) });
}
