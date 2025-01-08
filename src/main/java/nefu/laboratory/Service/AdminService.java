package nefu.laboratory.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Repository.NoticeRepository;
import nefu.laboratory.Repository.ReservationRepository;
import nefu.laboratory.dox.Laboratory;
import nefu.laboratory.dox.Notice;
import nefu.laboratory.dox.User;
import nefu.laboratory.Repository.LaboratoryRepository;
import nefu.laboratory.Repository.UserRepository;
import nefu.laboratory.dto.Code;
import nefu.laboratory.dto.exception.XException;
import org.springframework.data.domain.AuditorAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final NoticeRepository noticeRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuditorAware<String> auditorAware;
    private final JdbcTemplate jdbcTemplate;


    //########################       用户管理
    //查询所有老师
    public List<User> teacherList(){
       return userRepository.findByRole(User.ROLE_TEACHER);
    }
    public User findByName(String name){
        return userRepository.findByName(name);
    }
    public User findById(String id){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw XException.DATA_ERROR;
    }
    public User findByAccount(String account){
        return userRepository.findByAccount(account);
    }


    public List<User> userList(){
        return userRepository.list();
    }
    //添加老师
    public void addTeacher(User user){
        user.setRole(User.ROLE_TEACHER);
        user.setPassword(passwordEncoder.encode(user.getAccount()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw XException.DATA_ERROR;
        }
    }
    public void updateUser(User user){
        if(user.getId()==null){
            throw XException.DATA_ERROR;
        }
        userRepository.save(user);
    }
    public void updateTeacher(User user){
        user.setRole(User.ROLE_TEACHER);
        updateUser(user);
    }
    @Transactional
    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getAccount()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw XException.DATA_ERROR;
        }
    }

    @Transactional
    //删除老师
    public void delTeacher(String id){
        if(reservationRepository.existByTeacherId(id)!=null){
            throw XException.builder()
                    .codeN(432)
                    .message("该老师存在预约记录,禁止删除")
                    .build();
        }
        userRepository.deleteById(id);
    }

    @Transactional
    //批量导入老师
    public void batchInsertUsers(List<User> userList) {

        String sql = "INSERT INTO lab_user (id,name,account,password,role,phone) VALUES (?,?,?,?,?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (User user : userList) {
            //调用雪花算法审计操作
            Optional<String> snowflakeIdOptional = auditorAware.getCurrentAuditor();
            String pwd =passwordEncoder.encode(user.getAccount());
            batchArgs.add(new Object[]{snowflakeIdOptional.get(),user.getName(),user.getAccount(),pwd,User.ROLE_TEACHER, user.getPhone()});
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    //


    //######################实验室
    //查询所有实验室
    public List<Laboratory> listLab(){
        return laboratoryRepository.list();
    }
    //增加实验室
    @Transactional
    public void addLab(Laboratory laboratory){
        laboratory.setId(null);
        try {
            laboratoryRepository.save(laboratory);
        }catch (Exception e){
            throw XException.DATA_ERROR;
        }

    }
    //修改实验室
    @Transactional
    public void updateLab(Laboratory laboratory) {
        if (laboratory.getId() == null) {
            throw XException.DATA_ERROR;
        }
        String name = laboratoryRepository.findName(laboratory.getId());

        laboratoryRepository.save(laboratory);

        //原名字和改后的名字不相同
        if (!name.equals(laboratory.getName()))
        {
            //修改预约表
            reservationRepository.updateNameByLid(laboratory.getId(),laboratory.getName());
        }



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
    public void reset(String account){
       userRepository.updateByAccount(account,passwordEncoder.encode(account));
    }
    //初始化所有密码
    public void preAll() {
        userRepository.list().forEach(
                user -> this.reset(user.getAccount())
        );
    }



}
