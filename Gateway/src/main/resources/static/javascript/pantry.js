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

                    // Mappare i dati in paragrafi HTML con icone
                    let content = `<p class="id-dispensa"><i class="fas fa-id-badge"></i> ID Dispensa: ${data.id}</p>`;
                    content += `<p class="proprietario"><i class="fas fa-user"></i> Proprietario: <span>${data.ownerUsername}</span></p>`;

                    // Aggiungere i dettagli degli ospiti
                    content += '<p class="ospiti"><i class="fas fa-users"></i> Ospiti:</p>';
                    content += '<div class="guest-container">';
                    data.guestsUsernames.forEach(guest => {
                        content += '<div class="guest-item">';
                        content += `<p><i class="fas fa-user"></i> &nbsp ${guest}</p>`;
                        content += '</div>';
                    });
                    content += '</div>';

                    // Aggiungere i dettagli degli alimenti
                    content += '<p class="alimenti-title"><i class="fas fa-carrot"></i> Alimenti:</p>';
                    data.fuds.forEach(fud => {
                        content += '<div class="food-item">';
                        content += `<p><i class="fas fa-utensils"></i> <span>Nome:</span> ${fud.name}</p>`;
                        content += `<p><i class="fas fa-calendar-alt"></i> <span>Data di scadenza:</span> ${fud.expirationDate}</p>`;
                        content += `<p><i class="fas fa-exclamation-circle"></i> <span>Scaduto:</span> ${fud.isExpired ? 'Sì' : 'No'}</p>`;
                        content += `<p><i class="fas fa-snowflake"></i> <span>Frigo:</span> ${fud.isFridge ? 'Sì' : 'No'}</p>`;
                        content += `<p><i class="fas fa-balance-scale"></i> <span>Quantità:</span> ${fud.quantity}</p>`;
                        if (fud.brand) content += `<p><i class="fas fa-tag"></i> <span>Marca:</span> ${fud.brand}</p>`;
                        if (fud.category) content += `<p><i class="fas fa-list"></i> <span>Categoria:</span> ${fud.category}</p>`;
                        if (fud.nutritionGrade) content += `<p><i class="fas fa-chart-bar"></i> <span>Grado di nutrizione:</span> ${fud.nutritionGrade}</p>`;
                        content += '</div>';
                    });

                    // Aggiungere il contenuto al contenitore
                    pantry.innerHTML = content;
                } else {
                    console.error('Element with id "pantry" not found');
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
