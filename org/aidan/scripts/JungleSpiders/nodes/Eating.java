package org.aidan.scripts.JungleSpiders.nodes;

import org.aidan.scripts.JungleSpiders.JSKR;
import org.aidan.scripts.JungleSpiders.util.Methods;
import org.aidan.scripts.JungleSpiders.util.Vars;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

public class Eating extends Node {

    @Override
    public boolean activate() {
        JSKR.s = "Activating Food";
        return Inventory.getItem(Vars.FOOD_IDS) != null && Methods.getPercent() < 60 && Inventory.contains(Vars.FOOD_IDS);

    }

    @Override
    public void execute() {
        JSKR.s = "Executing Food";
        if (Methods.getPercent() < 60) {
            Item foods = Inventory.getItem(Vars.FOOD_IDS);
            foods.getWidgetChild().interact("Eat");
        }
    }
}