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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<security:authorize access="hasRole('ACADEMY')">
	<div class="btn-group btn-group-xs" role="group" aria-label="label">
	
		<button onclick="javascript:location.href='course/academy/create.do'"
			type="button" class="btn btn-default">
			<spring:message code="course.create" />
		</button>
	</div>

	<br />

	<acme:list list="${courses}" requestURI="course/academy/mylist.do"
		hidden_fields="id,version,academy" field_mapping="{levelCourse:value}"
		entityUrl="{style:style/view.do,applications:application/academy/listByCourse.do}"
		editUrl="course/academy/edit.do" deleteUrl="course/academy/delete.do"></acme:list>

</security:authorize>
