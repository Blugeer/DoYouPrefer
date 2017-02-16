<%-- 
    Document   : createGame
    Created on : 13 janv. 2017, 15:55:12
    Author     : Jonathan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Nouvelle question </title>
        
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
            h3 {color: #474e5d;}
            p {font-size: 16px;}
            .container-fluid {
                padding-top: 130px;
                padding-bottom: 130px;
            }
            .bg-1 { 
                background-color: #1abc9c; /* Green */
                color: #ffffff;
            }
            .bg-2 { 
                background-color: #474e5d; /* Dark Blue */
                color: #555555;
            }
            .form{
                color : #ffffff;
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
                    <h3> Créez votre question </h3>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <FORM method="POST" ACTION="wall.htm">
                                <P> <INPUT type=submit value="Mur" class="btn btn-default btn-lg input"> </P>  
                            </FORM>
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
                                
        <div class="container-fluid bg-1 text-center">
            <h1>Créez votre "Tu préfères"</h1>
        </div>
        <div class="container-fluid bg-2 text-center">
            <FORM method="POST" ACTION="createGame.htm" class="form">
                <h4> Ajouter des amis a la future question</h4>
                <P> 
                    Login de l'ami : <INPUT Type=text Name=loginParticipant class="filltext"> 
                    <INPUT Type=submit VALUE="Ajouter" class="btn btn-default btn-lg"> 
                </P>
                <h4> Liste des participants </h4>
                <c:forEach items="${participants}" var="invite">
                    <p>
                        ${invite}
                    </p>
                </c:forEach>
            </FORM> 
            <FORM method="POST" ACTION="wall.htm" class="form">
                <P> Tu préfères :</P>
                <P> <INPUT Type=text Name=choix1 class="filltext"> ou <INPUT Type=text Name=choix2 class="filltext"> ? </P>
                <P> <INPUT Type=submit VALUE="OK" class="btn btn-default btn-lg"> </P>
            </FORM> 
        </div>
    </body>
</html>
