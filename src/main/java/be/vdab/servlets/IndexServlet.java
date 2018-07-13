package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.services.LandService;
import be.vdab.services.SoortService;
import be.vdab.services.WijnService;
import be.vdab.utils.StringUtils;

@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String VIEW = "/WEB-INF/JSP/index.jsp";
	private final transient LandService landService = new LandService();
	private final transient SoortService soortService = new SoortService();
	private final transient WijnService wijnService = new WijnService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("landen", landService.findAll());
		HttpSession session = request.getSession(false);
		if (session!= null) {
			request.setAttribute("mandje", "true");
		}
		
		String landIdString = request.getParameter("landId");
		if (landIdString !=null && StringUtils.isLong(landIdString)) {
			landService.read(Long.parseLong(landIdString)).ifPresent(land -> {
				request.setAttribute("gekozenLand", land); 
				request.setAttribute("soorten", soortService.findByLandId(Long.parseLong(landIdString)));
			});
			String soortIdString = request.getParameter("soortId");
			if (soortIdString != null && StringUtils.isLong(soortIdString)) {
				soortService.read(Long.parseLong(soortIdString)).ifPresent(soort -> {
					request.setAttribute("gekozenSoort", soort); 
					request.setAttribute("wijnen", wijnService.findBySoortId((Long.parseLong(soortIdString))));
				});
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
