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
                > 5 & Variables.pathToTrees.validate();
    }

    @Override
    public void execute() {
        Mahogs.s = "In walk bank node";
        SceneObject Gate = SceneEntities.getNearest(9038);
        if (Variables.treeArea.contains(Players.getLocal()) && Gate.isOnScreen()) {
            Gate.interact("Quick-pay(100)");
            Task.sleep(3000,3300);
        }  else {
            if (Variables.treeArea.contains(Players.getLocal()) && !Gate.isOnScreen()) {
                Variables.treeGateTile.clickOnMap();
                sleep(1000,1200);
            }  else {
                if (!Variables.treeArea.contains(Players.getLocal())) {
                    Mahogs.s = "Walking to the Bank";
                    Variables.pathToBank.traverse();
                } else {
                    if (Variables.gateArea.contains(Players.getLocal()) && Variables.pathToBank.validate()) {
                        Variables.pathToBank.traverse();
                    }
                }
            }
        }
    }
}

