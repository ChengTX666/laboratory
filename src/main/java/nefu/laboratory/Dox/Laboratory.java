package nefu.laboratory.Dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Laboratory {
    @CreatedBy
    @Id
    private String id;
    private String name;
    private String config;
    private int capacity;
    private String manager;
}