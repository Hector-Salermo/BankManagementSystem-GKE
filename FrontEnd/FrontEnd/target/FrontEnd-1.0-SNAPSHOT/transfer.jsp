<%-- 
    Document   : userinfo
    Created on : Feb 15, 2021, 8:05:46 PM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.mycompany.frontend.FrontEnd"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Info</title>
    </head>
    <body>
    </body>
    

<br>
<form action="FrontEnd" method="post" enctype="application/x-www-form-urlencoded">


<center></center>

<table border="2" align="center" cellpadding="5" cellspacing="5">

<tr>
<th> Account Name </th>    
<th> UID </th>
<th> Account Number </th>
<th> Chequing Balance </th>
<th> Savings Balance </th>

</tr>

<tr>
<td> <input type="text" name="AccountName" value="${AccountName}" ></td>
<td> <input type="text" name="UID" value="${UID}" ></td>
<td> <input type="text" name="accID" value="${AccID}" ></td>
<td> <input type="text" name="balanceChequing" value="${balanceChequing}" ></td>
<td> <input type="text" name="balanceSavings"  value="${balanceSavings}" ></td>
<td> <input type="submit" name="pageName" value="Transfer From Chequing"></td>
<td> <input type="text" name="amountChequing"  size="30"> </td>
<td> <input type="submit"  name="pageName" value="Transfer From Savings" ></td>
<td> <input type="text" name="amountSavings" size="30" > </td>

</tr>
</table>
</form>
</body>
</html>