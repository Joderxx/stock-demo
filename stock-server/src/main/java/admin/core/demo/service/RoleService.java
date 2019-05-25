package admin.core.demo.service;

import admin.core.demo.mapper.RoleMapper;
import admin.core.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> roles() {
        return roleMapper.getRoles();
    }
}
