var panel = {id: "empty"};
let userMap=[];
var init = function () {
    webix.Date.startOnMonday = true;
    webix.ui(panel);
    panel = $$("empty");
    document.getElementById('logoutBtn').onclick=function () {
        window.location+="/logout";
    };
    webix.ajax().get("state").then((res)=>{
        userData=res.json();
        showApp(userData);
    }).catch(err=>{
        document.getElementById("logoutBtn").hidden=false;
        if (err.status===403){
            var error = webix.copy(errorLayout);
            webix.ui(error, panel);
            panel = $$("errorLayout");
        }else if (err.status===401){

            showUnauthorized();
        }else{
            console.log(err);
     //       window.location.reload();
        }

    });

};
const showUnauthorized=function(){

    document.getElementById("logoutBtn").hidden=false;
    var error = webix.copy(unauthorizedLayout);
    webix.ui(error, panel);
    panel = $$("unauthorizedLayout");
};
const showApp=function(userData){

    document.getElementById("logoutBtn").hidden=false;
    document.getElementById('welcomeText').innerHTML=`Dobrodošli, <b>${userData.username}</b>&nbsp;&nbsp;`;
    var main = webix.copy(mainLayout);
    webix.ui(main, panel);
    panel = $$("mainLayout");
    webix.ajax().get("document-type").then(res=>{
        $$("documentType").define("options",res.json());
        $$("documentType").refresh();
    });
    webix.ajax().get("user").then(res=>{
        const users=res.json();
        userMap=[];

        users.forEach(user=>{
            user.value=`${user.jmbg} - ${user.fname} ${user.lname}`;
            userMap[user.id]=user;
        });
        $$("userId").define("options",users);
        $$("userId").refresh();
    });
    webix.ajax().get("driving-category").then(res=>{
        categoryArray=res.json();
        categoryMap=[];
        categoryArray.forEach(cat=>{
            categoryMap[cat.id]=cat.value;
        });
    });
    $$("documentForm").hide();
};

//main call
window.onload = function () {
    init();
};

