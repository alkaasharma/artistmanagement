<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<style><%@include file="/WEB-INF/css/login.css"%></style>
</head>
<body>
<header>
</header>
<main>
<h2>Artist management</h2>
<br>
<br>
<form>
  <div class="form-group">
    <label>Username: </label>
    <input type="Username" id="username" required>
  </div>
  <div class="form-group">
    <label>Password: </label>
    <input type="password" id="password" required>
  </div>
  <div class="form-group">
  <button id ="login" type="submit" class="btn btn-success">Login</button>
  <button id ="signup" class="btn btn-success">Signup</button>
  </div>
</form>
</main>
</body>
</html>