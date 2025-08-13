document.getElementById("customerForm").addEventListener("submit", function(e) {
    e.preventDefault();
    const data = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value
    };
    fetch("/api/customer/create", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(data => document.getElementById("result").innerText = JSON.stringify(data))
    .catch(err => alert("Error: " + err));
});
