<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Login Template</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="<c:url value="/resources/css/signin.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
</head>
<body>

<div class="container">

    <form class="form-signin" role="form">
        <h2 class="form-signin-heading">Logg inn</h2>
        <input type="text" class="form-control" placeholder="Email" required autofocus>
        <input type="password" class="form-control" placeholder="Passord" required>
        <label class="checkbox">
            <input type="checkbox" value="husk"> Husk email
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Logg inn</button>
    </form>

</div>
</body>
</html>