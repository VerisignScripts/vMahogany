package org.aidan.scripts.Mahogany.HardwoodGrove;

import org.aidan.scripts.Mahogany.HardwoodGrove.nodes.*;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.util.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Manifest( authors = "Verisign", name = "vMahogany", description = "Chops Mahogany and banks it", version = 0.1)
public class Mahogs extends ActiveScript implements PaintListener, MessageListener {

    public static String s;
    private Timer runTime = new Timer(0);
    public static Timer t = new Timer(3200);
    private int logsChopped = 0;
    private int logPrice;
    final Node[] nodes = new Node[] { new Chopping(), new Recover(), new NavigateBank(), new NavigateTrees(),
            new OpenBank(), new PackLogs()};


    public void onStart() {
        logPrice = getPrices(6332);
    }

    @Override
    public int loop() {
        for (Node n: nodes) {
            if(n.activate()) {
                n.execute();
            }
        }
        return 250;
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