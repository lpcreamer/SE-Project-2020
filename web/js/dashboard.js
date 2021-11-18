window.onload = function() {
    if(getCookie("username") == null) {
        window.location.href = "./login.html";
    } else {
        validateLogin(getCookie("username"), getCookie("password"));
        getListings((listings) => {
          console.log(listings);
          let table = document.getElementById('listings_body');
          let history = document.getElementById('history_body');
          for(i in listings) {
            let row = document.createElement('tr');
            let dt = document.createElement('td');
            dt.innerText = `${listings[i]['Date']}, ${listings[i]['Start Time']}`;
            row.appendChild(dt);

            let desc = document.createElement("td");
            desc.innerText = listings[i]['Description'];
            row.appendChild(desc);

            let sitter = document.createElement('td');
            sitter.innerText = listings[i]['Booked'] == 0 ? 'Listed...': listings[i]['Sitter Username'];
            row.appendChild(sitter);

            let duration = document.createElement('td');
            duration.innerText = listings[i]['Duration'];
            row.appendChild(duration);

            var now = new Date();
            if (new Date(listings[i]['Date']) < now)
              history.appendChild(row);
            else
              table.appendChild(row);

          }
        });
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
