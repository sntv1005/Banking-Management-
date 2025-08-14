document.getElementById("accountForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const data = {
        customerId: document.getElementById("customerId").value,
        type: document.getElementById("accType").value.trim().toUpperCase(), // match AccountType enum
        initialDeposit: document.getElementById("initialDeposit").value
    };

    fetch("/api/account/create", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(res => {
        if (!res.ok) {
            throw new Error("Failed to create account. Server returned " + res.status);
        }
        return res.json();
    })
    .then(response => {
        Swal.fire({
            title: '✅ Account Created!',
            html: `<b>Account ID:</b> ${response.id}<br>
                   <b>Account Number:</b> ${response.accountNumber}<br>
                   <b>Type:</b> ${response.type}<br>
                   <b>Balance:</b> ₹${response.balance}`,
            icon: 'success',
            confirmButtonColor: '#4CAF50',
            background: '#f9f9f9'
        });

        document.getElementById("accountForm").reset();
    })
    .catch(err => {
        Swal.fire({
            title: '❌ Error!',
            text: err.message,
            icon: 'error',
            confirmButtonColor: '#d33',
            background: '#fff0f0'
        });
    });
});
