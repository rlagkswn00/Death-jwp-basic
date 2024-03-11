package next.controller;

import core.db.DataBase;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CreateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SQLException {
        User user = new User(httpServletRequest.getParameter("userId"),
                httpServletRequest.getParameter("password"),
                httpServletRequest.getParameter("name"),
                httpServletRequest.getParameter("email"));

        UserDao userDao = new UserDao();
        userDao.insert(user);

        return "redirect:/user/login.jsp";
    }
}
