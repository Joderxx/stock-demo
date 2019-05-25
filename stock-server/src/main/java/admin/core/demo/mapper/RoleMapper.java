package admin.core.demo.mapper;

import admin.core.demo.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RoleMapper {

    /**
     * 根据id数组获取权限名称
     *
     * @param ids
     * @return
     */
    List<String> getRolesName(@Param("ids") String ids);

    List<Role> getRoles();
}
