package org.aidan.scripts.JungleSpiders.nodes;

import org.aidan.scripts.JungleSpiders.util.Vars;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;


public class GoToBank extends Node {


    @Override
    public boolean activate() {
        return !Inventory.contains(Vars.lobster) && Calculations.distanceTo(Vars.bankTile) > 8;
    }

    @Override
    public void execute() {
        if (Vars.bankTile.isOnMap()) {
            if  (Vars.bankTile.clickOnMap()) {
                Timer t = new Timer( 3200);
                t.reset();
                while (t.isRunning() && Players.getLocal().isMoving()){
                    Task.sleep(300, 500);
                }

            }
        } else {
            Vars.pathToBank.traverse();
        }
    }
}