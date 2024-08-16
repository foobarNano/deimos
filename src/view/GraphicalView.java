package view;

import javax.swing.*;

import controller.AppSystem;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.plaf.*;;


public class GraphicalView implements Runnable
{
    private final AppSystem SYSTEM;
    private Font protoFont;

    public GraphicalView(AppSystem system)
    {
        this.SYSTEM = system;

        String fontPath = "res/fonts/0xProto-Regular.ttf";
        File fontFile = new File(fontPath);

        try 
        {
            protoFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(protoFont);
        }
        catch (FontFormatException | IOException e)  { e.printStackTrace(); }

        UIManager.put("control", new ColorUIResource(48, 48, 48));
        UIManager.put("info", new ColorUIResource(48, 48, 48));
        UIManager.put("nimbusBase", new ColorUIResource(18, 30, 49));
        UIManager.put("nimbusAlertYellow", new ColorUIResource(255, 173, 1));
        UIManager.put("nimbusDisabledText", new ColorUIResource(128, 128, 128));
        UIManager.put("nimbusFocus", new ColorUIResource(115, 164, 209));
        UIManager.put("nimbusGreen", new ColorUIResource(176, 179, 50));
        UIManager.put("nimbusInfoBlue", new ColorUIResource(66, 139, 221));
        UIManager.put("nimbusLightBackground", new ColorUIResource(18, 30, 49));
        UIManager.put("nimbusOrange", new ColorUIResource(255, 138, 101));
        UIManager.put("nimbusRed", new ColorUIResource(198, 83, 77));
        UIManager.put("nimbusSelectedText", new ColorUIResource(255, 255, 255));
        UIManager.put("nimbusSelectionBackground", new ColorUIResource(104, 93, 156));
        UIManager.put("text", new ColorUIResource(230, 230, 230));

        try 
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void run()
    {
        JFrame frame = new JFrame("My Java Swing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(0x2e2e2e));
        tabbedPane.setForeground(new Color(0xfafafa));

        tabbedPane.addTab("Welcome", prepareWelcome());
        tabbedPane.addTab("Store", prepareStore());
        tabbedPane.addTab("Employee Panel", prepareEmployeePanel());

        frame.getContentPane().setBackground(new Color(0x2e2e2e));
        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel prepareWelcome()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0x2e2e2e));
        panel.setForeground(new Color(0xfafafa));


        return panel;
    }

    private JPanel prepareStore()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0x2e2e2e));
        panel.setForeground(new Color(0xfafafa));


        return panel;
    }

    private JPanel prepareEmployeePanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0x2e2e2e));
        panel.setForeground(new Color(0xfafafa));


        return panel;
    }
}

