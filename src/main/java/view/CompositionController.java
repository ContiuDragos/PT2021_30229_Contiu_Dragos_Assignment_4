package view;

import BusinessLayer.BaseProduct;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompositionController {
    private final CompositionGUI compositionGUI;
    private final DeliveryService deliveryService;
    private ArrayList<BaseProduct> composition = new ArrayList<>();
    private int counter;
    private final AdminController adminController;

    public CompositionController(CompositionGUI compositionGUI, DeliveryService deliveryService, AdminController adminController)
    {
        this.compositionGUI=compositionGUI;
        this.deliveryService=deliveryService;
        this.adminController=adminController;
        compositionGUI.addAddListener(new AddListener());
        compositionGUI.addSubmitListener(new SubmitListener(adminController));
    }

    class AddListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ArrayList<MenuItem> menu = deliveryService.getMenuItems();
            for(int i=0; i<menu.size(); i++) {
                MenuItem product = menu.get(i);
                if (product instanceof BaseProduct)
                    if (((BaseProduct) product).getTitle().equals(compositionGUI.getProduct())) {
                        composition.add((BaseProduct) product);
                        compositionGUI.addToLabel(++counter);
                    }
            }
        }
    }

    class SubmitListener implements ActionListener
    {
        private AdminController adminController;

        public SubmitListener(AdminController adminController)
        {
            this.adminController=adminController;
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            compositionGUI.frame.dispose();
            deliveryService.createCompositeProduct(composition,compositionGUI.getName());
            adminController.setTable();
        }
    }
}
