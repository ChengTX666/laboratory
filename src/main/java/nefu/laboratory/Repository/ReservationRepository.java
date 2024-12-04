package nefu.laboratory.Repository;

import nefu.laboratory.dox.Reservation;
import nefu.laboratory.dto.WeeksDTO;
import nefu.laboratory.mapper.CountResultSet;
import nefu.laboratory.mapper.WeeksResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,String> {

    //获取老师预约记录
    @Query("SELECT * from reservation where teacher_id=:tid")
    List<Reservation> findByTeacherId(String tid);

    //获取实验室预约记录
    @Query("SELECT * from reservation where laboratory_id=:lid")
    List<Reservation> findByLaboratoryId(String lid);

    //查看一个实验室有几个预约记录
    @Query("SELECT count(*) from reservation where laboratory_id=:lid")
    int countByLaboratoryId(String lid);
    //根据每个实验室分组查询预约记录
    @Query(value = "SELECT laboratory_id,count(*) as count from reservation group by laboratory_id"
            ,resultSetExtractorClass = CountResultSet.class)
    Map<String,Integer> countByLaboratoryId();
    //取所有预约记录
    @Query("SELECT * from reservation")
    List<Reservation> findAll();

    //根据实验室id 周几 第几节 唯一索引查询
    @Query(value = "SELECT * from reservation where laboratory_id=:lid and period=:period and day=:day"
            ,resultSetExtractorClass = WeeksResultSetExtractor.class)
    WeeksDTO findByLidAndPeriodAndDay(String lid, int period, int day);


}
