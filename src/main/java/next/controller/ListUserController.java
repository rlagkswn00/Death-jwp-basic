package next.controller;

import core.db.DataBase;
import next.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if(!UserSessionUtils.isLogined(httpServletRequest.getSession())){
            return "redirect:/users/loginForm";
        }
        httpServletRequest.setAttribute("users", DataBase.findAll());
        return "/user/list.jsp";
    }
}
