package nefu.laboratory.Repository;

import nefu.laboratory.dox.Notice;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends CrudRepository<Notice,String> {

    @Query("SELECT * from notice")
    List<Notice> findAll();
    //分页查询
    @Query("SELECT * from notice limit 5,")
    List<Notice> findLimit


}
