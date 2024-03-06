package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO USERS(userId, password, name, email) VALUES (?, ?, ?, ?)";

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };

        jdbcTemplate.update(sql, pss);
    }

    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }
        };


        jdbcTemplate.update(sql, pss);
    }

    public List<User> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM USERS";

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {

            }
        };

        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                            rs.getString("email"));
                }
                return null;
            }
        };

        return (List<User>) jdbcTemplate.query(sql, pss, rowMapper);
    }

    public User findByUserId(String userId) throws SQLException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };

        RowMapper<User> rowMapper = new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                            rs.getString("email"));
                }
                return null;
            }
        };

        return jdbcTemplate.queryForObject(sql, pss, rowMapper);
    }
}
