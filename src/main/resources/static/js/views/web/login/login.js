const module = (function (global, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];



    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/
    function validateLoginForm() {
        const username = $('input[name=username]').val();
        const password = $('input[name=password]').val();

        if (!username.trim()) {
            $.alert('사용자 아이디를 입력하세요.');
            return false;
        } else if (!password.trim()) {
            $.alert('사용자 비밀번호를 입력하세요.');
            return false;
        }

        return true;
    }

    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {

        // login submit
        // $('#login').on('submit', function(e) {
        //     console.log("=============================")
        //     e.preventDefault();
        //     if (!validateLoginForm()) {
        //         return false;
        //     }
        // });

        // enter key login trigger
        // $('input[name=password]').on('keypress', function (e) {
        //     if (e.which === 13 || e.keyCode === 13) {
        //         $('#login').trigger('click');
        //         return false;
        //     }
        //     return true;
        // });

    } // end of events

    /***************************************************************************
     * @ 화면 로딩 시 최초로 실행할 함수 선언
     **************************************************************************/
    function moduleInitializr() {

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


})(window, jQuery, thisPage);