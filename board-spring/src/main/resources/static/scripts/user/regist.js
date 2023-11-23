$("#datePicker").datepicker({
  format: "yyyy-mm-dd",
  //   startDate: "-1d",
  endDate: "1d",
  autoclose: true,
  clearBtn: true,
  title: "Birth day",
  language: "ko",
});

const idReg = /^[a-z0-9]{3,20}$/i;
const pwReg = /^(?=.*[a-z])(?=.*[\!\@\#\$\%\^\&])(?=.*\d).{10,30}$/i;
const koreanReg = /[ㄱ-ㅎㅏ-ㅣ가-힣]/g;
const phoneReg = /^\d{3}-\d{3,4}-[0-9]{4}$/;
const emailReg = /^[A-Z0-9\.\_\%\+\-]+@[A-Z0-9\.\-]+(.com|.net|.co.kr|.org)$/i;

document.getElementById("regist-form").onsubmit = function (e) {
  // id => 3~20, 영어 숫자
  const tempName = e.target.name.value.replace(koreanReg, "aa");
  const tempPhone = e.target.phone.value.replace(
    /^(\d{3})(\d{3,4})(\d{4})$/,
    `$1-$2-$3`
  );

  let msg = checkId(e.target);
  if (!msg) msg = checkPassword(e.target);
  if (!msg) {
    if (tempName.length < 4 || tempName.length > 20) {
      msg = "이름의 길이는 한글 기준 2~10, 영어 기준 4~20으로 맞춰주세요.";
    } else if (!phoneReg.test(tempPhone)) {
      msg = "전화번호는 한국에서의 형식에 맞춰주세요.";
    } else if (
      e.target.address.value.length > 0 &&
      (e.target.address.value.length < 5 || e.target.address.value.length > 100)
    ) {
      msg = "주소는 5~100 자로 맞춰주세요.";
    } else if (!emailReg.test(e.target.email.value)) {
      msg = "이메일 형식에 맞춰주세요.";
    }
  }

  if (!checkMsg(msg, e)) {
    e.target.phone.value = tempPhone;
    if (
      e.target["git-address"].value.length > 0 &&
      !e.target["git-address"].value.startsWith("https://github.com/")
    ) {
      e.target["git-address"].value =
        "https://github.com/" + e.target["git-address"].value;
    }
  }
};