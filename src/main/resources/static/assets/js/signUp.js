$("#un").on("change", checkForExixitngUsername);// if you change whats inside the textfiled of username

async function checkForExixitngUsername() {
    var username = $('[name="username"]').val();
    var result = await fetchData(`/api/userIsTaken?username=${username}`);

    if(result == true){
        $("#signUpButton").prop('disabled', true);
        $("#error").html( "username is taken!");
    }
    else{
        $("#signUpButton").prop('disabled', false);
        $("#error").html( "");
    }
}

async function fetchData(url) {
    let response = await fetch(url);
    let data = await response.json();
    return data;
}