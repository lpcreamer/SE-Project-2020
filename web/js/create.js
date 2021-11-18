
function validateSignup() {
    let url = "./php/create.php";
    let form_email = document.getElementById("email").value;
    let form_password = document.getElementById("password").value;
    let form_username = document.getElementById("username").value;
    let form_phone_number = document.getElementById("phone_number").value;
    let form_name = document.getElementById("name").value
    let form_pet_name = document.getElementById("pet_name").value
    let form_pet_type = document.getElementById("pet_type").value
    let form_pet_breed = document.getElementById("pet_breed").value
    let form_pet_weight = document.getElementById("pet_weight").value
    let form_pet_care_instructions = document.getElementById("pet_care_instructions").value
    let state = document.getElementById("state").value
    let street = document.getElementById("street").value
    let city = document.getElementById("city").value
    let zip = document.getElementById("zip").value



    $.ajax({
        method: 'POST',
        url: url,
        data: {
          email: form_email,
          password: form_password,
          username: form_username,
          name: form_name,
          phone_number: form_phone_number,
          pet_name: form_pet_name,
          pet_type: form_pet_type,
          pet_breed: form_pet_breed,
          pet_weight: form_pet_weight,
          pet_care_instructions: form_pet_care_instructions,
          state: state,
          street: street,
          city: city,
          zip: zip
        },
        success: function (response)
        {
            console.log(response);
            if (response == 0)
                window.location.href = "./index.html";
            else if (response == 2)
                document.getElementById('error_code').removeAttribute("hidden");
            else {
                document.getElementById('error_code').removeAttribute("hidden");
                document.getElementById('error_code').innerText = 'Internal Server Error';
            }
        },
        error: function(error) {
            console.log(error);
            document.getElementById('error_code').removeAttribute("hidden");
            document.getElementById('error_code').innerText = "Internal Server Error";
        }
    });
}


function getter() {
  $.get("./php/get.php", function success(response) {
    console.log(response);
  })
}
