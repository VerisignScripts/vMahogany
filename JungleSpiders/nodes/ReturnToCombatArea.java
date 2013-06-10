package org.aidan.scripts.JungleSpiders.nodes;

import org.aidan.scripts.JungleSpiders.JSKR;
import org.aidan.scripts.JungleSpiders.util.Vars;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;


public class ReturnToCombatArea extends Node {

    @Override
    public boolean activate() {
        return Vars.spiderTile.isOnMap() && !Vars.combatArea.contains(Players.getLocal())
                && Inventory.contains(Vars.lobster);
    }

    @Override
    public void execute() {
        JSKR.s = "Executing Fail-safe";
        if (Vars.spiderTile.clickOnMap()) {
            Timer t = new Timer(3200);
            while (t.isRunning() && Players.getLocal().isMoving()){
                Task.sleep(300, 500);
            }
        }
    }
}