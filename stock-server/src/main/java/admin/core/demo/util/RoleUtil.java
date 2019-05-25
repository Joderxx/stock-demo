package admin.core.demo.util;

import admin.core.demo.model.Role;

public class RoleUtil {

    public static final Role ADMIN = new Role(1, "admin", null);
    public static final Role NORMAL = new Role(2, "normal", null);
    public static final Role VISITOR = new Role(3, "visitor", null);

}
