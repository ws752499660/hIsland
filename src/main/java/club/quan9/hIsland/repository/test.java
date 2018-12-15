package club.quan9.hIsland.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class test
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    private String SQL_FIND="select name from owner where id=?";

    public String test(int id)
    {
        final List<String> name=new ArrayList<>();
        jdbcTemplate.query(SQL_FIND,new Object[]{id},new RowCallbackHandler()
        {
            @Override
            public void processRow(ResultSet rs) throws SQLException
            {

                name.add(rs.getString("Name"));
            }
        });
        return name.get(0);
    }

}
