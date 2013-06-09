package org.aidan.scripts.Mahogany.Planker.nodes;

import org.aidan.scripts.Mahogany.Planker.Planking;
import org.aidan.scripts.Mahogany.Planker.util.Vars;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;


public class Deposit extends Node {
    @Override
    public boolean activate() {
        Planking.status = "Activating Deposit";
        return (Inventory.contains(Vars.planks) && (Calculations.distanceTo(Vars.bankTile) < 5));
    }

    @Override
    public void execute() {
        Planking.status = "Executing Deposit";
        if(Bank.isOpen()) {
            if (Inventory.contains(Vars.planks)) {
                if (Bank.deposit(Vars.planks, Bank.Amount.ALL)) {
                    Timer t = new Timer(3200);
                    while (t.isRunning() && Inventory.contains(Vars.planks)){
                        Task.sleep(300, 500);
                    }
                }
            }
        } else {
            Bank.open();
        }
    }
}
