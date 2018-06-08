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

<spring:message code="tutorial.video" var="videosHeader" />


<h1>
	<jstl:out value="${tutorial.title}" />
</h1>




<h2>
	<jstl:out value="${videosHeader}" />

</h2>
<input type="text" id="video" value="${tutorial.video}"
	style="display: none">
<br>

<div id="player"></div>

<script>
	// 2. This code loads the IFrame Player API code asynchronously.
	var tag = document.createElement('script');

	tag.src = "https://www.youtube.com/iframe_api";
	var firstScriptTag = document.getElementsByTagName('script')[0];
	firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

	// 3. This function creates an <iframe> (and YouTube player)
	//    after the API code downloads.
	var player;

	var video = document.getElementById("video").value;
	var filter = video.replace("https://www.youtube.com/watch?v=", "");

	function onYouTubeIframeAPIReady() {
		player = new YT.Player('player', {
			height : '360',
			width : '640',
			videoId : filter,
			events : {
				'onReady' : onPlayerReady
			}
		});
	}

	// 4. The API will call this function when the video player is ready.
	function onPlayerReady(event) {
		event.target.playVideo();
	}

	// 5. The API calls this function when the player's state changes.
	//    The function indicates that when playing a video (state=1),
	//    the player should play for six seconds and then stop.
	var done = false;
	function onPlayerStateChange(event) {
		if (event.data == YT.PlayerState.PLAYING && !done) {
			setTimeout(stopVideo, 12000);
			done = true;
		}
	}
	function stopVideo() {
		player.stopVideo();
	}
</script>



<display:table name="tutorial.description" id="row"
	class="table table-over">

</display:table>

<div class="btn-group btn-group-xs" role="group" aria-label="label">
	<security:authorize access="permitAll() and !hasRole('ACADEMY')">

		<button onclick="javascript:location.href='tutorial/list.do'"
			type="button" class="btn btn-default">
			<spring:message code="tutorial.view.return" />
		</button>

	</security:authorize>

	<security:authorize access="hasRole('ACADEMY')">
		<security:authentication property="principal.id" var="id" />
		<button
			onclick="javascript:location.href='tutorial/academy/mylist.do'"
			type="button" class="btn btn-default">
			<spring:message code="tutorial.view.return" />
		</button>

	</security:authorize>
</div>



