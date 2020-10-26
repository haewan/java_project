<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Application Level Log4j Demo In JBoss EAP 6</title>
</head>
<body>

      <form action="Log4jTestServlet" method="POST">
           Choose Log Level : 
                           <select name="logLevel">
                              <option>TRACE</option>
                              <option>DEBUG</option>
                              <option>WARN</option>
                              <option selected="true">INFO</option>
                           </select>
                           <BR>
           Enter Mesage to be logged: <input type="textarea" name="logMessage"/> <BR>
           <input type="submit" name="submit" value="log the message"/> 
      </form>
      
</body>
</html>
