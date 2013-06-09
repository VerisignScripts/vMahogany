package org.aidan.scripts.JungleSpiders;

import java.awt.*;
import java.util.*;
import java.util.List;

import org.aidan.scripts.JungleSpiders.nodes.*;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Widgets;



@Manifest( authors = "Verisign", name = "New JSK",
        description = "Kills Jungle Spiders east of Yanille and banks to replenish food", version = 1.2)
public class JSKR extends ActiveScript implements PaintListener {

    private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
    private Tree jobContainer = null;
    public static String s;

    public final void provide(final Node... jobs) {
        for (final Node job : jobs) {
            if (!jobsCollection.contains(job)) {
                jobsCollection.add(job);
            }
        }
        jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
    }

    public void onStart() {
        provide(new Attack(), new Eating(), new ReturnToCombatArea(), new Banking(), new GoToBank(), new GoToSpiders());

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

    public void onRepaint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        g.drawString("Status: " + s, 302, 327);

    }
}

