package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FowardController implements Controller{
    private String viewPath;

    public FowardController(String viewPath) {
        this.viewPath = viewPath;
    }

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return viewPath;
    }
}
