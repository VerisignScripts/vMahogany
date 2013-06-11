package org.aidan.scripts.Mahogany.HardwoodGrove.nodes;

import org.aidan.scripts.Mahogany.HardwoodGrove.Mahogs;
import org.aidan.scripts.Mahogany.HardwoodGrove.util.Constants;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;

public class PackLogs extends Node {


    @Override
    public boolean activate() {
        return Widgets.get(323, 5).visible() && Inventory.contains(Constants.LOGS) && Inventory.contains(Constants.STICKS);
    }

    @Override
    public void execute() {
        Mahogs.s = "Packing Logs";
        for(int i=0; i<28; i++){
            if(checkItem(Widgets.get(323, 5).getChild(i).getChildId()))
                Widgets.get(323,5).getChild(i).interact("Pack All");
        }
    }

    private boolean checkItem(int id){
        for(int log : Constants.LOGS)
            if(log==id)
                return true;
        return false;
    }
}