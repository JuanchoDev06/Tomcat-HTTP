package co.edu.poli.ces3.universitas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name= "subjectServlet", value = "/subject")
public class SubjectServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter out = resp.getWriter();

        out.println("{");
        out.println("\"message\":\"Hola Subject\"");
        out.println("}");
    }
}
