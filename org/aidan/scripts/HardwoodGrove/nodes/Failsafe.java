package org.aidan.scripts.Mahogany.HardwoodGrove.nodes;

import org.aidan.scripts.Mahogany.HardwoodGrove.util.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;


public class Failsafe extends Node {
    @Override
    public boolean activate() {
        return Variables.outArea.contains(Players.getLocal()) && Inventory.contains(Variables.logs)
                && !Players.getLocal().isMoving();

    }

    @Override
    public void execute() {
        Timer t = new Timer(12000);
        if (t.getElapsed() > 6000 && Variables.outArea.contains(Players.getLocal())) {
            Variables.safeTile.clickOnMap();
            sleep(1000,1200);
        }
    }
}
