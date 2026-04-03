import { initializeApp } from "firebase/app";
import {
    getAuth,
    isSignInWithEmailLink,
    signInWithEmailLink,
} from "firebase/auth";
import { getFirestore, doc, setDoc } from "firebase/firestore";

// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyC6l1pPdUG1RvOi565td_hnF-TRct0EFWE",
    authDomain: "quickc-ecom.firebaseapp.com",
    databaseURL: "https://quickc-ecom-default-rtdb.firebaseio.com",
    projectId: "quickc-ecom",
    storageBucket: "quickc-ecom.firebasestorage.app",
    messagingSenderId: "753457062369",
    appId: "1:753457062369:web:88bda2644e3d366e7172a0",
    measurementId: "G-VMMTQWPLJ5",
};
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const db = getFirestore(app);

if (isSignInWithEmailLink(auth, window.location.href)) {
    let email = localStorage.getItem("emailForSignIn");
    if (!email) {
        email = prompt("Enter your email for confirmation");
    }
    signInWithEmailLink(auth, email, window.location.href)
        .then(async (result) => {
            localStorage.removeItem("emailForSignIn");
            // Store user data in Firestore
            await setDoc(doc(db, "users", result.user.uid), {
                email: result.user.email,
                createdAt: new Date(),
            });
            // Redirect to login
            window.location.href = "../public/HtmlFiles/Login.html";
        })
        .catch((error) => {
            console.error(error);
        });
}
