document.getElementById("accountForm").addEventListener("submit", function(e) {
    e.preventDefault();
    const data = {
        customerId: document.getElementById("customerId").value,
        balance: parseFloat(document.getElementById("balance").value)
    };
    fetch("/api/account/create", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(data => document.getElementById("result").innerText = JSON.stringify(data))
    .catch(err => alert("Error: " + err));
});
