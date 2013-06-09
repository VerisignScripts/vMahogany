package org.aidan.scripts.Mahogany.Planker.nodes;

import org.aidan.scripts.Mahogany.Planker.Planking;
import org.aidan.scripts.Mahogany.Planker.util.Vars;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.tab.Inventory;
/**
 * Created with IntelliJ IDEA.
 * User: Aid
 * Date: 02/06/13
 * Time: 01:01
 * To change this template use File | Settings | File Templates.
 */
public class WalkToSawmill extends Node {
    @Override
    public boolean activate() {
        Planking.status = "Activating WalkToSawmill";
        return Inventory.contains(Vars.sLogs, 2) && Inventory.contains(Vars.mLogs, 26)
                && (Calculations.distanceTo(Vars.sawTile) > 5);
    }

    @Override
    public void execute() {
        Planking.status = "Executing WalkToSawmill";
        if (Calculations.distanceTo(Vars.sawTile) > 5) {
            Vars.pathToSawmill.traverse();

        }
    }
}

