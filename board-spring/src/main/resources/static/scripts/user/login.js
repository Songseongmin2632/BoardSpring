document.getElementById("login-form").onsubmit = (e) => {
  let msg = checkId(e.target);
  if (!msg) msg = checkPassword(e.target);
  checkMsg(msg, e);
};