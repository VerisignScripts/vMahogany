package org.aidan.scripts.JungleSpiders.nodes;

import org.aidan.scripts.JungleSpiders.JSKR;
import org.aidan.scripts.JungleSpiders.util.Vars;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;

public class GoToSpiders extends Node {


    @Override
    public boolean activate() {
        return Inventory.contains(Vars.FOOD_IDS) && !Vars.SPIDER_TILE.isOnMap()
                && Calculations.distanceTo(Vars.SPIDER_TILE) > 8;
    }

    @Override
    public void execute() {
        JSKR.s = "Executing Walk-To-Spiders";
        if (Vars.SPIDER_TILE.isOnMap()) {
            if (Vars.SPIDER_TILE.clickOnMap()) {
                Timer t = new Timer(8000);
                while (t.isRunning() && Players.getLocal().isMoving()){
                    sleep(300, 500);
                }
            }
        } else {
            Vars.PATH_TO_SPIDERS.traverse();
        }
    }
}
