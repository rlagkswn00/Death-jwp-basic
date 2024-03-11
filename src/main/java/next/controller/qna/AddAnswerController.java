package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import next.controller.Controller;
import next.dao.AnswerDao;
import next.model.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class AddAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Answer answer = new Answer(req.getParameter("writer"),
                req.getParameter("contents"),
                Long.parseLong(req.getParameter("questionId")));

        AnswerDao answerDao = new AnswerDao();
        Answer savedAnswer = answerDao.insert(answer);

        System.out.println(savedAnswer);
        ObjectMapper mapper = new ObjectMapper();
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.print(mapper.writeValueAsString(savedAnswer));

        return null;
    }
}
