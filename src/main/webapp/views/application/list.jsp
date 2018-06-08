<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="application.accept" var="accept" />
<spring:message code="application.denied" var="denied" />


<security:authorize access="hasRole('DANCER')">

	<jstl:if test="${a==1}">

		<acme:list columNames="{course:application.course}"
			field_mapping="{course:title,statusApplication:value}"
			list="${applications}"
			requestURI="application/dancer/listByDancer.do" pagesize="8">
		</acme:list>

	</jstl:if>

</security:authorize>


<security:authorize access="hasRole('ACADEMY')">

	<jstl:if test="${a==2}">

		<acme:list requestURI="application/listByCourse.do"
			field_mapping="{statusApplication:value}" hidden_fields="course" variable="e" pagesize="8"
			list="${applications}">

			<jstl:if test="${e.statusApplication.value eq 'PENDING'}">
				<a href="application/academy/accept.do?q=${e.id}"><jstl:out
						value="${accept}" /></a>
			</jstl:if>
			<jstl:if test="${e.statusApplication.value eq 'PENDING'}">
				<a href="application/academy/denied.do?q=${e.id}"><jstl:out
						value="${denied}" /></a>
			</jstl:if>

		</acme:list>
	</jstl:if>

</security:authorize>
