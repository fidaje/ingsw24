document.addEventListener('DOMContentLoaded', (event) => {
    buildBody();
});

const host = "172.31.6.1";

function buildBody() {
    const url = 'http://' + host + ':8080/ingsw24/gateway/pantries';
    console.log(url);
    let countPantries = 0;

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Basic ${sessionStorage.getItem("credentials")}`
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            const personalAreaSection = document.getElementById('personalAreaSection');

            // Creare una lista HTML
            const ul = document.createElement('ul');

            // Iterare sui dati e creare un elemento li per ciascuna dispensa
            data.forEach(pantry => {
                countPantries++;
            });

            let encodedEmail = sessionStorage.getItem("credentials");
            let decodedEmail = atob(encodedEmail).split(':')[0];

            const email = document.createElement('li');
            email.innerHTML = `<div class="info"><i class="fas fa-envelope"></i><span>Email: ${decodedEmail}</span></div>`;

            const pantries = document.createElement('li');
            pantries.innerHTML = `<div class="info"><i class="fas fa-utensils"></i><span>Numero di dispense: ${countPantries}</span></div>`;

            const password = document.createElement('li');

            let content = `<div class="info"><i class="fas fa-lock"></i><span>Cambia password</span></div>`;
            content += '<div id="changePasswordForm">';
            content += '<input type="password" id="oldPassword" placeholder="Vecchia password">';
            content += '<input type="checkbox" onclick="show(\'oldPassword\')">Mostra vecchia Password <br>';
            content += '<input type="password" id="newPassword" placeholder="Nuova password">';
            content += '<input type="checkbox" onclick="show(\'newPassword\')">Mostra nuova Password <br>';
            content += `<button onclick="changePassword('${decodedEmail}')">Cambia</button>`;
            content += '</div>';

            password.innerHTML = content;

            ul.appendChild(email);
            ul.appendChild(pantries);
            ul.appendChild(password);

            personalAreaSection.appendChild(ul);
            })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}

function changePassword(mail){
    const url = "http://"+host+":8080/ingsw24/gateway/users";
    const oldPassword = document.getElementById("oldPassword").value;
    const newPassword = document.getElementById("newPassword").value;

    const auth = btoa(mail + ":" + oldPassword);

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            console.log(this.responseText);
        }
    });

    xhr.open("PUT", url);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Authorization", "Basic " + auth);

    xhr.send(newPassword);

    if (xhr.status === 200) {
        alert("Password cambiata con successo");
        sessionStorage.clear();
        window.location.href = 'http://' + host + ':8080/html/login.html';
    }
    else {
        alert("Errore nel cambio della password");
    }
}

function show(elementId) {
    var password = document.getElementById(elementId);
    if (password.type === "password") {
        password.type = "text";
    } else {
        password.type = "password";
    }
}