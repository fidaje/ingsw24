var credentials = "Basic " + sessionStorage.getItem("credentials");

document.addEventListener("DOMContentLoaded", function() {
    buildBody();
});

function buildBody() {
    console.log("buildBody");

    let id = sessionStorage.getItem("id");
    const url = "http://127.0.0.1:8080/ingsw24/gateway/pantry/" + id;
    const credentials = sessionStorage.getItem("credentials");

    const xhttp = new XMLHttpRequest();
    xhttp.withCredentials = true;

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                const data = JSON.parse(this.responseText);
                console.log(data);

                var pantry = document.getElementById('pantry');
                if (pantry) {
                    pantry.innerHTML = '';

                    // Mappare i dati in paragrafi HTML
                    let content = `<p>ID Dispensa: ${data.id}</p>`;
                    content += `<p>Proprietario: ${data.ownerUsername}</p>`;
                    content += `<p>Ospiti: ${data.guestsUsernames.join(', ')}</p>`;

                    // Aggiungere i dettagli degli alimenti
                    content += '<p>Alimenti:</p>';
                    data.fuds.forEach(fud => {
                        content += '<p>';
                        content += `Nome: ${fud.name}<br>`;
                        content += `Data di scadenza: ${fud.expirationDate}<br>`;
                        content += `Scaduto: ${fud.isExpired ? 'Sì' : 'No'}<br>`;
                        content += `Frigo: ${fud.isFridge ? 'Sì' : 'No'}<br>`;
                        content += `Quantità: ${fud.quantity}<br>`;
                        if (fud.brand) content += `Marca: ${fud.brand}<br>`;
                        if (fud.category) content += `Categoria: ${fud.category}<br>`;
                        if (fud.nutritionGrade) content += `Grado di nutrizione: ${fud.nutritionGrade}<br>`;
                        content += '</p>';
                    });

                    // Aggiungere il contenuto al contenitore
                    pantry.innerHTML = content;
                } else {
                    console.error('Element with id "container" not found');
                }
            } else {
                console.error('There was a problem with the fetch operation:', this.statusText);
            }
        }
    };

    xhttp.open("GET", url, true);
    xhttp.setRequestHeader("Authorization", "Basic " + credentials);
    xhttp.send();
}


