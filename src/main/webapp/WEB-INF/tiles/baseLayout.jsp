<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<table border="10" cellpadding="2" cellspacing="2" align="left" height="500" width="1350">
    <tr>
        <td height="60" colspan="2">
            <tiles:insertAttribute name="header" />
        </td>
    </tr>
    <tr>
        <td height="250" width="25">
            <tiles:insertAttribute name="menu" />
        </td>
        <td width="350">
            <tiles:insertAttribute name="body" />
        </td>
    </tr>
    <tr>
        <td height="30" colspan="2">
            <tiles:insertAttribute name="footer" />
        </td>
    </tr>
</table>
</body>
</html>
