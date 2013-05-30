package org.aidan.scripts.Mahogany.nodes;


import org.aidan.scripts.Mahogany.Mahogs;
import org.aidan.scripts.Mahogany.util.MyTilePath;
import org.aidan.scripts.Mahogany.util.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.TilePath;
import org.powerbot.game.api.wrappers.node.SceneObject;

import java.awt.*;


public class NavigateBank extends Node {

    @Override
    public boolean activate() {
        return Inventory.isFull() && Inventory.contains(Variables.sticks) && Calculations.distanceTo(Variables.bankTile)
                > 5;
    }

    @Override
    public void execute() {
        SceneObject gate = SceneEntities.getNearest(9038);
        Mahogs.s = "1";
        if (!Variables.treeArea.contains(Players.getLocal())) {
            Mahogs.s = "2";
            Variables.pathToBank.traverse();
        } else {
            if (gate != null && gate.validate() && gate.isOnScreen() && Variables.treeArea.contains(Players.getLocal())) {
                Mahogs.s = "3";
                if (gate.interact("Quick-pay(100)")) {
                    Timer t = new Timer(7500);
                    while (t.isRunning() && gate != null) {
                        Task.sleep(50);
                    }
                }
            } else {
                if (!gate.isOnScreen()) {
                    Mahogs.s = "4";
                    if (Variables.treeGateTile.clickOnMap()) {
                        sleep(2000,2200);
                    }
                }
            }
        }
    }
}

