package nefu.laboratory.Dox;

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
    @CreatedBy
    @Id
    private String id;
    private String name;
    private String type;
    private String requireConfig;
    private String teacherId;
    private String teacherName;
    private String theory;//json数据
    private int total;
    private int reserved;
    @JsonIgnore
    @ReadOnlyProperty
    private LocalDateTime createTime;
    @JsonIgnore
    @ReadOnlyProperty
    private LocalDateTime updateTime;
}
