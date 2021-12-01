package CS2020.assignment2;

import javax.swing.*;
import java.awt.*;

public class App {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Maksim Listsenko: Assignment 2");
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenuItem about = new JMenuItem("About");
        menuBar.add(about);
        frame.add(menuBar, BorderLayout.NORTH);

        JList list = new JList();
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane listPane = new JScrollPane(list);
        frame.add(listPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton button1 = new JButton("Add Data Manually");
        JButton button2 = new JButton("Add Data From Database");
        JButton button3 = new JButton("Delete Selected");
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        frame.add(panel, BorderLayout.SOUTH);

        JPanel panel2 = new JPanel();  //eastern panel
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel("Date of Birth:");
        JTextField textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(155, 20));
        JPanel horizontalPanel1 = new JPanel();
        horizontalPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        horizontalPanel1.add(label1);
        horizontalPanel1.add(textField1);
        JLabel label2 = new JLabel("Place of Birth:");
        JTextField textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(152, 20));
        JPanel horizontalPanel2 = new JPanel();
        horizontalPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        horizontalPanel2.add(label2);
        horizontalPanel2.add(textField2);
        JLabel label3 = new JLabel("Born on Weekend:");
        JTextField textField3 = new JTextField();
        textField3.setPreferredSize(new Dimension(120, 20));
        JPanel horizontalPanel3 = new JPanel();
        horizontalPanel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        horizontalPanel3.add(label3);
        horizontalPanel3.add(textField3);
        panel2.add(horizontalPanel1);
        panel2.add(horizontalPanel2);
        panel2.add(horizontalPanel3);
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel2.add(textArea);
        //panel2.add(scrollPane);
        frame.add(panel2, BorderLayout.EAST);


        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}