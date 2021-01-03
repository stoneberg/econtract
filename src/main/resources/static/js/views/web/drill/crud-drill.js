const module = (function (global, $, _, moment, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];



    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/
    // function define_user_function_here() {
    //
    // }

    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {


        /**
         * Datepicker ####################################################################
         */
        // start date
        $('#date-start')
            .datepicker({
                format: 'yyyy-mm-dd',
                language: 'ko',
                autoclose: true,
                todayHighlight: true,
            })
            .on('changeDate', function (selected) {
                let minDate = new Date(selected.date.valueOf());
                $('#date-end').datepicker('setStartDate', minDate);
            });

        // end date
        $('#date-end')
            .datepicker({
                format: 'yyyy-mm-dd',
                language: 'ko',
                autoclose: true,
                todayHighlight: true,
            })
            .on('changeDate', function (selected) {
                let minDate = new Date(selected.date.valueOf());
                $('#date-start').datepicker('setEndDate', minDate);
            });

        $('#date-start').datepicker('setDate', '-1M');
        $('#date-end').datepicker('setDate', 'today');







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