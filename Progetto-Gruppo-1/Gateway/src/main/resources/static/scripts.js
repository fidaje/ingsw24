document.addEventListener("DOMContentLoaded", function() {
    if (document.body.contains(document.getElementById('homeContent'))) {
        handleHomePage();
    } else if (document.body.contains(document.getElementById('pantryContent'))) {
        handlePantry();
    } else if (document.body.contains(document.getElementById('addItemContent'))) {
        handleAddItem();
    } else if (document.body.contains(document.getElementById('addItemBarcodeContent'))) {
        handleAddItemBarcode();
    } else if (document.body.contains(document.getElementById('inviteContent'))) {
        handleInvite();
    } else if (document.body.contains(document.getElementById('joinContent'))) {
        handleJoin();
    } else if (document.body.contains(document.getElementById('credentialsContent'))) {
        handleCredentials();
    }
});

function attemptLogin(event) {
    event.preventDefault();

    // Get username and password from form inputs
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    var xhttp = new XMLHttpRequest();
    var url = `http://127.0.0.1:8080/ingsw24/gateway/user/${username}`;
    xhttp.open("GET", url, true); // asynchronous invocation
    xhttp.onreadystatechange = function() {
        if (this.status == 200) {
            localStorage.setItem("loggedIn", "true");
            console.log("Login successful, redirecting to homepage...");
            window.location.href = "home.html";
        } else if (this.status == 404){
            alert("Login failed. Please check your username and password.");
        }
    };
    xhttp.send();
}

function attemptRegister(event) {
    event.preventDefault();
    var username = document.getElementById("reg-username").value;
    var password = document.getElementById("reg-password").value;

    var xhttp = new XMLHttpRequest();
    var url = "http://127.0.0.1:8080/ingsw24/gateway/user";
    xhttp.open("POST", url, false); // synchronous invocation
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({ username: username, password: password, roles: ["OWNER"] }));

    if (xhttp.status === 201) {
        alert("Registration successful. Please log in.");
        window.location.href = "index.html";
    } else {
        alert("Registration failed. Please try again.");
    }
}

function checkAuthentication() {
    if (localStorage.getItem("loggedIn") !== "true") {
        window.location.href = "index.html";
    }
}

function logout() {
    localStorage.removeItem("loggedIn");
    window.location.href = "index.html";
}

//function handleHomePage() {
    //document.getElementById("homeContent").innerHTML = "<p>Welcome to MY PANTRY!</p>";
//}

function handlePantry() {
    checkAuthentication();
    var xhttp = new XMLHttpRequest();
    var url = "http://127.0.0.1:8080/gateway/pantry";
    xhttp.open("GET", url, false);
    xhttp.send();

    if (xhttp.status === 200) {
        var pantry = JSON.parse(xhttp.responseText);
        var content = "<ul>";
        pantry.items.forEach(function(item) {
            content += "<li>" + item.name + " - " + item.quantity + "</li>";
        });
        content += "</ul>";
        document.getElementById("pantryContent").innerHTML = content;
    } else {
        document.getElementById("pantryContent").innerHTML = "<p>Failed to load pantry items.</p>";
    }
}

function handleAddItem() {
    checkAuthentication();
    document.getElementById("addItemContent").innerHTML = `
        <form onsubmit="addItem(event)">
            <label for="itemName">Item Name:</label>
            <input type="text" id="itemName" required>
            <label for="itemQuantity">Quantity:</label>
            <input type="number" id="itemQuantity" required>
            <button type="submit">Add Item</button>
        </form>
    `;
}

function addItem(event) {
    event.preventDefault();
    var itemName = document.getElementById("itemName").value;
    var itemQuantity = document.getElementById("itemQuantity").value;

    var xhttp = new XMLHttpRequest();
    var url = "http://127.0.0.1:8080/gateway/pantry";
    xhttp.open("POST", url, false);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({ name: itemName, quantity: itemQuantity }));

    if (xhttp.status === 201) {
        alert("Item added successfully.");
        handleAddItem();
    } else {
        alert("Failed to add item.");
    }
}

function handleAddItemBarcode() {
    checkAuthentication();
    document.getElementById("addItemBarcodeContent").innerHTML = `
        <form onsubmit="addItemBarcode(event)">
            <label for="itemBarcode">Barcode:</label>
            <input type="text" id="itemBarcode" required>
            <button type="submit">Add Item by Barcode</button>
        </form>
    `;
}

function addItemBarcode(event) {
    event.preventDefault();
    var itemBarcode = document.getElementById("itemBarcode").value;

    var xhttp = new XMLHttpRequest();
    var url = "http://127.0.0.1:8080/gateway/pantry/barcode";
    xhttp.open("POST", url, false);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({ barcode: itemBarcode }));

    if (xhttp.status === 201) {
        alert("Item added successfully.");
        handleAddItemBarcode();
    } else {
        alert("Failed to add item by barcode.");
    }
}

function handleInvite() {
    checkAuthentication();
    document.getElementById("inviteContent").innerHTML = `
        <form onsubmit="inviteUser(event)">
            <label for="inviteEmail">Email:</label>
            <input type="email" id="inviteEmail" required>
            <button type="submit">Invite</button>
        </form>
    `;
}

function inviteUser(event) {
    event.preventDefault();
    var inviteEmail = document.getElementById("inviteEmail").value;

    var xhttp = new XMLHttpRequest();
    var url = "http://127.0.0.1:8080/gateway/invite";
    xhttp.open("POST", url, false);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({ email: inviteEmail }));

    if (xhttp.status === 201) {
        alert("Invitation sent successfully.");
        handleInvite();
    } else {
        alert("Failed to send invitation.");
    }
}

function handleJoin() {
    checkAuthentication();
    document.getElementById("joinContent").innerHTML = `
        <form onsubmit="joinPantry(event)">
            <label for="joinCode">Join Code:</label>
            <input type="text" id="joinCode" required>
            <button type="submit">Join Pantry</button>
        </form>
    `;
}

function joinPantry(event) {
    event.preventDefault();
    var joinCode = document.getElementById("joinCode").value;

    var xhttp = new XMLHttpRequest();
    var url = "http://127.0.0.1:8080/gateway/join";
    xhttp.open("POST", url, false);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({ joinCode: joinCode }));

    if (xhttp.status === 201) {
        alert("Joined pantry successfully.");
        handleJoin();
    } else {
        alert("Failed to join pantry.");
    }
}

function handleCredentials() {
    checkAuthentication();
    document.getElementById("credentialsContent").innerHTML = `
        <form onsubmit="changeCredentials(event)">
            <label for="newUsername">New Username:</label>
            <input type="text" id="newUsername" required>
            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" required>
            <button type="submit">Change Credentials</button>
        </form>
    `;
}

function changeCredentials(event) {
    event.preventDefault();
    var newUsername = document.getElementById("newUsername").value;
    var newPassword = document.getElementById("newPassword").value;

    var xhttp = new XMLHttpRequest();
    var url = "http://127.0.0.1:8080/gateway/user/credentials";
    xhttp.open("PUT", url, false);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({ username: newUsername, password: newPassword }));

    if (xhttp.status === 200) {
        alert("Credentials changed successfully.");
        handleCredentials();
    } else {
        alert("Failed to change credentials.");
    }
}

function createPantry(ownerUsername) {
    var xhttp = new XMLHttpRequest();
    var url = `http://127.0.0.1:8080/ingsw24/gateway/pantry/${ownerUsername}`;
    xhttp.open("POST", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 201) {
            console.log("Pantry created successfully");
        }
    };
}

function getPantry(pantryId) {
    var xhttp = new XMLHttpRequest();
    var url = `http://127.0.0.1:8080/ingsw24/gateway/pantry/${pantryId}`;
    xhttp.open("GET", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var pantry = JSON.parse(this.responseText);
            console.log(pantry);
        }
    };
}

function getPantries() {
    var xhttp = new XMLHttpRequest();
    var url = `http://127.0.0.1:8080/ingsw24/gateway/pantries`;
    xhttp.open("GET", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var pantries = JSON.parse(this.responseText);
            console.log(pantries);
        }
    };
}

function updateGuests(pantryId, guestUsername) {
    var xhttp = new XMLHttpRequest();
    var url = `http://127.0.0.1:8080/ingsw24/gateway/${pantryId}/guests`;
    xhttp.open("PUT", url, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({ username: guestUsername }));
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log("Guests updated successfully");
        }
    };
}

function deletePantry(pantryId) {
    var xhttp = new XMLHttpRequest();
    var url = `http://127.0.0.1:8080/ingsw24/gateway/${pantryId}`;
    xhttp.open("DELETE", url, true);
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 204) {
            console.log("Pantry deleted successfully");
        }
    };
}

