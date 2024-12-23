package nefu.laboratory.Repository;

import nefu.laboratory.dox.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,String> {

    @Query("SELECT * from `lab_user` where account=:account")
    User findByAccount(String account);

    @Query("SELECT * from lab_user")
    List<User> list();

    //所有老师
    @Query("SELECT * from lab_user where role=:role")
    List<User> findByRole(String role);



    @Modifying
    @Query("update lab_user set password=:pwd where account=:account")
    void updateByAccount(String account,String pwd);

    @Modifying
    @Query("update lab_user set password=:pwd where id=:id")
    User updateById(String id,String pwd);

    @Modifying
    @Query("delete from lab_user where id=:id")
    void deleteById(String id);
}
