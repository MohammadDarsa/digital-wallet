const wrapper = document.querySelector('.balances-list');
const addBalanceBtn = document.querySelector('#addBalanceBtn');
const addBalanceForm = document.querySelector('.addBalanceForm');
const submitBalanceForm = document.querySelector('#submitBalanceForm');
const dropDown = document.querySelector('.currency-dropdown');
let messagesContainer = document.querySelector(".messages-popup__list");
let messages;



let formOpen = false;
const setFormOpen = (bool) => {
    formOpen = bool;
    if(formOpen) addBalanceBtn.innerHTML = "Cancel";
    else addBalanceBtn.innerHTML = "Add Balance";
}


const fillBalances = async () => {
    const data = await getBalances();
    const currenciesData = await getCurrencies();
    if(!data) alert("error")
    const currencies = currenciesData.response.currencies.map(currency => currency.isoName);
    wrapper.innerHTML = '';
    for(let i = 0 ; i < currencies.length; i++) {
        let currencyI = currencies[i];
        wrapper.innerHTML += `
        <div class="balance-credit-card">
            <img src="assets/img/logo2.svg" alt="" width="35px" class="logo2">
            <img src="assets/img/MasterCard.svg" alt="" width="35px" class="master-card">
            <span class="card-number">${currencyI}</span>
            <span class="card-fname">Fatima</span>1
            <span class="card-lname">Baher</span>
            <span class="card-month">05/</span>
            <span class="card-year">24</span>
        </div>
        `;
    }

    populateDropDown(currencies);
}


const populateDropDown= (currencies) => {
    dropDown.innerHTML = "";
    for(let i = 0 ; i < currencies.length; i++) {
        dropDown.innerHTML += `
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








