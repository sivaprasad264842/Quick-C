import { initializeApp } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-app.js";
import {
    getAuth,
    createUserWithEmailAndPassword,
    sendEmailVerification,
} from "https://www.gstatic.com/firebasejs/10.14.1/firebase-auth.js";
import {
    getFirestore,
    doc,
    setDoc,
} from "https://www.gstatic.com/firebasejs/10.14.1/firebase-firestore.js";

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

document.getElementById("signupForm").addEventListener("submit", async (e) => {
    e.preventDefault(); // ← stops the "?" in URL
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;
    const confirm = document.getElementById("confirmPassword").value;

    if (password !== confirm) {
        alert("Passwords do not match");
        return;
    }

    try {
        const userCredential = await createUserWithEmailAndPassword(
            auth,
            email,
            password
        );
        await sendEmailVerification(userCredential.user, {
            url: "http://127.0.0.1:5500/public/HtmlFiles/Login.html", // ← important!
        });

        await setDoc(doc(db, "users", userCredential.user.uid), {
            email: email,
            createdAt: new Date(),
        });

        alert("Verification email sent! Check your inbox/spam.");
        // Optional: redirect immediately or wait
        // window.location.href = "/public/HtmlFiles/Login.html";
    } catch (err) {
        console.error(err);
        alert(err.message);
    }
});
