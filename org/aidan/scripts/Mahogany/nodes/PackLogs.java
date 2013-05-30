package org.aidan.scripts.Mahogany.nodes;

import org.aidan.scripts.Mahogany.Mahogs;
import org.aidan.scripts.Mahogany.util.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;

public class PackLogs extends Node {


    @Override
    public boolean activate() {
        return Widgets.get(323, 1).visible() && Inventory.contains(Variables.logs);
    }

    @Override
    public void execute() {
        Mahogs.s = "Packing Logs";
        for(int i=0; i<28; i++){
            System.out.println("Pack logs");
            if(checkItem(Widgets.get(323, 5).getChild(i).getChildId()))
                Widgets.get(323,5).getChild(i).interact("Pack All");
        }
    }

    private boolean checkItem(int id){
        for(int log : Variables.logs)
            if(log==id)
                return true;
        return false;
    }
}