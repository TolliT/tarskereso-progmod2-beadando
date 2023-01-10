const GETUrl = "http://localhost:8080/getuser";
const POSTUrl = "http://localhost:8080/prefs";

const male_ph = "placeholder.png";
const female_ph = "placeholder_female.png";

const cards = document.querySelector('.card');
const image = cards.querySelector("img");
const likeButton = document.querySelector('.like');
const dislikeButton = document.querySelector('.dislike');

const settings = document.querySelector('.settings');
const ageMin = settings.querySelector('[name=ageMin]');
const ageMax = settings.querySelector('[name=ageMax]');
const submitButton = settings.querySelector('[name=submitButton]');

const form = document.querySelector('form');

const infoLine = document.querySelector('.infoLine');
const bioLine = document.querySelector('.bioLine');


window.onload = function() {
  init();
};

function init() {
  newCard();
}



submitButton.addEventListener('click', () => {
    if(ageMin.value < 18 || ageMin.value > 99){
      console.log("Minimum age value is incorrect!")
      ageMin.value = 18;
      return;
    }

    if(ageMax.value < 18 || ageMax.value > 99){
      console.log("Maximum age value is incorrect!")
      ageMax.value = 99;
      return;
    }

    if(ageMax.value < ageMin.value){
      console.log("Minimum age must be smaller than maximum age!")
      ageMax.value = parseInt(ageMin.value) + 1;
      return;
    }

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
    if(response.nem == "ferfi"){
      image.src = male_ph
    }
    else{
      image.src = female_ph
    }
  })
  .catch(error => {
    console.log("ERROR: API call failed!");
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