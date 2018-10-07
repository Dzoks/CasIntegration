let mainLayout = {
    id: "mainLayout",
    rows: [
        {
            height: 50
        },
        {
            cols: [
                {
                    view: "form",
                    id: "pickUserForm",
                    borderless: true,
                    elementsConfig: {
                        labelWidth: 140,
                        bottomPadding: 18
                    },
                    elements: [
                        {
                            id: "userId",
                            name: "userId",
                            view: "combo",
                            label: "Korisnik:",
                            invalidMessage: "Morate odabrati korisnika!",
                            on:{
                                onChange:function(){
                                    $$("documentForm").hide();
                                },
                            },

                            required: true,
                            width: 400

                        },
                        {
                            id: "documentType",
                            name: "documentType",
                            view: "combo",
                            on:{
                                onChange:function(){
                                    $$("documentForm").hide();
                                },
                            },
                            label: "Tip dokumenta:",
                            invalidMessage: "Morate odabrati tip dokumenta!",
                            required: true,

                        },
                        {
                            id: "showDocumentForm",
                            view: "button",
                            value: "Odaberite",
                            type: "form",
                            click: "mainLayout.selectUser",
                            align: "right",
                            hotkey: "enter",
                            width: 150,
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
                                    required:true,
                                    width: 400,
                                },
                                {
                                    id:"issuingAuthority",
                                    name:"issuingAuthority",
                                    view:"text",
                                    label:"Nadležni organ:",
                                    required:true,
                                    width: 400,
                                },
                                {
                                    id:"residence",
                                    name:"residence",
                                    view:"text",
                                    label:"Prebivalište:",
                                    required:true,
                                    width: 400,
                                },
                                {
                                    id:"citizenship",
                                    name:"citizenship",
                                    view:"text",
                                    label:"Državljanstvo:",
                                    required:true,
                                    width: 400,
                                },
                                {
                                    id:"dateOfIssue",
                                    name:"dateOfIssue",
                                    view:"datepicker",
                                    stringResult:true,

                                    format:webix.Date.dateToStr("%d.%m.%Y."),
                                    label:"Datum izdavanja:",
                                    required:true,
                                    width: 400,
                                },
                                {
                                    id:"validUntil",
                                    name:"validUntil",
                                    view:"datepicker",
                                    stringResult:true,

                                    format:webix.Date.dateToStr("%d.%m.%Y."),
                                    label:"Važi do:",
                                    required:true,
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
                                    required:true,
                                    width: 400,
                                },
                                {
                                    id:"entityCitizenship",
                                    name:"entityCitizenship",
                                    view:"text",
                                    label:"Entitetsko državljanstvo:",
                                    required:true,
                                    width: 400,
                                },
                                {
                                    id: "addCategoryBtn",
                                    view: "button",
                                    value: "Dodajte kategoriju",

                                    click: "mainLayout.addLicence",
                                    align: "right",
                                    padding:10,
                                    width: 150,
                                },

                                {
                                    view:"datatable",
                                    id:"categories",
                                    width:400,
                                    columns:[
                                        {
                                            id:"drivingCategoryId",
                                            name:"drivingCategoryId",
                                            width:180,
                                            template:function(data){
                                                return categoryMap[data.drivingCategoryId];
                                            },
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
                                {
                                    id: "addDocumentBtn",
                                    view: "button",
                                    value: "Dodajte dokument",
                                    type: "form",
                                    click: "mainLayout.addDocument",
                                    align: "right",
                                    hotkey: "enter",
                                    width: 150,
                                },
                            ]
                        }
                    ],
                },
            ]
        }
    ],

    selectUser(){
        const form=$$("pickUserForm");
        if (form.validate()){
            const addForm=$$("documentForm");
            const user=userMap[form.elements.userId.getValue()];
            addForm.setValues(user);
            $$("sex").setValue(user.male===1?"Muško":"Žensko");
            const documentType=form.elements.documentType.getValue();
            switch(documentType){
                case properties.passport:
                    $$("entityCitizenship").define("required",false);
                    $$("entityCitizenship").refresh();
                    $$("entityCitizenship").hide();
                    $$("countryCode").define("required",true);
                    $$("countryCode").refresh();
                    $$("countryCode").show();
                    $$("categories").hide();
                    $$("addCategoryBtn").hide();
                    break;
                case properties.drivingLicence:
                    $$("entityCitizenship").define("required",false);
                    $$("entityCitizenship").refresh();
                    $$("entityCitizenship").hide();
                    $$("countryCode").define("required",false);
                    $$("countryCode").refresh();
                    $$("countryCode").hide();
                    $$("categories").show();
                    $$("addCategoryBtn").show();
                    break;
                case properties.personalId:
                    $$("entityCitizenship").define("required",true);
                    $$("entityCitizenship").refresh();
                    $$("entityCitizenship").show();
                    $$("countryCode").define("required",false);
                    $$("countryCode").refresh();
                    $$("countryCode").hide();
                    $$("categories").hide();
                    $$("addCategoryBtn").hide();
                    break;
            }
            addForm.show();
        }
    },


    addLicenceCategoryDialog:{
        view:"popup",
        id:"addLicenceCategoryDialog",
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
                            click: "util.dismissDialog('addLicenceCategoryDialog');"
                        }
                    ]
                },
                {
                    view: "form",
                    id: "addLicenceForm",
                    borderless: true,
                    width: 500,
                    elementsConfig: {
                        labelWidth: 200,
                        bottomPadding: 18
                    },
                    elements: [
                        {
                            id: "drivingCategoryId",
                            name: "drivingCategoryId",
                            view: "combo",
                            label: "Kategorija:",
                            invalidMessage: `Kategorija je obavezna!`,
                            required: true
                        },
                        {
                            id: "examDate",
                            name: "examDate",
                            view: "datepicker",
                            format:webix.Date.dateToStr("%d.%m.%Y."),
                            stringResult:true,

                            label: "Datum polaganja:",
                            invalidMessage: `Datum polaganja je obavezan!`,
                            required: true
                        },
                        {
                            view: "button",
                            value: "Dodajte",
                            type: "form",
                            click: "mainLayout.addSpecificLicence",
                            align: "right",
                            hotkey: "enter",
                            width: 150
                        }
                    ]
                }
            ]
        }
    },

    addDocument(){
        const form=$$("documentForm");
        const pickForm=$$("pickUserForm");
        if (form.validate()){
            if (pickForm.elements.documentType.getValue()===properties.drivingLicence && $$("categories").count()===0){
                webix.message("Morate unijeti barem jednu kategoriju!","error");
                return;
            }


            let documentToAdd=form.getValues();
            documentToAdd.userId=documentToAdd.id;
            documentToAdd.id=null;
            documentToAdd.documentTypeId=pickForm.elements.documentType.getValue();
            documentToAdd.validUntil+=":00";
            documentToAdd.dateOfIssue+=":00";
            const newDocument={
                document:documentToAdd,
                categories:[],
            };
            if (documentToAdd.documentTypeId===properties.drivingLicence){
                const cats=[];
                $$("categories").data.each(e=>{
                    e.examDate+=":00";
                    newDocument.categories.push(e)
                });
            }
            console.log(newDocument);
            webix.ajax().headers({"Content-type":"application/json"}).post("document",JSON.stringify(newDocument)).then(res=>{
                if (res.json()){
                    webix.message("Uspješno dodavanje!");
                    $$("pickUserForm").setValues({});

                }
            }).catch(err=>{
                webix.message("Greška prilikom dodavanja!","error");
                console.log(err);
            });
        }
    },


    addSpecificLicence(){
        const form=$$("addLicenceForm");
        if (form.validate()){
            $$("categories").add(form.getValues());
            util.dismissDialog("addLicenceCategoryDialog");
        }
    },
    addLicence(){
        webix.ui(webix.copy(mainLayout.addLicenceCategoryDialog)).show();
        webix.UIManager.setFocus("drivingCategoryId");
        const cats = [];
        $$("categories").data.each(e=>{
            e.id=null;
            cats.push(e);
        });
        const existingCategories=cats.map(c=>c.drivingCategoryId);
        const filtered=categoryArray.filter(el=>{
            return !existingCategories.includes(el.id);
        });
        $$("drivingCategoryId").define("options",filtered);
        $$("drivingCategoryId").refresh();
    }
};

