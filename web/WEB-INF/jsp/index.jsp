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
            input[type=text], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
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

            .imgcontainer {
                text-align: center;
                margin: 24px 0 12px 0;
            }
            img.avatar {
                width: 10%;
                border-radius: 50%;
            }
            .container-fluid{
                padding: 0px;
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
            <div class="imgcontainer">
              <img src="resources/images/img_avatar2.png" class="avatar">
            </div>

            <div class="container">
              <label><b>Login</b></label>
              <input type="text" placeholder="Entrez votre login :" name="login" required>

              <label><b>Mot de passe</b></label>
              <input type="password" placeholder="Entrez votre mot de passe : " name="mdp" required>

              <button class="btn" type="submit">Se connecter</button>
            </div>
          </form>
        </div>
    </body>
    
</html>