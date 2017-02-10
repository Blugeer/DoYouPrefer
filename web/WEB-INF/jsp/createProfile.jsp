<%-- 
    Document   : createProfil
    Created on : 13 janv. 2017, 15:31:13
    Author     : Jonathan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Création de profil </title>
        
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
            .container-fluid {
                padding-top: 65px;
                padding-bottom: 65px;
            }
            .bg-1 { 
                background-color: #1abc9c; /* Green */
                color: #ffffff;
            }
            .bg-2 { 
                background-color: #474e5d; /* Dark Blue */
                color: #555555;
            }
            input{
                color : #555555;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid bg-1 text-center">
        <h1> Créez votre profil </h1>
        <h2>Veuillez remplir les champs suivants : </h2>
        </div>
        <FORM method="POST" ACTION="wall.htm">
           <div class="container-fluid bg-1 text-center">
           <P> Nom : <INPUT Type=text Name=nom> </P>
           <P> Prenom : <INPUT Type=text Name=prenom> </P>
           <P> Login : <INPUT Type=text Name=login> </P>
	   <P> Mot de passe : <INPUT Type=password Name=mdp> </P>
           <P> Adresse mail : <INPUT Type=text Name=mail> </P>
           </div>
           <div class="container-fluid bg-2 text-center">
	   <P> <INPUT Type=submit VALUE="OK" class="btn btn-default btn-lg"> </P>
           </div>
	</FORM>  
    </body>
</html>
