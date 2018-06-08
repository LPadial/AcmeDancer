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


<security:authorize access="permitAll">

	<form action="course/search.do" method="get">
		<input style="max-width: 55%;" type="search" class="form-control"
			name="q" placeholder="<spring:message code='course.search'/>">
	</form>

	<jstl:if test="${a==0}">

		<acme:list requestURI="course/list.do" list="${courses}"
			hidden_fields="id,version,applications"
			field_mapping="{levelCourse:value}" time_stamps="{time:hh mm,start:dd/MM/yyyy,end:dd/MM/yyyy}"
			entityUrl="{academy:academy/view.do,style:style/view.do}"
			variable="e">

			<security:authorize access="hasRole('DANCER')">

				<jstl:if test="${!coursesApplyActor.contains(e)}">
					<a href="application/dancer/apply.do?q=${e.id}"> 
					<spring:message code="course.apply"/>
					</a>
				</jstl:if>

			</security:authorize>
		</acme:list>

	</jstl:if>

	<jstl:if test="${a==1}">

		<acme:list requestURI="course/listByAcademy.do" list="${courses}"
			hidden_fields="applications,academy"  time_stamps="{time:hh mm,start:dd/MM/yyyy,end:dd/MM/yyyy}"
			field_mapping="{levelCourse:value,style:name}"
			entityUrl="{style:style/view.do,academy:academy/view.do}">
		</acme:list>

	</jstl:if>

	<jstl:if test="${a==2}">

		<acme:list requestURI="course/listByStyle.do" list="${courses}"
			hidden_fields="id,version,applications"  time_stamps="{time:hh mm,start:dd/MM/yyyy,end:dd/MM/yyyy}"
			field_mapping="{levelCourse:value,academy:commercialName}"
			entityUrl="{style:style/view.do,academy:academy/view.do}">
		</acme:list>

	</jstl:if>

</security:authorize>
