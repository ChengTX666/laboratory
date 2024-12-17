package nefu.laboratory.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.dox.Course;
import nefu.laboratory.dox.Laboratory;
import nefu.laboratory.dox.Reservation;
import nefu.laboratory.Repository.CourseRepository;
import nefu.laboratory.Repository.LaboratoryRepository;
import nefu.laboratory.Repository.ReservationRepository;
import nefu.laboratory.dto.WeeksDTO;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final ReservationRepository reservationRepository;
    private final CourseRepository courseRepository;
    private final LaboratoryRepository laboratoryRepository;
    private final RedissonClient redissonClient;

    //老师id
    public List<Reservation> reservationTid(String tid) {
        return reservationRepository.findByTeacherId(tid);
    }

    //实验课
    public List<Course> courseLab(String tid) {
        return courseRepository.findByTeacherIdAndType(tid, "def2");
    }

    //所有实验室
    public List<Laboratory> labList() {
        return laboratoryRepository.list();
    }
    //所有实验室对应的预约个数
    public Map<String,Integer> countMap(){
        return reservationRepository.countByLaboratoryId();
    }

    //查询当前实验室相关的预约
    public List<Reservation> reservationLab(String lid) {

        return reservationRepository.findByLaboratoryId(lid);
    }
    //根据某一天查询空闲的教室

    //添加新预约
    public void addReservations(WeeksDTO weeksDTO){
        for (int week:weeksDTO.getWeeks()){
            Reservation reservation = Reservation.builder()
                    .teacherId(weeksDTO.getTeacherId())
                    .teacherName(weeksDTO.getTeacherName())
                    .courseId(weeksDTO.getCourseId())
                    .courseName(weeksDTO.getCourseName())
                    .laboratoryId(weeksDTO.getLaboratoryId())
                    .laboratoryName(weeksDTO.getLaboratoryName())
                    .period(weeksDTO.getPeriod())
                    .day(weeksDTO.getDay())
                    .week(week)
                    .build();
            reservationRepository.save(reservation);
        }
    }
    //删除预约
    public void delReservation(String id,String tid){
        reservationRepository.deleteByIdAndTeacherId(id,tid);
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