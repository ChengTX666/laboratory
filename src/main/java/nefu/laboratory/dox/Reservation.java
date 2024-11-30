package nefu.laboratory.dox;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {

    @CreatedBy
    @Id
    private String id;
    private String teacherId;
    private String laboratoryId;
    private String laboratoryName;
    private String courseId;
    private String courseName;
    private int period;
    private int day;
    private String weeks;
    @JsonIgnore
    @ReadOnlyProperty
    private LocalDateTime createTime;
    @JsonIgnore
    @ReadOnlyProperty
    private LocalDateTime updateTime;
}
