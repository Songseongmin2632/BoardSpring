function checkId(form) {
  const idReg = /^[a-z0-9]{3,20}$/i;
  let msg = "";
  if (!idReg.test(form["userId"].value)) {
    if (form.userId.value.length < 3 || form.userId.value.length > 20) {
      msg = "아이디의 길이는 3~20으로 해주세요.";
    } else {
      msg = "아이디는 영어와 숫자만 포함할 수 있어요.";
    }
  }
  return msg;
}

function checkPassword(form) {
  const pwReg = /^(?=.*[a-z])(?=.*[\!\@\#\$\%\^\&])(?=.*\d).{10,30}$/i;
  let msg = "";
  if (!pwReg.test(form.password.value)) {
    if (form.password.value.length < 10 || form.password.value.length > 30) {
      msg = "비밀번호의 길이는 10~30으로 해주세요.";
    } else {
      msg = "비밀번호는 대소문자, 숫자, 특수문자(!@#$%^&)를 포함해야해요.";
    }
  }
  return msg;
}

function checkMsg(msg, e) {
  if (msg) {
    e.preventDefault();
    document.getElementById("modal-container").classList.add("on");
    document.getElementById("modal-msg").innerHTML = msg;
    return true;
  }
  return false;
}