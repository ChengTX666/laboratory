package nefu.laboratory.service;

import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Service.TeacherService;
import nefu.laboratory.dox.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class cacheTest {

    @Autowired
    private TeacherService teacherService;
    @Test
    void del(){
        teacherService.delCourse("1322753126743187456","4");
    }


    @Test
    void test01(){
        Object o = teacherService.courseLab("4");
        log.info("{}",o);
    }

    @Test
    void update() {


    }
}
