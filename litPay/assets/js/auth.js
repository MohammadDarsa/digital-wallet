async function sendRequestPost(url, body){
    let myHeaders = new Headers();
    myHeaders.append("Authorization", "Bearer " + getToken());
    myHeaders.append("Content-Type", "application/json");

    let requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify(body),
        redirect: 'follow'
    };

    let result = await fetch("http://localhost:8080/api"+url, requestOptions);
    const data = await result.json();
    return data;
    

}

async function sendRequestGet(url){
    let myHeaders = new Headers();
    myHeaders.append("Authorization", "Bearer " + getToken());

    let requestOptions = {
        method: "GET",
        headers: myHeaders,
    };

    let result = await fetch("http://localhost:8080/api"+url, requestOptions);
    const data = await result.json();
    return data;
    
}

const getToken = () => {
    return localStorage.getItem("token");
}

function setToken(token){
    localStorage.setItem("token", token);
}

