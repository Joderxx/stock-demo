package admin.core.demo.service;

import admin.core.demo.mapper.SubscribeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscribeService {

    @Autowired
    private SubscribeMapper subscribeMapper;

    public List<String> subscribeList(String username) {
        if (StringUtils.isEmpty(username)) {
            return new ArrayList<>();
        }
        return subscribeMapper.getSubscribe(username);
    }

    public boolean add(String username, String tsCode) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(tsCode)) {
            return false;
        }
        return subscribeMapper.add(username, tsCode) > 0;
    }

    public boolean delete(String username, String tsCode) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(tsCode)) {
            return false;
        }
        return subscribeMapper.delete(username, tsCode) > 0;
    }
}
