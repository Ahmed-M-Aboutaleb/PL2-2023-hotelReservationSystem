package Other;

import FC.CRUD;
import FC.FC;

import java.util.ArrayList;
import java.util.UUID;

public class Service implements CRUD {
    private String id;
    private String name;
    private String description;
    private int price;
    private int usage;

    public Service() {
        this("temp", "temp", 0);
    }

    public Service(String name, String description, int price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public int getUsage() {
        return this.usage;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setName(String name) throws Exception {
        if (!name.matches("^[a-zA-Z]+$")) throw new Exception("Invalid name");
        this.name = name;
    }

    public void setDescription(String description) throws Exception {
        if (!description.matches("^([a-zA-Z0-9\\s])+$")) throw new Exception("Invalid description");
        this.description = description;
    }

    public void setPrice(int price) throws Exception {
        if (price < 0 || price > 5000) {
            throw new Exception("Invalid price");
        }
        this.price = price;
    }

    public void setUsage(int usage) throws Exception {
        if(usage <0) {
            throw new Exception("Invalid usage");
        }
        this.usage = usage;
    }

    @Override
    public boolean create() throws Exception {
        ArrayList<String> myData = this.toArrayList(this);
        String data = FC.encode(myData);
        FC myController = new FC("Service");
        myController.appendFile(data);
        return true;
    }

    @Override
    public Object read(String id) throws Exception {
        ArrayList<Service> services = this.getAll();
        Service myService = null;
        for (Service service : services) {
            if (id.equals(service.getID())) {
                myService = service;
                break;
            }
        }
        if (myService == null)
            throw new Exception("No service found!");
        return myService;
    }

    @Override
    public boolean update(Object obj) throws Exception {
        if (!(obj instanceof Service oldService)) {
            throw new Exception("Invalid Service");
        }
        ArrayList<String> myOldData = this.toArrayList(oldService);
        String oldData = FC.encode(myOldData);
        ArrayList<String> myData = this.toArrayList(this);
        String data = FC.encode(myData);
        FC myController = new FC("Service");
        myController.updateFile(oldData, data);
        return true;
    }

    @Override
    public boolean delete(Object obj) throws Exception {
        if (!(obj instanceof Service deleteService)) {
            throw new Exception("Invalid Service");
        }
        ArrayList<String> myData = this.toArrayList(deleteService);
        String data = FC.encode(myData);
        FC myController = new FC("Service");
        myController.updateFile(data, "");
        return true;
    }

    @Override
    public ArrayList<Service> getAll() throws Exception {
        ArrayList<Service> myData = new ArrayList<>();
        FC myController = new FC("Service");
        ArrayList<String> services = myController.readFile();
        for (String service : services) {
            Service myService = new Service();
            ArrayList<String> serviceData = FC.decode(service);
            myService.setID(serviceData.get(0));
            myService.setName(serviceData.get(1));
            myService.setDescription(serviceData.get(2));
            myService.setPrice(Integer.parseInt(serviceData.get(3)));
            myService.setUsage(Integer.parseInt(serviceData.get(4)));
            myData.add(myService);
        }
        return myData;
    }

    @Override
    public ArrayList<String> toArrayList(Object obj) throws Exception {
        if (!(obj instanceof Service myService)) {
            throw new Exception("Invalid Service");
        }
        ArrayList<String> service = new ArrayList<>();
        service.add(myService.getID());
        service.add(myService.getName());
        service.add(myService.getDescription());
        service.add(Integer.toString(myService.getPrice()));
        service.add(Integer.toString(myService.getUsage()));
        return service;
    }

    public ArrayList<String> generateUsageReport() throws Exception {
        ArrayList<String> report = new ArrayList<>();
        FC myControler = new FC("totalUsage");
        int totalUsage = Integer.parseInt(myControler.readFile().getFirst());
        if(totalUsage == 0) {
            totalUsage++;
        }
        report.add(this.getID());
        report.add(this.getName());
        report.add(Integer.toString(this.getUsage()));
        report.add(Double.toString((double) (this.getUsage() / totalUsage) * 100));
        return report;
    }
    @Override
    public String toString() {
        String data = "";
        data += "ID: " + this.getID() + "\n";
        data += "Name: " + this.getName() + "\n";
        data += "Description: " + this.getDescription() + "\n";
        data += "Price: " + this.getPrice() + "\n";
        data += "Usage: " + this.getUsage() + "\n";
        return data;
    }
}