package admin.core.demo.mapper;

import admin.core.demo.model.Knowledge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface KnowledgeMapper {

    int add(Knowledge knowledge);

    int update(Knowledge knowledge);

    int delete(@Param("id") int id);

    List<Knowledge> getAll();


}
