package org.aidan.scripts.Mahogany.Planker.nodes;

import org.aidan.scripts.Mahogany.Planker.Planking;
import org.aidan.scripts.Mahogany.Planker.util.Vars;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.interactive.NPC;

/**
 * Created with IntelliJ IDEA.
 * User: Aid
 * Date: 02/06/13
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public class MakePlanks extends Node {
    @Override
    public boolean activate() {
        Planking.status = "Activating Making";

        return Inventory.contains(Vars.sLogs) && Inventory.contains(Vars.mLogs)
                && (Calculations.distanceTo(Vars.sawTile) < 5);
    }

    @Override
    public void execute() {
        Planking.status = "Making";
        NPC planker = NPCs.getNearest(Vars.PLANKER); {
            if (!Widgets.get(1184, 18).visible() && !Widgets.get(1188, 23).visible()) {
                planker.interact("Talk-to");
                sleep(1000,1200);
            } else if (Widgets.get(1184, 18).visible()) {
                Widgets.get(1184, 18).click(true);
                sleep(1000,1300);
            } else if (Widgets.get(1188, 23).visible()) {
                Widgets.get(1188, 23).click(true);
                sleep(1000,1400);
            }
        }
    }
}

