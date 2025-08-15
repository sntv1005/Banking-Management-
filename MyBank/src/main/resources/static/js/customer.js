document.getElementById("customerForm").addEventListener("submit", function(e) {
    e.preventDefault();

    // DOB Validation - must be at least 18
    const dob = new Date(document.getElementById("dateOfBirth").value);
    const today = new Date();
    let age = today.getFullYear() - dob.getFullYear();
    const monthDiff = today.getMonth() - dob.getMonth();
    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < dob.getDate())) {
        age--;
    }
    if (age < 18) {
        Swal.fire({ title: 'Invalid Age', text: 'Customer must be at least 18 years old.', icon: 'error' });
        return;
    }

    // Government ID Validation
    const idType = document.getElementById("governmentIdType").value;
    const idNumber = document.getElementById("governmentIdNumber").value.trim();

    if (idType === "Aadhar" && !/^\d{12}$/.test(idNumber)) {
        Swal.fire({ title: 'Invalid Aadhar', text: 'Aadhar must be exactly 12 digits.', icon: 'error' });
        return;
    }

    if (idType === "PAN" && !/^[A-Z]{5}[0-9]{4}[A-Z]{1}$/.test(idNumber)) {
        Swal.fire({ title: 'Invalid PAN', text: 'PAN format should be like BNYPV8765M.', icon: 'error' });
        return;
    }

    const data = {
        name: document.getElementById("name").value,
        dateOfBirth: document.getElementById("dateOfBirth").value,
        gender: document.getElementById("gender").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        addressLine: document.getElementById("addressLine").value,
        city: document.getElementById("city").value,
        state: document.getElementById("state").value,
        postalCode: document.getElementById("postalCode").value,
        country: document.getElementById("country").value,
        governmentIdType: idType,
        governmentIdNumber: idNumber,
        password: document.getElementById("password").value,
        monthlyIncome: parseFloat(document.getElementById("monthlyIncome").value),
        occupation: document.getElementById("occupation").value
    };

    fetch("/api/customer/create", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
    .then(res => {
        if (!res.ok) throw new Error("Failed to create customer");
        return res.json();
    })
    .then(response => {
        Swal.fire({
            title: 'Customer Created!',
            html: `
                <b>Customer ID:</b> ${response.customerId}<br>
                <b>Name:</b> ${response.name}<br>
                <b>Email:</b> ${response.email}<br>
                <b>Phone:</b> ${response.phone}
            `,
            icon: 'success',
            confirmButtonColor: '#4CAF50'
        });
        document.getElementById("customerForm").reset();
    })
    .catch(err => {
        Swal.fire({
            title: 'Error!',
            text: err,
            icon: 'error',
            confirmButtonColor: '#d33'
        });
    });
});
