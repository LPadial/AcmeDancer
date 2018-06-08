<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<security:authorize access="hasRole('ADMINISTRATOR')">

		<table class="table">
			<jstl:forEach var="e" items="${style.pictures}">
				<tr>
					<td><img src="${e}" style="max-width: 120px; max-height: 120px;" /></td>
					<td><a href="style/administrator/deletePicture.do?picture=${e}"><spring:message code="acme.delete" /></a></td>
				</tr>
			</jstl:forEach>
		</table>
		
</security:authorize>
