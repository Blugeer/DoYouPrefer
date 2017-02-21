<%-- 
    Document   : wall
    Created on : 13 janv. 2017, 15:43:00
    Author     : Jonathan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            body{font-family:"Junction";}
            .co1{
                color:#FFFFFF;
                text-align:center;
            }
            .co2{
                text-align:right;
                padding-right:80px;
            }
            /* The side navigation menu */
            .sidenav {
                height: 100%; /* 100% Full-height */
                width: 0; /* 0 width - change this with JavaScript */
                position: fixed; /* Stay in place */
                z-index: 1; /* Stay on top */
                top: 0;
                left: 0;
                background-color: #111; /* Black*/
                overflow-x: hidden; /* Disable horizontal scroll */
                padding-top: 60px; /* Place content 60px from the top */
                transition: 0.5s; /* 0.5 second transition effect to slide in the sidenav */
            }

            /* The navigation menu links */
            .sidenav a {
                padding: 8px 8px 8px 32px;
                text-decoration: none;
                font-size: 25px;
                color: #818181;
                display: block;
                transition: 0.3s
            }

            /* When you mouse over the navigation links, change their color */
            .sidenav a:hover, .offcanvas a:focus{
                color: #f1f1f1;
            }

            /* Position and style the close button (top right corner) */
            .sidenav .closebtn {
                position: absolute;
                top: 0;
                right: 25px;
                font-size: 36px;
                margin-left: 50px;
            }

            /* Style page content - use this if you want to push the page content to the right when you open the side navigation */
            #main {
                transition: margin-left .5s;
                padding: 20px;
            }

            /* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
            @media screen and (max-height: 450px) {
                .sidenav {padding-top: 15px;}
                .sidenav a {font-size: 18px;}
            }
            button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
            }
            .button {
                border-radius: 4px;
                background-color: #4CAF50;
                border: none;
                color: #FFFFFF;
                text-align: center;
                font-size: 12px;
                padding: 20px;
                width: 90%;
                transition: all 0.5s;
                cursor: pointer;
                margin: 5px;
            }
            .notif{
                color: #FFFFFF;
            }
            .h3{
                padding-left: 100px;
                text-align: center;
            }
            .navbar-default{
                background-color:#2B3033;
            }
            .imgcontainer {
                text-align: center;
                margin: 24px 0 12px 0;
            }
            img.avatar {
                width: 10%;
                border-radius: 50%;
            }
            .friends{
                width: 20%;
            }
            .avatar2{
                width : 20%;
                padding-right : 50px;
            }
            [class*="col-"] {
                float: none;
                display: table-cell;
                vertical-align: top;
            }
            .col-sm-8{
                border-right: 1px solid #ccc;
            }
            .col-sm-4{
                background-color : #f1f1f1;
            }
            input[type=text], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }
            .friendlink{
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }
            
        </style>
    </head>
    <body>
        <div id="mySidenav" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <c:forEach items="${notifs}" var="current">
                <p class="notif">
                    ${current} <br/>
                </p>
            </c:forEach>
            <FORM method="POST" ACTION="wall.htm">
                <INPUT class="button btn btn-info" type="submit" name="deleteNotif" value="Supprimer les notifications">
            </FORM>
        </div>
        <nav class="navbar navbar-default">
            <div class="co1">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span> 
                    </button>
                    <h3 class="h3"> ${wallMessage} </h3>
                </div>
            </div>
            <div class="co2">
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <button class="button btn btn-info" onclick="openNav()"> Notifications </button>
                        </li>
                        <li>
                            <c:if test="${sessionScope.login == user}">
                                <FORM method="POST" ACTION="createGame.htm">
                                    <P> <INPUT class="button btn btn-info" type=submit value="Créer une question" class="btn btn-default btn-lg input"> </P>
                                </FORM>
                            </c:if>
                            <c:if test="${sessionScope.login != user}">
                                <FORM method="POST" ACTION="wall.htm">
                                    <P> <INPUT class="button btn btn-info" type=submit value="Mur" class="btn btn-default btn-lg input"> </P>
                                </FORM>
                            </c:if>
                        </li>
                        <li>
                            <FORM method="POST" ACTION="index.htm">
                                <P> <INPUT class="button btn btn-info" type=submit value="Déconnexion" class="btn btn-default btn-lg input"> </P>  
                            </FORM>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <c:if test="${sessionScope.login == user}">
                <div class="col-sm-8">
                    <div class="container-fluid bg-2 text-center login">

                        <h3> Vos questions </h3>
                        
                        <div class="imgcontainer">
                          <img src="resources/images/icon-1243668_960_720.png" class="avatar">
                        </div>

                        <c:forEach items="${questions}" var="current" varStatus="ind">
                            <FORM method="POST" ACTION="wall.htm">

                                <c:if test="${not questionsAnswered[ind.index]}">
                                    <P> 
                                        ${current}
                                        <INPUT type="hidden" name="question" value=${ind.index}>
                                        <INPUT type=submit name ="answer1" value="Choix 1" class="btn btn-default btn-lg input"> 
                                        <INPUT type=submit name ="answer2" value="Choix 2" class="btn btn-default btn-lg input"> 
                                        <br/>
                                    </P>
                                </c:if>
                                <c:if test="${questionsAnswered[ind.index]}">
                                    <P> 
                                        ${current} Vous avez déjà répondu à cette question
                                        <br/>
                                    </P>
                                </c:if>
                                <div class="progress">
                                    <c:if test="${questionsPercentages[ind.index] == -1}">
                                        <div class="progress-bar progress-bar-success" role="progressbar" style="width:100%">
                                            Aucune réponse donnée
                                        </div>
                                    </c:if>
                                    <c:if test="${questionsPercentages[ind.index] > -1}"> 
                                        <div class="progress-bar progress-bar-success" role="progressbar" style="width:${questionsPercentages[ind.index]}%">
                                          <fmt:formatNumber value="${questionsPercentages[ind.index]}" maxFractionDigits="2"/>%
                                        </div>
                                        <div class="progress-bar progress-bar-warning" role="progressbar" style="width:${100-questionsPercentages[ind.index]}%">
                                          <fmt:formatNumber value="${100-questionsPercentages[ind.index]}" maxFractionDigits="2"/>%
                                        </div>
                                    </c:if>
                                </div>
                            </FORM>   
                        </c:forEach>
                    </div>
                </div>


                <div class="col-sm-4">
                    <div class="container-fluid bg-1 login">  

                        <div class="imgcontainer">
                          <h3> Vos amis </h3>
                          <img src="resources/images/unnamed.png" class="friends">
                        </div>

                        
                        <c:forEach items="${amis}" var="friend">
                            <p class="friendlink">
                                <img src="resources/images/img_avatar.png" class="avatar2">
                                <a href='wall.htm?user=${friend}'> ${friend} </a> <br/>
                            </p>
                       </c:forEach> 
                       <br/>

                        <FORM method="POST" ACTION="wall.htm">
                            <label><b>Login de l'ami</b></label>
                            <INPUT Type=text Name=loginAmi placeholder="Pseudo de l'ami : ">
                            <button class="btn" type="submit"> Ajouter l'ami </button> 
                        </FORM>
                    </div>
                </div>
        </c:if>
        
        <c:if test="${sessionScope.login != user}">
            <div class="container-fluid bg-2 text-center otheruser">
                <h4>Liste de questions</h4>

                <c:forEach items="${questions}" var="current" varStatus="ind">
                    <FORM method="POST" ACTION="wall.htm">
                        <P> 
                            ${current}
                        </P>  
                    </FORM>   
                </c:forEach>
            </div>
        </c:if>
                
        <script>
            /* Set the width of the side navigation to 250px */
            function openNav() {
                document.getElementById("mySidenav").style.width = "250px";
            }

            /* Set the width of the side navigation to 0 */
            function closeNav() {
                document.getElementById("mySidenav").style.width = "0";
            }
        </script>
                   
    </body>
</html>
