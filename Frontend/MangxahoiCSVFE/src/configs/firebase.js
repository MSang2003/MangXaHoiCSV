import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";

const firebaseConfig = {
  apiKey: "AIzaSyC8scsGmtc6LOPaMj1EUe7FQL6-F0SES1E",
  authDomain: "chatmxh-1946a.firebaseapp.com",
  projectId: "chatmxh-1946a",
  storageBucket: "chatmxh-1946a.appspot.com",
  messagingSenderId: "977806701731",
  appId: "1:977806701731:web:80e2ac408c95d64e393b16",
  measurementId: "G-838L1LGS8R"
};


const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

const auth = getAuth(app);
const db = getFirestore(app);

export { auth, db };