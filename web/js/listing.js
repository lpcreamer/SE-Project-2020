function placeListing() {
    let url = "./php/createListing.php";
    let date = document.getElementById("date").value
    let start_time = document.getElementById("start_time").value;
    let duration = document.getElementById("duration").value
    let description = document.getElementById("description").value

    $.ajax({
        method: 'POST',
        url: url,
        data: {
          date: date,
          start_time: start_time,
          duration: duration,
          description: description,
          username: getCookie('username')
        },
        success: function (response)
        {
            console.log(response);
            window.location.href = "./index.html";
        },
        error: function(error) {
            console.log(error);
            document.getElementById('error_code').removeAttribute("hidden");
            document.getElementById('error_code').innerText = "Internal Server Error";
        }
    });
}

function cancelListing(listing) {
  let url = "./php/cancelListing.php";

  $.ajax({
      method: 'POST',
      url: url,
      data: {
        id: listing
      },
      success: function (response)
      {
        console.log(response);
      },
      error: function(error) {
          console.log(error);
          alert(error);
      }
  });
}

function getListings(completion) {
  let url = "./php/getListingsByOwner.php";

  $.ajax({
      method: 'GET',
      url: url,
      data: {
        username: getCookie('username')
      },
      success: function (response)
      {
        completion(JSON.parse(response));
      },
      error: function(error) {
          console.log(error);
          alert(error);
      }
  });
  return null;
}
