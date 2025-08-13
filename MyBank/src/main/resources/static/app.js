//const base = '';
//
//async function post(url, body) {
//  const res = await fetch(base + url, { method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify(body) });
//  if(!res.ok) throw new Error(await res.text());
//  return res.json();
//}
//
//function set(id, data){ document.getElementById(id).textContent = JSON.stringify(data, null, 2); }
//
//async function createCustomer(){
//  try {
//    const body = {
//      name: document.getElementById('custName').value,
//      email: document.getElementById('custEmail').value,
//      phone: document.getElementById('custPhone').value,
//      monthlyIncome: Number(document.getElementById('custIncome').value||0)
//    };
//    const out = await post('/api/customer/create', body);
//    set('customerOut', out);
//  } catch(e){ alert(e); }
//}
//
//async function createAccount(){
//  try {
//    const body = {
//      customerId: Number(document.getElementById('accCustId').value),
//      type: document.getElementById('accType').value,
//      initialDeposit: Number(document.getElementById('accInitial').value||0)
//    };
//    const out = await post('/api/account/create', body);
//    set('accountOut', out);
//  } catch(e){ alert(e); }
//}
//
//async function deposit(){
//  try {
//    const body = {
//      accountId: Number(document.getElementById('txnAccId').value),
//      amount: Number(document.getElementById('txnAmt').value),
//      description: document.getElementById('txnDesc').value
//    };
//    const out = await post('/api/txn/deposit', body);
//    set('txnOut', out);
//  } catch(e){ alert(e); }
//}
//
//async function withdraw(){
//  try {
//    const body = {
//      accountId: Number(document.getElementById('txnAccId').value),
//      amount: Number(document.getElementById('txnAmt').value),
//      description: document.getElementById('txnDesc').value
//    };
//    const out = await post('/api/txn/withdraw', body);
//    set('txnOut', out);
//  } catch(e){ alert(e); }
//}
//
//async function transfer(){
//  try {
//    const body = {
//      fromAccountId: Number(document.getElementById('fromId').value),
//      toAccountId: Number(document.getElementById('toId').value),
//      amount: Number(document.getElementById('trAmt').value),
//      description: document.getElementById('trDesc').value
//    };
//    await post('/api/txn/transfer', body);
//    set('transferOut', {status: 'OK'});
//  } catch(e){ alert(e); }
//}
//
//function downloadAccountStatement(){
//  const id = document.getElementById('stmtAccId').value;
//  const from = document.getElementById('stmtFrom').value;
//  const to = document.getElementById('stmtTo').value;
//  const url = `/api/statements/account/${id}?from=${from||''}&to=${to||''}`;
//  window.location = url;
//}
//
//async function checkEligibility(){
//  const id = document.getElementById('ccCustId').value;
//  const res = await fetch(`/api/credit/card/customer/${id}/eligible`);
//  const ok = await res.json();
//  set('ccOut', {eligible: ok});
//}
//
//async function issueCard(){
//  try{
//    const body = { customerId: Number(document.getElementById('ccCustId').value) };
//    const out = await post('/api/credit/card/issue', body);
//    set('ccOut', out);
//  }catch(e){ alert(e); }
//}
//
//async function cardCharge(){
//  try{
//    const body = {
//      cardId: Number(document.getElementById('cardId').value),
//      amount: Number(document.getElementById('ccAmt').value),
//      description: document.getElementById('ccDesc').value,
//      payment: document.getElementById('ccPay').checked
//    };
//    const out = await post('/api/credit/card/charge', body);
//    set('ccOut', out);
//  }catch(e){ alert(e); }
//}
//
//function downloadCardStatement(){
//  const id = document.getElementById('cardId').value;
//  window.location = `/api/statements/card/${id}`;
//}
