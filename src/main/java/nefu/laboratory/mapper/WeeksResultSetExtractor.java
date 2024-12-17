package nefu.laboratory.mapper;

import nefu.laboratory.dto.WeeksDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class WeeksResultSetExtractor implements ResultSetExtractor<WeeksDTO> {
    @Override
    public WeeksDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Integer> weeks = new LinkedList<>();
        WeeksDTO.WeeksDTOBuilder builder = WeeksDTO.builder();
        if (rs.next()) {
//            .id(rs.getString("id"))
            builder
                    .courseName(rs.getString("course_name"))
                    .courseId(rs.getString("course_id"))
                    .laboratoryId(rs.getString("laboratory_id"))
                    .laboratoryName(rs.getString("laboratory_name"))
                    .teacherId(rs.getString("teacher_id"))
                    .teacherName(rs.getString("teacher_name"))
                    .day(rs.getInt("day"))
                    .period(rs.getInt("period"));

            weeks.add(rs.getInt("week"));
        }
        while (rs.next()){
            weeks.add(rs.getInt("week"));
        }
        return builder.weeks(weeks).build();
    }
}
