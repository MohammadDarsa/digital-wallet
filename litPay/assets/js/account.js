const saveProfile = document.querySelector(".send-btn");
let data, messages;
let usernameInp = document.querySelector("#username");
let fnameInp = document.querySelector("#fname");
let lnameInp = document.querySelector("#lname");
let mobileInp = document.querySelector("#phone");
let emailInp = document.querySelector("#email");
let messagesContainer = document.querySelector(".messages-popup__list");

//adding event listeners
saveProfile.addEventListener("click", async ()=>{
    saveProfile.disabled = true;
    await setData();
    saveProfile.disabled = false;
})



//defining functions

async function getData(){
    let result = await sendRequestGet("/wallet/get-wallet-details");
    data = result.response;
}

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

async function setData(){
    await sendRequestPost("/profile/update-profile", {
        firstName: fnameInp.value,
        lastName: lnameInp.value,
        mobileNumber: mobileInp.value
    })
}
const getProfileInfo = async () => {
    await getData();
    usernameInp.value = data.wallet.user.username;
    fnameInp.value = data.profile.firstName;
    lnameInp.value = data.profile.lastName;
    mobileInp.value = data.profile.mobileNumber;
    emailInp.value = data.wallet.user.email;

}

getProfileInfo();
fillMessages();