package org.oa.tp.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.oa.tp.dao.AbstractDao;
import org.oa.tp.dao.DaoFacade;
import org.oa.tp.data.Genre;

public class GenreServiceServlet extends HttpServlet {

    private static final String PAREMETR_METHOD = "method";
    private static final String PAREMETR_ID = "id";
    private static final String PAREMETR_NAME = "name";

    private static final String GET_ALL_METHOD = "get";
    private static final String CREATE_METHOD = "create";
    private static final String DELETE_METHOD = "delete";
    private static final String UPDATE_METHOD = "update";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String requestMethod = request.getParameter(PAREMETR_METHOD);
        System.out.println("Method = " + requestMethod);
        response.setContentType("application/json;charset=UTF-8");

        DaoFacade daoFacade = new DaoFacade(getServletContext());
        final AbstractDao<Genre> genreDao = daoFacade.getGenreDao();
        if (CREATE_METHOD.equalsIgnoreCase(requestMethod)) {
            String requestName = request.getParameter(PAREMETR_NAME);
            final boolean isAdded = genreDao.add(new Genre(requestName));
            try (PrintWriter pw = response.getWriter()) {
                if (isAdded) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    pw.println("{\"response\":\"Genre created\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    pw.print("{\"error\":\"Failed create album\"}");
                }
            }
        } else if (GET_ALL_METHOD.equalsIgnoreCase(requestMethod)) {
            final List<Genre> loadAll = genreDao.loadAll();
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                Gson gson = new Gson();
                gson.toJson(loadAll, out);
            }
        } else if (DELETE_METHOD.equalsIgnoreCase(requestMethod)) {
            String ObjectId = request.getParameter(PAREMETR_ID);
            long id = Long.parseLong(ObjectId);
            boolean isDeleted = daoFacade.getGenreDao().delete(id);
            try (PrintWriter pw = response.getWriter()) {
                if (isDeleted) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    pw.println("{\"response\":\"Genre deleted\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    pw.print("{\"error\":\"Failed deleted album\"}");
                }
            }
        } else if (UPDATE_METHOD.equalsIgnoreCase(requestMethod)) {
            String idString = request.getParameter(PAREMETR_ID);
            String nameString = request.getParameter(PAREMETR_NAME);
            long id = Long.parseLong(idString);
            Genre genre = new Genre(id, nameString);
            boolean updated = daoFacade.getGenreDao().update(genre);
            try (PrintWriter out = response.getWriter()) {
                if (updated) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print("{\"response\":\"Genre updated\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\":\"Failed update genre\"}");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String queryString = request.getQueryString();
        System.out.println("query string " + queryString);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
