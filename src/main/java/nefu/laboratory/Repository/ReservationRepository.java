package nefu.laboratory.Repository;

import nefu.laboratory.dox.Reservation;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,String> {

    @Query("SELECT * from reservation where teacher_id=:tid")
    List<Reservation> findByTeacherId(String tid);

    @Query("SELECT * from reservation where laboratory_id=:lid")
    List<Reservation> findByLaboratoryId(String lid);

    //查看一个实验室有几个预约记录
    @Query("SELECT count(*) from reservation where laboratory_id=:lid")
    int countByLaboratoryId(String lid);


}
