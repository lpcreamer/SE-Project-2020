window.onload = function() {
    if(getCookie("username") == null) {
        window.location.href = "./login.html";
    } else {
        validateLogin(getCookie("username"), getCookie("password"));
        getUser();
    }
}

function validateLogin(user, pw)
{
    let url = "./php/login.php";
    $.post(url, { username: user, password: pw }, function (response)
    {
        if (response != 0)
            window.location.href = 'login.html';
    })
}
