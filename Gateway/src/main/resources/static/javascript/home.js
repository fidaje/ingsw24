document.addEventListener('DOMContentLoaded', (event) => {
    getPantries();
});

var host = "172.31.6.1";

function getPantries() {
    let encodedEmail = sessionStorage.getItem("credentials");
    let decodedEmail = atob(encodedEmail).split(':')[0];

    const url = 'http://' + host + ':8080/ingsw24/gateway/pantries';

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
            const pantrySection = document.getElementById('pantrySection');

            // Creare una lista HTML
            const ul = document.createElement('ul');

            // Iterare sui dati e creare un elemento li per ciascuna dispensa
            data.forEach(pantry => {
                const li = document.createElement('li');

                let content = `
                    <div class="info"><i class="fas fa-hashtag"></i><span>ID Dispensa: ${pantry.id}</span></div>
                    <div class="info"><i class="fas fa-utensils"></i><span>Numero di alimenti: ${pantry.fuds.length}</span></div>
                    <div class="info"><i class="fas fa-user"></i><span>Numero di ospiti: ${pantry.guestsUsernames.length}</span></div>
                `;

                if(pantry.ownerUsername === decodedEmail){
                    content += `<button class="remove-pantry-btn" onclick="removePantry('${pantry.id}', event)">Rimuovi</button>`;
                }


                li.innerHTML = content;
                li.onclick = () => getPantry(pantry.id);
                ul.appendChild(li);
            });

            // Aggiungere la lista alla sezione
            pantrySection.appendChild(ul);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}


function getPantry(id){
    const url = 'http://' + host + ':8080/html/pantry.html';
    sessionStorage.setItem("id", id);

    const credentials = sessionStorage.getItem("credentials");

    const headers = new Headers({'Authorization': `Basic ${credentials}`});

    fetch(url, {method: 'GET', headers: headers})
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        })
        .then(data => {
            window.open(url);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}

function logout() {
    sessionStorage.removeItem("credentials");
    sessionStorage.clear();
}

function createPantry(){

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            console.log(this.responseText);
            window.location.reload();
        }
    });

    xhr.open("POST", "http://"+host+":8080/ingsw24/gateway/pantry");
    xhr.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("credentials"));


    xhr.send();
}

function removePantry(pantryId, event){

    event.stopPropagation();

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            console.log(this.responseText);
            window.location.reload();
        }
    });

    xhr.open("DELETE", "http://"+host+":8080/ingsw24/gateway/" + pantryId + "/");
    xhr.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("credentials"));

    xhr.send();
}
