package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.LandService;

@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String VIEW = "/WEB-INF/JSP/index.jsp";
	private final transient LandService landService = new LandService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("landen", landService.findAll());
		
		String landIdString = request.getParameter("land");
		if (landIdString !=null) {
			
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
