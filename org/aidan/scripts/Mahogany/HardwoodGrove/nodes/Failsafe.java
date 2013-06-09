package org.aidan.scripts.Mahogany.HardwoodGrove.nodes;

import org.aidan.scripts.Mahogany.HardwoodGrove.util.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;


public class Failsafe extends Node {
    @Override
    public boolean activate() {
        return Variables.OUT_AREA.contains(Players.getLocal()) && Inventory.contains(Variables.LOGS)
                && !Players.getLocal().isMoving();

    }

    @Override
    public void execute() {
        Variables.t.reset();
        if (Variables.t.getElapsed() > 6000 && Variables.OUT_AREA.contains(Players.getLocal())) {
            Variables.SAFE_TILE.clickOnMap();
            sleep(1000,1200);
        }
    }
}
