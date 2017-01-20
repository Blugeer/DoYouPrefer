<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>

    <body>
       <H1>Page de connexion: </H1>
       <FORM method="POST" ACTION="wall.htm">
           <P> Entrez votre login : <INPUT Type=text Name=login> </P>
	   <P> Entrez votre mot de passe : <INPUT Type=text Name=mdp></P>
	   <P> <INPUT Type=submit VALUE="OK"> </P>
	</FORM>
        <FORM method="POST" ACTION="createProfile.htm">
            <P> <INPUT type=submit value="Créer un compte"> </P>  
        </FORM>
    </body>
</html>