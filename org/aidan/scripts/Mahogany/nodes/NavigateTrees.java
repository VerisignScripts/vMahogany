package org.aidan.scripts.Mahogany.nodes;

import org.aidan.scripts.Mahogany.Mahogs;
import org.aidan.scripts.Mahogany.util.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * Created with IntelliJ IDEA.
 * User: Aid
 * Date: 29/05/13
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
public class NavigateTrees extends Node {

    @Override
    public boolean activate() {
        return !Inventory.contains(Variables.logs) && Inventory.contains(Variables.sticks) && !Variables.treeArea.contains(Players.getLocal());
    }

    @Override
    public void execute() {
        SceneObject gate = SceneEntities.getNearest(9038);
        Mahogs.s = "Walking to trees";
        if (gate != null) {
            if (!gate.isOnScreen()) {
                Variables.pathToTrees.traverse();
            } else {
                gate.interact("Quick-pay(100)");
                sleep(1000,1200);
            }
        }
    }
}
