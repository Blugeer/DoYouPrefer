<%-- 
    Document   : createProfil
    Created on : 13 janv. 2017, 15:31:13
    Author     : Jonathan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Création de profil </title>
    </head>
    <body>
        <h1> Créez votre profil </h1>
        <h2>Veuillez remplir les champs suivants : </h2>
        <FORM method="POST" ACTION="wall.htm">
           <P> Nom : <INPUT Type=text Name=nom> </P>
           <P> Prenom : <INPUT Type=text Name=prenom> </P>
           <P> Login : <INPUT Type=text Name=login> </P>
	   <P> Mot de passe : <INPUT Type=text Name=mdp> </P>
           <P> Adresse mail : <INPUT Type=text Name=mail> </P>
	   <P> <INPUT Type=submit VALUE="OK"> </P>
	</FORM>  
    </body>
</html>
