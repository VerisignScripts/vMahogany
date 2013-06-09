package org.aidan.scripts.JungleSpiders.nodes;

import org.aidan.scripts.JungleSpiders.JSKR;
import org.aidan.scripts.JungleSpiders.util.Methods;
import org.aidan.scripts.JungleSpiders.util.Vars;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;


public class Attack extends Node {


    @Override
    public boolean activate() {
        JSKR.s = "Activating Attack";
        return (Players.getLocal().getInteracting() == null) && Vars.combatArea.contains(Players.getLocal())
                && Methods.getPercent() > 80 && Inventory.contains(Vars.lobster);
    }

    @Override
    public void execute() {
        NPC spiders = NPCs.getNearest(62);
        out:
        if (spiders != null) {
            JSKR.s = "Spider not null";
            if (Vars.combatArea.contains(spiders)) {
                if (spiders.isOnScreen()) {
                    if (spiders.getInteracting() == null) {
                        if (spiders.getAnimation() != 5329) {
                            if (spiders.interact("Attack")) {
                                do {
                                    sleep(1000, 1200);
                                }
                                while (Players.getLocal().getInteracting() != null);
                            }
                        }
                    } else {
                        sleep(1000, 1200);
                    }
                } else {
                    Camera.turnTo(spiders);
                }
            } else {
                JSKR.s = "No Spiders";
                sleep(500,600);
            }
        } else {
            JSKR.s = "Spider null";
            sleep(500,600);
        }
    }
}