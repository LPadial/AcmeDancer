<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Dancer Co., Inc." />
</div>


<div style="width: 60%">
	<nav class="navbar navbar-default" style="margin-bottom: 0px">
		<div class="container-fluid">
			<div class="navbar-header">
				<ul class="nav navbar-nav">
					<security:authorize access="isAnonymous()">
						<li><a class="fNiv" href="security/login.do"><spring:message
									code="master.page.login" /></a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><spring:message
									code="master.page.register" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a class="fNiv" href="academy/create.do"><spring:message
											code="master.page.academy.signup" /></a></li>
								<li><a class="fNiv" href="dancer/create.do"><spring:message
											code="master.page.dancer.signup" /></a></li>
							</ul></li>

						<li><a href="academy/list.do"><spring:message
									code="master.page.academy" /></a></li>
						<li><a href="course/listNoRegister.do"><spring:message
									code="master.page.course" /></a></li>
						<li><a href="style/list.do"><spring:message
									code="master.page.style" /></a></li>
					</security:authorize>

					<security:authorize access="isAuthenticated()">
						<security:authentication property="principal.id" var="id" />
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><security:authentication
									property="principal.username" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="actor/edit.do"><spring:message
											code="master.page.actor.edit" /></a></li>
								<li><a href="j_spring_security_logout"><spring:message
											code="master.page.logout" /> </a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><spring:message
									code="master.page.actor.chirps" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="chirp/actor/list.do"><spring:message
											code="master.page.actor.chirps" /></a></li>
								<li><a href="chirp/actor/create.do"><spring:message
											code="master.page.actor.createChirps" /></a></li>
								<li><a href="chirp/actor/myListSubscribe.do"><spring:message
											code="master.page.actor.chirpSubscribe" /></a></li>
								<li><a href="chirp/actor/mylist.do"><spring:message
											code="master.page.actor.mychirps" /></a></li>
							</ul></li>

						<security:authorize access="hasRole('ACADEMY')">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><spring:message
										code="master.page.course" /><span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="course/list.do"><spring:message
												code="master.page.course" /></a></li>
									<li><a href="course/academy/mylist.do"><spring:message
												code="master.page.academy.courses" /></a></li>
								</ul></li>
							<li class="dropdown"><a
								href="tutorial/academy/mylist.do"><spring:message
										code="master.page.academy.tutorials" /> </a></li>
						</security:authorize>

						<li><a href="academy/list.do"><spring:message
									code="master.page.academy" /></a></li>

						<security:authorize access="hasAnyRole('ACADEMY', 'DANCER')">
							<li><a href="style/list.do"><spring:message
										code="master.page.style" /></a></li>
						</security:authorize>

						<security:authorize access="hasRole('ADMINISTRATOR')">
							<li><a href="course/list.do"><spring:message
										code="master.page.course" /></a></li>
							<li><a href="style/administrator/list.do"><spring:message
										code="master.page.style" /> </a></li>
							<li><a href="administrator/dashboard.do"><spring:message
										code="master.page.administrator.dashboard" /> </a></li>
						</security:authorize>



						<security:authorize access="hasRole('DANCER')">
							<li><a href="course/list.do"><spring:message
										code="master.page.course" /></a></li>
							<li class="dropdown"><a href="application/dancer/mylist.do"><spring:message
										code="master.page.academy.applications" /> </a></li>
						</security:authorize>
					</security:authorize>
				</ul>
			</div>
		</div>
	</nav>
	<a href="?language=en"> <img src="images/flag_en.png" />
	</a> <a href="?language=es"> <img src="images/flag_es.png" />
	</a>
</div>


