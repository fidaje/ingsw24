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
                    let content = `<p class="id-dispensa">ID Dispensa: ${data.id}</p>`;
                    content += `<p class="proprietario">Proprietario: <span>${data.ownerUsername}</span></p>`;
                    content += `<p class="ospiti">Ospiti: <span>${data.guestsUsernames.join(', ')}</span></p>`;

                    // Aggiungere i dettagli degli alimenti
                    content += '<p class="alimenti-title">Alimenti:</p>';
                    data.fuds.forEach(fud => {
                        content += '<div class="food-item">';
                        content += `<p><span>Nome:</span> ${fud.name}</p>`;
                        content += `<p><span>Data di scadenza:</span> ${fud.expirationDate}</p>`;
                        content += `<p><span>Scaduto:</span> ${fud.isExpired ? 'Sì' : 'No'}</p>`;
                        content += `<p><span>Frigo:</span> ${fud.isFridge ? 'Sì' : 'No'}</p>`;
                        content += `<p><span>Quantità:</span> ${fud.quantity}</p>`;
                        if (fud.brand) content += `<p><span>Marca:</span> ${fud.brand}</p>`;
                        if (fud.category) content += `<p><span>Categoria:</span> ${fud.category}</p>`;
                        if (fud.nutritionGrade) content += `<p><span>Grado di nutrizione:</span> ${fud.nutritionGrade}</p>`;
                        content += '</div>';
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

