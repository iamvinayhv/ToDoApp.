<html>
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<script src="bower_components/angular/angular.min.js" type="text/javascript"></script>
	<script src="bower_components/jquery/dist/jquery.min.js" type="text/javascript"></script>
	<script src="bower_components/angular-ui-router/release/angular-ui-router.min.js" type="text/javascript"></script>

	<script src="js/app.js" type="text/javascript"></script>

</head>

<body>




<div data-ng-app="toDoApp">

	<ui-view></ui-view>

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

</html>
