import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import main.java.SerpAPI;

public class JSONTableApp extends JFrame {

    private JTable table;

    public JSONTableApp(ArrayList<ArrayList<String>> data) {
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

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // Retrieve the data from the JSON file using your existing code
        ArrayList<ArrayList<String>> items = SerpAPI.getItems();

        // Create and display the JFrame with the JSON data
        SwingUtilities.invokeLater(() -> {
            JSONTableApp app = new JSONTableApp(items);
            app.setVisible(true);
        });
    }
}
