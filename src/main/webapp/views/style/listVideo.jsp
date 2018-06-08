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
			<jstl:forEach var="e" items="${videos}">
				<tr>
					<td><acme:youtube_view url="${e}"></acme:youtube_view>  </td>
					<td><a href="style/administrator/deleteVideo.do?video=${e}"><spring:message code="acme.delete" /></a></td>
				</tr>
			</jstl:forEach>
		</table>
		
</security:authorize>
