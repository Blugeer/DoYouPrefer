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
        
        <title> Mur </title>
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
        
        <style>
            body {
                font: 20px "Montserrat", sans-serif;
                line-height: 1.8;
                color: #f5f6f7;
            }
            p {font-size: 16px;} 
            h3 {color: #474e5d;}
            .login{
                padding-top: 120px;
                padding-bottom: 120px;
            }
            .otheruser{
                padding-top: 320px;
                padding-bottom: 320px;
            }
            .bg-1 { 
                background-color: #1abc9c;
                color: #ffffff;
            }
            .bg-2 { 
                background-color: #474e5d; 
                color: #ffffff;
            }
            .input{
                margin: 10px;
            }
            .navbar {
                padding-top: 15px;
                padding-bottom: 15px;
                border: 0;
                border-radius: 0;
                margin-bottom: 0;
                font-size: 12px;
                letter-spacing: 5px;
            }
            .navbar-nav li a:hover {
                color: #1abc9c !important;
            }
            .filltext{
                color: #555555;
            }       
        </style>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span> 
                    </button>
                    <h3> ${wallMessage} </h3>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <c:if test="${sessionScope.login == user}">
                                <FORM method="POST" ACTION="createGame.htm">
                                    <P> <INPUT type=submit value="Créer une question" class="btn btn-default btn-lg input"> </P>
                                </FORM>
                            </c:if>
                            <c:if test="${sessionScope.login != user}">
                                <FORM method="POST" ACTION="wall.htm">
                                    <P> <INPUT type=submit value="Mur" class="btn btn-default btn-lg input"> </P>
                                </FORM>
                            </c:if>
                        </li>
                        <li>
                            <FORM method="POST" ACTION="index.htm">
                                <P> <INPUT type=submit value="Déconnexion" class="btn btn-default btn-lg input"> </P>  
                            </FORM>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
                
        <c:if test="${sessionScope.login == user}">
            <div class="container-fluid bg-1 text-center login">     
                <h4>Liste d'amis</h4>

                <c:forEach items="${amis}" var="friend">
                    <p>
                        <a href='wall.htm?user=${friend}'> ${friend} </a> <br/>
                    </p>
               </c:forEach> 

                <FORM method="POST" ACTION="wall.htm">
                    <P> 
                        Login de l'ami : <INPUT Type=text Name=loginAmi class="filltext">
                        <INPUT type=submit value="Ajouter l'ami" class="btn btn-default btn-lg input"> 
                    </P>  
                </FORM>
            </div>
            
            <div class="container-fluid bg-2 text-center login">
                <h4>Liste de questions</h4>

                <c:forEach items="${questions}" var="current" varStatus="ind">
                    <FORM method="POST" ACTION="wall.htm">
                        <P> 
                            ${current.toString()}
                            <c:set var="foundUser" value="false"/>
                            <c:forEach items="${current.getReponses()}" var="reponses">
                                <c:if test="${reponses.getPersonne().getLogin() == user}">
                                    <c:set var="foundUser" value="true"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${foundUser eq 'false'}">
                                <INPUT type="hidden" name="question" value=${ind.index}>
                                <INPUT type=submit name ="answer1" value="Choix 1" class="btn btn-default btn-lg input"> 
                                <INPUT type=submit name ="answer2" value="Choix 2" class="btn btn-default btn-lg input"> 
                                <br/>
                            </c:if>
                            <c:if test="${foundUser eq 'true'}">
                                Vous avez déjà répondu à cette question <br/>
                            </c:if>
                        </P>  
                    </FORM>   
                </c:forEach>
            </div>
        </c:if>
        
        <c:if test="${sessionScope.login != user}">
            <div class="container-fluid bg-2 text-center otheruser">
                <h4>Liste de questions</h4>

                <c:forEach items="${questions}" var="current" varStatus="ind">
                    <FORM method="POST" ACTION="wall.htm">
                        <P> 
                            ${current.toString()}
                            <c:set var="foundUser" value="false"/>
                            <c:forEach items="${current.getReponses()}" var="reponses">
                                <c:if test="${reponses.getPersonne().getLogin() == user}">
                                    <c:set var="foundUser" value="true"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${foundUser eq false}">
                                <INPUT type="hidden" name="question" value=${ind.index}>
                                <INPUT type=submit name ="answer1" value="Choix 1" class="btn btn-default btn-lg input"> 
                                <INPUT type=submit name ="answer2" value="Choix 2" class="btn btn-default btn-lg input"> 
                                <br/>
                            </c:if>
                            <c:if test="${foundUser eq true}">
                                Vous avez déjà répondu à cette question <br/>
                            </c:if>
                        </P>  
                    </FORM>   
                </c:forEach>
            </div>
        </c:if>
                   
    </body>
</html>
