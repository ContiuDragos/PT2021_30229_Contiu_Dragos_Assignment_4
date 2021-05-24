package view;

import BusinessLayer.BaseProduct;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
    private AdminGUI adminGUI;
    private DeliveryService deliveryService;

    public AdminController(AdminGUI adminGUI, DeliveryService deliveryService)
    {
        this.adminGUI=adminGUI;
        this.deliveryService=deliveryService;
        adminGUI.addBackListener(new BackListener());
    }

    class BackListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            adminGUI.frame.dispose();
            new MainController(new MainGUI(),deliveryService);
        }
    }

    class ImportMenu implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            List<MenuItem> list = deliveryService.getMenuItems();
            String[][] product = new String[list.size()][8];
            int counter = 0;

            for(MenuItem i : list)
            {
                if(i instanceof BaseProduct)
                {
                    product[counter][0] = ((BaseProduct) i).getTitle();
                    product[counter][1] = ""+((BaseProduct) i).getRating();
                    product[counter][2] = ""+((BaseProduct) i).getCalories();
                    product[counter][3] = ""+((BaseProduct) i).getProtein();
                    product[counter][4] = ""+((BaseProduct) i).getFat();
                    product[counter][5] = ""+((BaseProduct) i).getSodium();
                    product[counter][6] = ""+((BaseProduct) i).getPrice();
                }
            }
            String[] names = {"Title", "Rating", "Calories", "Protein","Fat", "Sodium", "Price"};

            JTable table = new JTable(product,names);
        }
    }
}
