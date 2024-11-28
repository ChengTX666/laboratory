package nefu.laboratory.Repository;

import nefu.laboratory.Dox.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {

    @Query("SELECT * from `user` where account=:account")
    User findByAccount(String account);
}
