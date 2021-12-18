import logo from "./logo.svg";
import "./App.scss";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login/login";
import Borrow from "./containers/RequestAnimal/Borrow";
import AddAnimal from "./containers/AddAnimal/AddAnimal";
import UpdateAnimal from "./containers/UpdateAnimal/UpdateAnimal";
import AnimalDiagnosis from "./containers/AnimalDiagnosis/AnimalDiagnosis";
import UploadPhoto from "./containers/UploadPhoto/UploadPhoto";
import "bulma/css/bulma.min.css";
import Home from "./pages/Home/home";
import NavButton from "./components/NavButton/NavButton";
import AnimalPrescription from "./containers/AnimalPrescription/AnimalPrescription";
import RequestsPending from "./containers/RequestAnimal/RequestsPending";
import AnimalProfile from "./containers/AnimalProfile/AnimalProfile";
import UserManagement from "./containers/UserProfile/UserManagement";
import DiseaseAlert from "./containers/DiseaseAlert/DiseaseAlert";
import UpdateHealth from "./containers/UpdateHealth/UpdateHealth";
import AddAlert from "./containers/DiseaseAlert/AddAlert";
import TreatmentRequest from "./containers/TreatmentRequest/TreatmentRequest";
import ProtectedRoute from "./pages/Home/ProtectedRoute";
import SecureRoute from "./pages/Home/SecureRoute";
import Level2And3Route from "./pages/Home/Level2And3Route";
import TeachingTechRoute from "./pages/Home/TeachingTechRoute";

function App() {
  return (
    <>
      <Router>
        <NavButton />
        <Routes>
          <Route path="/" element={<Login />}></Route>
          <Route element={<ProtectedRoute />}>
            <Route path="/manageUsers" element={<UserManagement />} />
            <Route path="/requestsPending" element={<RequestsPending />} />
          </Route>

          <Route element={<SecureRoute />}>
            <Route path="/uploadPhoto" element={<UploadPhoto />} />
            <Route path="/viewAlerts" element={<DiseaseAlert />} />
            <Route path="/home" element={<Home />}></Route>
            <Route path="/animalProfile" element={<AnimalProfile />} />
          </Route>

          <Route element={<Level2And3Route />}>
            <Route path="/addAnimal" element={<AddAnimal />} />
            <Route path="/updateHealth" element={<UpdateHealth />} />
            <Route path="/diagnosis" element={<AnimalDiagnosis />} />
            <Route path="/treatment" element={<TreatmentRequest />} />
            <Route path="/addAlert" element={<AddAlert />} />
            <Route path="/updateAnimal" element={<UpdateAnimal />} />
            <Route path="/prescription" element={<AnimalPrescription />} />
          </Route>

          <Route element={<TeachingTechRoute />}>
            <Route path="/borrowAnimal" element={<Borrow />} />
          </Route>
        </Routes>
      </Router>
    </>
  );
}
export default App;
