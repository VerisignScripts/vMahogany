package org.aidan.scripts.Mahogany.Planker.nodes;

import org.aidan.scripts.Mahogany.Planker.Planking;
import org.aidan.scripts.Mahogany.Planker.util.Vars;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.interactive.NPC;

/**
 * Created with IntelliJ IDEA.
 * User: Aid
 * Date: 02/06/13
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
public class WalkToBank extends Node {
    @Override
    public boolean activate() {
        Planking.status = "Activating WalkToBank";
        return Inventory.contains(Vars.planks) && (Calculations.distanceTo(Vars.bankTile) > 5);
    }

    @Override
    public void execute() {
        Planking.status = "Executing WalkToBank";
        Vars.pathToBank.traverse();
    }
}