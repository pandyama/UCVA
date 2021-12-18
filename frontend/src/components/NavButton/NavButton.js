import React from "react";
import { useNavigate } from "react-router-dom";
import "bulma/css/bulma.min.css";
import "./NavButton.css";
import auth from "../../pages/Login/auth";

export default function NavButton() {
  var navigate = useNavigate();
  function handleRoute() {
    navigate("/login");
  }
  function handleHome() {
    navigate("/home");
  }
  function handleAnimal() {
    navigate("/addAnimal");
  }
  function handleDiagnosis() {
    navigate("/diagnosis");
  }
  function handlePhoto() {
    navigate("/uploadPhoto");
  }

  function handleDisease() {
    navigate("/viewAlerts");
  }

  function handleUsers() {
    navigate("/manageUsers");
  }

  function handleDiseaseAlert() {
    navigate("/addAlert");
  }

  const logout = async () => {
    auth.setAuthenticated(0);
    navigate("/");
  };

  return (
    <>
      <div class="flex-container">
        <h1 class="title is-size-2">UCVA</h1>
        <button
          onClick={logout}
          class="button is-danger is-outlined is-rounded"
        >
          Logout
        </button>
      </div>

      <button class="button is-link" onClick={handleHome}>
        Home
      </button>
      <button class="button is-link" onClick={handleAnimal}>
        Add Animal
      </button>
      <button class="button is-link" onClick={handlePhoto}>
        Upload Photo
      </button>
      <button class="button is-link" onClick={handleDiseaseAlert}>
        Add Disease Alert
      </button>
      <button class="button is-link is-danger" onClick={handleDisease}>
        View Alerts
      </button>
      <button class="button button is-dark" onClick={handleUsers}>
        Manage Users
      </button>
    </>
  );
}
