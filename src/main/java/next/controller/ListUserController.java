package next.controller;

import core.db.DataBase;
import next.dao.UserDao;
import next.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SQLException {
        if(!UserSessionUtils.isLogined(httpServletRequest.getSession())){
            return "redirect:/users/loginForm";
        }

        UserDao userDao = new UserDao();
        httpServletRequest.setAttribute("users", userDao.findAll());
        return "/user/list.jsp";
    }
}
