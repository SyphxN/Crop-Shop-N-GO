package main.java;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

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

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create the sorting button
        JButton sortButton = new JButton("Sort by Price");
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

        // Add the sorting button to the JFrame
        getContentPane().add(sortButton, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        // Retrieve the data from the JSON file using your existing code
        ArrayList<ArrayList<String>> items = SerpAPI.getItems();

        // Create and display the JFrame with the JSON data
        SwingUtilities.invokeLater(() -> {
            DisplayResults app = new DisplayResults(items);
            app.setVisible(true);
        });
    }
}
