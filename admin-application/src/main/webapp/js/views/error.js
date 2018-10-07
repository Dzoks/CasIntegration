let errorLayout={
    id:"errorLayout",
    rows:[
        {
            height:50,
        },
        {
            cols:[
                {

                },
                {
                    rows:[
                        {
                            view:"template",
                            template:`<h4>Molimo unesite vaš token da nastavite sa radom:</h4>`,
                            height:60,
                            borderless: true
                        },
                        {
                            view: "form",
                            id: "tokenForm",
                            borderless: true,
                            width: 510,
                            elementsConfig: {
                                labelWidth: 140,
                                bottomPadding: 18
                            },
                            cols: [
                                {
                                    id: "token",
                                    name: "token",
                                    view: "text",
                                    label: "Token:",
                                    invalidMessage: "Token je obavezan!",
                                    required: true,

                                },
                                {
                                    id: "loginBtn",
                                    view: "button",
                                    value: "Prijavite se",
                                    type: "form",
                                    click: "errorLayout.sendToken",
                                    align: "right",
                                    hotkey: "enter",
                                    width: 150,

                                }
                            ]
                        },
                    ]
                },
                {

                }
            ]
        },
        {

        }
    ],
    sendToken(){
        const form=$$("tokenForm");
        if (form.validate()){
            webix.ajax().post("state/token",form.getValues()).then(res=>{
                userData=res.json();
                showApp(userData);
            }).catch(err=>{
                if (err.status===401){
                    showUnauthorized();
                }else{
                    webix.message("Token nije validan!");
                }
            });
        }
    }
};