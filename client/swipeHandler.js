const GETUrl = "http://localhost:8080/getuser";
const POSTUrl = "http://localhost:8080/prefs";

const cards = document.querySelectorAll('.card');
const likeButton = document.querySelector('.like');
const dislikeButton = document.querySelector('.dislike');

const settings = document.querySelector('.settings');
const ageMin = settings.querySelector('[name=ageMin]');
const ageMax = settings.querySelector('[name=ageMax]');
const submitButton = settings.querySelector('[name=submitButton]');

const form = document.querySelector('form');

const infoLine = document.querySelector('.infoLine');
const bioLine = document.querySelector('.bioLine');

submitButton.addEventListener('click', () => {

    const POSTData = {
      genderPref: form.elements.gender.value,
      minAge: ageMin.value,
      maxAge: ageMax.value
    }

    postData(POSTUrl, POSTData);
  });

likeButton.addEventListener('click', () => {
  console.log("Like");
  newCard();
});

dislikeButton.addEventListener('click', () => {
  console.log("Dislike");
  newCard();
});

function newCard() {
  getData(GETUrl)
  .then(response => {
    infoLine.textContent = response.nev + " (" + response.kor + ")";
    bioLine.textContent = response.leiras;
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

function postData(url, data) {
  const xhr = new XMLHttpRequest();

  xhr.open('POST', url);
  xhr.setRequestHeader('Content-Type', 'application/json');

  xhr.onload = function() {
    if (xhr.status >= 200 && xhr.status < 300) {
      // Success
      console.log(xhr.response);
    } else {
      // Request failed
      console.error(xhr.statusText);
    }
  };

  xhr.onerror = function() {
    console.error(xhr.statusText);
  };

  xhr.send(JSON.stringify(data));
}