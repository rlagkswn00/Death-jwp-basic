package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface Controller {
    String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SQLException;
}
