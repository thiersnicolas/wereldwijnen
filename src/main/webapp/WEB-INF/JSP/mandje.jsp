<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="v" uri="http://vdab.be/tags"%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title="Mandje"></v:head>
</head>
<body>
	<h1>Mandje</h1>
	<c:url value="/index.htm" var="indexURL" />
	<p>
		<a href="${indexURL}">Terug naar overzicht</a>
	</p>

	<table id="tablemandje">
		<thead id="tablemandje">
			<tr>
				<th scope="col">Wijn</th>
				<th scope="col">Prijs</th>
				<th scope="col">Aantal</th>
				<th scope="col">Te betalen</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="mandlijn" items="${mandje}">
				<tr>
					<td>${mandlijn.key.soort.land.naam} ${mandlijn.key.soort.naam}
						${mandlijn.key.jaar}</td>
					<td><fmt:formatNumber value='${mandlijn.key.prijs}'
							minFractionDigits="2" maxFractionDigits="2" /></td>
					<td>${mandlijn.value}</td>
					<td><fmt:formatNumber
							value='${mandlijn.value*mandlijn.key.prijs}'
							minFractionDigits="2" maxFractionDigits="2" /></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3"></td>
				<td>Totaal: ${totaal}</td>
			</tr>
		</tfoot>
	</table>

	<div>
		<form method="post" id="toevoegform">
			<dl>
				<dt>
					Naam<span class="fout"> ${fouten.naam}</span>
				</dt>
				<dd class="klant">
					<input name='naam' value='${param.naam}' autofocus required>
				</dd>

				<dt>
					Straat<span class="fout"> ${fouten.straat}</span>
				</dt>
				<dd class="klant">
					<input name='straat' value='${param.straat}' autofocus required>
				</dd>

				<dt>
					Huisnummer<span class="fout"> ${fouten.huisnr}</span>
				</dt>
				<dd class="klant">
					<input name='huisnr' value='${param.huisnr}' autofocus required>
				</dd>

				<dt>
					Postcode<span class="fout"> ${fouten.postcode}</span>
				</dt>
				<dd class="klant">
					<input name='postcode' value='${param.postcode}' autofocus required>
				</dd>

				<dt>
					Gemeente<span class="fout"> ${fouten.gemeente}</span>
				</dt>
				<dd class="klant">
					<input name='gemeente' value='${param.gemeente}' autofocus required>
				</dd>
			</dl>
			<div>
				<label><span class="fout"> ${fouten.bestelWijze}</span> <input
					type='radio' id="afhalen" name='bestelwijze' value='AFHALEN'
					onclick="document.getElementById('opsturen').checked = false"
					${param.bestelwijze=='afhalen' ? 'checked' : ''}>Afhalen</label>
			</div>
			<div>
				<label><input type='radio' name='bestelwijze'
					value='OPSTUREN'
					onclick="document.getElementById('afhalen').checked = false"
					id="opsturen" ${param.bestelwijze=='opsturen' ? 'checked' : ''}>Opsturen</label>
			</div>
			<input id="bonsubmit" type="submit" value="Als bestelbon bevestigen">
		</form>
	</div>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>