<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri="http://vdab.be/tags"%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title="Wereldwijnen"></v:head>
</head>
<body>
	<div id="container">
		<div id="hoofding">
			<h1>Wereldwijnen</h1>
			<ul id="vlaggen" class="nav clearFix">
				<c:forEach var="land" items="${landen}">
					<c:url value="/index.htm" var="indexURL">
						<c:param name="landId" value="${land.id}" />
					</c:url>
					<li class="nav item vlag"><a class="vlag-link"
						href="${indexURL}"><img class="nav-link"
							src="<c:url value='/images/${land.id}.png'/>" alt="${land.naam}" /></a></li>
				</c:forEach>
			</ul>
			<c:url value="/mandje.htm" var="mandjeURL" />
			<c:if test="${not empty mandje}">
				<h1>
					<a id="mandjelink" href="${mandjeURL}"><img id="mandjeimg"
						src="<c:url value='/images/mandje.png'/>" alt="mandje" /></a>
				</h1>
			</c:if>
		</div>
		<c:if test="${not empty bonId}">
			<h1>Je mandje is bevestigd als bestelbon ${bonId}</h1>
		</c:if>
		<c:if test="${not empty gekozenLand}">
			<h3>Soorten uit ${gekozenLand.naam}</h3>
			<c:forEach var="soort" items="${soorten}">
				<c:url value="/index.htm" var="indexSoortURL">
					<c:param name="landId" value="${gekozenLand.id}" />
					<c:param name="soortId" value="${soort.id}" />
				</c:url>
				<a href="${indexSoortURL}" class="soort-link"> ${soort.naam} </a>
			</c:forEach>
		</c:if>
		<c:if test="${not empty wijnen}">
			<h3>Wijnen uit ${gekozenSoort.naam}</h3>
			<ul>
				<c:forEach var="wijn" items="${wijnen}">
					<c:url value="/wijntoevoegen.htm" var="indexWijnURL">
						<c:param name="wijnId" value="${wijn.id}" />
					</c:url>
					<li class="wijnen"><a href="${indexWijnURL}">${wijn.jaar}
							<c:forEach var='index' begin='1' end='${wijn.beoordeling}'>&#9733;</c:forEach>
					</a></li>
				</c:forEach>
			</ul>
		</c:if>
	</div>
</body>
</html>