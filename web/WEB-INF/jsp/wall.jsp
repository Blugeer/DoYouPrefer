<%-- 
    Document   : wall
    Created on : 13 janv. 2017, 15:43:00
    Author     : Jonathan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wall</title>
    </head>
    <body>
        <h2>${wallMessage}</h2>
        
        <c:forEach items="${messages}" var="current">
            <p>
                ${current} <br/>
            </p>
        </c:forEach>
        
        <FORM method="POST" ACTION="createGame.htm">
            <P> <INPUT type=submit value="Créer une question"> </P>
        </FORM>
        <FORM method="POST" ACTION="index.htm">
            <P> <INPUT type=submit value="Déconnexion"> </P>  
        </FORM>
    </body>
</html>