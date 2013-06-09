package org.aidan.scripts.Mahogany.HardwoodGrove;

import org.aidan.scripts.Mahogany.HardwoodGrove.nodes.*;
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


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Manifest( authors = "Verisign", name = "vMahogany", description = "Chops Mahogany and banks it", version = 0.1)
public class Mahogs extends ActiveScript implements PaintListener, MessageListener {

    public static String s;
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
        provide(new Failsafe(), new Chopping(), new NavigateBank(), new OpenBank(), new NavigateTrees(), new PackLogs());

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

    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }

    private final Color color1 = new Color(102, 102, 102);
    private final Color color2 = new Color(255, 255, 255);
    private final Color color3 = new Color(255, 255, 0);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("Arial", 0, 11);

    private final Image img1 = getImage("http://images3.wikia.nocookie.net/__cb20100913144807/runescape/nl/images/d/d3/Woodcutting_cape.png");

    public void onRepaint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        int profit = logPrice * logsChopped;
        g.setColor(color1);
        g.setStroke(stroke1);
        g.fillRect(299, 306, 213, 29);
        g.fillRect(376, 214, 139, 74);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRect(376, 214, 139, 74);
        g.setFont(font1);
        g.setColor(color3);
        g.drawString("Runtime: " + runTime.toElapsedString(), 386, 238);
        g.drawString("Logs: " + logsChopped, 386, 252);
        g.drawString("Profit: " + profit, 386, 266);
        g.drawString("Status: " + s, 302, 327);
        g.drawImage(img1, 284, 203, null);
    }
}