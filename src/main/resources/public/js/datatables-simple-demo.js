$(document).ready(function (){
    console.log("문서 준비 완료")
    $.ajax({
        url: "http://localhost:8080/backoffice/restaurants/sales", // 스프링부트 백엔드 URL로 변경해야 합니다.
        type: "GET",
        dataType: "json",
        beforeSend: function(xhr) {
            // 세션 스토리지에서 토큰 가져오기
            var token = sessionStorage.getItem("Authorization");
            if (token) {
                // 요청 헤더에 토큰 추가
                xhr.setRequestHeader("Authorization", token);
                console.log("토큰 준비 완료");
            }
        },
        success: function(data) {
            // 데이터 로딩이 성공했을 때 실행될 함수
            console.log("데이터 로딩 완료");
            var tbody = $("#datatablesSimple tbody");
            tbody.empty(); // 기존 테이블 내용을 비웁니다.

            // 받아온 데이터를 사용하여 테이블 행을 동적으로 생성합니다.
            data.forEach(function(item) {
                var row = "<tr>" +
                    "<td>" + item.restaurantName + "</td>" +
                    "<td>" + item.address + "</td>" +
                    "<td>" + item.content + "</td>" +
                    "<td>" + item.category + "</td>" +
                    "<td>$" + item.sales + "</td>" +
                    "</tr>";
                tbody.append(row);
            });

        },
        error: function(xhr, status, error) {
            // 데이터 로딩에 실패했을 때 실행될 함수
            console.error("Data loading failed: ", error);
        }
    });
})



window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
    }
});
