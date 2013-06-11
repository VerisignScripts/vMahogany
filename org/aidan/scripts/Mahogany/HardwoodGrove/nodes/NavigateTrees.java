package org.aidan.scripts.Mahogany.HardwoodGrove.nodes;

import org.aidan.scripts.Mahogany.HardwoodGrove.Mahogs;
import org.aidan.scripts.Mahogany.HardwoodGrove.util.Constants;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class NavigateTrees extends Node {

    @Override
    public boolean activate() {
        return !Inventory.contains(Constants.LOGS) && Inventory.contains(Constants.STICKS)
                && !Constants.TREE_AREA.contains(Players.getLocal());
    }

    @Override
    public void execute() {
        SceneObject gate = SceneEntities.getNearest(9038);
        Mahogs.s = "Walking to trees";
        if (gate != null) {
            if (!gate.isOnScreen()) {
                Constants.PATH_TO_TREES.traverse();
            } else {
                if (gate.interact("Quick-pay(100)")) {
                    Mahogs.t.reset();
                    while (Mahogs.t.isRunning() && !Constants.TREE_AREA.contains(Players.getLocal())){
                        Task.sleep(300, 500);
                    }
                }
            }
        }
    }
}
