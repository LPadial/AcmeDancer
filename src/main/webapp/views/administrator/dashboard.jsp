<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('ADMINISTRATOR')">

	<b><spring:message code="administrator.minCourse" /> </b>
	<jstl:out value="${course[0]}" />
	<br />
	<b><spring:message code="administrator.stanCourse" /> </b>
	<jstl:out value="${course[1]}" />
	<br />
	<b><spring:message code="administrator.avgCourse" /> </b>
	<jstl:out value="${course[2]}" />
	<br />
	<b><spring:message code="administrator.maxCourse" /> </b>
	<jstl:out value="${course[3]}" />
	<br />


	<b><spring:message code="administrator.minApp" /> </b>
	<jstl:out value="${app[0]}" />
	<br />
	<b><spring:message code="administrator.avgApp" /> </b>
	<jstl:out value="${app[1]}" />
	<br />
	<b><spring:message code="administrator.stanApp" /> </b>
	<jstl:out value="${app[2]}" />
	<br />
	<b><spring:message code="administrator.maxApp" /> </b>
	<jstl:out value="${app[3]}" />
	<br />

	<b><spring:message code="administrator.minTutorialAcad" /> </b>
	<jstl:out value="${tutorialAcad[0]}" />
	<br />
	<b><spring:message code="administrator.avgTutorialAcad" /> </b>
	<jstl:out value="${tutorialAcad[1]}" />
	<br />
	<b><spring:message code="administrator.maxTutorialAcad" /> </b>
	<jstl:out value="${tutorialAcad[2]}" />
	<br />


	<b><spring:message code="administrator.minTutorialShow" /> </b>
	<jstl:out value="${tutorialShow[0]}" />
	<br />
	<b><spring:message code="administrator.avgTutorialShow" /> </b>
	<jstl:out value="${tutorialShow[1]}" />
	<br />
	<b><spring:message code="administrator.maxTutorialShow" /> </b>
	<jstl:out value="${tutorialShow[2]}" />
	<br />


	<b><spring:message code="administrator.listTutorialSee" /> </b>
	<jstl:forEach var="c" items="${tutorialSee}" varStatus="loop">
		<jstl:out value="${c.title}" />
		<jstl:if test="${!loop.last}">, </jstl:if>
	</jstl:forEach>
	<br />


	<b><spring:message code="administrator.avgChirpActor" /> </b>
	<jstl:forEach var="c" items="${chirpActor}" varStatus="loop">
		<jstl:out value="${c[0]}" />:
		<jstl:out value="${c[1]}" />
		<jstl:if test="${!loop.last}">; </jstl:if>
	</jstl:forEach>
	<br />

	<b><spring:message code="administrator.avgChirpSubcription" /> </b>
	<jstl:forEach var="c" items="${chirpSubcription}" varStatus="loop">
		<jstl:out value="${c[0]}" />:
		<jstl:out value="${c[1]}" />
		<jstl:if test="${!loop.last}">, </jstl:if>
	</jstl:forEach>
	<br />




</security:authorize>
