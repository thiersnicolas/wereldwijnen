<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri="http://vdab.be/tags" %>
<!doctype html>
<html lang='nl'> 
  <head>
	<v:head title="Wereldwijnen"></v:head>
  </head>
  <body>
  	<div id="hoofding">
  		<h1>Wereldwijnen</h1>
  		<ul id="vlaggen">
  			<c:forEach var="land" items="${landen}">
  				<c:url value="/index.htm" var="indexURL">
  					<c:param name="landId" value="${land.id}"/>
				</c:url>
  				<li><a href="${indexURL}"><img src="<c:url value='/images/${land.id}.png'/>" alt="${land.naam}"/></a></li>
  			</c:forEach>
  		</ul>
  	</div>
  </body>
 </html>