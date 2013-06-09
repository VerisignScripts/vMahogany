package org.aidan.scripts.JungleSpiders.nodes;

import org.aidan.scripts.JungleSpiders.util.Vars;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;


public class Banking extends Node {

    @Override
    public boolean activate() {
        return !Inventory.contains(Vars.lobster) && Calculations.distanceTo(Vars.bankTile) < 5;
    }

    @Override
    public void execute() {
        if(Bank.isOpen()) {
            if (Inventory.contains(Vars.carcass)) {
                Bank.deposit(Vars.carcass, Bank.Amount.ALL);
            } else if (!Inventory.contains(Vars.lobster)) {
                Bank.withdraw(Vars.lobster, Bank.Amount.ALL);
            }
        } else {
            Bank.open();
        }
    }
}