package main.java;

import ComputerCamera.ComputerCamera;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import main.java.SerpAPI;

public class DisplayResults extends JFrame {

    private JTable table;

    public DisplayResults(ArrayList<ArrayList<String>> data) {
        initializeUI(data);
    }

    private void initializeUI(ArrayList<ArrayList<String>> data) {
        setTitle("JSON Table");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240)); // Set background color

        // Create a table model with headers
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Title", "Price", "Source", "Link"});

        // Populate the table model with data
        for (ArrayList<String> item : data) {
            model.addRow(item.toArray());
        }

        // Create the table with the populated model
        table = new JTable(model);
        table.setEnabled(false);

        // Set table properties for a modern and clean look
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setBackground(new Color(59, 89, 152));
        table.setGridColor(new Color(189, 195, 199));
        table.setSelectionBackground(new Color(52, 152, 219));
        table.setSelectionForeground(Color.WHITE);

        // Center-align the price column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        // Make links clickable in the "Link" column
        table.getColumnModel().getColumn(3).setCellRenderer(new LinkCellRenderer());
        table.addMouseListener(new LinkMouseListener());

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the table

        // Create the sorting button
        JButton sortButton = new JButton("Sort by Price");
        sortButton.setFont(new Font("Arial", Font.BOLD, 14));
        sortButton.setBackground(new Color(59, 89, 152));
        sortButton.setForeground(Color.WHITE);
        sortButton.setFocusPainted(false);
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sort the data by the price column
                Collections.sort(data, new Comparator<ArrayList<String>>() {
                    @Override
                    public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                        double price1 = parsePrice(o1.get(1));
                        double price2 = parsePrice(o2.get(1));
                        return Double.compare(price1, price2);
                    }

                    private double parsePrice(String price) {
                        String cleanedPrice = price.replace("$", "").replace("*", "").trim();
                        return Double.parseDouble(cleanedPrice);
                    }
                });

                // Update the table model with the sorted data
                model.setRowCount(0);
                for (ArrayList<String> item : data) {
                    model.addRow(item.toArray());
                }
            }
        });


        // Create the panel for the sorting button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(sortButton);

        // Create the root panel and add components
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(new Color(240, 240, 240));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the content
        rootPanel.add(buttonPanel, BorderLayout.NORTH);
        rootPanel.add(scrollPane, BorderLayout.CENTER);

        // Set the root panel as the content pane
        setContentPane(rootPanel);
    }

    private class LinkCellRenderer extends DefaultTableCellRenderer {
        public LinkCellRenderer() {
            super();
            setHorizontalAlignment(JLabel.CENTER);
            setForeground(Color.BLUE);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof String) {
                String text = (String) value;
                setText("<html><u>" + text + "</u></html>");
            }
            return this;
        }
    }

    private class LinkMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = table.rowAtPoint(e.getPoint());
            int column = table.columnAtPoint(e.getPoint());

            if (column == 3) {
                String link = (String) table.getValueAt(row, column);
                if (link != null && !link.isEmpty()) {
                    try {
                        Desktop.getDesktop().browse(new URI(link));
                    } catch (IOException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Retrieve the data from the JSON file using your existing code
        ArrayList<ArrayList<String>> items = SerpAPI.getItems();
//
//        // Create and display the JFrame with the JSON data
//        SwingUtilities.invokeLater(() -> {
//            DisplayResults app = new DisplayResults(items);
//            app.setVisible(true);
//        });
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DisplayResults(items).setVisible(true);
            }
        });
    }
}
