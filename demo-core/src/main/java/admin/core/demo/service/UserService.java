package admin.core.demo.service;

import admin.core.demo.dto.UserDTO;
import admin.core.demo.mapper.RoleMapper;
import admin.core.demo.mapper.UserMapper;
import admin.core.demo.model.User;
import admin.core.demo.util.RequestUtil;
import admin.core.demo.util.RoleUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    public UserDTO login(String username, String password) {
        password = RequestUtil.md5(password);
        User user = userMapper.getUser(username, password);
        return dealUser(user);
    }

    public UserDTO info(String username) {
        User user = userMapper.getByName(username);
        return dealUser(user);
    }

    public byte[] userAvatar(String username) {
        User user = userMapper.getAvatar(username);
        return user == null ? null : user.getAvatar();
    }

    public boolean updateAvatar(String username, byte[] data) {
        if (username == null || data == null || data.length == 0) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setAvatar(data);
        return userMapper.updateAvatar(user) > 0;
    }

    public boolean register(String username, String password) {
        boolean duplication = userMapper.exist(username) > 0;
        if (duplication) {
            return false;
        }
        password = RequestUtil.md5(password);
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setRole1(RoleUtil.VISITOR.getId());
        return userMapper.add(user) > 0;
    }

    public boolean update(User user) {
        if (user == null) {
            return false;
        }
        return userMapper.update(user) > 0;
    }

    public List<User> users() {
        return userMapper.getUsers();
    }

    private UserDTO dealUser(User user) {
        if (user == null) {
            return null;
        }
        List<Integer> rn = new ArrayList<>(3);
        if (user.getRole1() != 0) {
            rn.add(user.getRole1());
        }
        if (user.getRole2() != 0) {
            rn.add(user.getRole2());
        }
        if (user.getRole3() != 0) {
            rn.add(user.getRole3());
        }
        List<String> rolesName = roleMapper
                .getRolesName("'" + StringUtils.join(rn, "','") + "'");
        String token = RequestUtil.createToken(user.getUsername(), rolesName);
        long time = System.currentTimeMillis();
        return new UserDTO(
                user.getUsername(),
                rolesName.toArray(new String[rolesName.size()]),
                token,
                time
        );
    }
}
