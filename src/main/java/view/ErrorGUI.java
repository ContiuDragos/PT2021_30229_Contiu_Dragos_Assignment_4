package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorGUI {

    public ErrorGUI(String message)
    {
        JFrame frame = new JFrame("Error");
        frame.setMinimumSize(new Dimension(300,100));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        JLabel label = new JLabel(message);
        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("Ok");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
            }
        });

        buttonPanel.add(button);
        frame.getContentPane().add(label);
        frame.getContentPane().add(buttonPanel);
        frame.setVisible(true);
    }
}
