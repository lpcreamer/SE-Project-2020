window.onload = function() {
    if(getCookie("username") == null) {
        window.location.href = "./login.html";
    } else {
        validateLogin(getCookie("username"), getCookie("password"));
        getListings((listings) => {
          console.log(listings);
          var now = new Date();
          let history = document.getElementById('listings_body');
          for(i in listings) {
            if (new Date(listings[i]['Date']) > now) {
              let row = document.createElement('tr');
              let dt = document.createElement('td');
              dt.innerText = listings[i]['Date'];
              row.appendChild(dt);

              let st = document.createElement('td');
              st.innerText = listings[i]['Start Time'];
              row.appendChild(st);

              let duration = document.createElement('td');
              duration.innerText = listings[i]['Duration'];
              row.appendChild(duration);

              let desc = document.createElement("td");
              desc.innerText = listings[i]['Description'];
              row.appendChild(desc);

              let sitter = document.createElement('td');
              sitter.innerText = listings[i]['Booked'] == 0 ? 'Listed...': listings[i]['Sitter Username'];
              row.appendChild(sitter);

              let cancel = document.createElement('td');
              let cancelBtn = document.createElement('button');
              cancelBtn.className = "btn btn-danger";
              cancelBtn.innerText = "Cancel";
              cancelBtn.tag = i;
              cancelBtn.addEventListener('click', () => {
                cancelListing(listings[cancelBtn.tag]['id']);
              });
              cancel.appendChild(cancelBtn);
              row.appendChild(cancel);

              history.appendChild(row);
            }

          }
        });
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
