window.onload = function() {
    if(getCookie("username") == null) {
      deleteCookie("username");
    }
    window.location.href = "./login.html";
}
