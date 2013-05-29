package org.aidan.scripts.Mahogany.nodes;

import org.aidan.scripts.Mahogany.util.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class Chopping extends Node {


    @Override
    public boolean activate() {
        return !Inventory.isFull() && Inventory.contains(Variables.sticks) && Variables.treeArea.contains(Players.getLocal());
    }

    @Override
    public void execute() {
        Variables.s = "Chopping logs";
        SceneObject theTree = SceneEntities.getNearest(Variables.tree);
        if (theTree != null) {
            if (theTree.isOnScreen()) {
                if(Players.getLocal().getAnimation() == -1) {
                    theTree.interact("Chop", "Mahogany");
                    sleep(2000,2300);
                }
            } else {
                Camera.turnTo(theTree);
            }
        }
    }
}

