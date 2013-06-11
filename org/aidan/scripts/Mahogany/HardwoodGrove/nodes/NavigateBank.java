package org.aidan.scripts.Mahogany.HardwoodGrove.nodes;


import org.aidan.scripts.Mahogany.HardwoodGrove.Mahogs;
import org.aidan.scripts.Mahogany.HardwoodGrove.util.Constants;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;



public class NavigateBank extends Node {

    @Override
    public boolean activate() {
        return Inventory.isFull() && Inventory.contains(Constants.STICKS)
                && (Calculations.distanceTo(Constants.BANK_TILE) > 8);
    }

    @Override
    public void execute() {
        SceneObject gate = SceneEntities.getNearest(9038);
        Mahogs.s = "Going to the Bank";
        if (gate != null && gate.validate() && gate.isOnScreen() && Constants.TREE_AREA.contains(Players.getLocal())) {
            if (gate.interact("Quick-pay(100)")) {
                Mahogs.t.reset();
                while (Mahogs.t.isRunning() && Constants.TREE_AREA.contains(Players.getLocal())){
                    Task.sleep(300,500);
                }
            }
        } else if (gate != null && !gate.isOnScreen() && Constants.TREE_AREA.contains(Players.getLocal())) {
            Camera.turnTo(gate);

        }
        if (!Constants.TREE_AREA.contains(Players.getLocal())) {
            Constants.PATH_TO_BANK.traverse();
        }
    }
}



