package org.aidan.scripts.Mahogany.nodes;


import org.aidan.scripts.Mahogany.Mahogs;
import org.aidan.scripts.Mahogany.util.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class NavigateBank extends Node {

    @Override
    public boolean activate() {
        return Inventory.isFull() && Inventory.contains(Variables.sticks) && Calculations.distanceTo(Variables.bankTile)
                > 5;
    }

    @Override
    public void execute() {
        SceneObject gate = SceneEntities.getNearest(9038);
        Mahogs.s = "0";
        if (gate != null && gate.validate() && gate.isOnScreen() && Variables.treeArea.contains(Players.getLocal())) {
            Mahogs.s = "1";
            gate.interact("Quick-pay(100)");
            sleep(1000,1200);
        } else if (Variables.treeGateTile.isOnMap() && gate != null && !gate.isOnScreen() && Variables.treeArea.contains(Players.getLocal())) {
            Mahogs.s = "2";
            if  (Variables.treeGateTile.clickOnMap()) {
                sleep(2000,2000);
            }
        } else {
            Mahogs.s = "3";
            Variables.pathToBank.traverse();
        }
    }
}

