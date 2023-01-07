const APIUrl = "127.0.0.1:8080";

const cards = document.querySelectorAll('.card');
const likeButton = document.querySelector('.like');
const dislikeButton = document.querySelector('.dislike');

const infoLine = document.querySelector('.infoLine');
const bioLine = document.querySelector('.bioLine');

likeButton.addEventListener('click', () => {
  console.log("Like");
  newCard();
});

dislikeButton.addEventListener('click', () => {
  console.log("Dislike");
  newCard();
});

function newCard() {
  getData(APIUrl)
  .then(response => {
    console.log("got data");
    // response.name
  })
  .catch(error => {
    console.log("ERROR: API call failed!");
    //REMOVE
    infoLine.textContent = "ERROR (ERROR)";
    bioLine.textContent = "Failed to get data.";
  });
}

function getData(url) {
  return new Promise((resolve, reject) => {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', url);
    xhr.onload = () => {
      if (xhr.status === 200) {
        resolve(JSON.parse(xhr.responseText));
      } else {
        reject(xhr.statusText);
      }
    };
    xhr.onerror = () => reject(xhr.statusText);
    xhr.send();
  });
}