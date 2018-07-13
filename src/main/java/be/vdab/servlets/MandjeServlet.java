package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Wijn;
import be.vdab.enums.Bestelwijze;
import be.vdab.services.BestelbonService;
import be.vdab.services.LandService;
import be.vdab.services.WijnService;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;

@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
	private static final String VIEW_BEVESTIGD_INDEX = "/WEB-INF/JSP/index.jsp";
	private static final String REDIRECT_URL = "%s/index.htm";
	private static final String MANDJE = "mandje";
	private final transient BestelbonService bestelbonService = new BestelbonService();
	private final transient WijnService wijnService = new WijnService();
	private final transient LandService landService = new LandService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);	
		if (session !=null)		{
			VerwerkMandjeEnTotaalEnGeefMeeAanRequest(request, session);
			request.getRequestDispatcher(VIEW).forward(request, response);
		} else {
			response.sendRedirect(
					response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
		}
	}
	
	private void VerwerkMandjeEnTotaalEnGeefMeeAanRequest(HttpServletRequest request, HttpSession session) {
		@SuppressWarnings({ "unchecked" })
		Map<Long, Integer> sessionMandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
		Map<Wijn, Integer> mandje = new HashMap<>();
		vanSessionMandjeNaarMandje(sessionMandje, mandje);
		request.setAttribute("mandje", mandje);
		BigDecimal totaal = BigDecimal.ZERO;
		for (Wijn wijn:mandje.keySet()) {
			totaal = totaal.add(wijn.getPrijs().multiply(BigDecimal.valueOf(mandje.get(wijn))));
		}
		request.setAttribute("totaal", totaal);
	}
	
	private void vanSessionMandjeNaarMandje(Map<Long, Integer> sessionMandje, Map<Wijn, Integer> mandje) {
		for (long wijnId:sessionMandje.keySet()) {
			wijnService.read(wijnId).ifPresent(wijn-> {
				mandje.put(wijn, sessionMandje.get(wijnId));
			});
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);	
		if (session ==null)		{
			response.sendRedirect(
					response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
		} else {
			@SuppressWarnings({ "unchecked" })
			Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
			
			Map<String, String> fouten = new HashMap<>();
			
			String naam = request.getParameter("naam");
			if (!Bestelbon.isNaamValid(naam)) {
				fouten.put("naam", "verplicht");
			}
			
			String straat = request.getParameter("straat");
			if (!Adres.isStraatValid(straat)) {
				fouten.put("straat", "verplicht");
			}
			
			String huisNr = request.getParameter("huisnr");
			if (!Adres.isHuisNrValid(huisNr)) {
				fouten.put("huisnr", "verplicht");
			}
			
			String postCode = request.getParameter("postcode");
			if (!Adres.isPostCodeValid(postCode)) {
				fouten.put("postcode", "verplicht");
			}
			
			String gemeente = request.getParameter("gemeente");
			if (!Adres.isPostCodeValid(gemeente)) {
				fouten.put("gemeente", "verplicht");
			}
			
			String bestellingsWijze = request.getParameter("bestelwijze");
			if (bestellingsWijze == null) {
				fouten.put("bestellingswijze", "verplicht");
			}
			
			System.out.println(naam + straat + huisNr + postCode + gemeente + bestellingsWijze);
			
			if (fouten.isEmpty()) {
				Adres adres = new Adres(straat, postCode, huisNr, gemeente);
				Bestelbon bestelbon = new Bestelbon(naam, adres, Bestelwijze.valueOf(bestellingsWijze));
				voegMandjeToe(bestelbon, mandje);
				bestelbonService.createBestelbon(bestelbon);
				session.invalidate();
				request.setAttribute("landen", landService.findAll());
				request.setAttribute("bonId", bestelbon.getId());
				request.getRequestDispatcher(VIEW_BEVESTIGD_INDEX).forward(request, response);
				
			} else {
				request.setAttribute("fouten", fouten);
				VerwerkMandjeEnTotaalEnGeefMeeAanRequest(request, session);
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
			
		}
	}
	
	private void voegMandjeToe(Bestelbon bestelbon, Map<Long, Integer> mandje) {
		for (long wijnId:mandje.keySet()) {
			wijnService.read(wijnId).ifPresent(wijn-> {
				Bestelbonlijn bestelbonlijn = new Bestelbonlijn(mandje.get(wijnId), bestelbon, wijn);
				bestelbon.addBestelbonlijn(bestelbonlijn);
			});
		}
	}
}
