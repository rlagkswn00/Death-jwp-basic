package next.mvc;

import next.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//loadOnStartUp이 1이면 톰캣이 구동되자마자 만들어짐.
//0이면 서블릿 사용시, 매핑될때 만들어짐.
//일반적으로 1을 사용
@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private final String REDIRECT_PREFIX = "redirect:";

    private RequestMapping requestMapping;

    @Override
    public void init() throws ServletException {
        requestMapping = new RequestMapping();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        Controller controller = requestMapping.getController(requestURI);
        try {
            String viewName = controller.execute(req, resp);
            if (viewName == null) {
                return;
            }
            move(viewName, req, resp);
        } catch (Throwable e) {
            throw new ServletException(e.getMessage());
        }

    }

    private void move(String viewName, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (viewName.startsWith(REDIRECT_PREFIX)) {
            resp.sendRedirect(viewName.substring(REDIRECT_PREFIX.length()));
            return;
        }

        req.getRequestDispatcher(viewName).forward(req, resp);
    }
}
