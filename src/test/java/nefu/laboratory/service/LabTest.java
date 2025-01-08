package nefu.laboratory.service;

import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Repository.LaboratoryRepository;
import nefu.laboratory.dox.Laboratory;
import nefu.laboratory.Service.AdminService;
import nefu.laboratory.dto.FreeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class LabTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private LaboratoryRepository laboratoryRepository;


    @Test
    void test01(){
        List<Laboratory> laboratories = adminService.listLab();
        log.info("{}",laboratories);
    }
    @Test
    void testWeeks(){
        List<FreeDTO> free = laboratoryRepository.findFree(1, 2);
        log.info("{}",free);

    }
}
