package view;

import javax.swing.*;
import java.awt.*;

public class WorkerGUI {
    JFrame frame = new JFrame("WorkerLogIn");

    public WorkerGUI()
    {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(350,100));
        frame.setLocationRelativeTo(null);

        frame.getContentPane().setLayout(new GridLayout(1,2));
        JLabel infoLabel = new JLabel("Logged In Successfully");
        JButton okButton = new JButton("Ok");
        JPanel infoPanel = new JPanel();
        JPanel okPanel = new JPanel();

        infoPanel.add(infoLabel);
        okPanel.add(okButton);
        okButton.addActionListener(actionEvent -> frame.dispose());

        frame.getContentPane().add(infoPanel);
        frame.getContentPane().add(okPanel);
        frame.setVisible(true);
    }
}
