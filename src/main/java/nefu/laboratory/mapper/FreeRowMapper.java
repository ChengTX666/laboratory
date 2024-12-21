package nefu.laboratory.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import nefu.laboratory.dto.FreeDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FreeRowMapper implements RowMapper<FreeDTO> {
    @Override
    public FreeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        return FreeDTO.builder()
                .laboratoryId(rs.getString("id"))
                .laboratoryName(rs.getString("name"))
                .freePeriods(rs.getString("free_periods"))
                .build();
    }
}
