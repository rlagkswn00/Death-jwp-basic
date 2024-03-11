package next.controller;

import core.db.DataBase;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UpdateUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SQLException {
        String userId = httpServletRequest.getParameter("userId");
        String password = httpServletRequest.getParameter("password");
        String email = httpServletRequest.getParameter("email");
        String name = httpServletRequest.getParameter("name");
        UserDao userDao = new UserDao();
        userDao.update(new User(userId, password, email, name));
        return "redirect:/user/list";
    }
}
