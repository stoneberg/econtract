const module = (function (global, $, _, moment, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];
    console.log("CTX=======>", CTX);



    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/
    window.name ="Parent_window";
    function openNiceIdentifyPop(){
        window.open('', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
        document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
        document.form_chk.target = "popupChk";
        document.form_chk.submit();
    }


    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {

        /**
         * Alert 이벤트
         */
        $('#alertEvent').on('click', function (e) {
            e.preventDefault();
            console.log("alertEvent====================>");
            $.alert("Hello World!");
        });

        /**
         * Error Test 이벤트
         */
        $('#errorTest1').on('click', function (e) {
            e.preventDefault();
            console.log("alertEvent====================>");
            const id = 123456
            $.ajaxRest($.reqGet(CTX + 'api/test/error/' + id)
                .build()
            ).done(function (response) {
                console.log("response=======>", response);
            });
        });

        /**
         * Error Test #2 이벤트
         */
        $('#errorTest2').on('click', function (e) {
            e.preventDefault();
            console.log("alertEvent====================>");
            const id = "AJK"
            $.ajaxRest($.reqGet(CTX + 'api/test/error2/' + id)
                .build()
            ).done(function (response) {
                console.log("response=======>", response);
            });
        });

        /**
         * Nice 본인인증
         */
        $('#nice-identify').on('click', function(e) {
            e.preventDefault();
            console.log("#나이스 본인인증============================>");
            openNiceIdentifyPop();
        });


    } // end of events

    /***************************************************************************
     * @ 화면 로딩 시 최초로 실행할 함수 선언
     **************************************************************************/
    function moduleInitializr() {
        // 정의된_사용자_함수_중_화면_최초_로딩시_호출된_함수();

    }


    /***************************************************************************
     * @ DOM Ready 실행
     **************************************************************************/
    $(function () {
        moduleEventHandlers();
        moduleInitializr();
    });

    /***************************************************************************
     * @ 외부로 노출할 함수 선언
     **************************************************************************/
    return {};


})(window, jQuery, _, moment, thisPage);