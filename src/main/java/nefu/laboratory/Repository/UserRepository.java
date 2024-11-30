package nefu.laboratory.Repository;

import nefu.laboratory.dox.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {

    @Query("SELECT * from `user` where account=:account")
    User findByAccount(String account);


    @Modifying
    @Query("update user set password=:pwd where account=:account")
    User updateByAccount(String account,String pwd);
}
