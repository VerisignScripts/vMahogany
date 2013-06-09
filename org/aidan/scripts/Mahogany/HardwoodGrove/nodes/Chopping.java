package org.aidan.scripts.Mahogany.HardwoodGrove.nodes;

import org.aidan.scripts.Mahogany.HardwoodGrove.Mahogs;
import org.aidan.scripts.Mahogany.HardwoodGrove.util.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class Chopping extends Node {


    @Override
    public boolean activate() {
        return !Inventory.isFull() && Inventory.contains(Variables.STICKS)
                && Variables.TREE_AREA.contains(Players.getLocal());
    }

    @Override
    public void execute() {
        Mahogs.s = "Chopping logs";
        SceneObject theTree = SceneEntities.getNearest(Variables.TREE);
        if (theTree != null) {
            if (theTree.isOnScreen()) {
                if(Players.getLocal().getAnimation() == -1) {
                    if (theTree.interact("Chop", "Mahogany")) {
                        Variables.t.reset();
                        while (Variables.t.isRunning() && theTree != null) {
                        }
                    }
                }
            }  else {
                Camera.turnTo(theTree);
            }
        }
    }
}

