package Room;

import FC.CRUD;
import FC.FC;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class RoomStatus implements CRUD {
    private String id;
    private String customerID;
    private String roomID;
    private String status;
    private String checkoutDate;
    private String checkinDate;
    public final static ArrayList<String> statuses = new ArrayList<>(Arrays.asList("pending", "approved"));

    public RoomStatus() {
        this("null", "null", "pending", LocalDate.now().toString(), LocalDate.now().toString());
    }
    public RoomStatus(String customerID, String roomID, String status, String checkoutDate, String checkinDate) {
        this.id = UUID.randomUUID().toString();
        this.customerID = customerID;
        this.roomID = roomID;
        this.status = status;
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

    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) throws Exception {
        if(!statuses.contains(status))
            throw new Exception("Invalid status");
        this.status = status;
    }

    public String getCheckoutDate() {
        return this.checkoutDate;
    }
    public void setCheckoutDate(String checkoutDate) throws Exception {
        if(!checkoutDate.matches("^\\\\d{4}-\\\\d{2}-\\\\d{2}$"))
            throw new Exception("Invalid checkout Date");
        LocalDate parsedCheckoutDate = LocalDate.parse(checkoutDate, DateTimeFormatter.ISO_DATE);
        LocalDate currentDate = LocalDate.now();
        if (parsedCheckoutDate.isBefore(currentDate)) {
            throw new Exception("Checkout date cannot be in the past");
        }

        this.checkoutDate = checkoutDate;
    }
    public String getCheckinDate() {
        return this.checkinDate;
    }
    public void setCheckinDate(String checkinDate) throws Exception {
        if(!checkinDate.matches("^\\\\d{4}-\\\\d{2}-\\\\d{2}$"))
            throw new Exception("Invalid checkout Date");
        LocalDate parsedCheckoutDate = LocalDate.parse(checkinDate, DateTimeFormatter.ISO_DATE);
        LocalDate currentDate = LocalDate.now();
        if (parsedCheckoutDate.isBefore(currentDate)) {
            throw new Exception("Checkin date cannot be in the past");
        }

        this.checkinDate = checkinDate;
    }

    @Override
    public boolean create() throws Exception {
        ArrayList<String> myData = this.toArrayList(this);
        String data = FC.encode(myData);
        FC myController = new FC("RoomStatus");
        myController.appendFile(data);
        return true;
    }

    @Override
    public Object read(String id) throws Exception {
        ArrayList<RoomStatus> roomStatuses = this.getAll();
        RoomStatus myRoomStatus = null;

        for (RoomStatus roomStatus : roomStatuses) {
            if (id.equals(roomStatus.getID())) {
                myRoomStatus = roomStatus;
                break;
            }
        }
        if(myRoomStatus == null) {
            throw new Exception("No room statuses found!");
        }
        return myRoomStatus;
    }

    @Override
    public boolean update(Object obj) throws Exception {
        if (!(obj instanceof RoomStatus oldObj)) {
            throw new Exception("Invalid Room Status");
        }
        ArrayList<String> myOldData = this.toArrayList(oldObj);
        String oldData = FC.encode(myOldData);
        ArrayList<String> myData = this.toArrayList(this);
        String data = FC.encode(myData);
        FC myController = new FC("RoomStatus");
        myController.updateFile(oldData, data);
        return true;
    }

    @Override
    public boolean delete(Object obj) throws Exception {
        if (!(obj instanceof RoomStatus deleteObj)) {
            throw new Exception("Invalid Room Status");
        }
        ArrayList<String> myData = this.toArrayList(deleteObj);
        String data = FC.encode(myData);
        FC myController = new FC("RoomStatus");
        myController.updateFile(data, "");
        return true;
    }

    @Override
    public ArrayList<RoomStatus> getAll() throws Exception {
        ArrayList<RoomStatus> myData = new ArrayList<>();
        FC myController = new FC("RoomStatus");
        ArrayList<String> rooms = myController.readFile();
        for (String room : rooms) {
            RoomStatus myRoom = new RoomStatus();
            ArrayList<String> roomData = FC.decode(room);
            myRoom.setID(roomData.get(0));
            myRoom.setCustomerID(roomData.get(1));
            myRoom.setRoomID(roomData.get(2));
            myRoom.setStatus(roomData.get(3));
            myRoom.setCheckoutDate(roomData.get(4));
            myRoom.setCheckinDate(roomData.get(5));
            myData.add(myRoom);
        }
        return myData;
    }

    @Override
    public ArrayList<String> toArrayList(Object obj) throws Exception {
        if (!(obj instanceof RoomStatus myRoom)) {
            throw new Exception("Invalid Room Status");
        }
        ArrayList<String> room = new ArrayList<>();
        room.add(myRoom.getID());
        room.add(myRoom.getCustomerID());
        room.add(myRoom.getRoomID());
        room.add(myRoom.getStatus());
        room.add(myRoom.getCheckoutDate());
        room.add(myRoom.getCheckinDate());
        return room;
    }

    @Override
    public String toString() {
        String data = "";
        data += "ID: " + this.getID() + "\n";
        data += "Customer ID: " + this.getCustomerID() + "\n";
        data += "Room ID: " + this.getRoomID() + "\n";
        data += "Status: " + this.getStatus() + "\n";
        data += "Checkout Date: " + this.getCheckoutDate() + "\n";
        data += "Checkin Date: " + this.getCheckinDate() + "\n";
        return data;
    }

    public boolean assignRoomToGuest(String customerID) throws Exception {
        RoomStatus old = this;
        this.setCustomerID(customerID);
        this.setStatus(statuses.get(1));
        this.update(old);
        Room theRoom = new Room();
        theRoom = (Room) theRoom.read(theRoom.getID());
        Room theOldRoom = new Room();
        theRoom = (Room) theRoom.read(theRoom.getID());
        theRoom.setStatus(this.getID());
        theRoom.update(theOldRoom);
        return true;
    }
    public ArrayList<RoomStatus> nearCheckOut() throws Exception {
        ArrayList<RoomStatus> data = new ArrayList<>();
        ArrayList<RoomStatus> roomStatuses = this.getAll();
        for (RoomStatus status: roomStatuses) {
            if (!LocalDate.parse(status.getCheckoutDate(), DateTimeFormatter.ISO_DATE).isBefore(LocalDate.now().plusDays(3))) {
                continue;
            }
            data.add(status);
        }
        return data;
    }
}
