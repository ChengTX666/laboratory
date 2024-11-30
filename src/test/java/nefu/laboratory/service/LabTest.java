package nefu.laboratory.service;

import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.dox.Laboratory;
import nefu.laboratory.Service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class LabTest {

    @Autowired
    private AdminService adminService;


    @Test
    void test01(){
        List<Laboratory> laboratories = adminService.listLab();
        log.info("{}",laboratories);

    }
}
