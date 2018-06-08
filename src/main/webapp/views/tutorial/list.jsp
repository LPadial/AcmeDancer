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

<jstl:if test="${a==0}">

	<acme:list list="${tutorials}" requestURI="tutorial/list.do"
		hidden_fields="id,version,video" viewUrl="tutorial/view.do" />


</jstl:if>

<jstl:if test="${a==1}">

	<acme:list list="${tutorials}" requestURI="tutorial/listByAcademy.do"
		hidden_fields="id,version,video" viewUrl="tutorial/view.do" />
</jstl:if>

<jstl:if test="${a==2}">
	<security:authorize access="hasRole('ACADEMY')">

		<div class="btn-group btn-group-xs" role="group" aria-label="label">
			<button
				onclick="javascript:location.href='tutorial/academy/create.do'"
				type="button" class="btn btn-default">
				<spring:message code="tutorial.create" />
			</button>
		</div>
		<br/>

		<acme:list list="${tutorials}" requestURI="tutorial/academy/mylist.do"
			hidden_fields="id,version,video" editUrl="tutorial/edit.do"
			viewUrl="tutorial/view.do" deleteUrl="tutorial/delete.do" />
	</security:authorize>
</jstl:if>


