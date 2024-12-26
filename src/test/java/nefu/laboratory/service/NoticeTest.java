package nefu.laboratory.service;

import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Repository.NoticeRepository;
import nefu.laboratory.dox.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class NoticeTest {

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    void test01(){
        List<Notice> limit = noticeRepository.findLimit(1, 1);
        log.info("{}",limit);
    }
    @Test
    void testSave(){
        Notice build = Notice.builder()
                .title("表体")
                .content("neirong")
                .publisher("wo")
                .build();
        noticeRepository.save(build);
    }
}
