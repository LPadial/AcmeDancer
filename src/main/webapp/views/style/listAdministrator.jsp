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


	<div class="btn-group btn-group-xs" role="group" aria-label="label">

		<button
			onclick="javascript:location.href='style/administrator/create.do'"
			type="button" class="btn btn-default">
			<spring:message code="style.create" />
		</button>
	</div>

	<acme:list list="${styles}"
		requestURI="style/listAdministratorStyle.do"
		hidden_fields="id,version"
		entityUrl="{courses: course/listByStyle.do}"
		editUrl="style/administrator/edit.do" video_fields="videos"
		image_fields="pictures" variable="s">

		<jstl:if test="${s.courses.isEmpty()}">
			<td><a href="style/administrator/delete.do?q=${s.id}"><spring:message
						code="acme.delete" /></a></td>
		</jstl:if>

	</acme:list>

</security:authorize>