package org.aidan.scripts.Mahogany.Planker.nodes;


import org.aidan.scripts.Mahogany.Planker.Planking;
import org.aidan.scripts.Mahogany.Planker.util.Vars;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;

public class Withdraw extends Node {


    @Override
    public boolean activate() {
        Planking.status = "Activating Withdraw";
        return (!Inventory.contains(Vars.mLogs, 26) || Inventory.contains(Vars.sLogs, 2)) &&
                (Calculations.distanceTo(Vars.bankTile) < 5);

    }

    @Override
    public void execute() {
        Planking.status = "Executing Withdraw";
        if(Bank.isOpen()) {
            if (Inventory.contains(Vars.planks)) {
                Bank.deposit(Vars.planks, Bank.Amount.ALL);
            } else {
                if (!Inventory.contains(Vars.sLogs, 2) || !Inventory.contains(Vars.mLogs, 26)) {
                    Bank.withdraw(Vars.mLogs, 26);
                    Bank.withdraw(Vars.sLogs, 2);
                }
            }
        } else {
            Bank.open();
        }
    }
}
