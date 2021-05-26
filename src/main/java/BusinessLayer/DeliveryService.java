package BusinessLayer;

import Model.Admin;
import Model.Client;
import Model.Worker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryService implements IDeliveryServiceProcessing{

    private HashMap<Order, ArrayList<MenuItem>> orders = new HashMap();
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<Admin>();
    private ArrayList<Worker> workers = new ArrayList<Worker>();
    private ArrayList<Client> clients = new ArrayList<Client>();
    private int nrProducts;
    private Set<String> set = new HashSet<>();


    @Override
    public void importProducts() {
        var filePath = System.getProperty("user.dir")+"/src/main/resources/products.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            List<String> lines;
            lines = br.lines().skip(1).collect(Collectors.toList());
            lines = lines.stream().distinct().collect(Collectors.toList());

            for(String i : lines)
            {
                String[] res = i.split(",",0);
                if(set.add(res[0])) {
                    MenuItem product = new BaseProduct(++nrProducts, res[0], Float.parseFloat(res[1]), Integer.parseInt(res[2]), Integer.parseInt(res[3]), Integer.parseInt(res[4]), Integer.parseInt(res[5]), Integer.parseInt(res[6]));
                    menuItems.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price) {
        if(set.add(title))
        {
            menuItems.add(new BaseProduct(++nrProducts,title,rating,calories,protein,fat,sodium,price));
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(String title) {
        if(!set.contains(title)) {
            return false;
        }
        set.remove(title);
        boolean removed = false;
        for(int i=0; i<menuItems.size(); i++)
        {
            if(menuItems.get(i) instanceof BaseProduct)
            {
                if(removed)
                    ((BaseProduct) menuItems.get(i)).setId(((BaseProduct) menuItems.get(i)).getId()-1);
                if(((BaseProduct) menuItems.get(i)).getTitle().equals(title))
                {
                    menuItems.remove(i);
                    i--;
                    nrProducts--;
                    removed = true;
                }
            }
        }
        return true;
    }

    @Override
    public boolean modifyProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price) {

        if(!set.contains(title))
            return false;
        for(MenuItem i : menuItems)
        {
            if(i instanceof BaseProduct)
            {
                if( i.getTitle().equals(title))
                {
                    ((BaseProduct) i).setRating(rating);
                    ((BaseProduct) i).setCalories(calories);
                    ((BaseProduct) i).setPrice(protein);
                    ((BaseProduct) i).setFat(fat);
                    ((BaseProduct) i).setSodium(sodium);
                    ((BaseProduct) i).setPrice(price);
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public boolean createCompositeProduct(ArrayList<BaseProduct> e, String title) {
        CompositeProduct compositeProduct = new CompositeProduct(title);
        for(BaseProduct i : e)
            compositeProduct.add(i);
        menuItems.add(compositeProduct);

        return true;
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
