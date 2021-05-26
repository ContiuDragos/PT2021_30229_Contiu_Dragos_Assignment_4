package view;

import BusinessLayer.*;
import BusinessLayer.MenuItem;
import Model.Admin;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminController {
    private final AdminGUI adminGUI;
    private final DeliveryService deliveryService;

    public AdminController(AdminGUI adminGUI, DeliveryService deliveryService)
    {
        this.adminGUI=adminGUI;
        this.deliveryService=deliveryService;
        deliveryService.importProducts();
        adminGUI.addBackListener(new BackListener());
        adminGUI.addImportListener(new ImportListener());
        adminGUI.addAddListener(new AddListener());
        adminGUI.addDeleteListener(new DeleteListener());
        adminGUI.addModifyListener(new ModifyListener());
        adminGUI.addCreateCompositionListener(new CreateCompositionListener(this));
        adminGUI.addReportsGenerator(new ReportGenerator());
    }

    public void setTable()
    {
        String[] names = {"Id","Title", "Rating", "Calories", "Protein","Fat", "Sodium", "Price"};
        List<MenuItem> list = deliveryService.getMenuItems();
        Object[][] product = new Object[list.size()][8];
        int counter = 0;

        for(MenuItem i : list)
        {
            product[counter][0] = i.getId();
            product[counter][1] = i.getTitle();
            product[counter][2] = i.getRating();
            product[counter][3] = i.getCalories();
            product[counter][4] = i.getProtein();
            product[counter][5] = i.getFat();
            product[counter][6] = i.getSodium();
            product[counter][7] = i.getPrice();
            counter++;
        }
        JTable table = new JTable(product,names);
        adminGUI.setTable(table);
    }

    class BackListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            adminGUI.frame.dispose();
            new MainController(new MainGUI(),deliveryService);
        }
    }

    class ImportListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            setTable();
        }
    }

    class AddListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String title = adminGUI.getTitle();
            float rating = Float.parseFloat(adminGUI.getRating());
            int calories = Integer.parseInt(adminGUI.getCalories());
            int protein = Integer.parseInt(adminGUI.getProtein());
            int fat = Integer.parseInt(adminGUI.getFat());
            int sodium = Integer.parseInt(adminGUI.getSodium());
            int price = Integer.parseInt(adminGUI.getPrice());

            if(!deliveryService.addProduct(title,rating,calories,protein,fat,sodium,price))
                new ErrorGUI("Product already exists");
            else
                setTable();
        }
    }

    class DeleteListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String title = adminGUI.getTitle();
            if(deliveryService.deleteProduct(title))
                setTable();
            else
                new ErrorGUI("Product does not exists");
        }
    }

    class ModifyListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String title = adminGUI.getTitle();
            float rating = Float.parseFloat(adminGUI.getRating());
            int calories = Integer.parseInt(adminGUI.getCalories());
            int protein = Integer.parseInt(adminGUI.getProtein());
            int fat = Integer.parseInt(adminGUI.getFat());
            int sodium = Integer.parseInt(adminGUI.getSodium());
            int price = Integer.parseInt(adminGUI.getPrice());

            if(deliveryService.modifyProduct(title,rating,calories,protein,fat,sodium,price))
                setTable();
            else
                new ErrorGUI("The product does not exists");
        }
    }

    class CreateCompositionListener implements ActionListener
    {
        private AdminController adminController;

        public CreateCompositionListener(AdminController adminController)
        {
            this.adminController=adminController;
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            CompositionGUI compositionGUI = new CompositionGUI();
            compositionGUI.addProducts(deliveryService.getMenuItems());

            CompositionController compositionController = new CompositionController(compositionGUI,deliveryService,adminController);
        }
    }

    class ReportGenerator implements ActionListener
    {
        JFrame frame =new JFrame("Report");
        JTable table;
        JScrollPane scroll;
        int counter=0;
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            counter=0;
            frame.setMinimumSize(new Dimension(600,400));
            frame.setLocationRelativeTo(null);
            if(!adminGUI.getStartTime().equals(""))
            {
                int startTime = Integer.parseInt(adminGUI.getStartTime());
                int stopTime = Integer.parseInt(adminGUI.getStopTime());
                List<Order> res = deliveryService.generateTimeReport(startTime,stopTime);
                String[] names = {"Order ID", "Client ID","Date"};
                Object[][] data = new Object[res.size()][3];
                for(Order i : res)
                {
                    data[counter][0]= i.getOrderID();
                    data[counter][1]= i.getClientID();
                    data[counter][2]= i.getDate();
                    counter++;
                }
                table = new JTable(data,names);
                scroll = new JScrollPane(table);
                frame.getContentPane().add(scroll);
                frame.setVisible(true);
                return;
            }
            if(!adminGUI.getMinOrderedProduct().equals("")) {
                List<MenuItem> result = deliveryService.generateProductOrderedMoreReport(Integer.parseInt(adminGUI.getMinOrderedProduct()));
                String[] names = {"Id","Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price","TimesOrdered"};
                Object[][] data = new Object[result.size()][9];
                for(MenuItem i : result)
                {
                    data[counter][0] = i.getId();
                    data[counter][1] = i.getTitle();
                    data[counter][2] = i.getRating();
                    data[counter][3] = i.getCalories();
                    data[counter][4] = i.getProtein();
                    data[counter][5] = i.getFat();
                    data[counter][6] = i.getSodium();
                    data[counter][7] = i.getPrice();
                    data[counter][8] = i.getTimesOrdered();
                    counter++;
                }
                table = new JTable(data,names);
                scroll = new JScrollPane(table);
                frame.getContentPane().add(scroll);
                frame.setVisible(true);
                return;
            }
            if(!adminGUI.getMinOrdersClient().equals(""))
            {
                int minimumOrders = Integer.parseInt(adminGUI.getMinOrdersClient());
                int minimumValue = Integer.parseInt(adminGUI.getMinPriceOrderClient());

                System.out.println(minimumOrders+" "+minimumValue);
                List<Client> result = deliveryService.generateClientWithMinOrders(minimumOrders,minimumValue);
                String[] names = {"Client ID","Username"};
                Object[][] data = new Object[result.size()][2];
                for(Client i: result)
                {
                    data[counter][0] = i.getId();
                    data[counter][1] = i.getUsername();
                    counter++;
                }
                table = new JTable(data,names);
                scroll = new JScrollPane(table);
                frame.getContentPane().add(scroll);
                frame.setVisible(true);
                return;
            }
            if(!adminGUI.getProductOrderedDay().equals(""))
            {
                int day = Integer.parseInt(adminGUI.getProductOrderedDay());

                List<MenuItem> result = deliveryService.generateProductsWithinASpecifiedDay(day);
                String[] names = {"Id","Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price","TimesOrdered"};
                Object[][] data = new Object[result.size()][9];
                for(MenuItem i : result)
                {
                    data[counter][0] = i.getId();
                    data[counter][1] = i.getTitle();
                    data[counter][2] = i.getRating();
                    data[counter][3] = i.getCalories();
                    data[counter][4] = i.getProtein();
                    data[counter][5] = i.getFat();
                    data[counter][6] = i.getSodium();
                    data[counter][7] = i.getPrice();
                    data[counter][8] = i.getTimesOrdered();
                    counter++;
                }
                table = new JTable(data,names);
                scroll = new JScrollPane(table);
                frame.getContentPane().add(scroll);
                frame.setVisible(true);
                return;
            }
        }
    }
}
