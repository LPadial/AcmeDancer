<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<acme:acme_view entity="${style}" skip_fields="courses,videos,pictures">
 <tr>
  <td><spring:message code="style.pictures"/></td>
  <td>
   <table class="table">
    <jstl:forEach var="e" items="${style.pictures }">
     <tr>
      <td><img src="${e }" style="max-width: 120px;max-height: 120px;"></td>
     </tr>
    </jstl:forEach>
   </table>
  </td>
 </tr>
 <tr>
  <td><spring:message code="style.videos"/></td>
  <td>
   <acme:youtube_view url="${style.videos }"></acme:youtube_view>
  </td>
 </tr>
</acme:acme_view>