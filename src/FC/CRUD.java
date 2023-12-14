package FC;

import java.util.ArrayList;

public interface CRUD {
    boolean create() throws Exception;

    Object read(String id) throws Exception;

    boolean update(Object obj) throws Exception;

    boolean delete(Object obj) throws Exception;

    ArrayList<?> getAll() throws Exception;

    ArrayList<String> toArrayList(Object obj) throws Exception;
    String toString();
}
