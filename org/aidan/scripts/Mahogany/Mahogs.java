package org.aidan.scripts.Mahogany;
import org.aidan.scripts.Mahogany.nodes.*;

import org.aidan.scripts.Mahogany.util.Variables;
import org.powerbot.core.Bot;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.client.Client;


import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Manifest( authors = "Verisign", name = "vMahogany", description = "Chops Mahogany and banks it", version = 0.1)
public class Mahogs extends ActiveScript implements PaintListener, MessageListener {

    public String s;
    private Timer runTime = new Timer(0);
    private int logsChopped = 0;
    private int logPrice;
    public Client client = Bot.client();
    private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
    private Tree jobContainer = null;

    public final void provide(final Node... jobs) {
        for (final Node job : jobs) {
            if (!jobsCollection.contains(job)) {
                jobsCollection.add(job);
            }
        }
        jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
    }

    public void onStart() {
        logPrice = getPrices(6332);
        provide(new Chopping(), new NavigateBank(), new OpenBank(), new NavigateTrees(), new PackLogs());

    }

    @Override
    public int loop() {
        if (Game.getClientState() != Game.INDEX_MAP_LOADED) { // TODO TEST
            return 1000;
        }
        if (client != Bot.client()) {
            WidgetCache.purge();
            Bot.context().getEventManager().addListener(this);
            client = Bot.client();
        }                             // TODO END
        if (jobContainer != null) {
            final Node job = jobContainer.state();
            if (job != null) {
                jobContainer.set(job);
                getContainer().submit(job);
                job.join();
            }
        }
        return 35;
    }


    int getPrices(final int id) {
        int price = 0;
        String add = "http://scriptwith.us/api/?return=text&item=";
        add += id;
        try {
            final BufferedReader in = new BufferedReader(new InputStreamReader(
                    new URL(add).openConnection().getInputStream()));
            final String line = in.readLine();
            in.close();
            final String[] subset = line.split("[:]");
            price = Integer.parseInt(subset[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    @Override
    public void messageReceived(MessageEvent e) {
        String f = e.getMessage();
        if (f.contains("You get some ma")) {
            logsChopped++;
        }
    }

    public void onRepaint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        int profit = logPrice * logsChopped;

        g.drawString("Profit: " + profit, 349, 330);
        g.drawString("Runtime: " + runTime.toElapsedString(), 349, 239);
        g.drawString("Logs Chopped: " + logsChopped, 349, 271);
        g.drawString("Status: " + s, 349, 307);
    }

}