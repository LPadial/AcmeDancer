<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('ACADEMY')">
	<jstl:set var="url" value="tutorial/academy/save-edit.do" />

	<acme:acme_form url="${url}" type="edit" skip_fields="description"
		hiddenFields="numShows" entity="${tutorial}">
		<script>
			tinymce.init({
				selector : 'textarea'
			});
		</script>
		<acme:textarea code="style.description" path="description" />
	</acme:acme_form>

</security:authorize>

