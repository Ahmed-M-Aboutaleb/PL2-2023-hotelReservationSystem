package Room;

import FC.CRUD;

import java.util.ArrayList;

public class RoomStatus implements CRUD {
    @Override
    public boolean create() throws Exception {
        return false;
    }

    @Override
    public Object read(String id) throws Exception {
        return null;
    }

    @Override
    public boolean update(Object obj) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Object obj) throws Exception {
        return false;
    }

    @Override
    public ArrayList<?> getAll() throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> toArrayList(Object obj) throws Exception {
        return null;
    }
}
