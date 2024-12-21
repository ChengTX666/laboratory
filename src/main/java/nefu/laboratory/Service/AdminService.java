package nefu.laboratory.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Repository.ReservationRepository;
import nefu.laboratory.dox.Laboratory;
import nefu.laboratory.dox.User;
import nefu.laboratory.Repository.LaboratoryRepository;
import nefu.laboratory.Repository.UserRepository;
import nefu.laboratory.dto.Code;
import nefu.laboratory.dto.exception.XException;
import org.springframework.data.domain.AuditorAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final LaboratoryRepository laboratoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuditorAware<String> auditorAware;
    private final JdbcTemplate jdbcTemplate;

    //批量导入老师
    public void batchInsertUsers(List<User> userList) {

        String sql = "INSERT INTO lab_user (id,name,account,password,role,phone) VALUES (?,?,?,?,?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (User user : userList) {
            //调用雪花算法审计操作
            Optional<String> snowflakeIdOptional = auditorAware.getCurrentAuditor();
            batchArgs.add(new Object[]{snowflakeIdOptional.get(),user.getName(),user.getAccount(),user.getPassword(),user.getRole(), user.getPhone()});
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }
    //

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

    //公告管理
    //查询所有公告
}
