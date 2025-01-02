package nefu.laboratory.Repository;

import nefu.laboratory.dox.Laboratory;
import nefu.laboratory.dto.FreeDTO;
import nefu.laboratory.mapper.FreeRowMapper;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratoryRepository extends CrudRepository<Laboratory,String> {

    @Query("select * from laboratory ")
    List<Laboratory>list();

    @Query("select name from laboratory where id=:id")
    String findName(String id);


    @Modifying
    @Query("DELETE from laboratory where id=:lid")
    void deleteById(String lid);

    @Query(value = """
select  l.id,l.name,group_concat(t.`period`) as free_periods from laboratory l
cross join temp t
left join reservation r
on l.id = r.laboratory_id and t.period=r.period and week=:week and day=:day
where laboratory_id is null
group by l.id;""",rowMapperClass = FreeRowMapper.class)
    List<FreeDTO> findFree(int week,int day);
}
