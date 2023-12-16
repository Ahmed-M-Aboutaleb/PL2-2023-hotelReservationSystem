package Room;

import FC.CRUD;
import FC.FC;
import Other.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Room implements CRUD {
    private String id;
    private String type;
    private String status;
    private String currentServiceID;
    private int roomPrice;
    public static final ArrayList<String> types = new ArrayList<>(Arrays.asList("Single", "Double", "Triple", "Quad"));

    public Room() {
        this("Single", "available", "none");
    }
    public Room(String type, String status, String currentServiceID) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.status = status;
        this.currentServiceID = currentServiceID;
    }
    public String getID() {
        return this.id;
    }
    public void setID(String id) throws Exception {
        if (id.isEmpty())
            throw new Exception("Invalid id");
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) throws Exception {
        if (!types.contains(type))
            throw new Exception("Invalid Type");
        this.type = type;
    }

    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) throws Exception {
        if(status.isEmpty())
            throw new Exception("Invalid status");
        this.status = status;
    }
    public String getCurrentServiceID() {
        return this.currentServiceID;
    }
    public void setCurrentServiceID(String currentServiceID) throws Exception {
        if (currentServiceID.isEmpty())
            throw new Exception("Invalid Service id");
        this.currentServiceID = currentServiceID;
    }
    public void setRoomPrice(int roomPrice) throws Exception {
        if (roomPrice < 0 || roomPrice > 5000) {
            throw new Exception("Invalid price");
        }
        this.roomPrice = roomPrice;
    }
    public int getRoomPrice() {
        return this.roomPrice;
    }
    @Override
    public boolean create() throws Exception {
        ArrayList<String> myData = this.toArrayList(this);
        String data = FC.encode(myData);
        FC myController = new FC("Room");
        myController.appendFile(data);
        return true;
    }

    @Override
    public Object read(String id) throws Exception {
        ArrayList<Room> rooms = this.getAll();
        Room myRoom = null;

        for (Room room : rooms) {
            if (id.equals(room.getID())) {
                myRoom = room;
                break;
            }
        }
        if(myRoom == null) {
            throw new Exception("No rooms found!");
        }
        return myRoom;
    }

    @Override
    public boolean update(Object obj) throws Exception {
        if (!(obj instanceof Room oldObj)) {
            throw new Exception("Invalid Room");
        }
        ArrayList<String> myOldData = this.toArrayList(oldObj);
        String oldData = FC.encode(myOldData);
        ArrayList<String> myData = this.toArrayList(this);
        String data = FC.encode(myData);
        FC myController = new FC("Room");
        myController.updateFile(oldData, data);
        return true;
    }

    @Override
    public boolean delete(Object obj) throws Exception {
        if (!(obj instanceof Room deleteObj)) {
            throw new Exception("Invalid Room");
        }
        ArrayList<String> myData = this.toArrayList(deleteObj);
        String data = FC.encode(myData);
        FC myController = new FC("Room");
        myController.updateFile(data, "");
        return true;
    }

    @Override
    public ArrayList<Room> getAll() throws Exception {
        ArrayList<Room> myData = new ArrayList<>();
        FC myController = new FC("Room");
        ArrayList<String> rooms = myController.readFile();
        for (String room : rooms) {
            Room myRoom = new Room();
            ArrayList<String> roomData = FC.decode(room);
            myRoom.setID(roomData.get(0));
            myRoom.setType(roomData.get(1));
            myRoom.setStatus(roomData.get(2));
            myRoom.setCurrentServiceID(roomData.get(3));
            myRoom.setRoomPrice(Integer.parseInt(roomData.get(4)));
            myData.add(myRoom);
        }
        return myData;
    }

    @Override
    public ArrayList<String> toArrayList(Object obj) throws Exception {
        if (!(obj instanceof Room myRoom)) {
            throw new Exception("Invalid Room");
        }
        ArrayList<String> room = new ArrayList<>();
        room.add(myRoom.getID());
        room.add(myRoom.getType());
        room.add(myRoom.getStatus());
        room.add(myRoom.getCurrentServiceID());
        room.add(Integer.toString(myRoom.getRoomPrice()));
        return room;
    }

    @Override
    public String toString() {
        String data = "";
        data += "ID: " + this.getID() + "\n";
        data += "Type: " + this.getType() + "\n";
        data += "Status: " + this.getStatus() + "\n";
        data += "Price: " + this.getRoomPrice() + "\n";
        return data;
    }

    public ArrayList<Room> filter(String key, String value) throws Exception {
        ArrayList<Room> rooms = this.getAll();
        ArrayList<Room> result = new ArrayList<>();
        switch (key) {
            case "notBusy" -> {
                for (Room room: rooms)
                    if(room.getStatus().equals("available"))
                        result.add(room);
            }
            case "busy" -> {
                for (Room room: rooms)
                    if(!room.getStatus().equals("available"))
                        result.add(room);
            }
            case "type" -> {
                for (Room room: rooms)
                    if(room.getType().equals(value))
                        result.add(room);
            }
            case "service" -> {
                for (Room room: rooms) {
                    Service myService = new Service();
                    myService = (Service) myService.read(room.getCurrentServiceID());
                    if (myService.getName().equals(value))
                        result.add(room);
                }
            }
        }
        return result;
    }
}
