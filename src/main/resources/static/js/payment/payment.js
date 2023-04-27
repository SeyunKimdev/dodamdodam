
$('.btn').on('click', function() {
    const $paymentAmount = $('input[name=category]:checked').val();

    /*부트페이 결제 API*/
    BootPay.request({
        price: $paymentAmount, //실제 결제되는 가격

        // 관리자로그인 -> 결제설치 -> 인증키 및 보안 -> WEB Application ID
        application_id: "640587563049c8001d365e76",

        name: '교차로', //결제창에서 보여질 이름
        pg: '',
        method: 'card', //결제수단, 입력하지 않으면 결제수단 선택부터 화면이 시작합니다.
        show_agree_window: 0, // 부트페이 정보 동의 창 보이기 여부
        items: [
            {
                item_name: $paymentAmount + "만 포인트", //상품명
                qty: 1, //수량
                unique: '123', //해당 상품을 구분짓는 primary key
                price: $paymentAmount, //상품 단가
            }
        ],
        order_id: '고유order_id_1234', //고유 주문번호로, 생성하신 값을 보내주셔야 합니다.
        }).error(function (data) {
            //결제 진행시 에러가 발생하면 수행됩니다.
            console.log(data);
        }).cancel(function (data) {
            //결제가 취소되면 수행됩니다.
            console.log(data);
        }).close(function (data) {
            // 결제창이 닫힐때 수행됩니다. (성공,실패,취소에 상관없이 모두 수행됨)
            console.log(data);
        }).done(function (data) {
        //결제가 정상적으로 완료되면 수행됩니다
        //비즈니스 로직을 수행하기 전에 결제 유효성 검증을 하시길 추천합니다.4
            $.ajax({
                url: "/points/pay-register", // 컨트롤러 주소
                type: "POST",
                data: {memberPoint: $paymentAmount}, // 결제정보 객체 paymetnVO
                success: function (result) {
                    location.href = result;
                },
                error: function (error) {
                    console.log(error);
                }
            });
            console.log(data);
        });
});