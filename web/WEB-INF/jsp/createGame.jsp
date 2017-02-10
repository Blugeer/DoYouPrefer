<%-- 
    Document   : createGame
    Created on : 13 janv. 2017, 15:55:12
    Author     : Jonathan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nouvelle question</title>
    </head>
    <body>
        <h1>Créez votre "Tu préfères"</h1>
        <FORM method="POST" ACTION="wall.htm">
            <P> Tu préfères :</P>
            <P> <INPUT Type=text Name=choix1> ou <INPUT Type=text Name=choix2> ? </P>
            <P> <INPUT Type=submit VALUE="OK"> </P>
	</FORM> 
        
        <h4>Invitez vos amis</h4> 
        <c:forEach items="${amis}" var="friend">
            <p>
                <a href='wall.htm'> ${friend} </a> <br/>
            </p>
        </c:forEach>
    </body>
</html>
