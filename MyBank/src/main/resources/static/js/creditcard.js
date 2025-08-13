document.getElementById("cardForm").addEventListener("submit", function(e) {
    e.preventDefault();
    const data = {
        customerId: document.getElementById("customerId").value,
        creditLimit: parseFloat(document.getElementById("limit").value)
    };
    fetch("/api/credit/card/issue", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(data => document.getElementById("result").innerText = JSON.stringify(data))
    .catch(err => alert("Error: " + err));
});
