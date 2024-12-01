package nefu.laboratory.Service;


import lombok.RequiredArgsConstructor;
import nefu.laboratory.Repository.ReservationRepository;
import nefu.laboratory.dox.Laboratory;
import nefu.laboratory.dox.Reservation;
import nefu.laboratory.dox.User;
import nefu.laboratory.Repository.LaboratoryRepository;
import nefu.laboratory.Repository.UserRepository;
import nefu.laboratory.dto.Code;
import nefu.laboratory.exception.XException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final LaboratoryRepository laboratoryRepository;
    private final PasswordEncoder passwordEncoder;


    //查询所有实验室
    public List<Laboratory> listLab(){
        return laboratoryRepository.list();
    }
    //增加实验室
    public Laboratory addLab(Laboratory laboratory){
        return laboratoryRepository.save(laboratory);
    }
    //修改实验室状态
    public void updateLabStatus(String lid,String status){
         laboratoryRepository.updateStatusById(lid,status);
    }
    //删除实验室(没有被预约的)
    public void deleteLab(String lid){
        int count = reservationRepository.countByLaboratoryId(lid);
        if(count>0){
            throw XException.builder()
                    .codeN(Code.ERROR)
                    .message("该实验室已有预约,禁止删除!")
                    .build();
        }
        laboratoryRepository.deleteById(lid);
    }


    //初始化密码
    public User reset(String account){
        return userRepository.updateByAccount(account,passwordEncoder.encode(account));
    }
}
