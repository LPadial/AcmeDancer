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
			onclick="javascript:location.href='style/administrator/addPicture.do?q=${style.id}'"
			type="button" class="btn btn-default">
			<spring:message code="style.add.picture" />
		</button>
		<button
			onclick="javascript:location.href='style/administrator/addVideo.do?q=${style.id}'"
			type="button" class="btn btn-default">
			<spring:message code="style.add.video" />
		</button>
		
		<button
			onclick="javascript:location.href='style/administrator/listPicture.do?q=${style.id}'"
			type="button" class="btn btn-default">
			<spring:message code="style.edit.picture" />
		</button>
		<button
			onclick="javascript:location.href='style/administrator/listVideo.do?q=${style.id}'"
			type="button" class="btn btn-default">
			<spring:message code="style.edit.video" />
		</button>
	</div>
	
	<acme:acme_form type="edit" hiddenFields="courses"
		skip_fields="description" url="style/administrator/save-edit.do"
		entity="${style}">

		<script>
			tinymce.init({
				selector : 'textarea'
			});
		</script>
		<acme:textarea code="style.description" path="description" />
		
		
	</acme:acme_form>
</security:authorize>