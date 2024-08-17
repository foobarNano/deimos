package view;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controller.AppSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;


public class GraphicalView implements Runnable
{
    private final AppSystem SYSTEM;
    private static JFrame frame;
    private static JTabbedPane tabbedPane;
    
    private static Font protoFont;
    private static ImageIcon logoIcon = new ImageIcon("res/LOGO.png");

    private static final Color BG_COLOR = new Color(0x2e2e2e);
    private static final Color FG_COLOR = new Color(0xfafafa);

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

        UIManager.put("Button.background", new ColorUIResource(0x2e2e2e));
        UIManager.put("Button.foreground", new ColorUIResource(0xfafafa));
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        UIManager.put("control", new ColorUIResource(40, 40, 40));
        UIManager.put("info", new ColorUIResource(40, 40, 40));
        UIManager.put("nimbusBase", new ColorUIResource(30, 30, 30));
        UIManager.put("nimbusAlertYellow", new ColorUIResource(255, 165, 0));
        UIManager.put("nimbusDisabledText", new ColorUIResource(100, 100, 100));
        UIManager.put("nimbusFocus", new ColorUIResource(255, 165, 0));
        UIManager.put("nimbusGreen", new ColorUIResource(150, 150, 150));
        UIManager.put("nimbusInfoBlue", new ColorUIResource(80, 80, 80));
        UIManager.put("nimbusLightBackground", new ColorUIResource(30, 30, 30));
        UIManager.put("nimbusOrange", new ColorUIResource(255, 165, 0));
        UIManager.put("nimbusRed", new ColorUIResource(200, 60, 60));
        UIManager.put("nimbusSelectedText", new ColorUIResource(255, 255, 255));
        UIManager.put("nimbusSelectionBackground", new ColorUIResource(160, 160, 160));
        UIManager.put("text", new ColorUIResource(0x2e2e2e));

        try 
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(frame);
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void run()
    {
        frame = new JFrame("Deimos Pharmaceutical");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(30, 30, 30));

        JLabel logoLabel = new JLabel();
        ImageIcon scaledIcon = new ImageIcon(logoIcon.getImage().getScaledInstance(250, 100, Image.SCALE_SMOOTH));
        logoLabel.setIcon(scaledIcon);
        frame.getContentPane().add(logoLabel, java.awt.BorderLayout.NORTH);
        
        tabbedPane = new JTabbedPane();
        tabbedPane.setUI(new CustomTabbedPaneUI());
        
        tabbedPane.addTab("Welcome", prepareWelcome());
        tabbedPane.addTab("Store", prepareStore());
        tabbedPane.addTab("Manage", prepareEmployeePanel());

        frame.add(tabbedPane);
        frame.setSize(1280, 720); 
        frame.setVisible(true);
    }

    private JPanel prepareWelcome()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(BG_COLOR);
        panel.setForeground(FG_COLOR);

        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(logoIcon);
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1000, 10));
        logoPanel.add(logoLabel);

        JLabel descriptionLabel = new JLabel("<html><i>Deimos Pharmaceutics: You won't die on our watch >:3</i></html>");
        descriptionLabel.setForeground(FG_COLOR);
        descriptionLabel.setFont(protoFont.deriveFont(Font.PLAIN, 18));
        descriptionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        logoPanel.add(descriptionLabel);
    
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel textLabel = new JLabel("@Deimos Pharmaceutics LLC, 2024");
        textLabel.setForeground(FG_COLOR);
        textLabel.setFont(protoFont.deriveFont(Font.PLAIN, 16));
        textLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textPanel.add(textLabel);

        Dimension buttonDimension = new Dimension(200, 50);

        JButton storeButton = new CustomButton("Store");
        JButton manageButton = new CustomButton("Employee Panel");

        storeButton.addActionListener(e -> tabbedPane.setSelectedIndex(1));
        manageButton.addActionListener(e -> tabbedPane.setSelectedIndex(2));

        logoPanel.add(storeButton);
        logoPanel.add(manageButton);
        
        panel.add(logoPanel, BorderLayout.CENTER);
        panel.add(textPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel prepareStore()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(BG_COLOR);
        panel.setForeground(FG_COLOR);


        return panel;
    }

    private JPanel prepareEmployeePanel()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_COLOR);
        panel.setForeground(FG_COLOR);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(BG_COLOR);
        buttonPanel.setForeground(FG_COLOR);

        JButton addButton = new CustomButton("Refresh");
        JButton editButton = new CustomButton("Edit");
        JButton deleteButton = new CustomButton("Cancel");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        String[] columnNames = {"ID", "Client", "Value", "Actions"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(24);
        
        applyCustomStyle(table);
        applyCustomStyle(table.getTableHeader());
        table.getTableHeader().setPreferredSize(new Dimension(200, 30));
        table.setBackground(BG_COLOR.brighter());
        
        tableModel.addRow(new Object[]{1L, "Client A", 100.00, "View Details"});
        tableModel.addRow(new Object[]{2L, "Client B", 200.00, "View Details"});

        TableColumn actionColumn = table.getColumnModel().getColumn(3);
        actionColumn.setCellRenderer(new ButtonRenderer());
        actionColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    private static JDialog makePopupWindow(String title, Component content)
    {
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(content);

        return dialog;
    }

    static class CustomButton extends JButton
    {
        public CustomButton(String text)
        {
            super(text);
            setBackground(BG_COLOR);
            setForeground(FG_COLOR);
            setPreferredSize(new Dimension(200, 40));
            setFont(protoFont.deriveFont(Font.PLAIN, 16));
            setFocusPainted(false);
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(BG_COLOR);
            setForeground(FG_COLOR);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(BG_COLOR);
                setForeground(FG_COLOR);
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // ButtonEditor for handling button clicks in the table
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onButtonClick();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }

        private void onButtonClick() {
            if (isPushed) {
                int row = table.convertRowIndexToModel(table.getSelectedRow());
                showOrderedProductsPopup(row);
            }
        }
    }

    private void showOrderedProductsPopup(int row)
    {
        String[] columnNames = {"Product ID", "Product Name", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        JTable productTable = new JTable(tableModel);
        productTable.setBackground(BG_COLOR);
        productTable.setForeground(FG_COLOR);
        productTable.setFillsViewportHeight(true);

        applyCustomStyle(productTable);
        
        JFrame popupFrame = new JFrame("Ordered Products");
        popupFrame.setSize(400, 300);
        popupFrame.setLocationRelativeTo(null);
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        popupFrame.add(new JScrollPane(productTable));
        
        popupFrame.setVisible(true);
    }

    private void applyCustomStyle(JComponent c)
    {
        c.setForeground(FG_COLOR);
        c.setBackground(BG_COLOR);
        c.setPreferredSize(new Dimension(200, 40));
        c.setFont(protoFont.deriveFont(Font.PLAIN, 16));
    }

    static class CustomTabbedPaneUI extends BasicTabbedPaneUI 
    {
        private static final int TAB_HEIGHT = 32;
        private static final int TAB_WIDTH = 160; 

        @Override
        protected void installDefaults() 
        {
            super.installDefaults();
            tabAreaInsets.right = 4; 
        }

        @Override
        protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) 
        {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            Color bgColor = new Color(30, 30, 30);
            g2d.setColor(bgColor);
            g2d.fillRect(rects[tabIndex].x, rects[tabIndex].y, rects[tabIndex].width, rects[tabIndex].height);
            
            g2d.setColor(Color.DARK_GRAY);
            g2d.draw(rects[tabIndex]);
            
            g2d.setColor(Color.WHITE);
            Font tabFont = protoFont.deriveFont(Font.PLAIN, 16); 
            g2d.setFont(tabFont);
            String title = tabbedPane.getTitleAt(tabIndex);
            Rectangle2D textBounds = tabFont.getStringBounds(title, g2d.getFontRenderContext());
            int textX = rects[tabIndex].x + (rects[tabIndex].width - (int) textBounds.getWidth()) / 2;
            int textY = rects[tabIndex].y + (rects[tabIndex].height - (int) textBounds.getHeight()) / 2 + g2d.getFontMetrics().getAscent();
            g2d.drawString(title, textX, textY);
            g2d.dispose();
        }

        @Override
        protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) 
        {
            return TAB_HEIGHT;
        }

        @Override
        protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) 
        {
            return TAB_WIDTH;
        }
    }
}

