package nefu.laboratory.Repository;

import nefu.laboratory.dox.Laboratory;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratoryRepository extends CrudRepository<Laboratory,String> {

    @Query("select * from laboratory ")
    List<Laboratory>list();

    @Modifying
    @Query("update laboratory l set l.status=:status where l.id=:lid")
    void updateStatusById(String lid,String status);

}
