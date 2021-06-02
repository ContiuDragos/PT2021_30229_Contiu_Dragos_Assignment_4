package Model;

import java.io.Serializable;

public class Worker extends User implements Serializable {
    public Worker(int id,String username, String password) {
        super(id,username, password);
    }
}
