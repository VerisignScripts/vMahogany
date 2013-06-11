package org.aidan.scripts.JungleSpiders;

import java.awt.*;
import java.util.*;
import java.util.List;

import org.aidan.scripts.JungleSpiders.nodes.*;
import org.aidan.scripts.JungleSpiders.util.Vars;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Timer;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;



@Manifest( authors = "Verisign", name = "New JSK",
        description = "Kills Jungle Spiders east of Yanille and banks to replenish food", version = 1.2)
public class JSKR extends ActiveScript implements PaintListener, MessageListener {

    private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
    private Tree jobContainer = null;
    public static String s;
    private int startExp;
    private Timer runTime = new Timer(0);

    public final void provide(final Node... jobs) {
        for (final Node job : jobs) {
            if (!jobsCollection.contains(job)) {
                jobsCollection.add(job);
            }
        }
        jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
    }

    public void onStart() {
        startExp = Skills.getExperience(Skills.ATTACK);
        startExp += Skills.getExperience(Skills.STRENGTH);
        startExp += Skills.getExperience(Skills.DEFENSE);
        provide(new GoToSpiders(), new Eating(), new Attack(), new GoToBank(), new Banking(), new ReturnToCombatArea());

    }


    @Override
    public int loop() {
        if (jobContainer != null) {
            final Node job = jobContainer.state();
            if (job != null) {
                jobContainer.set(job);
                getContainer().submit(job);
                job.join();
            }
        }
        return 100;
    }

    @Override
    public void messageReceived(MessageEvent e) {
        String f = e.getMessage();
        if (f.contains("You can't reach that.")) {
            JSKR.s = "Spider out of reach";
            if (Vars.SPIDER_TILE.clickOnMap()) {
                Timer x = new Timer(8000);
                while (x.isRunning()) {
                    sleep(5000);
                }
            }
        }
    }

    public void onRepaint(Graphics g1) {

        int tot =  Skills.getExperience(Skills.STRENGTH) + Skills.getExperience(Skills.ATTACK) +
                Skills.getExperience(Skills.DEFENSE);
        int expGained = tot - startExp;
        int expHour = (int)(expGained * 3600000d / runTime.getElapsed());

        Graphics2D g = (Graphics2D)g1;
        g.drawString("vJungleSpiders", 367, 150);
        g.drawString("Runtime: " + runTime.toElapsedString(), 349, 183);
        g.drawString("Exp/Hour: " + expHour, 349, 210);
        g.drawString("Status: " + s, 349, 169);
        g.drawString("Exp Gained: " + expGained, 349, 196);
    }

}


