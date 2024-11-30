package nefu.laboratory.Repository;

import nefu.laboratory.dox.Course;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course,String> {
    @Query("SELECT * from course where teacher_id=:tid and type=:type")
    List<Course> findByTeacherIdAndType(String tid,String type);
}
