package org.aidan.scripts.JungleSpiders.nodes;

import org.aidan.scripts.JungleSpiders.JSKR;
import org.aidan.scripts.JungleSpiders.util.Vars;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;


public class Banking extends Node {

    @Override
    public boolean activate() {
        return !Inventory.contains(Vars.FOOD_IDS) && Calculations.distanceTo(Vars.BANK_TILE) < 5;
    }

    @Override
    public void execute() {
        JSKR.s = "Executing Bank";
        if(Bank.isOpen()) {
            if (Inventory.contains(Vars.CARCASS)) {
                Bank.deposit(Vars.CARCASS, Bank.Amount.ALL);
            } else if (!Inventory.contains(Vars.FOOD_IDS)) {
                for (int i : Vars.FOOD_IDS) {
                    if (Bank.getItem(i) != null && !Inventory.isFull()) {
                        Bank.withdraw(i, Bank.Amount.ALL);
                    }
                }
            }
        } else {
            Bank.open();
        }
    }
}