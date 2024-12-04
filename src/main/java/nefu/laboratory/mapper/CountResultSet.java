package nefu.laboratory.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CountResultSet implements ResultSetExtractor<Map> {
    @Override
    public Map extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map map=new HashMap<>();
        while (rs.next()){
            map.put(rs.getString("laboratory_id"),rs.getInt("count"));
        }
        return map;
    }
}
