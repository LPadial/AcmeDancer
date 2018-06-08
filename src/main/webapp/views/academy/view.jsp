<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:acme_view entity="${academy}" skip_fields="tutorials,courses">
	<tr>
		<td><spring:message code="academy.tutorials"/></td>
		<td>
			<table>
				<jstl:forEach var="e" items="${academy.tutorials}">
					<tr>
						<td>${e.title}</td>
					</tr>
				</jstl:forEach>
			</table>	
		</td>
	</tr>
	
	<tr>
		<td><spring:message code="academy.courses"/></td>
		<td>
			<table>
				<jstl:forEach var="e" items="${academy.courses}">
					<tr>
						<td>${e.title}</td>
					</tr>
				</jstl:forEach>
			</table>	
		</td>
	</tr>
</acme:acme_view>