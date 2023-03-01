const wrapper = document.querySelector('.balances-list');
const addBalanceBtn = document.querySelector('#addBalanceBtn');
const addBalanceForm = document.querySelector('.addBalanceForm');
const submitBalanceForm = document.querySelector('#submitBalanceForm');
const dropDown = document.querySelector('.currency-dropdown');
let topupCurrency = document.querySelector("#topup-currency");
let currency = document.querySelector(".currency-symbol");
let messagesContainer = document.querySelector(".messages-popup__list");
let messages;



topupCurrency.addEventListener("change",()=>{
    setSymbol();
})


function setSymbol(){
    currency.innerHTML = topupCurrency.value=="USD"? "$" : "L.L ";
}
let formOpen = false;
const setFormOpen = (bool) => {
    formOpen = bool;
    if(formOpen) addBalanceBtn.innerHTML = "Cancel";
    else addBalanceBtn.innerHTML = "Add Balance";
}


const fillBalances = async () => {
    const data = await getBalances();
    let fname = data.response.profile.firstName;
    let lname = data.response.profile.lastName;
    const currenciesData = await getCurrencies();
    const userCurrenciesData = await getUserCurrencies();
    if(!data) alert("error")
    const currencies = currenciesData.response.currencies.map(currency => currency.isoName);
    const userCurrencies = userCurrenciesData.response.currencies.map(currency => currency.isoName);
    wrapper.innerHTML = '';
    for(let i = 0 ; i < userCurrencies.length; i++) {
        let currencyI = userCurrencies[i];
        wrapper.innerHTML += `
        <div class="balance-credit-card">
            <img src="assets/img/logo2.svg" alt="" width="35px" class="logo2">
            <img src="assets/img/MasterCard.svg" alt="" width="35px" class="master-card">
            <span class="card-number">${currencyI}</span>
            <span class="card-fname">${fname}</span>
            <span class="card-lname">${lname}</span>
        </div>
        `;
    }

    populateDropDown(currencies);
}


const populateDropDown= (currencies) => {
    const topupDropDown = document.querySelector("#topup-currency")
    const headerDropDown = document.querySelector("#select-currency")
    dropDown.innerHTML = "";
    headerDropDown.innerHTML = "";
    topupDropDown.innerHTML = "";
    for(let i = 0 ; i < currencies.length; i++) {
        dropDown.innerHTML += `
            <option value="${currencies[i]}">${currencies[i]}</option>
        `;
        topupDropDown.innerHTML += `
            <option value="${currencies[i]}">${currencies[i]}</option>
        `;
        headerDropDown.innerHTML += `
            <option value="${currencies[i]}">${currencies[i]}</option>
        `;
    }
}


const getBalances = async () => {
    const res = await sendRequestGet("/wallet/get-wallet-details");
    return res;
}

const getCurrencies = async () => {
    const res = await sendRequestGet("/currency/get-all-currencies");
    return res;
}

const getUserCurrencies = async () => {
    const res = await sendRequestGet("/currency/get-user-currencies");
    return res;
}

fillBalances();
fillMessages();


addBalanceBtn.addEventListener('click', () => {
    if(formOpen){
        setFormOpen(false)
        return addBalanceForm.style.display = 'none';  
    } 
    setFormOpen(true)
    addBalanceForm.style.display = 'flex'
})

submitBalanceForm.addEventListener("click", async () => {
    let body = {
        currency: dropDown.value
    }
    await sendRequestPost("/wallet/create-new-balance", body);
    fillBalances();
    addBalanceBtn.click();
});

async function getMessages(){
    let msgResult = await sendRequestGet("/messages");
    messages = msgResult;
}

async function fillMessages(){
    await getMessages();
    messagesContainer.innerHTML="";
    messages.forEach(element=>{

        let newLI = document.createElement("li");
        newLI.innerHTML = "<b>"+element["sender"] +": </b>"+ element["messageBody"];
        if(messagesContainer.childNodes.length==0)
            messagesContainer.appendChild(newLI);
        else
            messagesContainer.insertBefore(newLI, messagesContainer.childNodes[0]);
    })
}


//topup
const cvvInput = document.querySelector(".cvv");
const panInput = document.querySelector(".pan");
const expInput = document.querySelector(".exp");
const currencyDropDown = document.querySelector("#topup-currency");
const amountInput = document.querySelector(".amount-value");
const okBtn = document.querySelector(".ok-btn");
const resetBtn = document.querySelector(".reset-btn");
okBtn.addEventListener("click", async () => {
    const cvv = cvvInput.value;
    const pan = panInput.value;
    const expFull = expInput.value;
    const currency = currencyDropDown.value;
    const amount = parseFloat(amountInput.value);
    const date = new Date(expFull);
    const year = date.getFullYear().toString().slice(-2);
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const exp = year+"/"+month;
    console.log(exp); 
    const res = await sendRequestPost("/wallet/top-up", {
        "currency": currency, "cvv": cvv, "exp": exp, "pan": pan, "amount": amount
    });
    if(res.status === "SUCCESS") {
        alert("topup done successfully");
        resetBtn.click();
    }
    else{
        alert("Error in topup: "+ res.message);
    }
})







