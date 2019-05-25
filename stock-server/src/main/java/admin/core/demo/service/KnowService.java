package admin.core.demo.service;

import admin.core.demo.mapper.KnowledgeMapper;
import admin.core.demo.model.Knowledge;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowService {

    @Autowired
    private KnowledgeMapper knowledgeMapper;

    public boolean updateOrAdd(Knowledge knowledge) {
        if (StringUtils.isEmpty(knowledge.getName())
                || StringUtils.isEmpty(knowledge.getDescription())) {
            return false;
        }
        if (knowledge.getId() > 0) {
            return knowledgeMapper.update(knowledge) > 0;
        }
        return knowledgeMapper.add(knowledge) > 0;
    }

    public List<Knowledge> getAll() {
        return knowledgeMapper.getAll();
    }

    public boolean delete(int id) {
        return knowledgeMapper.delete(id) > 0;
    }
}
