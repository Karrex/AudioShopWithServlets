
package org.oa.tp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.oa.tp.dao.DaoFacade;
import org.oa.tp.data.Album;

public class AlbumsViewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DaoFacade facade = new DaoFacade(getServletContext());
        List<Album> albums = facade.getAlbumDao().loadAll();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Albums</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Albums</h1>");
            out.println("<table border=\"1\" style=\"width:100%\">");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>NAME</th>");
            out.println("<th width=\"10%\">YEAR</th>");
            out.println("<th width=\"10%\">DELETE</th>");
            out.println("</tr>");
            for (Album album : albums) {
                out.println("<tr>");
                out.println("<td>" + album.getId() + "</td>");
                out.println("<td>" + album.getName() + "</td>");
                out.println("<td>" + album.getYear() + "</td>");
                out.println("<td><a href=\"albums?method=delete&id=" + album.getId() + "\">Delete</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<a href=\"create_album.html\"><button>Create</button></a>");
            out.println("<input type=\"button\" onclick=\"history.back();\" value=\"Back\"/>");
            out.println("<a href=\"index.html\"><button>Main</button></a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}