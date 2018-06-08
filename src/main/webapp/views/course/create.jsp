<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('ACADEMY')">

	<acme:acme_form url="course/academy/save-create.do" skip_fields="levelCourse,time,style"
		hiddenFields="applications,academy" type="create" entity="${course}">
		
		<div class="form-group" style="width: 55%;">
			<input value="<fmt:formatDate value="${course.time}" pattern="HH:mm"/>" name="time" placeholder="HH:mm" type="text" class="form-control">
		</div>
		
		<div class="form-group" style="width: 55%;">
			<label for="label"><spring:message code='course.levelCourse' /></label>
			<select name="levelCourse">
				<option value="BEGINNER">BEGINNER</option>
				<option value="INTERMEDIATE">INTERMEDIATE</option>
				<option value="ADVANCED">ADVANCED</option>
				<option value="PROFESSIONAL">DINNERS</option>
			</select>
		</div>

		<div class="form-group" style="width: 55%;">
			<label for="label"><spring:message code="course.style" /> </label>

			<select name="style">
				<jstl:forEach var="s" items="${styles}">
					<option value="${s.id}">${s.name}</option>
				</jstl:forEach>
			</select>
			
		</div>
	</acme:acme_form>

</security:authorize>
