package nefu.laboratory.Service;

import lombok.RequiredArgsConstructor;
import nefu.laboratory.Repository.LaboratoryRepository;
import nefu.laboratory.Repository.ReservationRepository;
import nefu.laboratory.dox.Laboratory;
import nefu.laboratory.dox.Reservation;
import nefu.laboratory.dox.User;
import nefu.laboratory.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final LaboratoryRepository laboratoryRepository;
    private final ReservationRepository reservationRepository;





    public User getUser(String account){
        return userRepository.findByAccount(account);
    }
    public User update(User user){
        return userRepository.save(user);
    }

    //根据id改密码
    public User reset(String id,String newPassword){
        return userRepository.updateById(id, newPassword);
    }
}
