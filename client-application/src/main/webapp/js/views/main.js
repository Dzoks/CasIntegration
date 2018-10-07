let mainLayout={
    id:"mainLayout",
    rows:[
        {
            height:50
        },
        {
            id:"documentsDT",
            view:"datatable",
            select: true,
            navigation: true,
            on:{
              onItemDblClick:function (id) {
                  mainLayout.showDetails($$("documentsDT").getItem(id));
              }  
            },
            columns:[
                {
                    id:"object",
                    name:"object",
                    hidden:true,
                },
                {
                    id:"documentType",
                    name:"documentType",
                    fillspace:true,
                    header:[
                        "Tip dokumenta",
                        {
                            content: "textFilter",
                            sort: "string"
                        }
                    ]
                },
                {
                    id:"jmbg",
                    name:"jmbg",
                    fillspace:true,
                    header:[
                        "JMBG",
                        {
                            content: "textFilter",
                            sort: "string"
                        }
                    ]
                },
                {
                    id:"fname",
                    name:"fname",
                    fillspace:true,
                    header:[
                        "Ime",
                        {
                            content: "textFilter",
                            sort: "string"
                        }
                    ]
                },
                {
                    id:"lname",
                    name:"lname",
                    fillspace:true,
                    header:[
                        "Prezime",
                        {
                            content: "textFilter",
                            sort: "string"
                        }
                    ]
                },
                {
                    id:"serialNumber",
                    name:"serialNumber",
                    fillspace:true,
                    header:[
                        "Serijski broj",
                        {
                            content: "textFilter",
                            sort: "string"
                        }
                    ]
                },
                {
                    id:"dateOfIssue",
                    name:"dateOfIssue",
                    fillspace:true,
                    format:function(value){

                      return  webix.Date.dateToStr("%d.%m.%Y")(new Date(value));
                    },
                    header:[
                        "Datum izdavanja",
                        {
                            content: "textFilter",
                            sort: "date"
                        }
                    ]
                },{
                    id:"validUntil",
                    name:"validUntil",
                    fillspace:true,
                    format:function(value){

                        return  webix.Date.dateToStr("%d.%m.%Y")(new Date(value));
                    },
                    header:[
                        "Važi do",
                        {
                            content: "textFilter",
                            sort: "string"
                        }
                    ]
                }

            ]
        }
    ],
    detailsPopup:{
        view:"popup",
        id:"detailsPopup",
        modal:true,
        position:"center",
        body:{
            rows:[
                {
                    view:"toolbar",
                    css:"panelToolbar",
                    cols:[
                        {},{

                            view: "icon",
                            icon: "close",
                            align: "right",
                            click: "util.dismissDialog('detailsPopup');"
                        }
                    ]
                },
                {
                    view: "form",
                    id: "documentForm",
                    borderless: true,
                    elementsConfig: {
                        labelWidth: 200,
                        bottomPadding: 18
                    },
                    cols:[
                        {
                            rows:[
                                {
                                    id: "id",
                                    name: "id",
                                    view: "text",
                                    hidden:true,
                                    readonly:"true",
                                    width: 400

                                },
                                {
                                    id: "male",
                                    name: "male",
                                    view: "text",
                                    hidden:true,
                                    readonly:"true",
                                    width: 400

                                },
                                {
                                    id: "fname",
                                    name: "fname",
                                    view: "text",
                                    label: "Ime:",
                                    readonly:"true",
                                    width: 400

                                },
                                {
                                    id: "lname",
                                    name: "lname",
                                    view: "text",
                                    label: "Prezime:",
                                    readonly:"true",
                                    width: 400
                                },
                                {
                                    id: "jmbg",
                                    name: "jmbg",
                                    view: "text",
                                    label: "JMBG:",
                                    readonly:"true",
                                    width: 400

                                },{
                                    id: "sex",
                                    name: "sex",
                                    view: "text",
                                    label: "Pol:",
                                    readonly:"true",
                                    width: 400
                                },
                                {
                                    id: "birthDate",
                                    name: "birthDate",
                                    view: "datepicker",
                                    format:webix.Date.dateToStr("%d.%m.%Y."),
                                    label: "Datum rođenja:",
                                    readonly:"true",

                                },
                                {
                                    id:"placeOfBirth",
                                    name:"placeOfBirth",
                                    view:"text",
                                    label:"Mjesto rođenja:",
                                    readonly:"true",
                                    width: 400,

                                },
                            ]
                        },
                        {
                            rows:[
                                {
                                    id:"serialNumber",
                                    name:"serialNumber",
                                    view:"text",
                                    label:"Serijski broj:",
                                    readonly:true,
                                    width: 400,
                                },
                                {
                                    id:"issuingAuthority",
                                    name:"issuingAuthority",
                                    view:"text",
                                    label:"Nadležni organ:",
                                    readonly:true,
                                    width: 400,
                                },
                                {
                                    id:"residence",
                                    name:"residence",
                                    view:"text",
                                    label:"Prebivalište:",
                                    readonly:true,
                                    width: 400,
                                },
                                {
                                    id:"citizenship",
                                    name:"citizenship",
                                    view:"text",
                                    label:"Državljanstvo:",
                                    readonly:true,
                                    width: 400,
                                },
                                {
                                    id:"dateOfIssue",
                                    name:"dateOfIssue",
                                    view:"datepicker",
                                    stringResult:true,

                                    format:webix.Date.dateToStr("%d.%m.%Y."),
                                    label:"Datum izdavanja:",
                                    readonly:true,
                                    width: 400,
                                },
                                {
                                    id:"validUntil",
                                    name:"validUntil",
                                    view:"datepicker",
                                    stringResult:true,

                                    format:webix.Date.dateToStr("%d.%m.%Y."),
                                    label:"Važi do:",
                                    readonly:true,
                                    width: 400,
                                },
                            ]
                        },
                        {
                            rows:[
                                {
                                    id:"countryCode",
                                    name:"countryCode",
                                    view:"text",
                                    label:"Kod države:",
                                    readonly:true,
                                    width: 400,
                                },
                                {
                                    id:"entityCitizenship",
                                    name:"entityCitizenship",
                                    view:"text",
                                    label:"Entitetsko državljanstvo:",
                                    readonly:true,
                                    width: 400,
                                },
                                {
                                    id:"documentType",
                                    name:"documentType",
                                    view:"text",
                                    label:"Tip dokumenta:",
                                    readonly:true,
                                    width: 400,
                                },

                                {
                                    view:"datatable",
                                    id:"categories",
                                    width:400,
                                    columns:[
                                        {
                                            id:"category",
                                            name:"category",
                                            width:180,
                                            header:"Kategorija"
                                        },
                                        {
                                            id:"examDate",
                                            name:"examDate",
                                            format:webix.Date.dateToStr("%d.%m.%Y."),
                                            stringResult:true,

                                            width:200,
                                            header:"Datum polaganja"
                                        }
                                    ]
                                },

                            ]
                        }
                    ],
                },
                {
                    id: "saveDocumentBtn",
                    view: "button",
                    value: "Preuzmite dokument",
                    click: "mainLayout.saveDocument",
                    align: "right",
                    hotkey: "enter",
                    width: 150,
                },
            ]
        }
    },
    showDetails(document){
        var object=webix.copy(document);
        object.birthDate=new Date(object.birthDate);
        object.validUntil=new Date(object.validUntil);
        object.dateOfIssue=new Date(object.dateOfIssue);
        object.sex=object.male===1?"Muško":"Žensko";
        if (object.categories.length>0){
            object.categories.forEach(c=>c.examDate=new Date(c.examDate));
        }
        webix.ui(webix.copy(mainLayout.detailsPopup)).show();
        switch (object.documentType) {
            case "Lična karta":
                $$("categories").hide();
                $$("countryCode").hide();
                break;
            case "Pasoš":
                $$("categories").hide();
                $$("entityCitizenship").hide();
                break;
            case "Vozačka dozvola":
                $$("countryCode").hide();
                $$("entityCitizenship").hide();
                $$("categories").parse(object.categories);
                break;
        }
        $$("documentForm").setValues(object);
    },

    saveDocument(){
        webix.toPNG($$("documentForm"),{download:false}).then(blob=>{
            var reader = new FileReader();
            reader.readAsDataURL(blob);
            reader.onloadend = function() {
                var base64data = reader.result;
                    var docDefinition = {
                        pageSize: {height: 410, width: 1310},
                        content: [
                            {
                                image: base64data,
                            }
                        ]
                    };
                    pdfMake.createPdf(docDefinition).download('Dokument.pdf');
            }


        }).catch(err=>console.log(err));


    }
};
var converterEngine = function (input) { // fn BLOB => Binary => Base64 ?
    var uInt8Array = new Uint8Array(input),
        i = uInt8Array.length;
    var biStr = []; //new Array(i);
    while (i--) { biStr[i] = String.fromCharCode(uInt8Array[i]);  }
    var base64 = window.btoa(biStr.join(''));
    return base64;
};
