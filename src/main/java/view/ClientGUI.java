package view;

import BusinessLayer.BaseProduct;
import BusinessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientGUI {
    public JFrame frame = new JFrame("ClientGUI");
    private JComboBox products = new JComboBox();
    private JButton addButton = new JButton("Add");
    private JButton submitButton = new JButton("Submit");
    private JButton backButton = new JButton("Back");
    private JLabel nrProducts = new JLabel("Nr. products: 0");
    private JLabel price = new JLabel("Price: 0");

    private JTextField ratingText = new JTextField("",10);
    private JTextField fatText = new JTextField("",10);
    private JTextField caloriesText = new JTextField("",10);
    private JTextField proteinText = new JTextField("",10);
    private JTextField sodiumText = new JTextField("",10);
    private JTextField priceText = new JTextField("",10);
    private JTextField titleText = new JTextField("",10);
    private JButton searchButton = new JButton("Search");

    public ClientGUI()
    {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1000,400));
        frame.getContentPane().setLayout(new GridLayout(1,2,2,2));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3,1,2,2));

        JPanel upPanel = new JPanel();
        JLabel orderLabel = new JLabel("Order");
        upPanel.add(orderLabel);

        JPanel midPanel = new JPanel();
        midPanel.setLayout(new GridLayout(1,2,2,2));
        midPanel.add(products);
        midPanel.add(addButton);

        JPanel downPanel = new JPanel();
        downPanel.setLayout(new GridLayout(2,2,2,2));
        downPanel.add(nrProducts);
        downPanel.add(submitButton);
        downPanel.add(price);
        downPanel.add(backButton);

        leftPanel.add(upPanel);
        leftPanel.add(midPanel);
        leftPanel.add(downPanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(8,2,2,2));
        JLabel searchLabel = new JLabel("Search");
        JLabel titleLabel = new JLabel("Title");
        JLabel ratingLabel = new JLabel("Rating > ");
        JLabel fatLabel = new JLabel("Fat < ");
        JLabel proteinLabel = new JLabel("Protein > ");
        JLabel caloriesLabel = new JLabel("Calories < ");
        JLabel sodiumLabel = new JLabel("Sodium < ");
        JLabel priceLabel = new JLabel("Price < ");
        rightPanel.add(searchLabel);
        rightPanel.add(searchButton);
        rightPanel.add(titleLabel);
        rightPanel.add(titleText);
        rightPanel.add(ratingLabel);
        rightPanel.add(ratingText);
        rightPanel.add(fatLabel);
        rightPanel.add(fatText);
        rightPanel.add(caloriesLabel);
        rightPanel.add(caloriesText);
        rightPanel.add(proteinLabel);
        rightPanel.add(proteinText);
        rightPanel.add(sodiumLabel);
        rightPanel.add(sodiumText);
        rightPanel.add(priceLabel);
        rightPanel.add(priceText);

        frame.getContentPane().add(leftPanel);
        frame.getContentPane().add(rightPanel);
        frame.setVisible(true);
    }

    public void addProducts(ArrayList<MenuItem> menu)
    {
        for(MenuItem i : menu)
            products.addItem(i.getTitle());
        frame.setVisible(true);
    }

    public void addAddListener(ActionListener e)
    {
        addButton.addActionListener(e);
    }

    public void submitListener(ActionListener e)
    {
        submitButton.addActionListener(e);
    }

    public void addSearchListener(ActionListener e)
    {
        searchButton.addActionListener(e);
    }

    public void addBackListener(ActionListener e)
    {
        backButton.addActionListener(e);
    }
    public void addToLabels(int counter,int priceToSet)
    {
        nrProducts.setText("Nr. products: "+counter);
        price.setText("Price: "+priceToSet);
    }

    public String getProduct()
    {
        return (String) products.getSelectedItem();
    }

    public String getTitle()
    {
        return titleText.getText();
    }
    public String getRating()
    {
        return ratingText.getText();
    }
    public String getFat()
    {
        return fatText.getText();
    }
    public String getCalories()
    {
        return caloriesText.getText();
    }
    public String getProtein()
    {
        return proteinText.getText();
    }
    public String getPrice()
    {
        return priceText.getText();
    }
    public String getSodium()
    {
        return sodiumText.getText();
    }
}
