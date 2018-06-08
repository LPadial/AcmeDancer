<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form action="style/administrator/addVideoSave.do" method="get">
	<div class="form-group" style="width: 55%;">
		<label title="text"></label>
		<input type="text" name="video" id="video" class="form-control">
	</div>
	
	<input name="save" type="submit" class="btn btn-primary" value="<spring:message code="acme.save"/>">
	<input onclick="window.history.back()" type="button" class="btn btn-warning" value="<spring:message code="acme.cancel" />">
</form>