package next.controller;

import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomeController implements Controller{
    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questions = questionDao.findAll();

        httpServletRequest.setAttribute("questions", questions);
        return "/home.jsp";
    }
}
