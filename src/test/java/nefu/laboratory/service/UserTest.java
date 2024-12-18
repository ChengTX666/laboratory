package nefu.laboratory.service;

import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Service.AdminService;
import nefu.laboratory.dox.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
@Slf4j
public class UserTest {
    @Autowired
    private AdminService adminService;

    @Test
    void batchTest(){
       List<User> users = new LinkedList<>();
       users.add(User.builder()
               .name("ctx")
               .account("2021")
               .password("1")
               .role("def2")
               .phone("13290708000")
               .build());
        users.add(User.builder()
                .name("ctx")
                .account("2022")
                .password("2")
                .role("def2")
                .phone("13290708000")
                .build());
        adminService.batchInsertUsers(users);
    }

}
