package nefu.laboratory.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Service.AdminService;
import nefu.laboratory.dox.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AdminTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void batch() throws JsonProcessingException {
        String json="[ { \"account\": \"2024202402\", \"name\": \"程天鑫\", \"role\": \"def2\", \"phone\": \"11133344455\" }, { \"account\": \"2024202403\", \"name\": \"孙嘉庆\", \"role\": \"def2\", \"phone\": \"22233344455\" }, { \"account\": \"2024202404\", \"name\": \"徐瑞兵\", \"role\": \"def2\", \"phone\": \"99988877766\" }, { \"account\": \"2024202405\", \"name\": \"李昊龙\", \"role\": \"def2\", \"phone\": \"88877755544\" } ]";

        List<User> userList = objectMapper.readValue(json, new TypeReference<>() {});

        adminService.batchInsertUsers(userList);

    }
}
