package next.controller.qna;

import next.controller.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ShowController implements Controller {
    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SQLException, Exception {
        System.out.println(this.getClass() + " RUN");
        Long questionId = Long.parseLong(httpServletRequest.getParameter("id"));
        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();
        Question byId = questionDao.findById(questionId);
        httpServletRequest.setAttribute("question", byId);
        List<Answer> allByQuestionId = answerDao.findAllByQuestionId(questionId);
        httpServletRequest.setAttribute("answers", allByQuestionId);
        return "/qna/show.jsp";
    }
}
