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
            body{font-family:"Junction";}
            .jumbotron{background-color:#2B3033;}
            .col-sm-4{
                color:#FFFFFF;
                text-align:center;
            }
            .col-sm-8{
                text-align:right;
                padding-right:80px;
            }
            .button {
                border-radius: 4px;
                background-color: #4CAF50;
                border: none;
                color: #FFFFFF;
                text-align: center;
                font-size: 28px;
                padding: 20px;
                width: 300px;
                transition: all 0.5s;
                cursor: pointer;
                margin: 5px;
            }
            .container-fluid{
                padding: 0px;
            }
            /* Full-width input fields */
            input[type=text], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            /* Set a style for all buttons */
            button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
            }
            /* Float cancel and signup buttons and add an equal width */
            .cancelbtn,.signupbtn {
                float: left;
                width: 100%;
            }

            /* Add padding to container elements */
            .container {
                padding: 16px;
            }

            /* Clear floats */
            .clearfix::after {
                content: "";
                clear: both;
                display: table;
            }

            /* Change styles for cancel button and signup button on extra small screens */
            @media screen and (max-width: 300px) {
                .cancelbtn, .signupbtn {
                   width: 100%;
                }
            }
            
            .colbutton1{
                text-align:right;
                padding:0px;
                margin:0px;
            }
            .colbutton2{
                text-align:left;
                padding:0px;
                margin:0px;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="jumbotron">
              <div class="row">
                <div class="col-sm-4">
                    <h2>Tu préfères ?</h2>
                    <p> Le jeu </p>
                </div>
                <div class="col-sm-4 colbutton1">
                    <FORM method="POST" ACTION="createProfile.htm">
                        <input type=submit class="button btn btn-info" value="Créer un compte"/>
                    </FORM>
                </div>
                <div class="col-sm-4 colbutton2">
                    <FORM method="POST" ACTION="index.htm">
                        <input type=submit class="button btn btn-info" value="Connexion"/> 
                    </FORM>  
                </div>
              </div>
            </div>
        </div>
        <div>
            <form method="POST" action="wall.htm">
                <div class="container">
                  <label><b>Nom</b></label>
                  <input type="text" placeholder="Entrez votre nom : " name="nom" required>
                  
                  <label><b>Prenom</b></label>
                  <input type="text" placeholder="Entrez votre prenom : " name="prenom" required>
                  
                  <label><b>Login</b></label>
                  <input type="text" placeholder="Entrez votre login : " name="login" required>
                  
                  <label><b>Mot de passe</b></label>
                  <input type="password" placeholder="Entrez votre mot de passe : " name="mdp" required>
                  
                  <label><b>Email</b></label>
                  <input type="text" placeholder="Entrez votre addresse e-mail" name="mail" required>

                  <button type="submit" class="signupbtn">Valider l'inscription</button>
                </div>
            </form>
        </div>
    </body>
</html>
