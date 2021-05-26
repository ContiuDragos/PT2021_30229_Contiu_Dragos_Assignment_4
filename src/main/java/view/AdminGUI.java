package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminGUI {

    JFrame frame = new JFrame("AdminGUI");
    JButton importButton = new JButton("Import Menu");
    JButton addButton = new JButton("Add product");
    JButton deleteButton = new JButton("Delete product");
    JButton modifyButton = new JButton("Modify product");
    JButton createButton = new JButton("Create product");
    JButton backButton = new JButton("Back");

    JTextField titleText = new JTextField();
    JTextField ratingText = new JTextField();
    JTextField caloriesText = new JTextField();
    JTextField proteinText = new JTextField();
    JTextField fatText = new JTextField();
    JTextField sodiumText = new JTextField();
    JTextField priceText = new JTextField();

    JTextField startTimeText = new JTextField();
    JTextField stopTimeText = new JTextField();
    JTextField productOrderedMoreText = new JTextField();
    JTextField clientWithOrdersText = new JTextField();
    JTextField clientWithOrdersHigherText = new JTextField();
    JTextField productOrderOnText = new JTextField();

    JButton reportButton = new JButton("Create report");

    String[] names = {"Id","Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
    Object[][] data = new Object[100][100];
    JTable table = new JTable(data, names);
    JScrollPane scroll;

    JPanel upPanel = new JPanel();

    public AdminGUI() {
        frame.setMinimumSize(new Dimension(1000, 800));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(2, 1));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        upPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        upPanel.setLayout(new GridLayout(1, 2));

        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new GridLayout(2, 1, 5, 5));
        JLabel menuLabel = new JLabel("Menu");

        panelLeft.add(menuLabel);

        JPanel panelLeftUp = new JPanel();
        panelLeftUp.setLayout(new GridLayout(8, 2));

        JLabel nothingLabel = new JLabel();
        JLabel titleLabel = new JLabel("Title");
        JLabel ratingLabel = new JLabel("Rating");
        JLabel caloriesLabel = new JLabel("Calories");
        JLabel proteinLabel = new JLabel("Protein");
        JLabel fatLabel = new JLabel("Fat");
        JLabel sodiumLabel = new JLabel("Sodium");
        JLabel priceLabel = new JLabel("Price");

        panelLeftUp.add(menuLabel);
        panelLeftUp.add(nothingLabel);
        panelLeftUp.add(titleLabel);
        panelLeftUp.add(titleText);
        panelLeftUp.add(ratingLabel);
        panelLeftUp.add(ratingText);
        panelLeftUp.add(caloriesLabel);
        panelLeftUp.add(caloriesText);
        panelLeftUp.add(proteinLabel);
        panelLeftUp.add(proteinText);
        panelLeftUp.add(fatLabel);
        panelLeftUp.add(fatText);
        panelLeftUp.add(sodiumLabel);
        panelLeftUp.add(sodiumText);
        panelLeftUp.add(priceLabel);
        panelLeftUp.add(priceText);

        JPanel panelLeftDown = new JPanel();
        panelLeftDown.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelLeftDown.setLayout(new GridLayout(3, 2, 5, 5));
        panelLeftDown.add(importButton);
        panelLeftDown.add(addButton);
        panelLeftDown.add(deleteButton);
        panelLeftDown.add(modifyButton);
        panelLeftDown.add(createButton);
        panelLeftDown.add(backButton);

        panelLeft.add(panelLeftUp);
        panelLeft.add(panelLeftDown);

        JPanel panelRight = new JPanel();
        panelRight.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelRight.setLayout(new GridLayout(6, 1, 5, 5));
        JLabel reportLabel = new JLabel("Report");

        JPanel timePanel = new JPanel();
        timePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        timePanel.setLayout(new GridLayout(1, 4));
        JLabel timeLabel = new JLabel("Time between");
        timePanel.add(timeLabel);
        timePanel.add(startTimeText);
        timePanel.add(stopTimeText);

        JPanel productOrderedPanel = new JPanel();
        productOrderedPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        productOrderedPanel.setLayout(new GridLayout(1, 2));
        productOrderedPanel.add(new JLabel("Product ordered more than "));
        productOrderedPanel.add(productOrderedMoreText);

        JPanel clientWithOrdersPanel = new JPanel();
        clientWithOrdersPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        clientWithOrdersPanel.setLayout(new GridLayout(1, 4));
        clientWithOrdersPanel.add(new JLabel("Client with min. "));
        clientWithOrdersPanel.add(clientWithOrdersText);
        clientWithOrdersPanel.add(new JLabel(" orders higher than "));
        clientWithOrdersPanel.add(clientWithOrdersHigherText);

        JPanel productDayPanel = new JPanel();
        productDayPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        productDayPanel.setLayout(new GridLayout(1, 2));
        productDayPanel.add(new JLabel("Product ordered on "));
        productDayPanel.add(productOrderOnText);


        JPanel createReportButton = new JPanel();
        createReportButton.add(reportButton);

        panelRight.add(reportLabel);
        panelRight.add(timePanel);
        panelRight.add(productOrderedPanel);
        panelRight.add(clientWithOrdersPanel);
        panelRight.add(productDayPanel);
        panelRight.add(createReportButton);

        upPanel.add(panelLeft);
        upPanel.add(panelRight);

        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(500,500));
        scroll = new JScrollPane(table);

        frame.getContentPane().add(upPanel);
        frame.getContentPane().add(scroll);

        frame.setVisible(true);
    }

    public void addBackListener(ActionListener e) {
        backButton.addActionListener(e);
    }

    public void addImportListener(ActionListener e) {
        importButton.addActionListener(e);
    }

    public void addAddListener(ActionListener e)
    {
        addButton.addActionListener(e);
    }

    public void addDeleteListener(ActionListener e)
    {
        deleteButton.addActionListener(e);
    }

    public void addModifyListener(ActionListener e)
    {
        modifyButton.addActionListener(e);
    }

    public void addCreateCompositionListener(ActionListener e){
        createButton.addActionListener(e);
    }

    public void addReportsGenerator(ActionListener e)
    {
        reportButton.addActionListener(e);
    }

    public String getTitle()
    {
        return titleText.getText();
    }
    public String getRating()
    {
        return ratingText.getText();
    }
    public String getCalories()
    {
        return caloriesText.getText();
    }
    public String getProtein()
    {
        return proteinText.getText();
    }
    public String getFat()
    {
        return fatText.getText();
    }
    public String getSodium()
    {
        return sodiumText.getText();
    }
    public String getPrice()
    {
        return priceText.getText();
    }
    public String getStartTime() {return startTimeText.getText();}
    public String getStopTime() { return stopTimeText.getText(); }
    public String getMinOrderedProduct() { return productOrderedMoreText.getText();}
    public String getMinOrdersClient() {return clientWithOrdersText.getText();}
    public String getMinPriceOrderClient() {return clientWithOrdersHigherText.getText();}
    public String getProductOrderedDay() {return productOrderOnText.getText();}

    public void setTable(JTable table)
    {
        frame.getContentPane().remove(scroll);
        this.table=table;
        this.table.setFillsViewportHeight(true);
        this.table.setPreferredScrollableViewportSize(new Dimension(500,500));
        scroll = new JScrollPane(table);
        frame.getContentPane().add(scroll);

        frame.setVisible(true);
    }
}
