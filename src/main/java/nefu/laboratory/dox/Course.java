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
public class Course {
    public static final String THEORY="abc1";
    public static final String LAB="def2";
    @CreatedBy
    @Id
    private String id;
    private String name;
    private String type;
    private String requireConfig;
    private int requireNumber;
    private String teacherId;
    private String teacherName;
    private int total;
    @JsonIgnore
    @ReadOnlyProperty
    private LocalDateTime createTime;
    @JsonIgnore
    @ReadOnlyProperty
    private LocalDateTime updateTime;
}
