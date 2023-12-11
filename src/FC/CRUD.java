package FC;

import java.util.ArrayList;

public interface CRUD {
    public boolean create() throws Exception;
    public Object read(String id) throws Exception;
    public boolean update(Object obj) throws Exception;
    public boolean delete(Object obj) throws Exception;
    public ArrayList<String> toArrayList(Object obj) throws Exception;
}
