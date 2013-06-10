package org.aidan.scripts.JungleSpiders.nodes;

import org.aidan.scripts.JungleSpiders.JSKR;
import org.aidan.scripts.JungleSpiders.util.Methods;
import org.aidan.scripts.JungleSpiders.util.Vars;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;


public class Attack extends Node {


    @Override
    public boolean activate() {
        JSKR.s = "Activating Attack";
        return Players.getLocal().getInteracting() == null && Vars.combatArea.contains(Players.getLocal())
                && Methods.getPercent() > 80 && Inventory.contains(Vars.lobster);
    }

    @Override
    public void execute() {
        NPC spider = NPCs.getNearest(62);
        if (spider != null) {
            JSKR.s = "Engaging Spider";
            if (spider.isOnScreen()) {
                if (spider.getInteracting() == null) {
                    if (spider.getAnimation() != 5329) {
                        if (spider.interact("Attack")) {
                            Timer t = new Timer(15000);
                            while (t.isRunning() && Players.getLocal().getInteracting() != null) {
                                sleep(500,600);
                            }
                        }
                    }
                }
            } else {
                Camera.turnTo(spider);
            }
        }

    }
}
