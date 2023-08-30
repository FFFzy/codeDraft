<%@ include file="../common/IncludeTop.jsp"%>

<div id="Content">
	<c:if test="${sessionScope.messageSignOn != null}">
		<ul class="messages">
			<li>${sessionScope.messageSignOn}</li>
		</ul>
	</c:if>

<div id="Catalog">
	<form action="signOn" method="post">
		<p>Please enter your username and password.</p>
		<p>
			Username:<input type="text" name="username" value="j2ee"/><br />
		    Password:<input type="password" name="password" value="j2ee"/><br />
			VerificationCode:<input type="text" name="vCode" size="5" maxlength="4"/>
			<a href="signOn"><img border="0" src="verificationCode" name="checkcode"></a>
		</p>
		<input type="submit" name="signon" value="Login" />
	</form>
		Need a user name and password?
	    <!--newAccountForm-->
	    <a href="newAccountForm">Register Now!</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>

