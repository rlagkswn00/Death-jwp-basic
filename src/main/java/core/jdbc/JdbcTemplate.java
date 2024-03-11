package core.jdbc;

import core.web.filter.ResourceFilter;
import next.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    private static final Logger logger = LoggerFactory.getLogger(JdbcTemplate.class);

    public void update(String sql, PreparedStatementSetter pss) throws DataAccessException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pss.setValues(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void update(String sql, Object... parameters) throws DataAccessException {
        update(sql, createPreparedStatementSetter(parameters));
    }

    public void update(PreparedStatementCreator psc, KeyHolder keyHolder) throws DataAccessException {
        logger.info("JdbcTemplate update Method Run");
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement pstmt = psc.createPreparedStatement(conn);
            int i = pstmt.executeUpdate();
            logger.info(i + " row updated");

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                keyHolder.setId(rs.getLong(1));
            }
            rs.close();
            logger.info("JdbcTemplate update Method End");
            logger.info("keyHolder.getId() : " + keyHolder.getId());

        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }


    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, PreparedStatementSetter pss) {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);) {
            pss.setValues(pstmt);
            rs = pstmt.executeQuery();
            T result = null;
            if (rs.next()) {
                result = rowMapper.mapRow(rs);

            }
            return result;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DataAccessException(e);
                }
            }
        }
    }

    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... parameters) throws DataAccessException {
        return queryForObject(sql, rowMapper, createPreparedStatementSetter(parameters));
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, PreparedStatementSetter pss) {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);) {
            pss.setValues(pstmt);
            rs = pstmt.executeQuery();

            List<T> result = new ArrayList<T>();
            while (rs.next()) {
                result.add(rowMapper.mapRow(rs));
            }

            return result;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DataAccessException(e);
                }
            }
        }
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) throws DataAccessException {
        return query(sql, rowMapper, createPreparedStatementSetter(parameters));
    }

    private PreparedStatementSetter createPreparedStatementSetter(Object... parameters) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                for (int i = 0; i < parameters.length; i++) {
                    pstmt.setObject(i + 1, parameters[i]);
                }
            }
        };
    }
}
