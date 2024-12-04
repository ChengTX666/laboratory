package nefu.laboratory.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.dox.Course;
import nefu.laboratory.dox.Laboratory;
import nefu.laboratory.dox.Reservation;
import nefu.laboratory.Repository.CourseRepository;
import nefu.laboratory.Repository.LaboratoryRepository;
import nefu.laboratory.Repository.ReservationRepository;
import nefu.laboratory.exception.XException;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final ReservationRepository reservationRepository;
    private final CourseRepository courseRepository;
    private final LaboratoryRepository laboratoryRepository;
    private final RedissonClient redissonClient;

    //老师id
    public List<Reservation> reservationList(String tid) {
        return reservationRepository.findByTeacherId(tid);
    }

    //理论课
    public List<Course> courseTheory(String tid) {
        return courseRepository.findByTeacherIdAndType(tid, "abc1");
    }

    //实验课
    public List<Course> courseLab(String tid) {
        return courseRepository.findByTeacherIdAndType(tid, "def2");
    }

    //所有实验室
    public List<Laboratory> labList() {
        return laboratoryRepository.list();
    }

    //查询当前实验室相关的预约
    public List<Reservation> reservationLab(String lid) {
        return reservationRepository.findByLaboratoryId(lid);
    }
    //预约！！！！
//    public Reservation addReservation(Reservation newReservation){
//        int period=newReservation.getPeriod();
//        int day=newReservation.getDay();
//        Set<String> request = Arrays.stream(newReservation.getWeeks().split(",")).collect(Collectors.toSet());
//        //判断有无冲突
//        reservationRepository.findByPeriodAndDay(period, day).forEach(
//                weeks->{
//                    Set<String> set = Arrays.stream(weeks.split(",")).collect(Collectors.toSet());
//                    if(!Collections.disjoint(set,request)){
//                        throw XException.builder()
//                                .codeN(404)
//                                .message("该时间段已有预约!")
//                                .build();
//                    }
//                }
//        );
//        return reservationRepository.save(newReservation);
//    }

}