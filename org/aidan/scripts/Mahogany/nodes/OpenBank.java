package org.aidan.scripts.Mahogany.nodes;

import org.aidan.scripts.Mahogany.util.Variables;
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
        return Inventory.contains(Variables.logs) && Calculations.distanceTo(Variables.bankTile) < 3 && !Widgets.get(323, 1).visible() ;
    }

    @Override
    public void execute() {

        Variables.s = "Banking";
        final NPC banker = NPCs.getNearest(BANKER_ID);
        if (banker != null) {
            if (banker.isOnScreen() && !Widgets.get(323, 1).visible()) {
                System.out.println("Open deposit window");
                banker.interact("Send-parcel");
            } else {
                Camera.turnTo(banker);
            }
        }
    }
}