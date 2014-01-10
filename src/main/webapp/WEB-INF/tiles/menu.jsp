<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<a href="<c:url value="test.htm"/>" >test</a> --%>                <%-- Fix --%>

<!-- Sidebar -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index.html">SKS</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">

        <ul class="nav navbar-nav navbar-right navbar-user">
            <li class="dropdown user-dropdown">
                <a href="<c:url value=""/>" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="<c:url value="minside.htm"/>" >Minside</a></li>
                    <%--
                    <li><<a href="<c:url value="minside.htm"/><i class="fa fa-user"></i> Min side</a></li>
                    --%>
                    <li><a href="<c:url value="endrePassord.htm"/>"><i class="fa fa-power-off"></i>Endre Passord</a></li>
                    <li><a href="<c:url value=""/>"><i class="fa fa-power-off"></i> Logg ut</a></li>
                    <li class="divider"></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav side-nav">
            <li><a href="<c:url value="error.htm"/>">Error</a></li>
            <li><a href="<c:url value="login.htm"/>">Logg inn</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-square-o-down"></i>
                    Emner <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="emne.html">Emne1</a></li>
                    <li><a href="#">Another Item</a></li>
                    <li><a href="#">Third Item</a></li>
                    <li><a href="#">Last Item</a></li>
                </ul>
            </li>

            <li>Administrator:</li>
            <li><a href="<c:url value="adminBrukere.htm"/>">Administrer brukere</a></li>

            <li>Faglærer:</li>
            <li><a href="<c:url value="ovingsOpplegg.htm"/>">Øvingsopplegget</a></li>
            <li><a href="<c:url value="adminFag.htm"/>">Administrer fag</a></li>

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-square-o-down"></i>
                    Administrer studenter <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="nyStudent.htm">Ny student</a></li>
                    <li><a href="nyStudent.htm">Endre student</a></li>
                </ul>
            </li>

            <li>Student:</li>
            <li><a href="<c:url value="settIKo.htm"/>">Sett i kø</a></li>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>