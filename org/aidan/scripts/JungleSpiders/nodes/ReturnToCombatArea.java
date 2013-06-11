package org.aidan.scripts.JungleSpiders.nodes;

import org.aidan.scripts.JungleSpiders.JSKR;
import org.aidan.scripts.JungleSpiders.util.Vars;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;


public class ReturnToCombatArea extends Node {

    @Override
    public boolean activate() {
        return Vars.SPIDER_TILE.isOnMap() && !Vars.COMBAT_AREA.contains(Players.getLocal())
                && Inventory.contains(Vars.FOOD_IDS) && !Players.getLocal().isMoving()
                && Players.getLocal().getInteracting() == null;
    }

    @Override
    public void execute() {
        JSKR.s = "Executing Fail-safe";
        if (Vars.SPIDER_TILE.clickOnMap()) {
            Timer t = new Timer(3200);
            while (t.isRunning() && Players.getLocal().isMoving()){
                sleep(300, 500);
            }
        }
    }
}