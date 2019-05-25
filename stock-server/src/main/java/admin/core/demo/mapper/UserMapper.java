package admin.core.demo.mapper;

import admin.core.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    /**
     * 登录使用
     *
     * @param username
     * @param password
     * @return
     */
    User getUser(
            @Param("username") String username,
            @Param("password") String password
    );

    User getByName(@Param("username") String username);

    /**
     * 获取头像信息
     *
     * @param username
     * @return
     */
    User getAvatar(String username);

    int updateAvatar(User user);

    int update(User user);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 是否存在用户名
     *
     * @param username
     * @return
     */
    int exist(String username);

    List<User> getUsers();

}
