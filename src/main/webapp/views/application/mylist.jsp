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

<spring:message code="application.createMoment" var="createMoment" />
<spring:message code="application.statusApplication"
	var="statusApplication" />
<spring:message code="application.course" var="course" />
<spring:message code="application.accept" var="accept" />
<spring:message code="application.denied" var="denied" />
<spring:message code="application.cancel" var="cancel" />


<security:authorize access="hasRole('DANCER')">


		<acme:list columNames="{course:application.course}"
			field_mapping="{course:title,statusApplication:value}"
			list="${applications}"
			requestURI="application/dancer/mylist.do" pagesize="8">
		</acme:list>


</security:authorize>
