package nefu.laboratory.service;

import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Repository.LaboratoryRepository;
import nefu.laboratory.Repository.ReservationRepository;
import nefu.laboratory.Service.TeacherService;
import nefu.laboratory.dox.Reservation;
import nefu.laboratory.dto.FreeDTO;
import nefu.laboratory.dto.WeeksDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class ReservationTest {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    @Test
    void test1(){
//        WeeksDTO weeksDTO =WeeksDTO.builder()
//                .courseId("1")
//                .teacherId("1")
//                .laboratoryId("1")
//                .teacherName("CTX")
//                .laboratoryName("901")
//                .courseName("JAVA")
//                .period(4)
//                .day(7)
//                .weeks(List.of(1,2,3,4))
//                .build();
//        teacherService.addReservations(weeksDTO);
    }
    @Test
    void test00(){
        List<FreeDTO> free = laboratoryRepository.findFree(1, 2);
        log.info("{}",free);
    }
    @Test
    void test02(){
        WeeksDTO dto = reservationRepository.findByLidAndPeriodAndDay("1", 1, 1);
        log.info("{}",dto);
    }
    @Test
    void test03(){
        Map<String, Integer> map = reservationRepository.countByLaboratoryId();
        log.info("{}",map);
    }
}
