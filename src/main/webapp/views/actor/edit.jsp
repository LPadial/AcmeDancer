<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<security:authorize access="isAuthenticated()">


	<security:authorize access="hasRole('DANCER')">
		<jstl:set var="url" value="actor/save-dancer.do" />
	</security:authorize>

	<security:authorize access="hasRole('ACADEMY')">
		<jstl:set var="url" value="actor/save-academy.do" />
	</security:authorize>

	<security:authorize access="hasRole('ADMINISTRATOR')">
		<jstl:set var="url" value="actor/save-administrator.do" />
	</security:authorize>


	<acme:acme_form url="${url}" type="edit"
		another_mapped_classes="domain.Actor"
		hiddenFields="follower,chirps,userAccount.authorities,userAccount.username,userAccount.password"
		skip_fields="applications,commercialName,tutorials,courses"
		entity="${person}">

		<security:authorize access="hasRole('DANCER')">
			<form:hidden path="applications" />
		</security:authorize>

		<security:authorize access="hasRole('ACADEMY')">

			<div class="form-group" style="width: 55%">
				<form:label path="commercialName">
					<spring:message code="actor.commercialName" />
				</form:label>
				<br />
				<form:input path="commercialName" class="form-control" type="text" />
			</div>

			<br />

			<form:hidden path="tutorials" />
			<form:hidden path="courses" />
		</security:authorize>
	</acme:acme_form>
</security:authorize>


