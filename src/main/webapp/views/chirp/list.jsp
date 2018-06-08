<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="chirp.momentWritten" var="momentWritten" />
<spring:message code="chirp.text" var="text" />
<spring:message code="chirp.actor" var="actorName" />
<spring:message code="chirp.delete" var="delete" />
<spring:message code="chirp.subscribe" var="subscribe" />

<security:authorize access="isAuthenticated()">

	<jstl:if test="${a==1}">

		<acme:list list="${chirps}" requestURI="chirp/actor/mylist.do"
			pagesize="16" columNames="{actor:chirp.actor}"
			field_mapping="{actor:actorName}" deleteUrl="chirp/actor/delete.do">
		</acme:list>

	</jstl:if>


	<jstl:if test="${a==2}">

		<acme:list list="${chirps}" requestURI="chirp/actor/list.do"
			pagesize="16" variable="e" columNames="{actor:chirp.actor}"
			field_mapping="{actor:actorName}">

					<security:authentication property="principal.id" var="id" />
					<jstl:if
						test="${!followers.contains(e.actor) and e.actor.userAccount.id != id}">
						<a href="chirp/actor/subscribe.do?q=${e.id}"> <jstl:out
								value="${subscribe}" /></a>
					</jstl:if>

		</acme:list>

	</jstl:if>


	<jstl:if test="${a==3}">

		<acme:list list="${chirps}"
			requestURI="chirp/actor/mylistSubscribe.do" pagesize="16"
			columNames="{actor:chirp.actor}" field_mapping="{actor:actorName}">
		</acme:list>

	</jstl:if>

</security:authorize>
