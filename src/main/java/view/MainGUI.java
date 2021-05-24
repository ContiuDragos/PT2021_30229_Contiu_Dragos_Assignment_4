package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainGUI {

    public final JFrame frame = new JFrame("MainGUI");
    private final JPanel mainPanel = new JPanel();
    private final JPanel loginPanel= new JPanel();
    private final JPanel usernamePanel = new JPanel();
    private final JPanel rolePanel = new JPanel();
    private final JPanel passwordPanel = new JPanel();
    private final JPanel submitPanel = new JPanel();
    private final JTextField passwordText;
    private final JTextField usernameText;
    private JComboBox roleBox;
    JButton submitButton;

    public MainGUI()
    {
        frame.setMinimumSize(new Dimension(500,400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new GridLayout(5,1));

        JLabel loginLabel = new JLabel("LOGIN");
        loginPanel.add(loginLabel);
        frame.getContentPane().add(loginPanel);

        JLabel usernameLabel = new JLabel("Username");
        usernameText = new JTextField();
        usernamePanel.setLayout(new GridLayout(1,2));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameText);
        frame.getContentPane().add(usernamePanel);

        JLabel roleLabel = new JLabel("Role");
        roleBox = new JComboBox();
        roleBox.addItem("Admin");
        roleBox.addItem("Worker");
        roleBox.addItem("Client");
        rolePanel.setLayout(new GridLayout(1,2));
        rolePanel.add(roleLabel);
        rolePanel.add(roleBox);
        frame.getContentPane().add(rolePanel);

        JLabel passwordLabel = new JLabel("Password");
        passwordText = new JTextField();
        passwordPanel.setLayout(new GridLayout(1,2));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordText);
        frame.getContentPane().add(passwordPanel);

        submitButton = new JButton("Submit");
        submitPanel.add(submitButton);
        frame.getContentPane().add(submitPanel);
        frame.setVisible(true);
    }

    public String getUsername()
    {
        return usernameText.getText();
    }

    public String getPassword()
    {
        return passwordText.getText();
    }

    public String getRole()
    {
        return (String) roleBox.getSelectedItem();
    }

    public void addLogInListener(ActionListener a)
    {
        submitButton.addActionListener(a);
    }
}
