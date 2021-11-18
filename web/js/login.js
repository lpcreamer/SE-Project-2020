function validateLogin()
{
    let url = "./php/login.php";
    let form_username = document.getElementById("username").value;
    let form_password = document.getElementById("password").value;
    $.ajax({
        method: "POST",
        url: url,
        data: { username: form_username, password: form_password },
        success: function (response) {
            console.log(response);
            if (response == 0)
                window.location.href = "./index.html";
            else if (response == 1) {
              document.getElementById('error_code').removeAttribute("hidden");
              document.getElementById('error_code').innerText = "Invalid Password";
            } else if (response == 2) {
              document.getElementById('error_code').removeAttribute("hidden");
              document.getElementById('error_code').innerText = "Username not found in system";
            }
            else
                alert("UNEXPECTED ERROR CODE WHILE LOGGIN IN");
        }
    })
}

function getter() {
  $.get("./php/get.php", function success(response) {
    console.log(response);
  })
}
