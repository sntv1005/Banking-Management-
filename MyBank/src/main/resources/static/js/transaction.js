document.getElementById("transactionForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const type = document.getElementById("type").value;
    let url = "";
    let data = {};

    if (type === "deposit" || type === "withdraw") {
        // Backend expects: { accountId, amount }
        data = {
            accountId: parseInt(document.getElementById("accountId").value),
            amount: parseFloat(document.getElementById("amount").value)
        };
        url = type === "deposit" ? "/api/txn/deposit" : "/api/txn/withdraw";

    } else if (type === "transfer") {
        // Backend expects: { fromAccountId, toAccountId, amount, description }
        data = {
            fromAccountId: parseInt(document.getElementById("accountId").value),
            toAccountId: parseInt(document.getElementById("targetAccountId").value),
            amount: parseFloat(document.getElementById("amount").value),
            description: document.getElementById("description").value || ""
        };
        url = "/api/txn/transfer";
    }

    fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
        .then(res => {
            if (!res.ok) {
                return res.json().then(err => { throw err; });
            }
            return res.json();
        })
        .then(responseData => {
            document.getElementById("result").innerText =
                "✅ Success:\n" + JSON.stringify(responseData, null, 2);
        })
        .catch(err => {
            document.getElementById("result").innerText =
                "❌ Error:\n" + (err.message || JSON.stringify(err, null, 2));
        });
});
