$(document).ready(function (){
    console.log("문서 준비 완료");
    $.ajax({
        url: "http://localhost:8080/backoffice/restaurants/sales",
        type: "GET",
        dataType: "json",
        beforeSend: function(xhr) {
            var token = sessionStorage.getItem("Authorization");
            if (token) {
                xhr.setRequestHeader("Authorization", token);
                console.log("토큰 준비 완료");
            }
        },
        success: function(data) {
            console.log("데이터 로딩 완료");
            var tbody = $("#datatablesSimple tbody");
            tbody.empty();

            data.forEach(function(item) {
                var row = `<tr>
                    <td>${item.restaurantName}</td>
                    <td>${item.address}</td>
                    <td>${item.content}</td>
                    <td>${item.category}</td>
                    <td>${item.sales.toLocaleString('en-US')}</td>
                    </tr>`;
                tbody.append(row);
            });

            // 기존의 Simple DataTables 인스턴스 제거 및 새로 초기화
            if (datatablesSimple.DataTable) {
                datatablesSimple.DataTable.destroy();
            }
            new simpleDatatables.DataTable(datatablesSimple);
        },
        error: function(xhr, status, error) {
            console.error("Data loading failed: ", error);
        }
    });
});
