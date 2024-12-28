package nefu.laboratory.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.dox.Course;
import nefu.laboratory.dox.Laboratory;
import nefu.laboratory.dox.Reservation;
import nefu.laboratory.Repository.CourseRepository;
import nefu.laboratory.Repository.LaboratoryRepository;
import nefu.laboratory.Repository.ReservationRepository;
import nefu.laboratory.dto.CountDTO;
import nefu.laboratory.dto.FreeDTO;
import nefu.laboratory.dto.WeeksDTO;
import nefu.laboratory.dto.exception.XException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final ReservationRepository reservationRepository;
    private final CourseRepository courseRepository;
    private final LaboratoryRepository laboratoryRepository;
    //老师id
    public List<Reservation> reservationTid(String tid) {

        return reservationRepository.findByTeacherId(tid);
    }

    //查老师的课程
    public List<Course> courseLab(String tid) {
        return courseRepository.findByTeacherIdAndType(tid, Course.LAB);
    }
    // 添加课程
    public void addCourse(Course course){
        try {
            courseRepository.save(course);
        }catch (Exception e){
            throw XException.DATA_ERROR;
        }
    }
    @Transactional
    public void updateCourse(Course course,String tid,String name){
        course.setTeacherId(tid);
        course.setTeacherName(name);
        course.setType(Course.LAB);

        Optional<Course> byId = courseRepository.findById(course.getId());
        if(byId.isEmpty()){
            throw XException.builder()
                    .codeN(400)
                    .message("这个课程不存在")
                    .build();
        }else if(byId.get().getTeacherId().equals(tid)){
           courseRepository.save(course);
        }
        else {
            throw XException.builder()
                    .codeN(400)
                    .message("这不是你的课程,别捣乱")
                    .build();
        }
    }

    //查所有实验室

    public List<Laboratory> labList(){
        return laboratoryRepository.list();
    }

    public List<CountDTO> labAndCount(){
        List<CountDTO> countDTOList =new LinkedList<>();

        List<Laboratory> labList = laboratoryRepository.list();
        labList.forEach(laboratory -> {
            CountDTO countDTO =new CountDTO();
            BeanUtils.copyProperties(laboratory,countDTO);
            countDTO.setCount(reservationRepository.countByLaboratoryId(laboratory.getId()));
            countDTOList.add(countDTO);
        });
        return countDTOList;
    }


    //查询当前实验室相关的预约

    public List<Reservation> reservationLab(String lid) {

        return reservationRepository.findByLaboratoryId(lid);
    }
    //根据某一天查询空闲的教室
    public List<FreeDTO> findFree(int week,int day){
        return laboratoryRepository.findFree(week,day);
    }
    //添加新预约
    @Transactional
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
            try{
                reservationRepository.save(reservation);
            }catch (Exception e){
                throw XException.DATA_ERROR;
            }

        }
    }
    //删除预约
//    @CacheEvict("reservation_t")
    public void delReservation(String id,String tid){
        Optional<Reservation> r = reservationRepository.findById(id);
        if(r.isEmpty()) {
            throw XException.builder()
                    .codeN(400)
                    .message("预约记录不存在")
                    .build();
        }
        if(r.get().getTeacherId().equals(tid)){
        reservationRepository.deleteByIdAndTeacherId(id,tid);
        }
        else {
            throw XException.builder()
                    .codeN(403)
                    .message("这不是你的,别捣乱哦")
                    .build();
        }
    }

    @Transactional
    public void delReservations(List<Reservation> reservationList,String tid){
        reservationList.forEach(reservation-> {
            delReservation(reservation.getId(),tid);
        });
    }

    @Transactional
    public void delCourse(String id,String tid){
            Optional<Course> c = courseRepository.findById(id);
            if(c.isEmpty()) {
                throw XException.builder()
                        .codeN(404)
                        .message("课程不存在")
                        .build();
            }
            if(c.get().getTeacherId().equals(tid)){
                if (reservationRepository.existByTeacherIdAndCourseId(tid,id)!=null) {
                    throw XException.builder()
                            .codeN(432)
                            .message("该课程存在预约记录,禁止删除")
                            .build();
                }
                courseRepository.deleteByIdAndTeacherId(id, tid);
            }
            else {
                throw XException.PERMISSION_ERROR;
            }
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