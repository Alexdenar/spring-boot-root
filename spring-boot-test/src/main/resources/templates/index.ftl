<!DOCTYPE html>
<html lang="en">
<head>
    <title>SpringBoot + Freemarker</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<h1>Hello ${name}${age}${sex}</h1><br>
<p>${birth?string("yyyy-MM-dd HH:mm:ss.sss")}</p>
</body>
</html>