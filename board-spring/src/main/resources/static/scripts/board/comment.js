const commentListElem = document.getElementById("comment-list");
const commentForm = document.getElementById("board-comment");

const getList = async () => {
  const data = (
    await axios.get(
      `/comments?boardId=${commentListElem.dataset.boardId}&start=${commentListElem.children.length}`
    )
  ).data;
  if (data.end) {
    document.getElementById("add-comment-btn").outerHTML = "";
  }
  // commentListElem.innerHTML = list;
  console.log(data);
  setList(data.list, commentListElem);

  // list.forEach((item) => {
  //   const tempLiElem = document.createElement("li");
  //   tempLiElem.innerHTML = `${item.content} - ${item.userName} / ${new Date(
  //     item.createdAt
  //   )}`;
  //   const tempOlElem = document.createElement("ol");
  //   item.children.forEach((child) => {
  //     const tempLiElem2 = document.createElement("li");
  //     tempLiElem2.innerHTML = `${child.content} - ${
  //       child.userName
  //     } / ${new Date(item.createdAt)}`;
  //     tempOlElem.append(tempLiElem2);
  //   });
  //   tempLiElem.append(tempOlElem);

  //   commentListElem.append(tempLiElem);
  // });
};

function setList(list, parentElem) {
  list.forEach((item, idx) => {
    const tempLiElem = document.createElement("div");
    // if (!idx) {
    const tempArrowElem = document.createElement("img");
    tempArrowElem.classList.add("recomment-img");
    tempArrowElem.src = "/imgs/right-down.png";
    tempLiElem.append(tempArrowElem);
    // }
    tempLiElem.classList.add("comment-item");
    tempLiElem.innerHTML += `${item.content} <span class="comment-user-name">${
      item.userName
    }</span> / ${new Date(item.createdAt)}`;

    if (commentForm) {
      const tempButtonOnElem = document.createElement("button");
      tempButtonOnElem.innerHTML = "댓글";
      tempButtonOnElem.classList.add("btn", "btn-primary");
      tempLiElem.append(tempButtonOnElem);

      const tempFormElem = document.createElement("form");
      tempFormElem.action = "/comments/add";
      tempFormElem.method = "post";
      tempLiElem.append(tempFormElem);

      const tempUserIdElem = document.createElement("input");
      tempUserIdElem.type = "hidden";
      tempUserIdElem.name = "user_id";
      tempUserIdElem.value = commentForm["user_id"].value;
      tempFormElem.append(tempUserIdElem);

      const tempBoardIdElem = document.createElement("input");
      tempBoardIdElem.type = "hidden";
      tempBoardIdElem.name = "board_id";
      tempBoardIdElem.value = item.boardId;
      tempFormElem.append(tempBoardIdElem);

      const tempCommentIdElem = document.createElement("input");
      tempCommentIdElem.type = "hidden";
      tempCommentIdElem.name = "comment_id";
      tempCommentIdElem.value = item.id;
      tempFormElem.append(tempCommentIdElem);

      const tempContentElem = document.createElement("input");
      tempContentElem.name = "content";
      tempFormElem.append(tempContentElem);

      tempButtonOnElem.onclick = () => {
        tempFormElem.classList.toggle("on");
        tempButtonOnElem.classList.toggle("btn-primary");
        tempButtonOnElem.classList.toggle("btn-dark");
        if (tempButtonOnElem.className.indexOf("btn-primary") > -1) {
          tempButtonOnElem.innerHTML = "댓글";
        } else {
          tempButtonOnElem.innerHTML = "취소";
          tempContentElem.focus();
        }
      };

      const tempButtonElem = document.createElement("button");
      tempButtonElem.classList.add("btn", "btn-primary");
      tempButtonElem.innerHTML = "댓글 추가";
      tempFormElem.append(tempButtonElem);
    }

    const tempOlElem = document.createElement("div");
    tempOlElem.classList.add("comment-list");
    setList(item.children, tempOlElem);
    tempLiElem.append(tempOlElem);

    parentElem.append(tempLiElem);
  });
}

getList();

document.getElementById("add-comment-btn").onclick = () => {
  getList();
};