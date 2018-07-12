<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri="http://vdab.be/tags"%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title="Wijn toevoegen"></v:head>
</head>
<body>
<h1>Wijn toevoegen aan mandje</h1>
<p><a href="">Terug naar overzicht</a></p>
<table>
	<tr> 
		<td>Land</td>
		<td>${wijn.soort.land.naam}</td>
	</tr>
	<tr> 
		<td>Soort</td>
		<td>${wijn.soort.naam}</td>
	</tr>
	<tr> 
		<td>Jaar</td>
		<td>${wijn.jaar}</td>
	</tr>
	<tr> 
		<td>Beoordeling</td>
		<td><c:forEach var='index' begin='1' end='${wijn.beoordeling}'>&#9733;</c:forEach></td>
	</tr>
	<tr> 
		<td>Prijs</td>
		<td>${wijn.prijs}</td>
	</tr>
</table>
<form method="post">
<dl>
<dd>Aantal flessen<span>${fout}</span></dd>
<dt><input type="number" name="aantal" value="${aantal}"></dt>
</dl>
<input type="submit" value="Toevoegen">
</form>
</body>
</html>

