package nefu.laboratory.Service;


import lombok.RequiredArgsConstructor;
import nefu.laboratory.dox.Laboratory;
import nefu.laboratory.dox.User;
import nefu.laboratory.Repository.LaboratoryRepository;
import nefu.laboratory.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
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

    //初始化密码
    public User reset(String account){
        return userRepository.updateByAccount(account,passwordEncoder.encode(account));
    }
}
