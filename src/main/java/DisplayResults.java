package main.java;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class DisplayResults extends JFrame {

    private JTable table;

    public DisplayResults(ArrayList<ArrayList<String>> data) {
        initializeUI(data);
    }

    private void initializeUI(ArrayList<ArrayList<String>> data) {
        setTitle("JSON Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
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
                        double price1 = Double.parseDouble(o1.get(1));
                        double price2 = Double.parseDouble(o2.get(1));
                        return Double.compare(price1, price2);
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

    public static void main(String[] args) {
        // Retrieve the data from the JSON file using your existing code
        ArrayList<ArrayList<String>> items = SerpAPI.getItems();

        // Create and display the JFrame with the JSON data
        SwingUtilities.invokeLater(() -> {
            DisplayResults app = new DisplayResults(items);
            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }
}
