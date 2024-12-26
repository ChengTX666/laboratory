package nefu.laboratory.Repository;

import nefu.laboratory.dox.Notice;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends CrudRepository<Notice,String> {

    @Query("SELECT * from notice order by id desc ")
    List<Notice> findAll();
    //分页查询
    @Query("SELECT * from notice order by id desc limit :offset,:limit ")
    List<Notice> findLimit(int offset,int limit);

    @Modifying
    @Query("DELETE from notice where id=:id")
    void deleteById(String id);




}