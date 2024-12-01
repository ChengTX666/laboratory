package nefu.laboratory.service;

import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Service.TeacherService;
import nefu.laboratory.dox.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReservationTest {

    @Autowired
    private TeacherService teacherService;
    @Test
    void test1(){
        Reservation newReservation =Reservation.builder()
                .courseId("1")
                .teacherId("1")
                .laboratoryId("1")
                .laboratoryName("901")
                .courseName("JAVA")
                .period(1)
                .day(1)
                .weeks("1,2,9")
                .build();

        teacherService.addReservation(newReservation);

    }
}
