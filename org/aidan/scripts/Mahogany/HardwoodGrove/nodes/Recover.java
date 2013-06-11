package org.aidan.scripts.Mahogany.HardwoodGrove.nodes;

import org.aidan.scripts.Mahogany.HardwoodGrove.util.Constants;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;


public class Recover extends Node {
    @Override
    public boolean activate() {
        return Constants.OUT_AREA.contains(Players.getLocal()) && Inventory.contains(Constants.LOGS)
                && !Players.getLocal().isMoving();

    }

    @Override
    public void execute() {
        Timer f = new Timer(7000);
        if (f.getElapsed() > 6000 && Constants.OUT_AREA.contains(Players.getLocal())) {
            Constants.SAFE_TILE.clickOnMap();
            sleep(1000,1200);
        }
    }
}
