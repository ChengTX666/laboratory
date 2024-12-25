package nefu.laboratory.Repository;

import nefu.laboratory.dox.Notice;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends CrudRepository<Notice,String> {

    @Query("SELECT * from notice")
    List<Notice> findAll();

    @Query("DELETE from notice where id=:id")
    void deleteById(String id);



    //分页查询
    @Query("SELECT * from notice limit :offset,:limit")
    List<Notice> findLimit(int offset,int limit);


}
