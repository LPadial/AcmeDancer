<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:acme_form url="dancer/save.do" type="create"
	cancel="welcome/index.do" hiddenFields="follower,chirps,tutorials,courses" skip_fields="userAccount" entity="${dancer}"
	another_mapped_classes="domain.Actor">

	<acme:acme_input entity="${dancer.userAccount}"
		name="userAccount.username" field="username" />
	<acme:acme_input entity="${dancer.userAccount}"
		name="userAccount.password" field="password" typeIn="password" />
	<form:hidden path="userAccount.Authorities" />

</acme:acme_form>
