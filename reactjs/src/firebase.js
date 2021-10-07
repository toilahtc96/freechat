import { firebase } from '@firebase/app';
import '@firebase/messaging';
import $ from 'jquery';


// Assign handlers immediately after making the request,
// and remember the jqxhr object for this request
var jqxhr = $.getJSON("test.json", function () {
    console.log("success");
})
    .done(function () {
        console.log("second success");
    })
    .fail(function (err) {
        console.log(err);
        console.log("error");
    })
    .always(function () {
        console.log("complete");
    });

// Perform other work here ...

// Set another completion function for the request above
jqxhr.always(function () {
    console.log("second complete");
});
// TODO: Replace the following with your app's Firebase project configuration
const firebaseConfig = {
    apiKey: "AIzaSyC_3rY63CTvbxLc1KICJJkQZGMU9WIDFGU",
    authDomain: "message-project-6af76.firebaseapp.com",
    projectId: "message-project-6af76",
    storageBucket: "message-project-6af76.appspot.com",
    messagingSenderId: "810671594218",
    appId: "1:810671594218:web:cd44f5caa1cc4f876f1593",
    measurementId: "G-2JYTQWNYJL"
};

const firebaseApp = firebase.initializeApp(firebaseConfig);
export const messaging = firebase.messaging();
export const getToken = messaging.getToken({ vapidKey: 'BMdt88vX_XN1dYq8x1a62sIm2fukSl6bOucuMkSFnWqIFIJ74r24ESmASrfsCVj1h1bVVtO_TtkdgN8-Eydn4Dk' }).then((currentToken) => {
    if (currentToken) {
        // Send the token to your server and update the UI if necessary
        // ...
    } else {
        // Show permission request UI
        console.log('No registration token available. Request permission to generate one.');
        // ...
    }
}).catch((err) => {
    console.log('An error occurred while retrieving token. ', err);
    // ...
});

