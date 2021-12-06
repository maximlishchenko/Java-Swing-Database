package CS2020.assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class App {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Maksim Listsenko: Assignment 2");
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenuItem about = new JMenuItem("About");
        about.setMaximumSize(new Dimension(about.getPreferredSize().width, 50));
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Assignment 2 App v.0.1");
            }
        });
        menuBar.add(about);
        frame.add(menuBar, BorderLayout.NORTH);

        JList<Artist> list = new JList<Artist>();
        DefaultListModel listModel = new DefaultListModel();
        list.setModel(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane listPane = new JScrollPane(list);
        listPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(listPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        JButton button1 = new JButton("Add Data Manually");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Utils.createExampleArtists(list);
                button1.setEnabled(false);
            }
        });
        JButton button2 = new JButton("Add Data From Database");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Utils.readArtistAndSongsFromDatabase(list);
                button2.setEnabled(false);
            }
        });
        JButton button3 = new JButton("Delete Selected");
        southPanel.add(button1);
        southPanel.add(button2);
        southPanel.add(button3);
        frame.add(southPanel, BorderLayout.SOUTH);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Date of Birth:");
        labels.add("Place of Birth:");
        labels.add("Born on Weekend:");
        for (int i = 0; i < 3; i++) {
            JLabel label = new JLabel(labels.get(i));
            JTextField textField = new JTextField();
            textField.setPreferredSize(new Dimension(150, 20));
            textField.setEditable(false);
            JPanel horizontalPanel = new JPanel();
            horizontalPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            horizontalPanel.add(label);
            horizontalPanel.add(textField);
            eastPanel.add(horizontalPanel);
        }
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        eastPanel.add(textArea);
        frame.add(eastPanel, BorderLayout.EAST);


        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        createAndShowGUI();
        //Utils utils = new Utils();
        Utils.connectToDatabase();
    }
}