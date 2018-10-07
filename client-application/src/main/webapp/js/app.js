var panel = {id: "empty"};

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
            const error = webix.copy(errorLayout);
            webix.ui(error, panel);
            panel = $$("errorLayout");
        }else{
            window.location.reload();
        }

    });

};
const showApp=function(userData){

    document.getElementById("logoutBtn").hidden=false;
    document.getElementById('welcomeText').innerHTML=`Dobrodošli, <b>${userData.username}</b>&nbsp;&nbsp;`;
    var main = webix.copy(mainLayout);
    webix.ui(main, panel);
    panel = $$("mainLayout");
    webix.ajax().get("document").then(res=>{
        const jsObject=res.json();
        const documentArray=[];
        jsObject.documents.forEach(el=>{
            documentArray.push(el);
        });
        $$("documentsDT").parse(documentArray);
    });
};

//main call
window.onload = function () {
    init();
};

