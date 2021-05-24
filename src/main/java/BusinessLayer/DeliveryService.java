package BusinessLayer;

import Model.Admin;
import Model.Client;
import Model.Worker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DeliveryService implements IDeliveryServiceProcessing{

    private HashMap<Order, ArrayList<MenuItem>> orders = new HashMap();
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<Admin>();
    private ArrayList<Worker> workers = new ArrayList<Worker>();
    private ArrayList<Client> clients = new ArrayList<Client>();

    @Override
    public void importProducts() {
        var filePath = System.getProperty("user.dir")+"/src/main/resources/products.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line = br.readLine();
            while((line = br.readLine()) != null)
            {
                String[] res = line.split(",",0);
                MenuItem product = new BaseProduct(res[0],Float.parseFloat(res[1]),Integer.parseInt(res[2]),Integer.parseInt(res[3]),Integer.parseInt(res[4]),Integer.parseInt(res[5]),Integer.parseInt(res[6]));
                menuItems.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProduct() {


    }

    @Override
    public void deleteProduct() {

    }

    @Override
    public void modifyProduct() {

    }

    @Override
    public void generateReport() {

    }

    public boolean verifyLogIn(String role, String username, String password)
    {
        if(role.equals("Admin"))
        {
            for(Admin i: admins)
            {
                if(i.getUsername().equals(username) && i.getPassword().equals(password))
                    return true;
            }
        }
        else
        {
            if(role.equals("Worker"))
            {
                for(Worker i: workers)
                    if(i.getUsername().equals(username) && i.getPassword().equals(password))
                        return true;
                return false;
            }
            for(Client i: clients)
                if(i.getUsername().equals(username) && i.getPassword().equals(password))
                    return true;
        }
        return false;
    }

    public void addInitialUsers()
    {
        var filePath = System.getProperty("user.dir")+"/src/main/resources/users.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while((line = br.readLine()) != null)
            {
                String[] res = line.split(",",0);
                if(res[2].equals("admin"))
                {
                    Admin admin = new Admin(res[0],res[1]);
                    admins.add(admin);
                }
                else
                {
                    if(res[2].equals("client"))
                    {
                        Client client = new Client(res[0],res[1]);
                        clients.add(client);
                    }
                    else
                    {
                        Worker worker = new Worker(res[0],res[1]);
                        workers.add(worker);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }
}
