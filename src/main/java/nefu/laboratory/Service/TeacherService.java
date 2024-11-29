package nefu.laboratory.Service;

import lombok.RequiredArgsConstructor;
import nefu.laboratory.Dox.Course;
import nefu.laboratory.Dox.Laboratory;
import nefu.laboratory.Dox.Reservation;
import nefu.laboratory.Repository.CourseRepository;
import nefu.laboratory.Repository.LaboratoryRepository;
import nefu.laboratory.Repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final ReservationRepository reservationRepository;
    private final CourseRepository courseRepository;
    private final LaboratoryRepository laboratoryRepository;

    //老师id
    public List<Reservation> reservationList(String tid){
        return reservationRepository.findByTeacherId(tid);
    }
    public List<Course> courseTheory(String tid) {
        return courseRepository.findByTeacherIdAndType(tid,"abc1");
    }

    public List<Course> courseLab(String tid){
        return courseRepository.findByTeacherIdAndType(tid,"def2");
    }

    public List<Laboratory> labList() {
        return laboratoryRepository.list();
    }

    //查询当前实验室相关的预约
    public List<Reservation> reservationLab(String lid){
        return reservationRepository.findByLaboratoryId(lid);
    }
}
