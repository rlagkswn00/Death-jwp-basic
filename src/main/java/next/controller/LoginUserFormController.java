package next.controller;

import core.db.DataBase;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class LoginUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SQLException {
        String userId = httpServletRequest.getParameter("userId");
        String password = httpServletRequest.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.findByUserId(userId);

        if (user == null || !user.getPassword().equals(password)) {
            return "/user/login_failed.jsp";
        }
        httpServletRequest.getSession().setAttribute("user", user);
        return "redirect:/user/list";
    }
}
