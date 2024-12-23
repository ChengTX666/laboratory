package nefu.laboratory.dox;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("lab_user")
public class User {
    public static final String ROLE_ADMIN="abc1";
    public static final String ROLE_TEACHER="def2";
    @Id
    @CreatedBy
    private String id;
    private String name;
    private String account;
    @JsonIgnore
    private String password;
    private String role;
    private String phone;
    @JsonIgnore
    @ReadOnlyProperty
    private LocalDateTime createTime;
    @JsonIgnore
    @ReadOnlyProperty
    private LocalDateTime updateTime;
}