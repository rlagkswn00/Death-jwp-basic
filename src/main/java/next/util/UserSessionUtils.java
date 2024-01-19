package next.util;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static boolean isLogined(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return false;
        }
        return true;
    }
}
