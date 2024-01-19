package next.controller;

import core.db.DataBase;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String userId = httpServletRequest.getParameter("userId");
        String password = httpServletRequest.getParameter("password");
        User user = DataBase.findUserById(userId);
        if (user == null || !user.getPassword().equals(password)) {
            return "/user/login_failed.jsp";
        }
        httpServletRequest.getSession().setAttribute("user", user);
        return "redirect:/user/list";
    }
}
