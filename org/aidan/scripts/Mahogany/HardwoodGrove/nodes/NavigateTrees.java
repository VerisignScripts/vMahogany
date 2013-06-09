package org.aidan.scripts.Mahogany.HardwoodGrove.nodes;

import org.aidan.scripts.Mahogany.HardwoodGrove.Mahogs;
import org.aidan.scripts.Mahogany.HardwoodGrove.util.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class NavigateTrees extends Node {

    @Override
    public boolean activate() {
        return !Inventory.contains(Variables.LOGS) && Inventory.contains(Variables.STICKS) && !Variables.TREE_AREA.contains(Players.getLocal());
    }

    @Override
    public void execute() {
        SceneObject gate = SceneEntities.getNearest(9038);
        Mahogs.s = "Walking to trees";
        if (gate != null) {
            if (!gate.isOnScreen()) {
                Variables.PATH_TO_TREES.traverse();
            } else {
                if (gate.interact("Quick-pay(100)")) {
                    Variables.t.reset();
                    while (Variables.t.isRunning() && !Variables.TREE_AREA.contains(Players.getLocal())){
                        Task.sleep(300, 500);
                    }
                }
            }
        }
    }
}
