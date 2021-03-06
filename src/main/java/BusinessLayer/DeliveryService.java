package BusinessLayer;

import Model.Admin;
import Model.Client;
import Model.Worker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable{

    private HashMap<Order, ArrayList<MenuItem>> orders = new HashMap();
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<Admin>();
    private ArrayList<Worker> workers = new ArrayList<Worker>();
    private ArrayList<Client> clients = new ArrayList<Client>();
    private ArrayList<MenuItem> itemsInOrder = new ArrayList<>();
    private int nrProducts;
    private int nrClients;
    private int nrWorkers;
    private int nrAdmins;
    private int nrOrders;
    private int activeClient;
    private Order order;
    private Set<String> set = new HashSet<>();
    public final Serializator serializator = new Serializator();


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
        assert title.equals("") : "To add a product, you have to give the name";
        if(set.add(title))
        {
            menuItems.add(new BaseProduct(++nrProducts,title,rating,calories,protein,fat,sodium,price));
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(String title) {

        assert title.equals("") : "To delete a product, you have to give the name";
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

        assert removed : "The item could not be deleted";
        return true;
    }

    @Override
    public boolean modifyProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price) {

        assert title.equals("") : "To modify a product, you have to give the name";
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
    public void createCompositeProduct(ArrayList<BaseProduct> e, String title) {
        assert e.size()==0 : "A composite product must have at least one element";
        assert title.equals("") : "A composite product must have a name";

        CompositeProduct compositeProduct = new CompositeProduct(title,++nrProducts);
        for(BaseProduct i : e)
            compositeProduct.add(i);
        menuItems.add(compositeProduct);

        assert menuItems.contains(compositeProduct) : "The product could not be added to the menu";
    }

    @Override
    public List<Order> generateTimeReport(int startTime, int stopTime) {
        Map<Order,ArrayList<MenuItem>> result = orders.entrySet().stream().filter(map -> map.getKey().getDate().getHours() >= startTime).filter(map -> map.getKey().getDate().getHours() <= stopTime).collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
        List<Order> listResult = new ArrayList<>(result.keySet());
        return listResult;
    }

    @Override
    public List<MenuItem> generateProductOrderedMoreReport(int minimum) {
        return menuItems.stream().filter(product -> product.getTimesOrdered() >= minimum).collect(Collectors.toList());
    }

    @Override
    public List<Client> generateClientWithMinOrders(int minimumOrders, int minimumValue) {
        List<Client> result = clients.stream().filter(client -> client.getNrOrders() >= minimumOrders).collect(Collectors.toList());

        List<Client> finalResult = new ArrayList<>();
        for(Client i : result) {
            Map<Order, ArrayList<MenuItem>> orderResult = orders.entrySet().stream().filter(map -> map.getKey().getClientID() == i.getId()).filter(map -> map.getKey().getTotal() >= minimumValue).collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
            if(orderResult.size()>0)
                finalResult.add(i);
        }
        finalResult = finalResult.stream().distinct().collect(Collectors.toList());
        return finalResult;
    }

    @Override
    public List<MenuItem> generateProductsWithinASpecifiedDay(int day) {
        Map<Order,ArrayList<MenuItem>> result = orders.entrySet().stream().filter(map -> map.getKey().getDate().getDay() == day).collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
        List<MenuItem> finalResult = new ArrayList<>();
        for(Order i : result.keySet())
            finalResult.addAll(result.get(i));
        finalResult = finalResult.stream().distinct().collect(Collectors.toList());
        return finalResult;
    }

    @Override
    public void addOrder() {
        ArrayList<MenuItem> finalItemsInOrder = new ArrayList<>(itemsInOrder);
        assert order.getClientID() <= 0 : "An order must have a valid client";
        assert finalItemsInOrder.size() == 0 : "An order must have at least one product";

        String text = "";
        for(MenuItem i : itemsInOrder) {
            i.setTimesOrdered(i.getTimesOrdered() + 1);
            order.setTotal(order.getTotal()+i.getPrice());
            text = text + i.getTitle()+" ";
        }

        orders.put(order,finalItemsInOrder);
        setChanged();
        notifyObservers("OrderId = "+order.getOrderID()+" contains "+text);
        itemsInOrder.clear();

        assert order.getTotal() < 0 : "The order must cost at least 1 Euro";
        assert order.getClientID() <= 0 : "An order must have a valid client";
        assert orders.get(order).size() <= 0 : "An order must have at least an item";

        clients.get(order.getClientID()-1).setNrOrders(clients.get(order.getClientID()-1).getNrOrders()+1);
        new Bill(finalItemsInOrder,order);
    }

    @Override
    public List<MenuItem> searchItemByClient(String title, float rating, int fat, int protein, int calories,int sodium, int price) {

        List<MenuItem> searchedProducts ;
        if(title.equals(""))
            searchedProducts = menuItems.stream().filter(product -> product.getRating() >= rating).filter(product -> product.getCalories() <= calories).filter(product -> product.getFat() <= fat).filter(product -> product.getSodium() <= sodium).filter(product -> product.getProtein() >= protein).filter(product -> product.getPrice() <= price).collect(Collectors.toList());
        else
            searchedProducts = menuItems.stream().filter(product -> product.getTitle().contains(title)).filter(product -> product.getRating() >= rating).filter(product -> product.getCalories() <= calories).filter(product -> product.getFat() <= fat).filter(product -> product.getSodium() <= sodium).filter(product -> product.getProtein() >= protein).filter(product -> product.getPrice() <= price).collect(Collectors.toList());
        return searchedProducts;
    }

    public void addToOrder(String title)
    {
        if(itemsInOrder.size()==0)
            order = new Order(++nrOrders,activeClient,Calendar.getInstance().getTime());
        for(MenuItem i : menuItems)
        {
            if(i.getTitle().equals(title))
                itemsInOrder.add(i);
        }
    }

    public int getPrice(String title)
    {
        for(MenuItem i :menuItems)
            if(i.getTitle().equals(title))
                return i.computePrice();
        return 0;
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
                {
                    activeClient = clients.indexOf(i)+1;
                    return true;
                }
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
                    Admin admin = new Admin(++nrAdmins,res[0],res[1]);
                    admins.add(admin);
                }
                else
                {
                    if(res[2].equals("client"))
                    {
                        Client client = new Client(++nrClients,res[0],res[1]);
                        clients.add(client);
                    }
                    else
                    {
                        Worker worker = new Worker(++nrWorkers,res[0],res[1]);
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

    public Boolean wellFormed()
    {
        for(MenuItem i : menuItems)
            if(i.getPrice() < 0 || i.getCalories() <0 || i.getProtein() <0 || i.getSodium() <0 || i.getFat() < 0 || i.getRating() <0)
                return false;
        for(Order i : orders.keySet())
            if(i.getTotal()<0 || i.getOrderID() <0)
                return false;
        return true;
    }
}
