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
        FreeDTO.builder()
                .id(rs.getString("l.id"))
                .name(rs.getString("l.name"))
                .period(rs.getObject("free_period"),new TypE<List<Integer>(){})
                .build()
    }
}
