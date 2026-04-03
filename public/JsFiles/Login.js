// /public/JsFiles/Login.js
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-app.js";
import {
    getAuth,
    signInWithEmailAndPassword,
    onAuthStateChanged,
} from "https://www.gstatic.com/firebasejs/10.14.1/firebase-auth.js";

const firebaseConfig = {
    apiKey: "AIzaSyC6l1pPdUG1RvOi565td_hnF-TRct0EFWE",
    authDomain: "quickc-ecom.firebaseapp.com",
    projectId: "quickc-ecom",
    storageBucket: "quickc-ecom.firebasestorage.app",
    messagingSenderId: "753457062369",
    appId: "1:753457062369:web:88bda2644e3d366e7172a0",
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);


onAuthStateChanged(auth, (user) => {
    if (user && user.emailVerified) {
        window.location.href = "/public/HtmlFiles/home.html";
    }
});

document.getElementById("loginForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const email = document.getElementById("loginEmail").value.trim();
    const password = document.getElementById("loginPassword").value;

    try {
        const userCredential = await signInWithEmailAndPassword(
            auth,
            email,
            password
        );

        if (!userCredential.user.emailVerified) {
            alert("Please verify your email first!");
            auth.signOut();
            return;
        }

        
        window.location.href = "/public/HtmlFiles/home.html";
    } catch (error) {
        alert(error.message);
    }
});
