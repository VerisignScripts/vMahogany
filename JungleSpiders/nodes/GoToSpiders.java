package org.aidan.scripts.JungleSpiders.nodes;

import org.aidan.scripts.JungleSpiders.JSKR;
import org.aidan.scripts.JungleSpiders.util.Vars;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;

public class GoToSpiders extends Node {


    @Override
    public boolean activate() {
        return Inventory.contains(Vars.lobster) && Calculations.distanceTo(Vars.spiderTile) > 8;
    }

    @Override
    public void execute() {
        JSKR.s = "Executing Walk-To-Spiders";
        if (Vars.spiderTile.isOnMap()) {
            if (Vars.spiderTile.clickOnMap()) {
                Timer t = new Timer(8000);
                while (t.isRunning() && Players.getLocal().isMoving()){
                    Task.sleep(300, 500);
                }
            }
        } else {
            Vars.pathToSpiders.traverse();
        }
    }
}
