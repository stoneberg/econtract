/****************************************************************************************************
 *    @Description: Ajax Utilites
 *    @Author: 91264983
 *    @Since: 2020.08.15
 *****************************************************************************************************/
(function (global, $, moment, _, thisPage) {

    /** context root **/
    const _CTX = thisPage['ctxPath'];

    /** active ajax **/
    let ACTIVE_AJAX = 0;

    /** ajax start status flag **/
    let AJAX_START = false;

    /** disable loading indicator urls **/
    const NO_INDICATOR_URLS = ["/aaa/bbb/ccc", "/eee/fff/ggg"];

    const indicatorStart = function () {
        let ajaxIndicator = '<div id="indicator" style="display:none;"><span class="radius"></span></div>';
        $('body').append(ajaxIndicator);
        $.blockUI.defaults.css = {};
        $.blockUI({
            message: $('#indicator'),
            border: '3px solid #aaa',
            overlayCSS: {
                backgroundColor: '#000',
                opacity: 0.2,
                cursor: 'default'
            }
        });
    };

    const indicatorStop = function () {
        $.unblockUI();
        $(document).find('#indicator').remove();
    };

    /**
     * jQuery ajaxSend
     * handle each ajax request
     * control loading indcator : start
     */
    $(document).ajaxSend(function (event, jqhr, settings) {
        if (AJAX_START) return;
        // if (_.contains(NO_INDICATOR_URLS, settings.url)) return;
        AJAX_START = true;
        indicatorStart();
    });

    /**
     * jQuery ajaxStop
     * handl entire ajax request
     * control loading indcator : stop
     */
    $(document).ajaxStop(function () {
        indicatorStop();
        AJAX_START = false;
    });

    /**
     * Ajax Error Handler
     */
    $(document).ajaxError(function (event, jqxhr, settings, thrownError) {
        console.error("e:", jqxhr);
        console.error("settings:", settings);
        const url = settings.url;
        const status = jqxhr.status;
        const message = jqxhr['responseJSON'] && jqxhr['responseJSON']['message'] || '';

        if (url.includes("pdf")) {
            $.alert("Pdf 다운로드 중 오류가 발생했습니다.");
            // return Promise.reject("file download error");
        } else if (url.includes("excel")) {
            $.alert("Excel 다운로드 중 오류가 발생했습니다.");
            // return Promise.reject("file download error");
        } else if (status === 0) { // abnormal disconnect
            $.alert("서버와 연결이 끊어졌습니다.");
        } else if (!message && status === 400) {
            $.alert("잘못된 요청입니다.(400)");
        } else if (!message && status === 404) {
            $.alert("요청한 서비스를 찾을 수 없습니다.(404)");
        } else {
            $.alert(message || '서버에서 오류가 발생했습니다. 관리자에게 문의하세요.');
        }

    });

    /**
     * jQuery.extend methods
     */
    $.extend({

        /**
         * I. Ajax Proxy(Request by JSON)
         */
        ajaxRest: function (request) {

            const options = {
                url: request.url,
                type: request.method,
                data: request.param && JSON.stringify(request.param),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                cache: false,
                timeout: 30000, // 30 seconds
            };

            return $.ajax(options).done(request.callback).always(function () { });
        },

        /**
         * II. Ajax Proxy(Request by Form Submit)
         */
        ajaxForm: function (request) {

            const options = {
                url: request.url,
                type: request.method,
                data: request.param,
                dataType: 'json',
                contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                cache: false,
                timeout: 30000, // 30 seconds
            };

            return $.ajax(options).always(function () { });
        },

        /**
         * III. Ajax Proxy with blockUI
         */
        ajaxUpload: function (request) {

            const options = {
                enctype: 'multipart/form-data',
                url: request.url,
                type: request.method, // shoud be "POST"
                data: request.param,
                dataType: 'json',
                processData: false, //prevent jQuery from automatically transforming the data into a query string
                contentType: false,
                cache: false,
                timeout: 30000, // 30 seconds
            };

            return $.ajax(options).always(function () { });
        },

        /**
         * blob file download
         */
        ajaxDownload: function (request) {
            $.ajax({
                url: request.url,
                method: request.method,
                xhrFields: {
                    responseType: 'arraybuffer'
                },
                data: $.param(request.param) // a=1&b=2&c=3 방식
            }).done(function (data, textStatus, jqXhr) {
                try {
                    const blob = new Blob([data], {type: jqXhr.getResponseHeader('content-type')});
                    let fileName = $.getFileName(jqXhr.getResponseHeader('content-disposition'));
                    fileName = decodeURI(fileName);

                    if (window.navigator.msSaveOrOpenBlob) { // IE 10+
                        window.navigator.msSaveOrOpenBlob(blob, fileName);
                    } else { // not IE
                        const link = document.createElement('a');
                        const url = window.URL.createObjectURL(blob);
                        link.href = url;
                        link.target = '_self';
                        if (fileName) link.download = fileName;
                        document.body.append(link);
                        link.click();
                        link.remove();
                        window.URL.revokeObjectURL(url);
                    }
                } catch (e) {
                    console.error(e)
                }
            });
        },

        getFileName: function (contentDisposition) {
            const fileName = contentDisposition
                .split(';')
                .filter(function (ele) {
                    return ele.indexOf('filename') > -1
                })
                .map(function (ele) {
                    return ele
                        .replace(/"/g, '')
                        .split('=')[1]
                });
            return fileName[0] ? fileName[0] : null
        },

        /**
         * Ajax Post option builder
         * $.reqPost('/url/abc').setData({city: "A", town: "B"}).setCallback(callback).build();
         */
        reqPost: function (reqUrl) {
            const url = reqUrl;
            const method = 'POST';
            let callback = null;
            let param = {};
            return {
                setData: function (reqParam) {
                    param = reqParam;
                    return this;
                },
                setCallback: function (resCallback) {
                    callback = resCallback;
                    return this;
                },
                build: function () {
                    return {
                        url: url,
                        method: method,
                        param: param,
                        callback: callback
                    };
                }
            };
        },

        /**
         * Ajax Put option builder
         * $.reqPut('/url/abc/id').setData({city: "A", town: "B"}).setCallback(callback).build();
         */
        reqPut: function (reqUrl) {
            const url = reqUrl;
            const method = 'PUT';
            let callback = null;
            let param = {};
            return {
                setData: function (reqParam) {
                    param = reqParam;
                    return this;
                },
                setCallback: function (resCallback) {
                    callback = resCallback;
                    return this;
                },
                build: function () {
                    return {
                        url: url,
                        method: method,
                        param: param,
                        callback: callback
                    };
                }
            };
        },

        /**
         * Ajax Get option builder
         * $.reqGet('/url/abc').setData({city: "A", town: "B").setCallback(callback).build();
         */
        reqGet: function (reqUrl) {
            const url = reqUrl;
            const method = 'GET';
            let callback = null;
            let param = '';
            return {
                setData: function (reqParam) {
                    param = reqParam;
                    return this;
                },
                setCallback: function (resCallback) {
                    callback = resCallback;
                    return this;
                },
                build: function () {
                    return {
                        url: param ? (url + '?' + param) : url,
                        method: method,
                        callback: callback
                    };
                }
            };
        },

        /**
         * Ajax Delete option builder
         * $.reqDel('/url/abc/id').build();
         */
        reqDel: function (reqUrl) {
            const url = reqUrl;
            const method = 'DELETE';
            return {
                build: function () {
                    return {
                        url: url,
                        method: method,
                    };
                }
            };
        },

        /**
         * Ajax Get option builder
         * $.reqFile('/url/abc').setData({city: "A", town: "B").setCallback(callback).build();
         */
        reqFile: function (reqUrl) {
            const url = reqUrl;
            const method = 'GET';
            let param = '';
            return {
                setData: function (reqParam) {
                    param = reqParam;
                    return this;
                },
                build: function () {
                    return {
                        url: param ? (url + '?' + param) : url,
                        method: method,
                    };
                }
            };
        },

        /**
         * common alert modal
         */
        alert: function(message) {
            $('.modal').modal("hide");
            $('#alertMessage').html(message);
            $('#alertPop').modal({backdrop: 'static', keyboard: false});
        },

        /**
         * modal on
         */
        modalOn: function ($target, callback, option) {
            $.blockUI.defaults.css = {};
            $.blockUI({
                message: $target, // $target element to show
                css: {
                    padding: 0,
                    margin: 0,
                    textAlign: option && option.textAlign || '',
                    color: '#000',
                    border: option && option.border || '3px solid #aaa',
                    backgroundColor: '#fff',
                    cursor: 'default'
                },
                overlayCSS: {
                    backgroundColor: '#000', // ''
                    opacity: 0.3, // ''
                    cursor: 'default'
                },
                onBlock: function () {
                    if (typeof callback === "function") {
                        callback();
                    }
                }
            });

            const modal = $('.blockUI.blockMsg').get(length - 1);
            $(modal).center();

        },

        /**
         * modal off
         */
        modalOff: function (callback) {
            $.unblockUI({
                onUnblock: function () {
                    if (typeof callback === "function") {
                        callback();
                    }
                }
            });
        },

        /**
         * convert number to currency format(#,###)
         */
        formatNumber: function (num) {
            return num && num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,") || 0;
        },

        /**
         * abbreviate string length by number(ABC...)
         */
        abbreviate: function (str, num) {
            return ($.trim(str).length > num) ? $.trim(str).substring(0, num) + "..." : $.trim(str);
        },

        /**
         * convert value to percentFormat format(xx.xx)
         */
        percentFormat: function (change, reference, nth) {
            return Number((change / reference) * 100 || 0).toFixed(nth);
        },

        /**
         * number round up to the nth
         */
        numberRoundUp: function (value, nth) {
            return Number(value).toFixed(nth);
        },

        /**
         * in string replace xss characters
         */
        sanitize: function (input) {
            return input != "null" && input.replace(/\&/g, '&amp;')
                .replace(/\</g, '&lt;')
                .replace(/\>/g, '&gt;')
                .replace(/\"/g, '&quot;')
                .replace(/\'/g, '&#x27')
                .replace(/\//g, '&#x2F') || '';
        },

        /**
         * convert JSON Object to String
         * param : JSON Object
         */
        jsonStr: function (jsonObj) {
            return global.encodeURIComponent(JSON.stringify(jsonObj));
        },

        /**
         * convert String to JSON Object
         * param : JSON Format String
         */
        jsonObj: function (jsonStr) {
            return JSON.parse(global.decodeURIComponent(jsonStr));
        },

        /**
         * Uppercase the first character of string
         */
        upperCaseFirstChar: function (str) {
            return str && str.charAt(0).toUpperCase() + str.slice(1) || '';
        },

        /**
         * convert local date to utc date format
         */
        utcDateFormat: function (date) {
            return date && moment(new Date(date)).utc().toISOString() || '';
        },

        /**
         * convert date to date format
         */
        dateFormat: function (date, type) {
            if (!date) {
                return '';
            } else if (type === 'D') {
                return moment(new Date(date)).format("YYYY/MM/DD");
            } else if (type === 'H') {
                return moment(new Date(date)).format("YYYY/MM/DD HH");
            } else if (type === 'M') {
                return moment(new Date(date)).format("YYYY/MM/DD HH:mm");
            } else {
                return moment(new Date(date)).format("YYYY/MM/DD HH:mm:ss");
            }
        },

        /**
         * convert start date to date format
         */
        startDateFormat: function (startDate) {
            return startDate && moment(new Date(startDate)).format("YYYY/MM/DD HH:mm:ss") || '';
        },

        /**
         * convert end date to date format
         */
        endDateFormat: function (endDate) {
            const fnsDt = endDate && moment(new Date(endDate)).format("YYYY/MM/DD HH:mm:ss") || '';
            return (fnsDt !== '9999/12/31 00:00:00' && fnsDt) || '';
        },

        /**
         * get the dynamic variables stored in the url as parameters
         * example.com?param1=name&param2=&id=6
         * $.urlParam('param1'); // name ,$.urlParam('id'); // 6, $.urlParam('param2'); // null
         * http://www.jquery4u.com?city=Gold Coast
         * $.urlParamValue('city') // Gold%20Coast
         * decodeURIComponent($.urlParam('city'))// Gold Coast
         */
        urlParamValue: function (name) {
            const results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(global.location.href);
            return results[1] || 0;
        },

        /**
         * get file extension
         *
         * @param filename
         * @returns {string|*}
         */
        getFileExtension: function (filename) {
            return filename.substring(filename.lastIndexOf('.') + 1, filename.length);
        }

    }); //eof

    /**
     * moment.js 한국어 locale 설정
     */
    typeof moment !== 'undefined' && moment.locale('ko');

    /**
     * desc   : form의 데이터를 json 형태로 변환
     * return : 성공시에는 객체(JSON)을 리턴한다. 실패시에는 null을 리턴
     * usage  : $('#form').serializeForm()
     */
    $.fn.serializeForm = function () {
        let obj = null;
        try {
            if (this[0].tagName && this[0].tagName.toUpperCase() === "FORM") {
                let arr = this.serializeArray();
                if (arr) {
                    obj = {};
                    jQuery.each(arr, function () {
                        obj[this.name] = this.value;
                    });
                }
            }
        } catch (e) {
            console.error(e.message);
        }

        return obj;
    };

    /**
     * jQuery BlockUI - Center Modal on the screen
     */
    $.fn.center = function () {
        this.css("position", "absolute");
        this.css("top", ($(window).height() - this.height()) / 2 + $(window).scrollTop() + "px");
        this.css("left", ($(window).width() - this.width()) / 2 + $(window).scrollLeft() + "px");
        return this;
    };

    /**
     * Polyfills for Old Browser
     */
    if (!String.prototype.startsWith) {
        String.prototype.startsWith = function (search, pos) {
            return this.substr(!pos || pos < 0 ? 0 : +pos, search.length) === search;
        };
    }

    if (!String.prototype.endsWith) {
        String.prototype.endsWith = function (search, pos) {
            var subjectStr = this.toString();
            if (typeof pos !== 'number' || !isFinite(pos) || Math.floor(pos) !== pos || pos > subjectStr.length) {
                pos = subjectStr.length;
            }
            pos -= search.length;
            var lastIndex = subjectStr.indexOf(search, pos);
            return lastIndex !== -1 && lastIndex === pos;
        };
    }

    if (!String.prototype.trim) {
        String.prototype.trim = function () {
            return this.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, '');
        };
    }

    if (!String.prototype.includes) {
        String.prototype.includes = function (search, start) {
            'use strict';
            if (typeof start !== 'number') {
                start = 0;
            }

            if (start + search.length > this.length) {
                return false;
            } else {
                return this.indexOf(search, start) !== -1;
            }

        };
    }

})(window, jQuery, moment, _, thisPage);
