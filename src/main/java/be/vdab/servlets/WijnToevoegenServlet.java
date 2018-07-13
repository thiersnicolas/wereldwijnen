package be.vdab.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Wijn;
import be.vdab.services.WijnService;
import be.vdab.utils.StringUtils;

@WebServlet("/wijntoevoegen.htm")
public class WijnToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/wijntoevoegen.jsp";
	private static final String REDIRECT_URL = "%s/index.htm";
	private final transient WijnService wijnService = new WijnService();
	private static final String MANDJE = "mandje";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String wijnIdString = request.getParameter("wijnId");
		if (wijnIdString != null && StringUtils.isLong(wijnIdString)) {
			long wijnId = Long.parseLong(wijnIdString);
			wijnService.read(wijnId).ifPresent(wijn -> request.setAttribute("wijn", wijn));
			if (session != null) {
				@SuppressWarnings("unchecked")
				Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
				request.setAttribute("aantal", mandje.get(wijnId));
			}
			request.getRequestDispatcher(VIEW).forward(request, response);
		} else {
			response.sendRedirect(
					response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String wijnIdString = request.getParameter("wijnId");
		String aantalString = request.getParameter("aantal");
		Optional<Wijn> wijnOptional;
		if (StringUtils.isInteger(wijnIdString)) {
			wijnOptional = wijnService.read(Long.parseLong(wijnIdString));
			if (aantalString != null && !aantalString.trim().isEmpty() && StringUtils.isInteger(aantalString)) {
				int aantal = Integer.parseInt(aantalString);
				HttpSession session = request.getSession(false);
				if (session == null) {
					session = request.getSession();
					Map<Long, Integer> nieuwMandje = new TreeMap<>();
					nieuwMandje.put(wijnOptional.get().getId(), aantal);
					session.setAttribute(MANDJE, nieuwMandje);
				} else {
					@SuppressWarnings("unchecked")
					Map<Long, Integer> oudMandje = (TreeMap<Long, Integer>) session.getAttribute(MANDJE);
					oudMandje.put(wijnOptional.get().getId(), aantal);
					session.setAttribute(MANDJE, oudMandje);
				}
				response.sendRedirect(
						response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
			} else {
				request.setAttribute("fout", "Geheel, positief getal verplicht");
				if (wijnOptional.isPresent()) {
					request.setAttribute("wijn", wijnOptional.get());
					request.getRequestDispatcher(VIEW).forward(request, response);
				} else {
					response.sendRedirect(
							response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
				}
			}

		} else {
			response.sendRedirect(
					response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
		}
	}
}
