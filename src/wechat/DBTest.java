package wechat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class DBTest extends HttpServlet {
    public DBTest() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");

        try {
            DBConnection db = new DBConnection();
            ResultSet rs = db.executeQuery("select * from qadata");
            int id = 0;
            String question = "";
            String optA = "";
            String optB = "";
            String optC = "";
            String optD = "";
            String answer = "";
            ArrayList list = new ArrayList();

            while(rs.next()) {
                id = rs.getInt(1);
                question = rs.getString(2);
                optA = rs.getString(3);
                optB = rs.getString(4);
                optC = rs.getString(5);
                optD = rs.getString(6);
                answer = rs.getString(7);
                Question q = new Question(id, question, optA, optB, optC, optD, answer);
                list.add(q);
            }

            db.close();
            Random r = new Random();
            int j = 0;
            int[] ques = new int[]{-1, -1, -1, -1};

            while(true) {
                int flag = 0;
                int randnumber = r.nextInt(10);
                System.out.println(randnumber);
                if (j > 0) {
                    for(int i = 0; i < ques.length; ++i) {
                        if (randnumber == ques[i]) {
                            flag = 1;
                            break;
                        }
                    }
                }

                if (flag == 0) {
                    ques[j] = randnumber;
                    ++j;
                    if (j == 4) {
                        response.setCharacterEncoding("utf-8");
                        PrintWriter out = response.getWriter();
                        JSONObject obj = new JSONObject();
                        Question q1 = (Question)list.get(ques[0]);
                        JSONObject obj1 = new JSONObject();
                        obj1.put("question", q1.question);
                        obj1.put("optA", q1.optA);
                        obj1.put("optB", q1.optB);
                        obj1.put("optC", q1.optC);
                        obj1.put("optD", q1.optD);
                        obj1.put("answer", q1.answer);
                        obj.put("Qa1", obj1);
                        JSONObject obj2 = new JSONObject();
                        Question q2 = (Question)list.get(ques[1]);
                        obj2.put("question", q2.question);
                        obj2.put("optA", q2.optA);
                        obj2.put("optB", q2.optB);
                        obj2.put("optC", q2.optC);
                        obj2.put("optD", q2.optD);
                        obj2.put("answer", q2.answer);
                        obj.put("Qa2", obj2);
                        JSONObject obj3 = new JSONObject();
                        Question q3 = (Question)list.get(ques[2]);
                        obj3.put("question", q3.question);
                        obj3.put("optA", q3.optA);
                        obj3.put("optB", q3.optB);
                        obj3.put("optC", q3.optC);
                        obj3.put("optD", q3.optD);
                        obj3.put("answer", q3.answer);
                        obj.put("Qa3", obj3);
                        JSONObject obj4 = new JSONObject();
                        Question q4 = (Question)list.get(ques[3]);
                        obj4.put("question", q4.question);
                        obj4.put("optA", q4.optA);
                        obj4.put("optB", q4.optB);
                        obj4.put("optC", q4.optC);
                        obj4.put("optD", q4.optD);
                        obj4.put("answer", q4.answer);
                        obj.put("Qa4", obj4);
                        out.print(obj.toString());
                        out.flush();
                        out.close();
                        break;
                    }
                }
            }
        } catch (Exception var27) {
            var27.printStackTrace();
        }

    }

    public static void main(String[] args) {
    }
}