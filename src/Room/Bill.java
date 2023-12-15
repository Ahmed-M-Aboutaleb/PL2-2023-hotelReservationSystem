package Room;

import FC.CRUD;
import FC.FC;
import Other.Service;

import java.util.ArrayList;
import java.util.UUID;

public class Bill implements CRUD {
    private String id;
    private int total;
    private String customerID;
    private String roomID;
    private String checkoutDate;
    private String checkinDate;

    public Bill() {
        this(0, "none", "none", "0000-00-00", "0000-00-00");
    }
    public Bill(int total, String customerID, String roomID, String checkoutDate, String checkinDate) {
        this.id = UUID.randomUUID().toString();
        this.total = total;
        this.customerID = customerID;
        this.roomID = roomID;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
    }
    public String getID() {
        return this.id;
    }
    public void setID(String id) throws Exception {
        if (id.isEmpty())
            throw new Exception("Invalid id");
        this.id = id;
    }

    public void setTotal(int total) throws Exception {
        if(total <0)
            throw new Exception("Invalid total price");
        this.total = total;
    }
    public int getTotal() {
        return this.total;
    }
    public String getCustomerID() {
        return this.customerID;
    }
    public void setCustomerID(String customerID) throws Exception {
        if(customerID.isEmpty())
            throw new Exception("Invalid customer id");
        this.customerID = customerID;
    }
    public String getRoomID() {
        return this.roomID;
    }
    public void setRoomID(String roomID) throws Exception {
        if(roomID.isEmpty())
            throw new Exception("Invalid room id");
        this.roomID = roomID;
    }
    public String getCheckoutDate() {
        return this.checkoutDate;
    }
    public void setCheckoutDate(String checkoutDate) throws Exception {
        if(!checkoutDate.matches("^\\d{4}-\\d{2}-\\d{2}$"))
            throw new Exception("Invalid checkout Date");

        this.checkoutDate = checkoutDate;
    }
    public String getCheckinDate() {
        return this.checkinDate;
    }
    public void setCheckinDate(String checkinDate) throws Exception {
        if(!checkinDate.matches("^\\d{4}-\\d{2}-\\d{2}$"))
            throw new Exception("Invalid checkin Date");
        this.checkinDate = checkinDate;
    }
    @Override
    public boolean create() throws Exception {
        ArrayList<String> myData = this.toArrayList(this);
        String data = FC.encode(myData);
        FC myController = new FC("Bill");
        myController.appendFile(data);
        return true;
    }

    @Override
    public Object read(String id) throws Exception {
        ArrayList<Bill> bills = this.getAll();
        Bill myBill = null;

        for (Bill bill : bills) {
            if (id.equals(bill.getID())) {
                myBill = bill;
                break;
            }
        }
        if(myBill == null) {
            throw new Exception("No bills found!");
        }
        return myBill;
    }

    @Override
    public boolean update(Object obj) throws Exception {
        if (!(obj instanceof Bill oldObj)) {
            throw new Exception("Invalid Room Status");
        }
        ArrayList<String> myOldData = this.toArrayList(oldObj);
        String oldData = FC.encode(myOldData);
        ArrayList<String> myData = this.toArrayList(this);
        String data = FC.encode(myData);
        FC myController = new FC("Bill");
        myController.updateFile(oldData, data);
        return true;
    }

    @Override
    public boolean delete(Object obj) throws Exception {
        if (!(obj instanceof Bill deleteObj)) {
            throw new Exception("Invalid Bill");
        }
        ArrayList<String> myData = this.toArrayList(deleteObj);
        String data = FC.encode(myData);
        FC myController = new FC("Bill");
        myController.updateFile(data, "");
        return true;
    }

    @Override
    public ArrayList<Bill> getAll() throws Exception {
        ArrayList<Bill> myData = new ArrayList<>();
        FC myController = new FC("Bill");
        ArrayList<String> bills = myController.readFile();
        for (String bill : bills) {
            Bill myBill = new Bill();
            ArrayList<String> billData = FC.decode(bill);
            myBill.setID(billData.get(0));
            myBill.setTotal(Integer.parseInt(billData.get(1)));
            myBill.setCustomerID(billData.get(2));
            myBill.setRoomID(billData.get(3));
            myBill.setCheckoutDate(billData.get(4));
            myBill.setCheckinDate(billData.get(5));
            myData.add(myBill);
        }
        return myData;
    }

    @Override
    public ArrayList<String> toArrayList(Object obj) throws Exception {
        if (!(obj instanceof Bill myBill)) {
            throw new Exception("Invalid Bill Status");
        }
        ArrayList<String> bill = new ArrayList<>();
        bill.add(myBill.getID());
        bill.add(Integer.toString(myBill.getTotal()));
        bill.add(myBill.getCustomerID());
        bill.add(myBill.getRoomID());
        bill.add(myBill.getCheckoutDate());
        bill.add(myBill.getCheckinDate());
        return bill;
    }

    @Override
    public String toString() {
        String data = "";
        data += "ID: " + this.getID() + "\n";
        data += "Total: " + this.getTotal() + "\n";
        data += "Customer ID: " + this.getCustomerID() + "\n";
        data += "Room ID: " + this.getRoomID() + "\n";
        data += "Checkout Date: " + this.getCheckoutDate() + "\n";
        data += "Checkin Date: " + this.getCheckinDate() + "\n";
        return data;
    }

    public void requestService(Service service) throws Exception {
        Bill oldBill = new Bill();
        oldBill = (Bill) oldBill.read(this.getID());
        this.setTotal(this.getTotal() + service.getPrice());
        Service oldService = new Service();
        oldService = (Service) oldService.read(service.getID());
        service.setUsage(service.getUsage()+1);
        FC myControler = new FC("totalUsage");
        int totalUsage = Integer.parseInt(myControler.readFile().getFirst()) + 1;
        myControler.writeFile(Integer.toString(totalUsage));
        this.update(oldBill);
        service.update(oldService);
    }
}
