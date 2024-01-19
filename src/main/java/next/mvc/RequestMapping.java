package next.mvc;

import next.controller.Controller;
import next.controller.CreateUserController;
import next.controller.FowardController;
import next.controller.HomeController;
import next.controller.ListUserController;
import next.controller.LoginUserFormController;
import next.controller.LogoutUserController;
import next.controller.UpdateUserFormController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private Map<String, Controller> controllers = new HashMap<>();

    public RequestMapping() {
        init();
    }

    void init(){
        controllers.put("/", new HomeController());
        controllers.put("/home.jsp", new HomeController());
        controllers.put("/user/form", new FowardController("/user/form.jsp"));
        controllers.put("/user/login", new FowardController("/user/login.jsp"));
        controllers.put("/user/update",new FowardController("/user/update.jsp"));
        controllers.put("/user/list", new ListUserController());
        controllers.put("/user/create", new CreateUserController());
        controllers.put("/user/loginForm", new LoginUserFormController());
        controllers.put("/user/logout", new LogoutUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
    }

    public Controller getController(String requestUri) {
        return controllers.get(requestUri);
    }
}
