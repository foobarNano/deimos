package view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controller.AppSystem;
import model.Client;
import model.Order;
import model.Product;
import model.ProductInOrder;

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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GraphicalView implements Runnable
{
    private final AppSystem SYSTEM;
    private static JFrame frame;
    private static JTabbedPane tabbedPane;
    
    private static Font protoFont;
    private static ImageIcon logoIcon = new ImageIcon("res/LOGO.png");

    private static final Color BG_COLOR = new Color(0x2e2e2e);
    private static final Color FG_COLOR = new Color(0xfafafa);

    private Map<Product, Integer> cart = new HashMap<>();
    private JLabel totalLabel = new JLabel("Total: CR 0");

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
    
    private int getTotal(Map<Product, Integer> products)
    {
        int total = 0;

        for (Product p : products.keySet())
        {
            total += (p.value * products.get(p));
        }

        return total;
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
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_COLOR);
        panel.setForeground(FG_COLOR);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(BG_COLOR);
        panel.setForeground(FG_COLOR);

        panel.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                totalLabel.setText("Total: CR " + getTotal(cart));
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });

        JButton cartButton = new CustomButton("View Cart");
        totalLabel.setText("Total: CR " + getTotal(cart));
        applyCustomStyle(totalLabel);

        cartButton.addActionListener(e -> showCartPopup());

        topPanel.add(cartButton);
        topPanel.add(totalLabel);

        String[] columnNames = {"ID", "Brand", "Name", "Value", "Description", "Action"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return column == 5;
            }
        };

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        
        applyCustomStyle(table);
        applyCustomStyle(table.getTableHeader());
        table.getTableHeader().setPreferredSize(new Dimension(200, 30));
        table.setBackground(BG_COLOR.darker());

        for (Product p : SYSTEM.getProducts())
        {
            tableModel.addRow(new Object[] { p.id, p.brand.name, p.name, p.value, p.description, "Add to cart" });
        }

        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(160);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.getColumnModel().getColumn(2).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setMaxWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setMaxWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox())
        {
            @Override
            protected void onButtonClick()
            {
                int row = table.convertRowIndexToModel(table.getSelectedRow());
                Product p = SYSTEM.getProducts().get(row);

                if (cart.containsKey(p))
                {
                    cart.put(p, cart.get(p) + 1);
                }
                else
                {
                    cart.put(p, 1);
                }

                totalLabel.setText("Total: CR " + getTotal(cart));
            };
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

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

        JButton refreshButton = new CustomButton("Refresh");
        JButton editButton = new CustomButton("Edit [WIP]");
        JButton cancelButton = new CustomButton("Cancel [WIP]");
        JLabel hintLabel = new JLabel("Select a record and then select the desired option");
        applyCustomStyle(hintLabel);
        hintLabel.setForeground(FG_COLOR.darker());
        hintLabel.setBorder(new EmptyBorder(5, 20, 5, 0));

        JPanel hintPanel = new JPanel(new BorderLayout());
        hintPanel.add(hintLabel, BorderLayout.CENTER);
        applyCustomStyle(hintPanel);
        hintPanel.setMinimumSize(new Dimension(300, 0));
        hintPanel.setPreferredSize(new Dimension(600, 50));

        buttonPanel.add(refreshButton);
        buttonPanel.add(editButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(hintPanel);

        String[] columnNames = {"ID", "Client", "Value", "Status", "Timestamp", "Products"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return column == 5;
            }
        };

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(24);
        
        applyCustomStyle(table);
        applyCustomStyle(table.getTableHeader());
        table.getTableHeader().setPreferredSize(new Dimension(200, 30));
        table.setBackground(BG_COLOR.darker());

        TableColumn actionColumn = table.getColumnModel().getColumn(5);
        actionColumn.setCellRenderer(new ButtonRenderer());
        actionColumn.setCellEditor(new ButtonEditor(new JCheckBox())
        {
            @Override
            protected void onButtonClick()
            {
                int row = table.convertRowIndexToModel(table.getSelectedRow());
                showOrderedProductsPopup(SYSTEM.getOrders().get(row));
            }
        });

        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(160);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.getColumnModel().getColumn(2).setMaxWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setMaxWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setMaxWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);

        refreshButton.addActionListener(e ->
        {
            SYSTEM.pullDB();
            tableModel.setRowCount(0);

            for (Order o : SYSTEM.getOrders())
            {
                String status = (o.completed != null) ? "Completed" : (o.confirmed != null) ? "Confirmed" : "Placed";
                Timestamp timestamp = (o.completed != null) ? o.completed : (o.confirmed != null) ? o.confirmed : o.placed;
                tableModel.addRow(new Object[] { o.id, o.client.cryptonym, o.getValue(), status, timestamp });
            }
        });

        editButton.addActionListener(e ->
        {
            int row = table.convertRowIndexToModel(table.getSelectedRow());
            if (row < 0 || row > table.getRowCount()) return;
            showEditOrderPopup(SYSTEM.getOrders().get(row));
        });

        cancelButton.addActionListener(e ->
        {
            int row = table.convertRowIndexToModel(table.getSelectedRow());
            if (row < 0 || row > table.getRowCount()) return;
            showConfirmPopup(SYSTEM.getOrders().get(row));
        });

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        refreshButton.doClick();

        return panel;
    }

    public void showConfirmPopup(Order order)
    {
        JFrame removeFrame = new JFrame("Order cancellation");

        JLabel messageLabel = new JLabel("Are you sure you wish to cancel order " + order.id + "?");
        applyCustomStyle(messageLabel);
        messageLabel.setPreferredSize(new Dimension(400, 100));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton yesButton = new CustomButton("Yes");
        JButton noButton = new CustomButton("No");

        Dimension dimension = new Dimension(120, 40);
        yesButton.setPreferredSize(dimension);
        noButton.setPreferredSize(dimension);

        yesButton.setBorder(new EmptyBorder(20, 20, 20, 20));
        noButton.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(messageLabel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        yesButton.addActionListener(e ->
        {
            SYSTEM.popOrder(order);
            removeFrame.dispose();
        });

        noButton.addActionListener(e ->
        {
            removeFrame.dispose();
        });

        removeFrame.setSize(500, 300);
        removeFrame.setMinimumSize(new Dimension(500, 300));
        removeFrame.setLocationRelativeTo(null);
        removeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeFrame.setAlwaysOnTop(true);

        removeFrame.add(contentPanel);

        removeFrame.setVisible(true);
    }

    private void showCartPopup()
    {
        String[] columnNames = {"Brand", "Name", "Price", "Count", "Remove"};
        DefaultTableModel cartTableModel = new DefaultTableModel(columnNames, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return column == 4;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column)
            {
                super.setValueAt(aValue, row, column);
                totalLabel.setText("Total: CR " + getTotal(cart));
            }
        };

        List<Product> removed = new ArrayList<>();

        for (Product product : cart.keySet())
        {
            if (cart.get(product) == 0) removed.add(product);
            else cartTableModel.addRow(new Object[]{product.brand.name, product.name, product.value, cart.get(product), "-1"});
        }

        for (Product product : removed)
        {
            cart.remove(product);
        }

        JTable cartTable = new JTable(cartTableModel);
        cartTable.setFillsViewportHeight(true);
        cartTable.setRowHeight(24);
        
        applyCustomStyle(cartTable);
        applyCustomStyle(cartTable.getTableHeader());
        cartTable.getTableHeader().setPreferredSize(new Dimension(200, 30));
        cartTable.setBackground(BG_COLOR.darker());

        cartTable.getColumnModel().getColumn(0).setMaxWidth(160);
        cartTable.getColumnModel().getColumn(0).setPreferredWidth(160);
        cartTable.getColumnModel().getColumn(1).setMaxWidth(200);
        cartTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        cartTable.getColumnModel().getColumn(2).setMaxWidth(80);
        cartTable.getColumnModel().getColumn(2).setPreferredWidth(80);

        TableColumn removeColumn = cartTable.getColumnModel().getColumn(4);
        removeColumn.setCellRenderer(new ButtonRenderer());
        removeColumn.setCellEditor(new ButtonEditor(new JCheckBox())
        {
            @Override
            protected void onButtonClick()
            {
                try
                {
                    int row = cartTable.convertRowIndexToModel(cartTable.getSelectedRow());
                    Product p = new ArrayList<>(cart.keySet()).get(row);
                    Integer v = cart.get(p);

                    if (v > 1)
                    {
                        cart.put(p, cart.get(p) - 1);
                        cartTableModel.setValueAt(v-1, row, 3);
                    }
                    else if (v == 1)
                    {
                        cart.put(p, 0);
                        cartTableModel.setValueAt(0, row, 3);
                        ((JButton) cartTable.getValueAt(row, 4)).setEnabled(false);
                    }
                }
                catch (Exception e) {}
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(cartTable), BorderLayout.CENTER);

        CustomButton placeOrderButton = new CustomButton("Place Order");

        CustomTextfield fname = new CustomTextfield("First name: ");
        CustomTextfield lname = new CustomTextfield("Last name*: ");
        CustomTextfield address = new CustomTextfield("Address*: ");
        
        JLabel info = new JLabel();
        applyCustomStyle(info);
        info.setFont(info.getFont().deriveFont(20f));
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        infoPanel.add(info, BorderLayout.CENTER);

        JPanel subPanel = new JPanel(new FlowLayout());
        subPanel.add(totalLabel);
        subPanel.add(placeOrderButton);

        JPanel underneath = new JPanel();
        underneath.setLayout(new BoxLayout(underneath, BoxLayout.Y_AXIS));
        underneath.setMinimumSize(new Dimension(500, 300));
        underneath.add(fname);
        underneath.add(lname);
        underneath.add(address);
        underneath.add(infoPanel);
        underneath.add(subPanel);

        placeOrderButton.addActionListener(e ->
        {
            if(lname.getText().isBlank() || address.getText().isBlank())
            {
                info.setForeground(Color.RED);
                info.setText("Please fill the mandatory fields");
            }
            else
            {
                try
                {
                    cartTable.setForeground(FG_COLOR.darker());
                    cartTable.setBackground(BG_COLOR.brighter());
                    cartTable.removeColumn(removeColumn);
                    cartTable.setEnabled(false);

                    underneath.setBackground(BG_COLOR.brighter());
                    underneath.setEnabled(false);
                    fname.setEnabled(false);
                    lname.setEnabled(false);
                    address.setEnabled(false);
                    subPanel.setEnabled(false);

                    Client client = new Client();
                    if (!fname.getText().isBlank()) client.first_name = fname.getText();
                    client.last_name = lname.getText();
                    client.cryptonym = client.last_name;
                    client.contact_address = address.getText();

                    Order order = new Order();
                    order.client = client;
                    order.placed = Timestamp.from(Instant.now());

                    SYSTEM.pushOrder(order);

                    order = SYSTEM.getOrders().getLast();

                    for (Product p : cart.keySet())
                    {
                        int v = cart.get(p);

                        ProductInOrder pio = new ProductInOrder();
                        pio.order = order;
                        pio.product = p;
                        pio.count = v;

                        SYSTEM.pushProductInOrder(pio);
                    }

                    cart.clear();
                    totalLabel.setText("Total: CR " + getTotal(cart));

                    info.setForeground(FG_COLOR);
                    info.setText("Order placed!");
                }
                catch (Exception ee)
                {
                    info.setForeground(Color.RED);
                    info.setText("Error while placing order :c");
                }
            }
        });

        panel.add(underneath, BorderLayout.SOUTH);

        JFrame cartFrame = new JFrame("Cart");
        cartFrame.setSize(500, 500);
        cartFrame.setMinimumSize(new Dimension(500, 500));
        cartFrame.setLocationRelativeTo(null);
        cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartFrame.setAlwaysOnTop(true);

        cartFrame.add(panel);

        cartFrame.setVisible(true);
    }

    private void showEditOrderPopup(Order order)
    {
        JDialog dialog = new JDialog(frame, "Edit Order", true);
        dialog.setLayout(new GridLayout(3, 1));
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(frame);
        dialog.setAlwaysOnTop(true);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Change order state:"));

        String[] states = {"Placed", "Confirmed", "Completed"};
        if (order.completed != null) states = Arrays.copyOfRange(states, 2, states.length);
        else if (order.confirmed != null) states = Arrays.copyOfRange(states, 1, states.length);

        JComboBox<String> comboBox = new JComboBox<>(states);
        panel.add(comboBox);

        JButton acceptButton = new JButton("Accept");
        acceptButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String selectedState = (String) comboBox.getSelectedItem();
                
                switch (selectedState)
                {
                    case "Placed":
                        order.confirmed = null;
                        order.completed = null;
                        SYSTEM.pushOrder(order);
                        break;
                    case "Confirmed":
                        order.confirmed = Timestamp.from(Instant.now());
                        SYSTEM.pushOrder(order);
                        break;
                    case "Completed":
                        order.completed = Timestamp.from(Instant.now());
                        SYSTEM.pushOrder(order);
                        break;
                    default:
                        break;
                }

                dialog.dispose();
            }
        });
        panel.add(acceptButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    public class CustomTextfield extends JPanel
    {
        JLabel label;
        JTextField textField;
    
        public CustomTextfield(String text)
        {
            setLayout(new BorderLayout());
            applyCustomStyle(this);

            label = new JLabel(text);
            applyCustomStyle(label);
            label.setBorder(new EmptyBorder(0, 20, 0, 20));

            textField = new JTextField();
            applyCustomStyle(textField);
            label.setBorder(new EmptyBorder(0, 20, 0, 20));

            add(label, BorderLayout.WEST);
            add(textField, BorderLayout.CENTER);
        }

        public String getText()
        {
            return textField.getText();
        }

        public void setText(String text)
        {
            textField.setText(text);
        }

        public void setEnabled(boolean enabled)
        {
            label.setEnabled(enabled);
            textField.setEnabled(enabled);
            textField.setEditable(enabled);
        }
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

    class ButtonRenderer extends JButton implements TableCellRenderer
    {
        public ButtonRenderer()
        {
            setOpaque(true);
            setBackground(BG_COLOR);
            setForeground(FG_COLOR);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (isSelected)
            {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            }
            else
            {
                setBackground(BG_COLOR);
                setForeground(FG_COLOR);
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor
    {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox)
        {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    onButtonClick();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
        {
            this.table = table;
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue()
        {
            return label;
        }

        @Override
        public boolean stopCellEditing()
        {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped()
        {
            super.fireEditingStopped();
        }

        protected void onButtonClick()
        {
            if (isPushed)
            {
                int row = table.convertRowIndexToModel(table.getSelectedRow());
                showOrderedProductsPopup(SYSTEM.getOrders().get(row));
            }
        }
    }

    private void showOrderedProductsPopup(Order order)
    {
        String[] columnNames = {"Product ID", "Product Name", "Price", "Count"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        JTable productTable = new JTable(tableModel);
        productTable.setFillsViewportHeight(true);
        productTable.setRowHeight(24);
        applyCustomStyle(productTable);
        applyCustomStyle(productTable.getTableHeader());

        for (ProductInOrder pio : order.productsOrdered)
        {
            Product p = pio.product;
            tableModel.addRow(new Object[] { p.id, (p.brand.name + " " + p.name), p.value, pio.count });
        }

        productTable.getColumnModel().getColumn(0).setMaxWidth(80);
        productTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        productTable.getColumnModel().getColumn(2).setMaxWidth(80);
        productTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        productTable.getColumnModel().getColumn(3).setMaxWidth(80);
        productTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        
        JFrame popupFrame = new JFrame("Ordered Products");
        popupFrame.setSize(600, 600);
        popupFrame.setLocationRelativeTo(null);
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popupFrame.setAlwaysOnTop(true);

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

