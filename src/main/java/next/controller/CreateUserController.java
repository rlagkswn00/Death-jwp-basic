package next.controller;

import core.db.DataBase;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        User user = new User(httpServletRequest.getParameter("userId"),
                httpServletRequest.getParameter("password"),
                httpServletRequest.getParameter("name"),
                httpServletRequest.getParameter("email"));
        DataBase.addUser(user);
        DataBase.findAll().stream()
                .forEach(e ->{
                    System.out.println(e);
                });
        return "redirect:/user/login.jsp";
    }
}
