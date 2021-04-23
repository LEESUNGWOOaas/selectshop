let targetId;

$(document).ready(function () {
    // id 가 query 인 객체 위에서 엔터를 누르면 execSearch() 함수를 실행해라
    $('#query').on('keypress', function (e) {
        if (e.key == 'Enter') {
            execSearch();
        }
    });
    $('#close').on('click', function () {
        $('#container').removeClass('active');
    })

    $('.nav div.nav-see').on('click', function () {
        $('div.nav-see').addClass('active');
        $('div.nav-search').removeClass('active');

        $('#see-area').show();
        $('#search-area').hide();
    })
    $('.nav div.nav-search').on('click', function () {
        $('div.nav-see').removeClass('active');
        $('div.nav-search').addClass('active');

        $('#see-area').hide();
        $('#search-area').show();
    })

    $('#see-area').show();
    $('#search-area').hide();

    showProduct();
})


function numberWithCommas (x) { // 숫자를 넣어주면 반점(,)을 넣어주는 함수
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

/////

function execSearch() { //검색을 실행하는 부분
    /**
     * 검색어 input id: query
     * 검색결과 목록: #search-result-box
     * 검색결과 HTML 만드는 함수: addHTML
     */
    $('#search-result-box').empty();// 검색했던 결과를 초기화한다.
    // 1. 검색창의 입력값을 가져온다.
    let query = $('#query').val();//.val()은 양식(form)의 값을 가져오거나 값을 설정하는 메소드

    // 2. 검색창 입력값을 검사하고, 입력하지 않았을 경우 focus.
    if(query==''){
         alert("검색어를 입력해주세요.");
         $('#query').focus // focus는 검색창에 타자커서가 반짝이는 기능
    }
    // 3. GET /api/search?query=${query} 요청
        $.ajax({
            type : 'GET',
            url : `/api/search?query=${query}`,
            success : function(response){
                for(let i=0;i<response.length;i++){
                    let itemDto = response[i]; //itemDto값안에 하나하나 검색결과가 들어감
                    let tempHtml = addHTML(itemDto);
                    $('#search-result-box').append(tempHtml);
                }
            }

        })
    // 4. for 문마다 itemDto를 꺼내서 HTML 만들고 검색결과 목록에 붙이기


}

function addHTML(itemDto) { //itemDto 안에있는 데이터를 HTML로 바꿔주는 역할
    /**
     * class="search-itemDto" 인 객체에서
     * image, title, lprice, addProduct 활용하기
     * 참고) onclick='addProduct(${JSON.stringify(itemDto)})' // addproduct는 문자열의 형태로 받게된다.
     */
    return `<div class="search-itemDto">
            <div class="search-itemDto-left">
                <img src="${itemDto.image}" alt="">
            </div>
            <div class="search-itemDto-center">
                <div>${itemDto.title}</div>
                <div class="price">
                    ${numberWithCommas(itemDto.lprice)}
                    <span class="unit">원</span>
                </div>
            </div>
            <div class="search-itemDto-right">
                <img src="images/icon-save.png" alt="" onclick='addProduct(${JSON.stringify(itemDto)})'>
            </div>
        </div>`
}

function addProduct(itemDto) {
    /**
     * modal 뜨게 하는 법: $('#container').addClass('active');
     * data를 ajax로 전달할 때는 두 가지가 매우 중요
     * 1. contentType: "application/json",
     * 2. data: JSON.stringify(itemDto),
     */

    // 1. POST /api/products 에 관심 상품 생성 요청
    $.ajax({
        type : 'POST',
        url : "/api/products",
        data : JSON.stringify(itemDto),
        contentType : "application/json",
        success : function(response){
            $('#container').addClass('active');
            targetId = response.id; //targetId = 전역변수 (제일최근에 담긴 관심상품 id가 담김)
        }
    })
    // 2. 응답 함수에서 modal을 뜨게 하고, targetId 를 reponse.id 로 설정 (myprice 설정하기 위함)

}

function showProduct() {
    /**
     * 관심상품 목록: #product-container
     * 검색결과 목록: #search-result-box
     * 관심상품 HTML 만드는 함수: addProductItem
     */
    // 1. GET /api/products 요청
    // 2. 관심상품 목록, 검색결과 목록 비우기
    // 3. for 문마다 관심 상품 HTML 만들어서 관심상품 목록에 붙이기
}

function addProductItem(product) {
    // link, image, title, lprice, myprice 변수 활용하기
    return ``;
}

function setMyprice() {
    /**
     * 1. id가 myprice 인 input 태그에서 값을 가져온다.
     * 2. 만약 값을 입력하지 않았으면 alert를 띄우고 중단한다.
     * 3. PUT /api/product/${targetId} 에 data를 전달한다.
     *    주의) contentType: "application/json",
     *         data: JSON.stringify({myprice: myprice}),
     *         빠뜨리지 말 것!
     * 4. 모달을 종료한다. $('#container').removeClass('active');
     * 5, 성공적으로 등록되었음을 알리는 alert를 띄운다.
     * 6. 창을 새로고침한다. window.location.reload();
     */
}