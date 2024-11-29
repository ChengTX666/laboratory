package nefu.laboratory.Repository;

import nefu.laboratory.Dox.Laboratory;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratoryRepository extends CrudRepository<Laboratory,String> {

    @Query("select * from laboratory ")
    List<Laboratory>list();

}
