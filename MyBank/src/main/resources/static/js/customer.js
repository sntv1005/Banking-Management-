document.getElementById("customerForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const data = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        monthlyIncome: document.getElementById("monthlyIncome").value
    };

    fetch("/api/customer/create", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(response => {
        Swal.fire({
            title: 'Customer Created!',
            html: `<b>ID:</b> ${response.id}<br>
                   <b>Name:</b> ${response.name}<br>
                   <b>Email:</b> ${response.email}<br>
                   <b>Phone:</b> ${response.phone}<br>
                   <b>Monthly Income:</b> ${response.monthlyIncome}`,
            icon: 'success',
            confirmButtonColor: '#4CAF50',
            background: '#f9f9f9'
        });

        // Clear the form after successful creation
        document.getElementById("customerForm").reset();
    })
    .catch(err => {
        Swal.fire({
            title: 'Error!',
            text: err,
            icon: 'error',
            confirmButtonColor: '#d33',
            background: '#fff0f0'
        });
    });
});
