package admin.core.demo.model;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String username;
    private String password;
    private byte[] avatar;
    private int role1;
    private int role2;
    private int role3;
}
