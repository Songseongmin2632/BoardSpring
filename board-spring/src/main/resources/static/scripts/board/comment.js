const commentListElem = document.getElementById("comment-list");

const getList = async () => {
  const list = (
    await axios.get(`/comments?boardId=${commentListElem.dataset.boardId}`)
  ).data;
  console.log(list);

  list.forEach((item) => {
    const tempLiElem = document.createElement("li");
    tempLiElem.innerHTML = `${item.content} - ${item.userId}`;
    const tempOlElem = document.createElement("ol");
    item.children.forEach((child) => {
      const tempLiElem2 = document.createElement("li");
      tempLiElem2.innerHTML = `${child.content} - ${child.userId}`;
      tempOlElem.append(tempLiElem2);
    });
    tempLiElem.append(tempOlElem);

    commentListElem.append(tempLiElem);
  });
};

getList();