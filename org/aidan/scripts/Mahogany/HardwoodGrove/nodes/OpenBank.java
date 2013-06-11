package org.aidan.scripts.Mahogany.HardwoodGrove.nodes;

import org.aidan.scripts.Mahogany.HardwoodGrove.Mahogs;
import org.aidan.scripts.Mahogany.HardwoodGrove.util.Constants;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class OpenBank extends Node {
    private static final int BANKER_ID = 2531;


    @Override
    public boolean activate() {
        final NPC theBanker = NPCs.getNearest(BANKER_ID);
        return Inventory.contains(Constants.LOGS) && theBanker.validate() && !Widgets.get(323, 5).visible()
                && (Calculations.distanceTo(Constants.BANK_TILE) <= 8);
    }

    @Override
    public void execute() {

        Mahogs.s = "Opening bank";
        final NPC banker = NPCs.getNearest(BANKER_ID);
        if (banker != null) {
            if (banker.isOnScreen()) {
                if (!Widgets.get(323, 5).visible()) {
                    if (banker.interact("Send-parcel")) {
                        sleep(1000,2000);
                    }
                }
            } else {
                Camera.turnTo(banker);
            }
        }
    }
}
