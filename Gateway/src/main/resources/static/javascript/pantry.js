var credentials = "Basic " + sessionStorage.getItem("credentials");
var host = "172.31.6.1";

document.addEventListener("DOMContentLoaded", function() {
    buildBody();
});

function buildBody() {
    console.log("buildBody");
    // estraggo email dell'utente corrente
    let encodedEmail = sessionStorage.getItem("credentials");
    let decodedEmail = atob(encodedEmail).split(':')[0];

    let id = sessionStorage.getItem("id");
    const url = "http://" + host + ":8080/ingsw24/gateway/pantry/" + id;
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
                    let content = "";

                    if( data.ownerUsername === decodedEmail ){
                        // Mappare i dati in paragrafi HTML con icone
                        content += `<p class="id-dispensa"><i class="fas fa-id-badge"></i> ID Dispensa: ${data.id}</p>`;
                        content += `<p class="proprietario"><i class="fas fa-user"></i> Proprietario: <span>${data.ownerUsername}</span></p>`;

                        // Campo di input e pulsante per aggiungere un nuovo ospite
                        content += '<div class="add-guest">';
                        content += '<input type="text" id="newGuestUsername" placeholder="Nuovo ospite username">';
                        content += `<button onclick="addGuest('${id}')">Aggiungi Ospite</button>`;
                        content += '</div>';

                        // Aggiungere i dettagli degli ospiti
                        content += '<p class="ospiti"><i class="fas fa-users"></i> Ospiti:</p>';
                        content += '<div class="guest-container">';
                        data.guestsUsernames.forEach(guest => {
                            content += '<div class="guest-item">';
                            content += `<p><i class="fas fa-user"></i> &nbsp ${guest}</p>`;
                            content += `<button class="remove-guest-btn" onclick="removeGuest('${id}', '${guest}')">Rimuovi</button>`;
                            content += '</div>';
                        });
                        content += '</div>';

                        // Aggiungere i dettagli degli alimenti
                        content += '<p class="alimenti-title"><i class="fas fa-carrot"></i> Alimenti:</p>';
                        content += '<button id="showAddUnPackedForm" onclick="toggleAddUnPackedForm()">Aggiungi Alimento</button>';
                        content += '<div id="addUnPackedForm" style="display:none;">';
                        content += '<input type="text" id="newUnPackedName" placeholder="Nome alimento">';
                        content += '<input type="number" id="newUnPackedQuantity" placeholder="Quantità">';
                        content += '<label for="newFoodFridge">In frigo</label>';
                        content += '<input type="checkbox" id="newUnPackedFridge">';
                        content += `<button onclick="addUnPacked('${id}')">Aggiungi</button>`;
                        content += '</div>';

                        content += '<button id="showAddPackedForm" onclick="toggleAddPackedForm()">Aggiungi Packed</button>';
                        content += '<div id="addPackedForm" style="display:none;">';
                        content += '<input type="number" id="newPackedQuantity" placeholder="Quantità">';
                        content += '<input type="text" id="newPackedExpire" placeholder="Data Scadenza (yyyy-mm-gg)">';
                        content += '<label for="newPackedFridge">In frigo</label>';
                        content += '<input type="checkbox" id="newPackedFridge">';
                        content += '<input type="text" id="newPackedBarcode" placeholder="Codice a barre">';
                        content += '<div id="my-qr-reader-container" style="margin-top: 20px; display: none;"></div>';
                        content += `<button onclick="addPacked('${id}')">Aggiungi</button>`;
                        content += '</div>';

                        initializeQrScanner();

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
                            content += `<button class="remove-food-btn" onclick="removeFood('${id}', '${fud.name}')">Rimuovi</button>`;
                            content += '</div>';
                        });

                    }
                    else{
                        // Mappare i dati in paragrafi HTML con icone
                        content += `<p class="id-dispensa"><i class="fas fa-id-badge"></i> ID Dispensa: ${data.id}</p>`;
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
                        content += '<button id="showAddUnPackedForm" onclick="toggleAddUnPackedForm()">Aggiungi Alimento</button>';
                        content += '<div id="addUnPackedForm" style="display:none;">';
                        content += '<input type="text" id="newUnPackedName" placeholder="Nome alimento">';
                        content += '<input type="number" id="newUnPackedQuantity" placeholder="Quantità">';
                        content += '<label for="newFoodFridge">In frigo</label>';
                        content += '<input type="checkbox" id="newUnPackedFridge">';
                        content += `<button onclick="addUnPacked('${id}')">Aggiungi</button>`;
                        content += '</div>';

                        content += '<button id="showAddPackedForm" onclick="toggleAddPackedForm()">Aggiungi Packed</button>';
                        content += '<div id="addPackedForm" style="display:none;">';
                        content += '<input type="number" id="newPackedQuantity" placeholder="Quantità">';
                        content += '<input type="text" id="newPackedExpire" placeholder="Data Scadenza (yyyy-mm-gg)">';
                        content += '<label for="newPackedFridge">In frigo</label>';
                        content += '<input type="checkbox" id="newPackedFridge">';
                        content += '<input type="text" id="newPackedBarcode" placeholder="Codice a barre">';
                        content += '<div id="my-qr-reader-container" style="margin-top: 20px; display: none;"></div>';
                        content += `<button onclick="addPacked('${id}')">Aggiungi</button>`;
                        content += '</div>';

                        initializeQrScanner();

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
                            content += `<button class="remove-food-btn" onclick="removeFood('${id}', '${fud.name}')">Rimuovi</button>`;
                            content += '</div>';
                        });
                    }


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

// Funzione per rimuovere un ospite
function removeGuest(pantryId, username) {
    const url = "http://"+host+":8080/ingsw24/gateway/"+pantryId+"/guests/"+username;
    const credentials = sessionStorage.getItem("credentials");

    const xhttp = new XMLHttpRequest();
    xhttp.withCredentials = true;

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 204) {
                console.log(`Guest ${username} removed successfully`);
                // Ricaricare il corpo per aggiornare l'elenco degli ospiti
                buildBody();
            } else {
                console.error('There was a problem with the delete operation:', this.statusText);
            }
        }
    };

    xhttp.open("DELETE", url, true);
    xhttp.setRequestHeader("Authorization", "Basic " + credentials);

    console.log(xhttp)

    xhttp.send();
}

// Funzione per aggiungere un ospite
function addGuest(pantryId) {
    const newGuestUsername = document.getElementById('newGuestUsername').value;
    if (!newGuestUsername) {
        alert("Inserisci un username per il nuovo ospite.");
        return;
     }

    const url = "http://" + host + ":8080/ingsw24/gateway/" + pantryId + "/guests";
    const credentials = sessionStorage.getItem("credentials");

    const xhttp = new XMLHttpRequest();
    xhttp.withCredentials = true;

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                console.log(`Guest ${newGuestUsername} added successfully`);
                // Ricaricare il corpo per aggiornare l'elenco degli ospiti
                buildBody();
            } else {
                console.error('There was a problem with the add operation:', this.statusText);
            }
        }
    };

    xhttp.open("PUT", url, true);
    xhttp.setRequestHeader("Authorization", "Basic " + credentials);
    xhttp.setRequestHeader("Content-Type", "application/json");
    
    xhttp.send(newGuestUsername);
}

// Funzione per rimuovere un alimento
function removeFood(pantryId, name) {
    const url = "http://"+host+":8080/ingsw24/gateway/"+pantryId+"/foods/"+name;
    const credentials = sessionStorage.getItem("credentials");

    const xhttp = new XMLHttpRequest();
    xhttp.withCredentials = true;

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 204) {
                console.log(`Food ${name} removed successfully`);
                // Ricaricare il corpo per aggiornare l'elenco degli ospiti
                buildBody();
            } else {
                console.error('There was a problem with the delete operation:', this.statusText);
            }
        }
    };

    xhttp.open("DELETE", url, true);
    xhttp.setRequestHeader("Authorization", "Basic " + credentials);

    console.log(xhttp)

    xhttp.send();
}

function toggleAddUnPackedForm() {
    var form = document.getElementById("addUnPackedForm");
    if (form.style.display === "none") {
        form.style.display = "block";
    } else {
        form.style.display = "none";
    }
}

function addUnPacked(pantryId) {
    const name = document.getElementById('newUnPackedName').value;
    const quantity = document.getElementById('newUnPackedQuantity').value;
    const isFridge = document.getElementById('newUnPackedFridge').checked;

    if (!name || !quantity) {
        alert("Inserisci nome e quantità dell'alimento.");
        return;
    }

    const url = "http://" + host + ":8080/ingsw24/gateway/" + pantryId + "/foods/unpacked/"+name+"?isFridge="+isFridge+"&quantity="+quantity;
    const credentials = sessionStorage.getItem("credentials");

    const xhttp = new XMLHttpRequest();
    xhttp.withCredentials = true;

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                console.log(`Food ${name} added successfully`);
                // Ricaricare il corpo per aggiornare l'elenco degli alimenti
                buildBody();
            } else {
                console.error('There was a problem with the add operation:', this.statusText);
            }
        }
    };

    xhttp.open("PUT", url, true);
    xhttp.setRequestHeader("Authorization", "Basic " + credentials);

    xhttp.send();
}

function toggleAddPackedForm() {
    var form = document.getElementById("addPackedForm");
    if (form.style.display === "none") {
        form.style.display = "block";
    } else {
        form.style.display = "none";
    }
}

function domReady(fn) {
    if (document.readyState === "complete" || document.readyState === "interactive") {
        setTimeout(fn, 1000);
    } else {
        document.addEventListener("DOMContentLoaded", fn);
    }
}

function initializeQrScanner() {

	domReady(function () {
        	// Crea dinamicamente il div del lettore QR
        	const qrReaderContainer = document.getElementById('my-qr-reader-container');
        	const qrReaderDiv = document.createElement('div');
        	qrReaderDiv.id = 'my-qr-reader';
        	qrReaderContainer.appendChild(qrReaderDiv);
        	qrReaderContainer.style.display = 'block';
    		// Se si trova un QR code
    		function onScanSuccess(decodeText, decodeResult) {
       			alert("Codice trovato: " + decodeText);
       			document.getElementById('newPackedBarcode').value = decodeText;
		}

    		let htmlscanner = new Html5QrcodeScanner(
		        "my-qr-reader",
        		{ fps: 10, qrbox: 250 }
    		);
    		htmlscanner.render(onScanSuccess);
	});
}

function addPacked(pantryId) {
    const barcode = document.getElementById('newPackedBarcode').value;
    const expire = document.getElementById('newPackedExpire').value;
    const quantity = document.getElementById('newPackedQuantity').value;
    const isFridge = document.getElementById('newPackedFridge').checked;

    if (!quantity) {
        alert("COMPILA TUTTO");
        return;
    }

    const url = "http://" + host + ":8080/ingsw24/gateway/" + pantryId + "/foods/packed/"+barcode+"?isFridge="+isFridge+"&quantity="+quantity+"&expirationDate="+expire;
    const credentials = sessionStorage.getItem("credentials");

    const xhttp = new XMLHttpRequest();
    xhttp.withCredentials = true;

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                console.log(`Food ${barcode} added successfully`);
                // Ricaricare il corpo per aggiornare l'elenco degli alimenti
                buildBody();
            } else {
                console.error('There was a problem with the add operation:', this.statusText);
            }
        }
    };

    xhttp.open("PUT", url, true);
    xhttp.setRequestHeader("Authorization", "Basic " + credentials);

    xhttp.send();

}
