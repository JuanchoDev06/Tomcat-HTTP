package co.edu.poli.ces3.universitas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "studentSrv", value = "/student")
public class StudentSrv extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8"); // Para caracteres especiales

        PrintWriter out = response.getWriter();

        String json = "{\n" +
                "  \"informacionPersonal\": {\n" +
                "    \"nombreCompleto\": \"Ricardo Perez Restrepo\",\n" +
                "    \"edad\": 22,\n" +
                "    \"correo\": \"ricardo.perez123@gmail.com\",\n" +
                "    \"telefono\": \"+57 1234567890\",\n" +
                "    \"ciudadResidencia\": \"Medellín\"\n" +
                "  },\n" +
                "  \"academico\": {\n" +
                "    \"programa\": \"Ingeniería de Software\",\n" +
                "    \"semestreActual\": 5,\n" +
                "    \"promedioAcumulado\": 4.1,\n" +
                "    \"materiasInscritas\": [\"Programación Web\", \"Matematicas\", \"Bases de Datos\"],\n" +
                "    \"detalleMaterias\": [\n" +
                "      {\n" +
                "        \"nombre\": \"Programación Web\",\n" +
                "        \"creditos\": 4,\n" +
                "        \"docente\": \"Ana Ramírez\",\n" +
                "        \"estado\": \"cursando\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"nombre\": \"Matematicas\",\n" +
                "        \"creditos\": 3,\n" +
                "        \"docente\": \"Rober Lopez\",\n" +
                "        \"estado\": \"cursando\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"nombre\": \"Bases de Datos\",\n" +
                "        \"creditos\": 3,\n" +
                "        \"docente\": \"Carlos Pérez\",\n" +
                "        \"estado\": \"cursando\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"preferencias\": {\n" +
                "    \"modalidadEstudio\": \"híbrida\",\n" +
                "    \"actividadesExtracurriculares\": [\n" +
                "      \"Fútbol\",\n" +
                "      \"Club de Videojuegos\",\n" +
                "      \"Fotografía\"\n" +
                "    ],\n" +
                "    \"notificaciones\": {\n" +
                "      \"email\": true,\n" +
                "      \"sms\": false,\n" +
                "      \"app\": true\n" +
                "    }\n" +
                "  }\n" +
                "}";

        out.println(json);
        out.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
