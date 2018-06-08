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
	
	<acme:acme_form type="create" hiddenFields="courses"
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