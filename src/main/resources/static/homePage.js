$("#menu").on("change", logOut);

function logOut(){
    if($("#menu").val() == "LogOut"){
        window.location.href='/logOut';
    }
}