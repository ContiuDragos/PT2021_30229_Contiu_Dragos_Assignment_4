package view;

import javax.swing.*;
import java.awt.*;

public class WorkerGUI {
    JFrame frame = new JFrame("WorkerLogIn");
    JLabel ordersLabel = new JLabel("Pending orders\n");
    JPanel orderPanel = new JPanel();
    public WorkerGUI()
    {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(550,500));
        frame.setLocationRelativeTo(null);

        frame.getContentPane().setLayout(new GridLayout(3,1,2,2));

        frame.getContentPane().add(new JLabel("Successfully logged in"));
        frame.getContentPane().add(ordersLabel);
        frame.getContentPane().add(orderPanel);

        frame.setVisible(true);
    }

    public void addOrders(String order)
    {
        String text = ordersLabel.getText()+"\n"+order+"\n";
        JLabel label = new JLabel(text);
        orderPanel.add(label);
        frame.setVisible(true);
    }

}