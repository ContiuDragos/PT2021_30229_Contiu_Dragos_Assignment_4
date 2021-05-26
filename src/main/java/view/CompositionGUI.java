package view;

import BusinessLayer.BaseProduct;
import BusinessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompositionGUI {

    public JFrame frame = new JFrame("Composition Product");
    private JButton submitButton = new JButton("Submit");
    private JButton addButton = new JButton("Add");
    private JComboBox products = new JComboBox();
    private JTextField name = new JTextField("",20);
    private JLabel label = new JLabel("Nr. products: 0");

    public CompositionGUI()
    {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400,300));
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new GridLayout(3,1,2,2));

        JPanel upPanel = new JPanel();
        upPanel.setLayout(new GridLayout(1,2));
        upPanel.add(new JLabel("Product name"));
        upPanel.add(name);
        name.setMinimumSize(new Dimension(100,100));

        JPanel midPanel = new JPanel();
        midPanel.setLayout(new GridLayout(1,2,2,2));
        midPanel.add(products);
        midPanel.add(addButton);

        JPanel downPanel = new JPanel();
        downPanel.setLayout(new GridLayout(1,2,2,2));
        downPanel.add(label);
        downPanel.add(submitButton);

        frame.getContentPane().add(upPanel);
        frame.getContentPane().add(midPanel);
        frame.getContentPane().add(downPanel);
        frame.setVisible(true);
    }

    public void addProducts(ArrayList<MenuItem> menu)
    {
        for(MenuItem i : menu)
        {
            if(i instanceof BaseProduct)
                products.addItem(((BaseProduct)i).getTitle());
        }
        frame.setVisible(true);
    }

    public void addAddListener(ActionListener e)
    {
        addButton.addActionListener(e);
    }
    public void addSubmitListener(ActionListener e)
    {
        submitButton.addActionListener(e);
    }

    public String getProduct()
    {
        return (String)products.getSelectedItem();
    }

    public String getName()
    {
        return name.getText();
    }

    public void addToLabel(int counter)
    {
        label.setText("Nr products: "+counter);
    }
}
