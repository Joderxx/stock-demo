package admin.core.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SubscribeMapper {

    List<String> getSubscribe(@Param("username") String username);

    int delete(@Param("username") String username, @Param("tsCode") String tsCode);

    int add(@Param("username") String username, @Param("tsCode") String tsCode);
}
