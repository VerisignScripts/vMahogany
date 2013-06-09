package org.aidan.scripts.Mahogany.Planker;

import org.aidan.scripts.Mahogany.Planker.nodes.*;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Manifest( authors = "Verisign", name = "vPlanker", description = "Makes mahogany planks using special logs", version = 0.1)
public class Planking extends ActiveScript implements PaintListener {

    private final List<org.powerbot.core.script.job.state.Node> jobsCollection = Collections.synchronizedList(new ArrayList<org.powerbot.core.script.job.state.Node>());
    private Tree jobContainer = null;
    public static String status;

    public final void provide(final org.powerbot.core.script.job.state.Node... jobs) {
        for (final org.powerbot.core.script.job.state.Node job : jobs) {
            if (!jobsCollection.contains(job)) {
                jobsCollection.add(job);
            }
        }
        jobContainer = new Tree(jobsCollection.toArray(new org.powerbot.core.script.job.state.Node[jobsCollection.size()]));
    }

    public void onStart() {
        provide(new Deposit(), new WalkToSawmill(), new MakePlanks(), new Withdraw(), new WalkToBank());  // Nodes

    }

    @Override
    public int loop() {
        if (Game.getClientState() != Game.INDEX_MAP_LOADED) {
            return 1000;
        }
        if (jobContainer != null) {
            final org.powerbot.core.script.job.state.Node job = jobContainer.state();
            if (job != null) {
                jobContainer.set(job);
                getContainer().submit(job);
                job.join();
            }
        }
        return 35;
    }

    public void onRepaint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;

        g.drawString("Status: " + status, 302, 327);

    }


}
