package nefu.laboratory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeeksDTO {
    private String teacherId;
    private String teacherName;
    private String laboratoryId;
    private String laboratoryName;
    private String courseId;
    private String courseName;
    private int period;
    private int day;
    private List<Integer> weeks;
}
