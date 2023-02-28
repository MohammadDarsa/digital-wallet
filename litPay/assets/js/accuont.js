let data;
let usernameInp = document.querySelector("#username");
let fnameInp = document.querySelector("#fname");
let lnameInp = document.querySelector("#lname");
let mobileInp = document.querySelector("#phone");
let emailInp = document.querySelector("#email");
async function getData(){
    let result = await sendRequestGet("/wallet/get-wallet-details");
    data = result.response;
}

function setData(){
    //
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