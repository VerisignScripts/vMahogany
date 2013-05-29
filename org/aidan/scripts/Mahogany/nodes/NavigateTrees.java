package org.aidan.scripts.Mahogany.nodes;

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
        return !Inventory.isFull() && Inventory.contains(Variables.sticks) && !Variables.treeArea.contains(Players.getLocal());
    }

    @Override
    public void execute() {
        SceneObject Gate = SceneEntities.getNearest(9038);
        if (Calculations.distanceTo(Variables.bankGateTile) > 6 && Variables.pathToTrees.validate()) {  // add checks like path.validate() and whatever is in that api
            Variables.pathToTrees.traverse();
        } else if (Gate != null) {
            Gate.interact("Quick-pay(100)");
            sleep(2000,2300);
        }
    }
}