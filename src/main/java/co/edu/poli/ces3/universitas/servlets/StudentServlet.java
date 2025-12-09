package co.edu.poli.ces3.universitas.servlets;

import co.edu.poli.ces3.universitas.dto.Student;
import co.edu.poli.ces3.universitas.services.StudentService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

@WebServlet(name = "studentServlet", value = "/student")
public class StudentServlet extends HttpServlet {

    private StudentService service;

    @Override
    public void init() throws ServletException {
        service = new StudentService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");

        if (id != null) {
            Student s = service.findById(id);
            if (s == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"error\":\"Student not found\"}");
            } else {
                out.print(gson.toJson(s));
            }
        } else {
            Vector<Student> students = service.find();
            out.print(gson.toJson(students));
        }

        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();

        JsonObject json = getParamsFromBody(req);
        Student saved = service.add(json);

        resp.setStatus(HttpServletResponse.SC_CREATED);
        out.print(gson.toJson(saved));
        out.flush();
    }

    //Sobreescribir metodo service para habilitar patch
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();

        JsonObject json = getParamsFromBody(req);
        Student updated = service.update(json);

        if (updated == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"error\":\"Student not found\"}");
        } else {
            out.print(gson.toJson(updated));
        }

        out.flush();
    }


    protected void doPatch(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();

        JsonObject json = getParamsFromBody(req);
        Student patched = service.patch(json);

        if (patched == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"error\":\"Student not found\"}");
        } else {
            out.print(gson.toJson(patched));
        }

        out.flush();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if ("PATCH".equalsIgnoreCase(req.getMethod())) {
            doPatch(req, resp);
        } else {
            super.service(req, resp);
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");

        if (id == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\": \"Missing id parameter\"}");
            return;
        }

        Student deleted = service.delete(id);

        if (deleted == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"error\":\"Student not found\"}");
        } else {
            out.print(gson.toJson(deleted));
        }

        out.flush();
    }

    protected JsonObject getParamsFromBody(HttpServletRequest request)
            throws IOException {

        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();

        while (line != null) {
            sb.append(line);
            line = reader.readLine();
        }

        return JsonParser.parseString(sb.toString()).getAsJsonObject();
    }
}
