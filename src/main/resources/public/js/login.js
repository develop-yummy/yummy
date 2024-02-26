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

  // Sign Up 버튼 클릭 시 요청 보내기
  $("#postSignUp").click(function(){
    event.preventDefault(); // 기본 동작 막기
    console.log("Sign Up 요청을 보냈습니다.");
    // 여기에 요청을 보내는 코드 작성
    var userData = {
      email: $("input[placeholder='Email']").val(),
      password: $("input[placeholder='Password']").val(),
      username: $("input[placeholder='UserName']").val(),
      phoneNumber: $("input[placeholder='phoneNumber']").val(),
      zipCode: $("input[placeholder='Zip Code']").val(),
      city: $("input[placeholder='City']").val(),
      street: $("input[placeholder='Street']").val()
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
        console.log("회원가입 요청이 성공했습니다.");
        console.log("서버 응답:", response);
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
    console.log("Sign In 요청을 보냈습니다.");
  });
});
