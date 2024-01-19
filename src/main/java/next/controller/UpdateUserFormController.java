package next.controller;

import core.db.DataBase;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String userId = httpServletRequest.getParameter("userId");
        String password = httpServletRequest.getParameter("password");
        String email = httpServletRequest.getParameter("email");
        String name = httpServletRequest.getParameter("name");
        DataBase.addUser(new User(userId, password, email, name));
        return "redirect:/user/list";
    }
}
