//defining variables

const total = document.querySelector(".total-value");
const limit = document.querySelector(".credit-limit");
const income = document.querySelector(".income");
const outcome = document.querySelector(".outcome");
const tableBody = document.querySelector("tbody");
const currencyList = document.querySelector("#select-currency");
const tableField = document.querySelector(".table-field");
let currentCurrency = currencyList.value;
let currencySymbol;
let data, transactions, incomeVal=0, outcomeVal=0;

//adding event listeners

currencyList.addEventListener("change", ()=>{
    currentCurrency = currencyList.value;
    changeSymbol(); 
    renderUI();

})

//defining functions

function changeSymbol(){
    currencySymbol = currentCurrency =="USD"? "$" : "L.L";
}
async function getData(){
    let result = await sendRequestGet("/wallet/get-wallet-details");
    data = result.response;
}

async function init(){
    await getData();
    changeSymbol();    
    renderUI();
    
}

function renderUI(){
    total.innerHTML = currencySymbol + " "+ getTotal(); 
    limit.innerHTML = currencySymbol +" " +25000;
    getTransactions();
    fillTransactions(getFormattedTrans(transactions));
    getIncomeOutcome();
    income.innerHTML = currencySymbol + " " + incomeVal;
    outcome.innerHTML = currencySymbol + " " + outcomeVal;

}

let array=[{"date": "2020-1-23", "name": "Dunkin Donuts", "type": "outcome", "value": -19.5}, {"date": "2023-2-2", "name": "Dunkin Donuts", "type": "outcome", "value": -20},{"date": "2020-1-23", "name": "salary", "type": "itcome", "value": 100}]
function getTransactions(){
    transactions = data.transactions.map(transaction => {
        return {
            "date": transaction.createdDate.split("T")[0],
            "name": transaction.description,
            "type": transaction.type,
            "value": transaction.amount
        }
    });
 
}

function getFormattedTrans(transactions){ //combined by dates
    let newTrans = {};
    for(let transaction of transactions){
        if(transaction.date in newTrans){
            newTrans[transaction.date].push({"name":transaction.name, "type": transaction.type, "value": transaction.value});
        }else{
            newTrans[transaction.date]=[{"name":transaction.name, "type": transaction.type, "value": transaction.value}];
        }
    }
    return newTrans;
}

function getTotal(){
    let balances = data.balances;
    for(let balance of balances){
        if(balance.currency.isoName==currentCurrency){

            return balance.amount;
        }
    }
}


function getLimit(){
    fetch("assets/data.json")
    .then(res=>res.json())
    .then(res=>limit.innerHTML= "$" + res.limit);
}

function getIncomeOutcome(){
    for(let transaction of transactions){
        console.log(transaction)
        if(transaction.type == "TRANSFER_OUT"){
            outcomeVal += transaction.value;
        }   
        else{
            incomeVal += transaction.value;
        }
    }
}


function createTable(dateKey, transactions){
    let date = document.createElement("span");
    date.classList.add("date");
    date.innerHTML = dateKey;
    tableField.appendChild(date);
    let table = document.createElement("table");
    let tbody = document.createElement("tbody");
    for(element of transactions[dateKey]){
        let newTr = document.createElement("tr");
        let nameTd = document.createElement("td");
        let iconTd = document.createElement("td");
        let valueTd = document.createElement("td");
        nameTd.innerHTML = `<div>
                                <span class="transaction-name">`+element.name+`</span>
                                <span class="date2">`+dateKey+`</span>
                            </div>`;
        let imgName = element.type == "TRANSFER_OUT" ? "transaction-outcome.svg" : "transaction-income.svg";
        iconTd.innerHTML =  `<img src="../assets/img/${imgName}" alt="" width="30px">`
        iconTd.classList.add("right-cell");
        let operator = element.type == "TRANSFER_OUT" ? "-" : "+";
        let value = element.value > 0 ? element.value : -1 * element.value;
        valueTd.innerHTML = `<span class="transaction-type">`+operator+`</span>
                             <span class="transaction-currency">`+currencySymbol+`</span>
                             <span class="transaction-value">`+value+`</span>`
        valueTd.classList.add("right-cell");
        newTr.appendChild(nameTd);
        newTr.appendChild(iconTd);
        newTr.appendChild(valueTd);
        tbody.append(newTr)
    }
    table.appendChild(tbody);
    table.classList.add("transactions-table");
    tableField.appendChild(table);
}
function fillTransactions(transactions){
    for(let key in transactions){      
        createTable(key,transactions);       
    }
}

// Quick transfer
const accountNbInput = document.querySelector(".transfer-accountNb");
const currencyDropDown = document.querySelector("#transfer-currency");
const amountInput = document.querySelector(".amount-value");
const sendBtn = document.querySelector(".send-btn");
const resetBtn = document.querySelector(".reset-btn");
sendBtn.addEventListener("click", async () => {
    const accountNb = accountNbInput.value;
    const currency = currencyDropDown.value;
    const amount = amountInput.value;

    const res = await sendRequestPost("/wallet/transfer", {
        currency, amount, referenceId: accountNb
    });
    if(res.status === "SUCCESS") {
        alert("transfer done successfully");
        resetBtn.click();
        await init();
    }
    else{
        alert("Error sending transfer: "+ res.message);
    }
})

init();