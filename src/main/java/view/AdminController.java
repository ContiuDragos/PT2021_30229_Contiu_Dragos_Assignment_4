package view;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import Model.Admin;

import javax.swing.*;
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
    }

    public void setTable()
    {
        String[] names = {"Id","Title", "Rating", "Calories", "Protein","Fat", "Sodium", "Price"};
        List<MenuItem> list = deliveryService.getMenuItems();
        Object[][] product = new Object[list.size()][8];
        int counter = 0;

        for(MenuItem i : list)
        {
            if(i instanceof BaseProduct)
            {
                product[counter][0] = ((BaseProduct) i).getId();
                product[counter][1] = ((BaseProduct) i).getTitle();
                product[counter][2] = ((BaseProduct) i).getRating();
                product[counter][3] = ((BaseProduct) i).getCalories();
                product[counter][4] = ((BaseProduct) i).getProtein();
                product[counter][5] = ((BaseProduct) i).getFat();
                product[counter][6] = ((BaseProduct) i).getSodium();
                product[counter][7] = ((BaseProduct) i).getPrice();
            }
            else
            {
                product[counter][0] = ((CompositeProduct)i).getId();
                product[counter][1] = ((CompositeProduct)i).getTitle();
                product[counter][2] = ((CompositeProduct)i).getRating();
                product[counter][3] = ((CompositeProduct)i).getCalories();
                product[counter][4] = ((CompositeProduct)i).getProtein();
                product[counter][5] = ((CompositeProduct)i).getFat();
                product[counter][6] = ((CompositeProduct)i).getSodium();
                product[counter][7] = ((CompositeProduct)i).getPrice();
            }
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
}
