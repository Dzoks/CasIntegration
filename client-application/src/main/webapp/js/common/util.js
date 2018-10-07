/**
 * UTILS
 */
var util = {



    dismissDialog: function (formName) {
        $$(formName).hide();
        $$(formName).destructor();
    },

};