/**
 * The URLs used to fetch and send data to the beckend server
 */
const GETUrl = "http://localhost:8080/getuser";
const POSTUrl = "http://localhost:8080/prefs";

/**
 * Placeholder images used for the user cards, one for each gender
 */
const male_ph = "placeholder.png";
const female_ph = "placeholder_female.png";

/**
 * Constant variables pointing to the main divisions of the frontend
 */
const cards = document.querySelector('.card');
const main = document.querySelector('.main');

/**
 * Constant variables pointing to the divisions used in the card system
 */
const matchDiv = document.querySelector('.match');
const matchText = matchDiv.querySelector('h1');
const defMatchText = "You matched with ";

/**
 * Constant variables pointing to the elements used to display user data on the card panels
 */
const image = cards.querySelector("img");
const infoLine = document.querySelector('.infoLine');
const bioLine = document.querySelector('.bioLine');

/**
 * Constant variables pointing to the elements used to handle the like/dislike logic
 */
const likeButton = document.querySelector('.like');
const dislikeButton = document.querySelector('.dislike');

/**
 * Constant variables pointing to the elements used to handle user prefences
 */
const settings = document.querySelector('.settings');
const ageMin = settings.querySelector('[name=ageMin]');
const ageMax = settings.querySelector('[name=ageMax]');
const submitButton = settings.querySelector('[name=submitButton]');
const form = document.querySelector('form');

/**
 * Constant variables storing data about the currently displayed user
 */
let currLike = false;
let currName = "";


/**
 * Fetches the data for a new card as soon as the page loads
 */
window.onload = function() {
  init();
};

function init() {
  newCard();
}


/**
 * Handles the logic of submitting user prefences to the backend server
 */
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
    setTimeout(newCard, 500);
});


/**
 * Handles the logic of the like button, calling the match() function if both users like eachother
 */
likeButton.addEventListener('click', () => {
  if(currLike === true){ match(); }
  newCard();
});


/**
 * Handles the logic of the dislike button, fetching the data of the next user
 */
dislikeButton.addEventListener('click', () => {
  newCard();
});


/**
 * Handles the logic of matching with another user, showing the match panel
 */
function match(){
  main.style.filter = "blur(10px)";
  main.style.WebkitFilter = "blur(10px)";
  matchDiv.style.visibility = "visible";
  matchText.textContent = defMatchText + currName + "!";
  setTimeout(matchFade, 3000);
}


/**
 * Handles the logic of fading out the match panel
 */
function matchFade(){
  main.style.filter = "blur(0px)";
  main.style.WebkitFilter = "blur(0px)";
  matchDiv.style.visibility = "hidden";
}


/**
 * Handles the logic of fetching user data from the backend server and showing it on the card panel
 */
function newCard() {
  getData(GETUrl)
  .then(response => {
    infoLine.textContent = response.nev + " (" + response.kor + ")";
    bioLine.textContent = response.leiras;
    currLike = JSON.parse(response.kedvel);
    currName = response.nev;

    if(response.nem === "ferfi"){
      image.src = male_ph
    }
    else{
      image.src = female_ph
    }
  })
  .catch(error => {
    console.error("Error:", error);
  });
}


/**
 * Send out a API Call with the GET method to the specified URL, fetching the JSON data
 * @param {String} url - The target URL of the call, including the subdomain
 */
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


/**
 * Send out a API Call with the POST method to the specified URL, sending the specified JSON data
 * @param {String} url - The target URL of the call, including the subdomain
 * @param {JSONObject} data - The specified data object, to be sent to the backen server
 */
function postData(url, data) {
  const xhr = new XMLHttpRequest();

  xhr.open('POST', url);
  xhr.setRequestHeader('Content-Type', 'application/json');

  xhr.onload = function() {
    if (xhr.status >= 200 && xhr.status < 300) {
      console.log(xhr.response);
    } else {
      console.error(xhr.statusText);
    }
  };

  xhr.onerror = function() {
    console.error(xhr.statusText);
  };

  xhr.send(JSON.stringify(data));
}