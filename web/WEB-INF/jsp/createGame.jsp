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
            .centertext{
                text-align : center;
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
                    <h3 class="h3"> Créez votre question </h3>
                </div>
            </div>
            <div class="co2">
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <button class="button btn btn-info" onclick="openNav()"> Notifications </button>
                        </li>
                        <li>
                            <FORM method="POST" ACTION="wall.htm">
                                <P> <INPUT class="button btn btn-info" type=submit value="Mur" class="btn btn-default btn-lg input"> </P>
                            </FORM>
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
        
        <div class="col-sm-8">
            <FORM method="POST" ACTION="wall.htm" class="form">
                <label><b>Login de l'ami</b></label>
                <p class="centertext"> Tu préfères <INPUT Type=text Name=choix1 class="filltext"> ou <INPUT Type=text Name=choix2 class="filltext"> ? </p>
                <button class="btn" type="submit"> Valider </button>
            </FORM> 
        </div>
        
        <div class="col-sm-4">
                    <div class="container-fluid bg-1 login">  

                        <div class="imgcontainer">
                          <h3> Les participants </h3>
                          <img src="resources/images/unnamed.png" class="friends">
                        </div>

                        
                        <c:forEach items="${participants}" var="invite">
                            <p>
                                ${invite}
                            </p>
                        </c:forEach>
                       <br/>

                        <FORM method="POST" ACTION="createGame.htm">
                            <label><b>Login de l'ami</b></label>
                            <INPUT Type=text Name=loginParticipant placeholder="Pseudo de l'ami : ">
                            <button class="btn" type="submit"> Ajouter </button> 
                        </FORM>
                    </div>
        </div> 
                                
        <%-- <div class="container-fluid bg-2 text-center">
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
        </div> --%>
        
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
