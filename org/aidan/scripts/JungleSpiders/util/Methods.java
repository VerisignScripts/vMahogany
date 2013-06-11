package org.aidan.scripts.JungleSpiders.util;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;

/**
 * Created with IntelliJ IDEA.
 * User: Aid
 * Date: 09/06/13
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */
public class Methods {

    public static int getPercent() {
        if (!Players.getLocal().isInCombat()) {
            int currHP = Widgets.get(748, 5).getHeight();
            if ((Math.abs(100 - 100 * currHP / 28) * 120 / 100) >= 100)
                return 100;
            return Math.abs(100 - 100 * currHP / 28) * 120 / 100;
        }
        return Players.getLocal().getHealthPercent();
    }

}
