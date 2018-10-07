/**
 * UTILS
 */
var util = {

    //common - utils

    stringContains: function (string, test) {
        return string.indexOf(test) > -1;
    },

    stringStartsWith: function (string, test) {
        return string.indexOf(test) == 0;
    },

    isset: function (variable) {
        return typeof variable !== typeof undefined ? true : false;
    },

    arrayToCsv: function (array) {
        var retVal = "";
        for (var i = 0; i < array.length; i++) {
            retVal += array[i];
            if (i < array.length - 1) retVal += ",";
        }
        return retVal;
    },

    formatDate: function (date) {
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();
        hours = hours < 10 ? '0' + hours : hours;
        minutes = minutes < 10 ? '0' + minutes : minutes;
        seconds = seconds < 10 ? '0' + seconds : seconds;
        var strTime = hours + ':' + minutes + ':' + seconds;
        return date.getDate() + "." + (date.getMonth() + 1) + "." + date.getFullYear() + ".  " + strTime;
    },

    addCSSRule: function (selector, rules, index) {
        if (!util.isset(util.definedCssRules)) {
            util.definedCssRules = [];
        }

        if (util.definedCssRules.indexOf(selector) < 0) {
            util.definedCssRules.push(selector);
            var style = document.createElement("style");
            document.head.appendChild(style);

            if ("insertRule" in style.sheet) {
                style.sheet.insertRule(selector + "{" + rules + "}", index);
            }
            else if ("addRule" in style.sheet) {
                style.sheet.addRule(selector, rules, index);
            }
        }
    },


    calculateTooltipPosition: function (mouseX, mouseY, maxWidth, maxHeight) {
        var whereX = mouseX;
        var whereY = mouseY;

        var wWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
        if (whereX + maxWidth > wWidth) {
            whereX = wWidth - (maxWidth + 50);
        }

        var wHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
        if (whereY + maxHeight > wHeight) {
            whereY = wHeight - (maxHeight + 50);
        }

        return {
            x: whereX,
            y: whereY
        }
    },

    // preloader
    preloader: {
        state: 0,

        inc: function () {
            if (this.state == 0) {
                document.getElementById("preloader").style.display = "block";
                document.getElementById("menu-collapse").style.display = "none";
            }
            this.state++;
        },

        dec: function () {
            if (this.state == 0) return;
            this.state--;
            if (this.state == 0) {
                document.getElementById("preloader").style.display = "none";
                document.getElementById("menu-collapse").style.display = "block";
            }
        },

        reset: function () {
            this.state = 0;
            document.getElementById("preloader").style.display = "none";
            document.getElementById("menu-collapse").style.display = "block";
        }
    },

    images: {
        noImageUrl: "/img/no-image.png",
        imageUrl: "/hub/admin/image/",

        imageIconAreaClicked: function () {
            $$("imageUploader").fileDialog();
        },

        //defult changed from contain to cover, dimensions changed also
        pictureTemplate: function (width, height, image, prop) {
            var chosenImage;
            var backgroundSize;

            if (util.isset(image)) {
                chosenImage = image;
                backgroundSize = "cover";
            }
            else {
                chosenImage = this.noImageUrl;
                backgroundSize = "initial";
            }

            if (util.isset(prop)) {
                backgroundSize = prop;
            }
            var imagePlacer = '<div onclick="util.images.imageIconAreaClicked()" style="cursor:pointer; margin:auto; margin-top: 12px;background: url(' + chosenImage + ') center no-repeat;width:' + width + 'px;height:' + height + 'px; background-size:' + backgroundSize + '"></div>';
            var progressBar = '<div id="uploadProgressBar"><div id="uploadProgressInside"></div></div>';
            return imagePlacer + progressBar;
        },

        staticPictureTemplate: function (width, height, image, prop) {
            var chosenImage;
            var backgroundSize;

            if (util.isset(image)) {
                chosenImage = image;
                backgroundSize = "contain";
            }
            else {
                chosenImage = this.noImageUrl;
                backgroundSize = "initial";
            }

            if (util.isset(prop)) {
                backgroundSize = prop;
            }
            var imagePlacer = '<div style=" margin:auto; margin-top: 10px;background: url(' + chosenImage + ') center no-repeat;width:' + width + 'px;height:' + height + 'px; background-size:' + backgroundSize + '"></div>';
            return imagePlacer;
        }
    },
    //common validation

    validation: {
        checkStandardInput: function (value) {
            if (!value) return false;
            return (value.length > 1 && value.length < 45);
        },
        checkStandardInputMinMax: function (value, min, max) {
            return (value.length >= min && value.length <= max);
        },
        checkSupportedTypes: function (type, typesStr) {
            var suppTypesArray = typesStr.split(',');
            var supported = false;
            for (var i = 0; i < suppTypesArray.length; i++) {
                if (type == suppTypesArray[i]) {
                    supported = true;
                    break;
                }
            }

            return supported;
        },

        checkPositiveNumber: function (value, errorMessageTarget, errorMessage) {
            var valid = true;
            if (isNaN(value)) valid = false;
            else valid = (value > 0);

            if (!valid)
                $$(errorMessageTarget).setHTML(errorMessage);
            return valid;
        },

        checkMacAddress: function (mac) {
            var macRegex = new RegExp("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
            return macRegex.test(mac);
        },
        checkPhoneNumber: function (mac) {
            var macRegex = new RegExp("^[\+]?[0-9]{1,20}$");
            return macRegex.test(mac);
        },

        valueHasWhitespaces: function (value) {
            return (/\s/.test(value));
        },

        validateUponEdit: function (editor, type) {
            switch (type) {
                case "number": {
                    var check = (editor.getValue() != "" && !isNaN(editor.getValue()));
                    if (!check) {
                        util.messages.showErrorMessage("Molimo unesite ispravan broj!");
                        return false;
                    } else return true;
                }
                    break;
            }

            return true;
        },
        checkLength: function (value, length) {
            return value.length <= length;
        },
        isInteger(value) {
            return (!isNaN(value) && parseInt(value, 10) &&
                !value.includes("."));
        }
    },

    //common messages

    messages: {
        showMessage: function (message) {
            webix.message({type: "default", text: message});
        },
        showErrorMessage: function (message) {
            webix.message({type: "error", text: message});
        },
        showWarningMessage: function (message, expire) {
            webix.message({type: "warning", text: message, expire: expire || 0});
        },
        showSessionExpiredError: function () {
            webix.message({type: "error", text: "Vaša sesija je istekla. Prijavite se ponovo..."});
        },
        showLogoutMessage: function () {
            webix.message({type: "defult", text: "Uspješno ste se odjavili."});
        },

        showLogoutErrorMessage: function () {
            webix.message({
                type: "default",
                text: "Greška prilikom odjavljivanja. Aplikacija će biti ponovo učitana..."
            });
        }
    },


    isAdmin: function () {
        return true;
    },

    dismissDialog: function (formName) {
        $$(formName).hide();
        $$(formName).destructor();
    },

    time: {
        getTodayAtMidnight: function () {
            var d = new Date();
            d.setHours(0);
            d.setMinutes(0);
            d.setSeconds(0);

            return d;
        },

        getFormattedDurationFromSeconds: function (seconds) {
            var hours = parseInt(seconds / 3600) % 24;
            var minutes = parseInt(seconds / 60) % 60;

            var result = (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + "h"/* + ":" + (seconds < 10 ? "0" + seconds : seconds)*/;

            return result;
        },

        getHhMm: function (hours, minutes) {
            //return 'bla';
            return (hours >= 10 ? hours : ('0' + hours)) + ":" + (minutes >= 10 ? minutes : ('0' + minutes));
        },

        getDuration: function (start, end) {
            var totalSec = parseInt((end - start) / 1000);

            var hours = parseInt(totalSec / 3600) % 24;
            var minutes = parseInt(totalSec / 60) % 60;
            //var seconds = parseInt(totalSec % 60);

            var result = (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + "h"/* + ":" + (seconds < 10 ? "0" + seconds : seconds)*/;

            return result;

        },

        getFormatedDate: function (time) {
            var d = new Date(time);
            return d.getDate() + "." + (d.getMonth() + 1) + "." + d.getFullYear() + ". ";

        },

        getFormatedStartEnd: function (start, end) {
            var from = new Date(start);
            var to = new Date(end);

            return this.getHhMm(from.getHours(), from.getMinutes()) + " - " + this.getHhMm(to.getHours(), to.getMinutes());

        },

        getFormatedDateDash: function (date) {
            var format = webix.Date.dateToStr("%Y-%m-%d-%H-%i-%s");
            return format(date);
        }
    },

    export: {
        exportToCsv: function (entityName, data) {
            var csvContent = "";
            data.forEach(function (infoArray, index) {

                dataString = infoArray.join(";");
                csvContent += index < data.length ? dataString + "\n" : dataString;

            });


            if (navigator.msSaveBlob) {
                var blob = new Blob(["\uFEFF" + csvContent], {type: "text/csv;charset=utf-8;"});
                navigator.msSaveBlob(blob, entityName + "-" + util.time.getFormatedDateDash(new Date()) + ".csv")
            } else {
                var encodedUri = encodeURI(csvContent);
                var link = document.createElement("a");
                link.setAttribute("href", "data:text/csv;charset=utf-8,%EF%BB%BF" + encodedUri);
                link.setAttribute("download", entityName + "-" + util.time.getFormatedDateDash(new Date()) + ".csv");
                document.body.appendChild(link);

                link.click();
            }
        }
    },

    checkboxes: {
        importCsvStateCheckbox: function (obj, common, value) {
            if (obj.state != null && obj.state == 0)
                return "<span style='color:green; padding:2px 7px; background:#f1f1f1; border-radius:3px'>Uspešno</span>";
            else if (obj.state != null && obj.state == 2)
                return "<span style='color:#e9b32f; padding:2px 7px; background:#f1f1f1; border-radius:3px'>Postoji</span>";
            else
                return "<span style='color:red;padding:2px 7px; background:#f1f1f1; border-radius:3px'>Greška</span>";
        },
        deviceTypeCheckbox: function (obj, common, value) {
            console.log(obj.tipUredjaja);
            if (obj.tipUredjaja && obj.tipUredjaja == 0)
                return "<span style='color:dimgrey; padding:2px 7px; background:#f1f1f1; border-radius:3px'><span class='fa fa-phone'></span> Telefon</span>";
            else if (obj.tipUredjaja == 1)
                return "<span style='color:dimgrey;padding:2px 7px; background:#f1f1f1; border-radius:3px'><span class='fa fa-tv'></span> STB</span>";
            else return "";
        },
        publishedCheckbox: function (obj, common, value) {
            if (obj.published)
                return "<span style='color:green; padding:2px 7px; background:#f1f1f1; border-radius:3px'> DA</span>";
            else
                return "<span style='color:red;padding:2px 7px; background:#f1f1f1; border-radius:3px'> NE</span>";
        },
        completedCheckbox: function (obj, common, value) {
            if (obj.completed)
                return "<span style='color:green; padding:2px 7px; background:#f1f1f1; border-radius:3px'> DA</span>";
            else
                return "<span style='color:red;padding:2px 7px; background:#f1f1f1; border-radius:3px'> NE</span>";
        },
        canModifyCheckbox: function (obj, common, value) {
            if (obj.canModify)
                return "<div class='webix_table_checkbox checked'><div class='visibleHiddenCheckbox'><span class='fa fa-eye'></span> DA</div></div>";
            else
                return "<div class='webix_table_checkbox notchecked'><div class='visibleHiddenCheckbox'><span class='fa fa-eye-slash'></span> NE</div></div>";
        },

        toggleCheckbox: function (obj, common, value) {
            if (value)
                return "<div class='webix_table_checkbox onOffCheckboxContainer checked'><span class='checkArea'></span></div>";
            else
                return "<div class='webix_table_checkbox onOffCheckboxContainer notchecked'><span class='checkArea'></span></div>";
        },

        nonEditableYesNoField: function (obj, common, value) {
            if (value)
                return '<div class="yesNoField checked"><span class="nonCheckableCheckArea"></span></div>';
            else
                return '<div class="yesNoField notchecked"><span class="nonCheckableCheckArea"></span></div>';
        }

    },

    templates: {
        currentlyActiveHotelsTemplate: function (item, hasInfo, programId) {
            var html = "<div class='overallCardItem'>" +
                "<div class='cardImageOutside'>" +
                "<div class='cardImageInside' " +
                (
                    (util.isset(item.imageid) && item.imageid != null) ? "style='background-image: url(/hub/admin/image/getById/" + item.imageid + "/hotel-logo-big)'" : ""
                ) + "></div>" +
                "</div>" +
                "<div class='cardItemDescriptor'>" +
                "   <div class='cardItemDescriptorFirst'>" + item.name + "</div>" +
                "   <div class='cardItemDescriptorSecond'>" + item.address + ", " + item.cityName + "</div>" +
                "</div>" +
                "</div>";

            return html;
        }
    },

    formats: {
        percent: function (value) {
            return value + "%";
        }
    },

    enableIptv: function (roomId, roomName) {
        var confirmBox = (webix.copy(commonViews.confirm("Aktiviranje IPTV servisa za sobu: <b>" + roomName + "</b>", "Da li ste sigurni da želite aktivirati IPTV servis?")));
        confirmBox.callback = function (result) {
            if (result == 1) {
                connection.sendAjax("PUT", "hub/admin/rooms/enableIptv", function (text, data, xhr) {
                    var jsonResp = JSON.parse(text);
                    if (util.isset(jsonResp.iptvEnabled) && jsonResp.iptvEnabled == false && util.isset(jsonResp.iptvErrorSubscriberId))
                        webix.message({
                            type: "error",
                            text: "Greška pri aktiviranju iptv servisa za: " + ((jsonResp.iptvErrorSubscriberId == "") ? "nema subscriber id" : jsonResp.iptvErrorSubscriberId),
                            expire: -1
                        });
                    else {
                        webix.message({text: "Aktiviran iptv za sobu: " + roomName});
                        $$("roomsDT").getItem(roomId).iptvEnabled = 1;
                        $$("roomsDT").refresh();
                    }
                }, function () {
                    util.showErrorMessage("Greška prilikom aktiviranja iptv servisa.");
                }, roomId);
            }
        };
        webix.confirm(confirmBox);
    },

    disableIptv: function (roomId, roomName) {
        var confirmBox = (webix.copy(commonViews.confirm("Deaktiviranje IPTV servisa za sobu: <b>" + roomName + "</b>", "Da li ste sigurni da želite deaktivirati IPTV servis?")));
        confirmBox.callback = function (result) {
            if (result == 1) {
                connection.sendAjax("PUT", "hub/admin/rooms/disableIptv", function (text, data, xhr) {
                    var jsonResp = JSON.parse(text);
                    if (util.isset(jsonResp.iptvDisabled) && jsonResp.iptvDisabled == false && util.isset(jsonResp.iptvErrorSubscriberId))
                        webix.message({
                            type: "error",
                            text: "Greška pri deaktiviranju iptv servisa za: " + ((jsonResp.iptvErrorSubscriberId == "") ? "nema subscriber id" : jsonResp.iptvErrorSubscriberId),
                            expire: -1
                        });
                    else {
                        webix.message({text: "Deaktiviran iptv za sobu: " + roomName});
                        $$("roomsDT").getItem(roomId).iptvEnabled = false;
                        $$("roomsDT").refresh();
                    }
                }, function () {
                    util.showErrorMessage("Greška prilikom deaktiviranja iptv servisa.");
                }, roomId);
            }
        };
        webix.confirm(confirmBox);
    },

    enableVoice: function (roomId, roomName) {
        var confirmBox = (webix.copy(commonViews.confirm("Aktiviranje Voice servisa za sobu: <b>" + roomName + "</b>", "Da li ste sigurni da želite aktivirati Voice servis?")));
        confirmBox.callback = function (result) {
            if (result == 1) {
                connection.sendAjax("PUT", "hub/admin/rooms/enableVoice", function (text, data, xhr) {
                    var jsonResp = JSON.parse(text);
                    if (util.isset(jsonResp.voiceEnabled) && jsonResp.voiceEnabled == false) {
                        if (!util.isset(jsonResp.voiceErrorNumbers)) {
                            webix.message({
                                type: "error",
                                text: "Greška prilikom aktiviranja voice servisa.",
                                expire: -1
                            });
                        } else {
                            webix.message({
                                type: "error",
                                text: "Greška pri aktiviranju voice servisa za: " + ((jsonResp.voiceErrorNumbers == "") ? "nema brojeva telefona" : jsonResp.voiceErrorNumbers),
                                expire: -1
                            });
                        }
                    } else {
                        webix.message({text: "Aktiviran voice za sobu: " + roomName});
                        $$("roomsDT").getItem(roomId).voiceEnabled = true;
                        $$("roomsDT").refresh();
                    }
                }, function () {
                    util.showErrorMessage("Greška prilikom aktiviranja voice servisa.");
                }, roomId);
            }
        };
        webix.confirm(confirmBox);
    },

    disableVoice: function (roomId, roomName) {
        var confirmBox = (webix.copy(commonViews.confirm("Deaktiviranje Voice servisa za sobu: <b>" + roomName + "</b>", "Da li ste sigurni da želite deaktivirati Voice servis?")));
        confirmBox.callback = function (result) {
            if (result == 1) {
                connection.sendAjax("PUT", "hub/admin/rooms/disableVoice", function (text, data, xhr) {
                    var jsonResp = JSON.parse(text);
                    if (util.isset(jsonResp.voiceDisabled) && jsonResp.voiceDisabled == false) {
                        if (!util.isset(jsonResp.voiceErrorNumbers)) {
                            webix.message({
                                type: "error",
                                text: "Greška prilikom deaktiviranja voice servisa.",
                                expire: -1
                            });
                        } else {
                            webix.message({
                                type: "error",
                                text: "Greška pri deaktiviranju voice servisa za: " + ((jsonResp.voiceErrorNumbers == "") ? "nema brojeva telefona" : jsonResp.voiceErrorNumbers),
                                expire: -1
                            });
                        }
                    } else {
                        webix.message({text: "Deaktiviran voice za sobu: " + roomName});
                        $$("roomsDT").getItem(roomId).voiceEnabled = false;
                        $$("roomsDT").refresh();
                    }
                }, function () {
                    util.showErrorMessage("Greška prilikom deaktiviranja voice servisa.");
                }, roomId);
            }
        };
        webix.confirm(confirmBox);
    },

    enableVoicePremium: function (roomId, roomName) {
        var confirmBox = (webix.copy(commonViews.confirm("Aktiviranje Voice premium saobraćaja za sobu: <b>" + roomName + "</b>", "Da li ste sigurni da želite aktivirati Voice premium saobraćaj?")));
        confirmBox.callback = function (result) {
            if (result == 1) {
                connection.sendAjax("PUT", "hub/admin/rooms/enableAccountPremiumCB", function (text, data, xhr) {
                    var jsonResp = JSON.parse(text);
                    if (util.isset(jsonResp.voicePremiumEnabled) && jsonResp.voicePremiumEnabled == false) {
                        if (!util.isset(jsonResp.voiceErrorNumbers)) {
                            webix.message({
                                type: "error",
                                text: "Greška prilikom aktiviranja voice premium saobraćaja.",
                                expire: -1
                            });
                        } else {
                            webix.message({
                                type: "error",
                                text: "Greška pri aktiviranju voice premium saobraćaja za: " + ((jsonResp.voiceErrorNumbers == "") ? "nema brojeva telefona" : jsonResp.voiceErrorNumbers),
                                expire: -1
                            });
                        }
                    } else {
                        webix.message({text: "Aktiviran voice premium saobraćaj za sobu: " + roomName});
                        $$("roomsDT").getItem(roomId).voicePremiumEnabled = true;
                        $$("roomsDT").refresh();
                    }
                }, function () {
                    util.showErrorMessage("Greška prilikom aktiviranja voice premium saobraćaja.");
                }, roomId);
            }
        };
        webix.confirm(confirmBox);
    },

    disableVoicePremium: function (roomId, roomName) {
        var confirmBox = (webix.copy(commonViews.confirm("Deaktiviranje Voice premium saobraćaja za sobu: <b>" + roomName + "</b>", "Da li ste sigurni da želite deaktivirati Voice premium saobraćaj?")));
        confirmBox.callback = function (result) {
            if (result == 1) {
                connection.sendAjax("PUT", "hub/admin/rooms/disableAccountPremiumCB", function (text, data, xhr) {
                    var jsonResp = JSON.parse(text);
                    if (util.isset(jsonResp.voicePremiumDisabled) && jsonResp.voicePremiumDisabled == false) {
                        if (!util.isset(jsonResp.voiceErrorNumbers)) {
                            webix.message({
                                type: "error",
                                text: "Greška prilikom deaktiviranja voice premium saobraćaja.",
                                expire: -1
                            });
                        } else {
                            webix.message({
                                type: "error",
                                text: "Greška pri deaktiviranju voice premium saobraćaja za: " + ((jsonResp.voiceErrorNumbers == "") ? "nema brojeva telefona" : jsonResp.voiceErrorNumbers),
                                expire: -1
                            });
                        }
                    } else {
                        webix.message({text: "Deaktiviran voice premium saobraćaj za sobu: " + roomName});
                        $$("roomsDT").getItem(roomId).voicePremiumEnabled = false;
                        $$("roomsDT").refresh();
                    }
                }, function () {
                    util.showErrorMessage("Greška prilikom deaktiviranja voice premium saobraćaja.");
                }, roomId);
            }
        };
        webix.confirm(confirmBox);
    },
    onAfterFilter: function (count) {
        $$("rowsNumberIndicator").setValues({filteredRowsCount: count, config: $$("pagingDiv").config});
    },
    clearAllFiltersForDatatable: function (datatableId) {
        var typeOfArgument = typeof  datatableId, datatable;
        if (typeOfArgument === "string") {
            datatable = $$(datatableId);
        } else if (typeOfArgument === "object") {
            datatable = datatableId;
        } else throw "argument is not valid";
        var columns = datatable.config.columns;
        for (var i = 0; i < columns.length; i++) {
            datatable.getFilter(columns[i].id).value = "";
        }
    },

    datetime: {
        getLastSunday: function (d) {
            var t = new Date(d);
            t.setDate(t.getDate() - t.getDay());
            return t;
        }
    },

    workaround: function (dt, that) {

        setTimeout(function () {
            $$(dt).define("pager", "pagingDiv");
            // $$("pagingDiv").config.group = 1;
            $$("pagingDiv").render();
            connection.fetchPaginationSettings(that, "pagination");
        }, 500)
    },

    popupIsntAlreadyOpened: function (popupId) {
        if ($$(popupId) && $$(popupId).isVisible()) {
            return false;
        }
        return true;
    },

    b64toBlob: function (b64Data, contentType, sliceSize) {
        contentType = contentType || '';
        sliceSize = sliceSize || 512;

        var byteCharacters = atob(b64Data);
        var byteArrays = [];

        for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
            var slice = byteCharacters.slice(offset, offset + sliceSize);
            var byteNumbers = new Array(slice.length);
            for (var i = 0; i < slice.length; i++) {
                byteNumbers[i] = slice.charCodeAt(i);
            }
            var byteArray = new Uint8Array(byteNumbers);

            byteArrays.push(byteArray);
        }

        var blob = new Blob(byteArrays, {type: contentType});
        return blob;
    },
    formatter: webix.Date.dateToStr("%d.%m.%Y. %H:%i"),
    parser: webix.Date.strToDate("%d.%m.%Y. %H:%i"),
    elementsConfig: {
        labelWidth: 140,
        bottomPadding: 18
    },


};