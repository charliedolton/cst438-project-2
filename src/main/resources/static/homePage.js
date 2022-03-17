$("#menu").on("change", logOut);
//$('#profile').attr('src', 'https://mywaterearth.com/wp-content/uploads/2020/12/261946-1600x1030-take-care-new-goldfish-properly-1.jpg?ezimgfmt=ng:webp/ngcb83');
function logOut(){
    if($("#menu").val() == "LogOut"){
        window.location.href='/logOut';
    }
}
async function fetchData(url) {
    let response = await fetch(url);
    let data = await response.json();
    return data;
}
/*<div id ="img">
    <img id= "profile" width="50" height="50"  th:src="@{/img/nullProfile.png}">

</div>*/