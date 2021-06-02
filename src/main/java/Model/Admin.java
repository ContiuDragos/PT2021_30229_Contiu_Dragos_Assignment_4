package Model;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin(int id,String username, String password)
    {
        super(id,username,password);
    }
}
