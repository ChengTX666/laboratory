package nefu.laboratory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreeDTO {
    private String id;
    private String name;
    private int week;
    private int day;
    private List<Integer> period;
}
