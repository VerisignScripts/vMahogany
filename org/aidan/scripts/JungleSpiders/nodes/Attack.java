package org.aidan.scripts.JungleSpiders.nodes;

import org.aidan.scripts.JungleSpiders.JSKR;
import org.aidan.scripts.JungleSpiders.util.Methods;
import org.aidan.scripts.JungleSpiders.util.Vars;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.interactive.NPC;
import sk.general.TimedCondition;


public class Attack extends Node {


    @Override
    public boolean activate() {
        JSKR.s = "Activating Attack";
        return Players.getLocal().getInteracting() == null && Vars.COMBAT_AREA.contains(Players.getLocal())
                && Methods.getPercent() >= 60 && Players.getLocal().getInteracting() == null && Inventory.contains(Vars.FOOD_IDS);
    }


    @Override
    public void execute() {
        final NPC spider = NPCs.getNearest(targetFilter);
        if(spider != null) {
            if(!isOnScreen(spider)) {
                Camera.turnTo(spider, 50);
            }
            if(!spider.isInCombat()) {
                JSKR.s = "Attacking spider";
                spider.interact("Attack", spider.getName());
                new TimedCondition(1500) {
                    @Override
                    public boolean isDone() {
                        return Players.getLocal().getInteracting() != null;
                    }
                }.waitStop();
            }
        }
    }


    public static boolean isOnScreen(Entity e) {
        return e.isOnScreen() && !(Widgets.get(640, 4).validate() ?
                Widgets.get(640, 4) : Widgets.get(640, 2)).getBoundingRectangle().contains(e.getCentralPoint());
    }

    private Filter<NPC> targetFilter = new Filter<NPC>() {
        @Override
        public boolean accept(NPC n) {
            return n != null && n.validate() && !Vars.AVOID_AREA.contains(n) &&
                    n.getAnimation() != Vars.DYING_ANIMATION &&
                    n.getId() == 62 &&
                    (n.getInteracting() == null || n.getInteracting() == Players.getLocal());
        }
    };

}
