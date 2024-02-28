const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
  container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
  container.classList.remove("right-panel-active");
});

$(document).ready(function(){
  sessionStorage.removeItem('Authorization');


  // Sign Up 버튼 클릭 시 요청 보내기
  $("#postSignUp").click(function(){
    event.preventDefault(); // 기본 동작 막기
    console.log("Sign Up 요청을 보냈습니다.");
    // 여기에 요청을 보내는 코드 작성
    var userData = {
      email: $("#signUpEmail").val(),
      password: $("#signUpPassword").val(),
      username: $("#signUpUserName").val(),
      phoneNumber: $("#signUpPhoneNumber").val(),
      adminToken: $("#signUpAdminToken").val(),
      // adminToken 값에 따라 admin 필드 값을 조건적으로 설정
      admin: $("#signUpAdminToken").val() === "" ? false : true
    };

    console.log(userData);
    // 서버로 POST 요청 보내기
    $.ajax({
      url: "http://localhost:8080/v1/api/users/signup", // 서버의 실제 URL로 변경해야 합니다.
      type: "POST",
      data: JSON.stringify(userData),
      contentType: "application/json", // 요청 헤더에 JSON 형식 설정
      success: function(response) {
        // 요청이 성공했을 때 실행할 코드 작성
        console.log(response);
        alert("회원가입에 성공하였습니다.");
        location.reload();
      },
      error: function(xhr, status, error) {
        // 요청이 실패했을 때 실행할 코드 작성
        console.error("회원가입 요청이 실패했습니다.");
        console.error("에러 메시지:", error);
      }
    });


  });

  // Sign In 버튼 클릭 시 요청 보내기
  $("#postSignIn").click(function(){
    // 여기에 요청을 보내는 코드 작성
    event.preventDefault(); // 기본 동작 막기
    console.log("Sign In 요청을 보냈습니다.");
    var userData = {
      email: $("#signInEmail").val(),
      password: $("#signInPassword").val(),
    };
    $.ajax({
      url: "http://localhost:8080/v1/api/users/login", // 서버의 실제 URL로 변경해야 합니다.
      type: "POST",
      data: JSON.stringify(userData),
      contentType: "application/json", // 요청 헤더에 JSON 형식 설정
      success: function (response, status, xhr) {
        // 요청이 성공했을 때 실행할 코드 작성
        console.log("로그인 요청이 성공했습니다.");
        // 응답 객체의 헤더에서 Authorization 헤더의 값을 가져오기
        var authorizationHeader = xhr.getResponseHeader("Authorization");

        // 토큰을 로컬 스토리지에 저장
        sessionStorage.setItem('Authorization', authorizationHeader);
        console.log(authorizationHeader);
        console.log(response);

        // response 값에 따른 조건 처리
        if (response === "USER") {
          alert("일반 사용자는 관리자 페이지에 접근할 수 없습니다.");
          window.location.reload();
        } else if (response === "ADMIN") {
          alert("환영합니다 관리자님");
          window.location.href = "/mainpage.html"; // 관리자 페이지로 이동
        }
      },
      error: function (xhr, status, error) {
        // 요청이 실패했을 때 실행할 코드 작성
        console.error("회원가입 요청이 실패했습니다.");
        console.error("에러 메시지:", error);
      }
    });
  });
});
