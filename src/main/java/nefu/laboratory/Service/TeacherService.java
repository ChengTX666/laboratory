package nefu.laboratory.Service;

import lombok.RequiredArgsConstructor;
import nefu.laboratory.Dox.Course;
import nefu.laboratory.Dox.Reservation;
import nefu.laboratory.Repository.CourseRepository;
import nefu.laboratory.Repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final ReservationRepository reservationRepository;
    private final CourseRepository courseRepository;

    public List<Reservation> reservationList(String tid){
        return reservationRepository.findByTeacherId(tid);
    }
    public List<Course> courseTheory(String tid) {
        return courseRepository.findByTeacherIdAndType(tid,"abc1");
    }

    public List<Course> courseLab(String tid){
        return courseRepository.findByTeacherIdAndType(tid,"def2");
    }

}
