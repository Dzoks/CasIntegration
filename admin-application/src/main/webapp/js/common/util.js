const util={
    dismissDialog(formName) {
        $$(formName).hide();
        $$(formName).destructor();
    }
};

