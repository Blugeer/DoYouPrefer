<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Connexion</title>  

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
                padding-top: 82px;
                padding-bottom: 82px;
            }
            .bg-1 { 
                background-color: #1abc9c; /* Green */
                color: #ffffff;
            }
            .bg-2 { 
                background-color: #474e5d; /* Dark Blue */
                color: #555555;
            }
            .filltext{
                color: #555555;
            }
        </style>

    </head>

    <body>
        <div class="container-fluid bg-1 text-center">
            <h2> Connexion </h2> <br/>
            <FORM method="POST" ACTION="wall.htm">
                <P> Entrez votre login : <INPUT Type=text Name=login class="filltext"> </P> <br/>
                <P> Entrez votre mot de passe : <INPUT Type=password Name=mdp class="filltext"></P> <br/>
                <P> <INPUT Type=submit VALUE="OK" class="btn btn-default btn-lg"> </P> <br/>
            </FORM>
        </div>
        <div class="container-fluid bg-2 text-center">
            <FORM method="POST" ACTION="createProfile.htm">
                <P> <INPUT type=submit value="Créer un compte" class="btn btn-default btn-lg"> </P>  
            </FORM> 
            <br/>   
        </div>
	
    </body>
    
</html>