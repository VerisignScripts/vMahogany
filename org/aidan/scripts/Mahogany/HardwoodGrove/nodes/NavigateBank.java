package org.aidan.scripts.Mahogany.HardwoodGrove.nodes;


import org.aidan.scripts.Mahogany.HardwoodGrove.Mahogs;
import org.aidan.scripts.Mahogany.HardwoodGrove.util.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;

import java.awt.*;


public class NavigateBank extends Node {

    @Override
    public boolean activate() {
        return Inventory.isFull() && Inventory.contains(Variables.sticks) && (Calculations.distanceTo(Variables.bankTile) > 8);
    }

    @Override
    public void execute() {
        SceneObject gate = SceneEntities.getNearest(9038);
        Mahogs.s = "1";
        if (gate != null && gate.validate() && gate.isOnScreen() && Variables.treeArea.contains(Players.getLocal())) { // Gate visible + in tree area
            Mahogs.s = "3";
            if (gate.interact("Quick-pay(100)")) {
                Timer t = new Timer( 3200);
                t.reset();
                while (t.isRunning() && Variables.treeArea.contains(Players.getLocal())){
                    Task.sleep(300,500);
                }
            }
        }  else {
            if (gate != null && !gate.isOnScreen() && Variables.treeArea.contains(Players.getLocal())) {    // Gate not visible + in tree area
                Camera.turnTo(gate);
            }
        }
        if (!Variables.treeArea.contains(Players.getLocal())) {  // not in tree area ( no need to open gate)
            Variables.pathToBank.traverse();
        }
    }
}

