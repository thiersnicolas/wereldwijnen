<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri="http://vdab.be/tags"%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title="Wijn toevoegen"></v:head>
</head>
<body>
	<div id="container">
		<h1>Wijn toevoegen aan mandje</h1>
		<c:url value="/index.htm" var="indexURL" />
		<p>
			<a href="${indexURL}">Terug naar overzicht</a>
		</p>
		<table id="tablewijn">
			<tr>
				<td class="tdlinks">Land</td>
				<td class="wijn">${wijn.soort.land.naam}</td>
			</tr>
			<tr>
				<td class="tdlinks">Soort</td>
				<td class="wijn">${wijn.soort.naam}</td>
			</tr>
			<tr>
				<td class="tdlinks">Jaar</td>
				<td class="wijn">${wijn.jaar}</td>
			</tr>
			<tr>
				<td class="tdlinks">Beoordeling</td>
				<td class="wijn"><c:forEach var='index' begin='1'
						end='${wijn.beoordeling}'>&#9733;</c:forEach></td>
			</tr>
			<tr>
				<td class="tdlinks">Prijs</td>
				<td class="wijn">${wijn.prijs}</td>
			</tr>
		</table>
		<form method="post">
			<dl id="aantalflessen">
				<dd>
					Aantal flessen<span class="fout"> ${fout}</span>
				</dd>
				<dt>
					<input type="number" name="aantal" value="${aantal}">
				</dt>
			</dl>
			<input type="submit" value="Toevoegen">
		</form>
	</div>
</body>
</html>

