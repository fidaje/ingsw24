document.addEventListener('DOMContentLoaded', (event) => {
    // Seleziona il form utilizzando il suo id
    const form = document.getElementById('formLogin');

    // Aggiungi un listener per l'evento 'submit'
    form.addEventListener('submit', attemptLogin);
});

var host = "172.31.6.1";

// Funzione di callback per l'evento submit
function attemptLogin(event) {
    // Prevenire il comportamento predefinito del form (ovvero il submit)
    event.preventDefault();

    // Esegui la tua logica di login qui
    console.log("Tentativo di login");

    // Puoi accedere ai valori dei campi del form così:
    const username = event.target.username.value;
    const password = event.target.password.value;


    const credentials = btoa(`${username}:${password}`)

    sessionStorage.setItem("credentials", credentials)


    const url = 'http://' + host + ':8080/html/home.html';
    const headers = new Headers({'Authorization': `Basic ${credentials}`});

    fetch(url, {method: 'GET', headers: headers})
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        })
        .then(data => {
            window.location.href = url;
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });

}