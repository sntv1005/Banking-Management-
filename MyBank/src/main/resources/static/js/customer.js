document.getElementById("customerForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const data = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        password: document.getElementById("password").value,
        monthlyIncome: parseFloat(document.getElementById("monthlyIncome").value)
    };

    fetch("/api/customer/create", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
    .then(res => {
        if (!res.ok) {
            return res.json().then(err => { throw new Error(err.message || "Failed to create customer"); });
        }
        return res.json();
    })
    .then(response => {
        Swal.fire({
            title: 'Customer Created!',
            html: `
                <b>Customer ID:</b> ${response.customerId}<br>
                <b>Name:</b> ${response.name}<br>
                <b>Email:</b> ${response.email}<br>
                <b>Phone:</b> ${response.phone}<br>
                <b>Monthly Income:</b> ₹${response.monthlyIncome}
            `,
            icon: 'success',
            confirmButtonColor: '#4CAF50',
            background: '#f9f9f9'
        });

        document.getElementById("customerForm").reset();
    })
    .catch(err => {
        Swal.fire({
            title: 'Error!',
            text: err.message,
            icon: 'error',
            confirmButtonColor: '#d33',
            background: '#fff0f0'
        });
    });
});
