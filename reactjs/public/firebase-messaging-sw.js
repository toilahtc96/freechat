importScripts('https://www.gstatic.com/firebasejs/7.6.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.18.0/firebase-messaging.js');
importScripts('https://www.gstatic.com/firebasejs/7.6.0/firebase-analytics.js');
const messagingSenderId = "810671594218";

firebase.initializeApp({
    messagingSenderId
});
console.log(firebase);
const messaging = firebase.messaging();