document.getElementById("statementForm").addEventListener("submit", function(e) {
    e.preventDefault();
    const accountId = document.getElementById("accountId").value;
    fetch(`/api/statement/${accountId}`)
    .then(res => res.json())
    .then(data => document.getElementById("result").innerText = JSON.stringify(data, null, 2))
    .catch(err => alert("Error: " + err));
});
