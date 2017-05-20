<html>
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	
	<script src="bower_components/angular/angular.min.js" type="text/javascript"></script>
	<script src="bower_components/jquery/dist/jquery.min.js" type="text/javascript"></script>
	<script src="bower_components/angular-ui-router/release/angular-ui-router.min.js" type="text/javascript"></script>
	<script src="bower_components/angular-ui-bootstrap/src/modal/modal.js" type="text/javascript"></script>
	<script src="bower_components/angular-sanitize/angular-sanitize.min.js" type="text/javascript"></script>
	<script src="bower_components/angular-animate/angular-animate.min.js" type="text/javascript"></script>
	<script src="bower_components/angular-ui-bootstrap/dist/ui-bootstrap-tpls.js" type="text/javascript"></script>
	
	
	<script src="js/app.js" type="text/javascript"></script>
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0-beta.2/angular-sanitize.js"></script>  
	
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="" href="bower_components/angular-ui-bootstrap/dist/ui-bootstrap-csp.css">



	<!-- <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.js"></script>
    <script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.6.0.js"></script>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">
 -->
</head>

<body id="Body">




<div data-ng-app="toDoApp">
	<div class="container-fluid">
	
		<ui-view></ui-view>
	
	</div>

</div>


</body>


<script src="js/controller/signUpController.js" type="text/javascript"></script>
<script src="js/controller/signInController.js" type="text/javascript"></script>
<script src="js/controller/toDoHomeController.js" type="text/javascript"></script>


<script src="js/service/toDoHomeService.js" type="text/javascript"></script>


<script src="js/validation/signUpValidation.js" type="text/javascript"></script>
<script src="js/validation/signInValidation.js" type="text/javascript"></script>




<link rel="stylesheet" href="css/signUp.css">
<link rel="stylesheet" href="css/signIn.css">
<link rel="stylesheet" href="css/home.css">

</html>
