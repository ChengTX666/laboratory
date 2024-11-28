package nefu.laboratory.Service;

import lombok.RequiredArgsConstructor;
import nefu.laboratory.Dox.Reservation;
import nefu.laboratory.Repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final ReservationRepository reservationRepository;

    public List<Reservation> courseList(String tid){
        return reservationRepository.findByTeacherId(tid);
    }
}
