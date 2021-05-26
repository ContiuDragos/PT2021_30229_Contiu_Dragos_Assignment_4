package view;

import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientController {
    private ClientGUI clientGUI;
    private DeliveryService deliveryService;
    private int counter;

    public ClientController(ClientGUI clientGUI, DeliveryService deliveryService)
    {
        this.clientGUI=clientGUI;
        this.deliveryService=deliveryService;
        deliveryService.importProducts();
        clientGUI.addProducts(deliveryService.getMenuItems());
        AddListener addListener = new AddListener();
        clientGUI.addAddListener(addListener);
        clientGUI.submitListener(new SubmitListener(addListener));
        clientGUI.addSearchListener(new SearchListener());
        clientGUI.addBackListener(new BackListener());
    }

    class AddListener implements ActionListener
    {
        public int price = 0;
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            price += deliveryService.getPrice(clientGUI.getProduct());
            clientGUI.addToLabels(++counter,price);
            deliveryService.addToOrder(clientGUI.getProduct());
        }
    }

    class SubmitListener implements ActionListener
    {
        private AddListener addListener;
        public SubmitListener(AddListener addListener)
        {
            this.addListener=addListener;
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            counter = 0;
            clientGUI.addToLabels(0,0);
            deliveryService.addOrder();
            addListener.price=0;
        }
    }

    class SearchListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String title;
            float rating;
            int fat;
            int calories;
            int protein;
            int price;
            int sodium;

            if(clientGUI.getTitle().equals(""))
                title="";
            else
                title=clientGUI.getTitle();

            if(clientGUI.getRating().equals(""))
                rating=0;
            else
                rating=Float.parseFloat(clientGUI.getRating());

            if(clientGUI.getCalories().equals(""))
                calories=50000000;
            else
                calories=Integer.parseInt(clientGUI.getCalories());

            if(clientGUI.getFat().equals(""))
                fat=50000000;
            else
                fat=Integer.parseInt(clientGUI.getFat());

            if(clientGUI.getSodium().equals(""))
                sodium=50000000;
            else
                sodium=Integer.parseInt(clientGUI.getSodium());

            if(clientGUI.getProtein().equals(""))
                protein=0;
            else
                protein=Integer.parseInt(clientGUI.getProtein());

            if(clientGUI.getPrice().equals(""))
                price=50000000;
            else
                price = Integer.parseInt(clientGUI.getPrice());

            List<MenuItem> searchedList = deliveryService.searchItemByClient(title,rating,fat,protein,calories,sodium,price);

            JFrame resultSearchFrame = new JFrame("Search Result");
            resultSearchFrame.setLocationRelativeTo(null);
            resultSearchFrame.setMinimumSize(new Dimension(1000,800));

            String[] names = {"Id","Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
            Object[][] data = new Object[searchedList.size()][8];
            JTable table = new JTable(data, names);
            JScrollPane scroll = new JScrollPane(table);

            for(MenuItem i : searchedList)
            {
                data[counter][0] = i.getId();
                data[counter][1] = i.getTitle();
                data[counter][2] = i.getRating();
                data[counter][3] = i.getCalories();
                data[counter][4] = i.getProtein();
                data[counter][5] = i.getFat();
                data[counter][6] = i.getSodium();
                data[counter][7] = i.getPrice();
                counter++;
            }
            counter=0;
            table = new JTable(data,names);
            table.setFillsViewportHeight(true);
            table.setPreferredScrollableViewportSize(new Dimension(500,500));

            scroll = new JScrollPane(table);
            resultSearchFrame.getContentPane().add(scroll);
            resultSearchFrame.setVisible(true);
        }
    }
    class BackListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            clientGUI.frame.dispose();
            new MainController(new MainGUI(),deliveryService);
        }
    }
}
