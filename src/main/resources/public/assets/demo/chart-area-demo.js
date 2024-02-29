// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// 오늘 날짜를 기준으로 15일 전부터 오늘까지의 날짜를 생성하여 라벨 배열에 추가
var labels = [];
for (var i = 15; i >= 0; i--) {
  var date = new Date();
  date.setDate(date.getDate() - i);
  var month = String(date.getMonth() + 1).padStart(2, '0');
  var day = String(date.getDate()).padStart(2, '0');
  var label = month + '/' + day;
  labels.push(label);
}

// 차트 초기화 (데이터는 나중에 채워짐)
var ctx = document.getElementById("myAreaChart");
var myLineChart = new Chart(ctx, {
  type: 'line',
  data: {
    labels: labels,
    datasets: [{
      label: "Sessions",
      lineTension: 0.3,
      backgroundColor: "rgba(2,117,216,0.2)",
      borderColor: "rgba(2,117,216,1)",
      pointRadius: 5,
      pointBackgroundColor: "rgba(2,117,216,1)",
      pointBorderColor: "rgba(255,255,255,0.8)",
      pointHoverRadius: 5,
      pointHoverBackgroundColor: "rgba(2,117,216,1)",
      pointHitRadius: 50,
      pointBorderWidth: 2,
      data: [], // 초기 데이터는 비워둠
    }]
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: 'date'
        },
        gridLines: {
          display: false
        },
        ticks: {
          maxTicksLimit: 7
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: 200000,
          maxTicksLimit: 5
        },
        gridLines: {
          color: "rgba(0, 0, 0, .125)",
        }
      }],
    },
    legend: {
      display: false
    }
  }
});

// 스프링부트 서버로부터 데이터를 받아와 차트에 적용
$(document).ready(function() {
  $.ajax({
    url: "http://localhost:8080/backoffice/sales/month", // 스프링부트 백엔드 URL로 변경
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
    success: function(response) {
      // 서버로부터 받은 데이터가 객체 형태이므로, 배열로 변환
      var sortedKeys = Object.keys(response).sort((a, b) => a - b);
      var salesData = sortedKeys.map(key => response[key]);

      // 정렬된 키와 데이터 콘솔에 출력
      console.log("정렬된 키:", sortedKeys);
      console.log("정렬된 데이터:", salesData);

      // 차트 데이터 업데이트
      myLineChart.data.datasets[0].data = salesData;
      myLineChart.update();
    },
    error: function(xhr, status, error) {
      console.error("Data loading failed: ", error);
    }
  });
});
