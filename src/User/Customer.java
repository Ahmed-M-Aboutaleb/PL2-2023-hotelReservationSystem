package User;

import FC.FC;
import Room.Bill;

import java.util.ArrayList;
import java.util.UUID;

public class Customer extends User {
    private String billID;

    public Customer() throws Exception {
        this("null", 1, "none");
    }

    public Customer(String name, int age, String billID) throws Exception {
        String uniqueID = UUID.randomUUID().toString();
        super.setID(uniqueID);
        super.setName(name);
        super.setAge(age);
        this.billID = billID;
    }

    public String getbillID() {
        return this.billID;
    }

    public void setbillID(String billID) throws Exception {
        if (billID.isEmpty())
            throw new Exception("Invalid bill id");
        this.billID = billID;
    }
    @Override
    public boolean create() throws Exception {
        ArrayList<String> myData = this.toArrayList(this);
        String data = FC.encode(myData);
        FC myController = new FC("Customer");
        myController.appendFile(data);
        return true;
    }

    @Override
    public Object read(String id) throws Exception {
        ArrayList<Customer> customers = this.getAll();
        Customer myCustomer = null;

        for (Customer customer : customers) {
            if (id.equals(customer.getID())) {
                myCustomer = customer;
                break;
            }
        }
        if(myCustomer == null) {
            throw new Exception("No customers found!");
        }
        return myCustomer;
    }

    @Override
    public boolean update(Object obj) throws Exception {
        if (!(obj instanceof Customer oldCustomer)) {
            throw new Exception("Invalid Customer");
        }
        ArrayList<String> myOldData = this.toArrayList(oldCustomer);
        String oldData = FC.encode(myOldData);
        ArrayList<String> myData = this.toArrayList(this);
        String data = FC.encode(myData);
        FC myController = new FC("Customer");
        myController.updateFile(oldData, data);
        return true;
    }

    @Override
    public boolean delete(Object obj) throws Exception {
        if (!(obj instanceof Customer deleteCustomer)) {
            throw new Exception("Invalid Customer");
        }
        ArrayList<String> myData = this.toArrayList(deleteCustomer);
        String data = FC.encode(myData);
        FC myController = new FC("Customer");
        myController.updateFile(data, "");
        return true;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        ArrayList<Customer> myData = new ArrayList<>();
        FC myController = new FC("Customer");
        ArrayList<String> customers = myController.readFile();
        for (String customer : customers) {
            Customer myCustomer = new Customer();
            ArrayList<String> employeeData = FC.decode(customer);
            myCustomer.setID(employeeData.get(0));
            myCustomer.setName(employeeData.get(1));
            myCustomer.setAge(Integer.parseInt(employeeData.get(2)));
            myCustomer.setbillID(employeeData.get(3));
            myData.add(myCustomer);
        }
        return myData;
    }

    @Override
    public ArrayList<String> toArrayList(Object obj) throws Exception {
        if (!(obj instanceof Customer myCustomer)) {
            throw new Exception("Invalid Customer");
        }
        ArrayList<String> customer = new ArrayList<>();
        customer.add(myCustomer.getID());
        customer.add(myCustomer.getName());
        customer.add(Integer.toString(myCustomer.getAge()));
        customer.add(myCustomer.getbillID());
        return customer;
    }
    @Override
    public String toString() {
        String data = "";
        data += "ID: " + this.getID() + "\n";
        data += "Name: " + this.getName() + "\n";
        data += "Age: " + this.getAge() + "\n";
        data += "Bill id: " + this.getbillID() + "\n";
        return data;
    }
    public String getInvoice() throws Exception {
        Bill myBill = new Bill();
        myBill = (Bill) myBill.read(this.getbillID());
        return myBill.toString();
    }
}
