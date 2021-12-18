import React, { useState, useEffect, Component } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Timestamp from "../Timestamp";

async function sendAlert(alert) {
  var receivedAlert = alert;
  axios
    .post(`http://127.0.0.1:8080/addAlert`, {
      alert: receivedAlert,
    })
    .then((res) => {
      console.log(res);
    })
    .catch((error) => {
      console.log(error);
    });
}

export default function AddAlert() {
  var navigate = useNavigate();

  const [a_location, setLocation] = useState();
  const [a_alert, setAlert] = useState();
  const [className, setClass] = useState();
  const [a_status, setStatus] = useState();

  useEffect(() => {
    setClass("none");
  }, []);

  const addAlert = () => {
    if (
      (a_location != undefined || a_location != "") &&
      (a_alert != undefined || a_alert != "")
    ) {
      const timestamp = Timestamp.getCurrentTime();
      var alert = {
        location: String(a_location),
        alert: String(a_alert),
        status: String(a_status),
        time: String(timestamp),
        user: Number(localStorage.getItem("userID")),
      };
      sendAlert(alert);
      setClass("none");
      navigate("/home");
    } else {
      setClass("inline");
    }
  };
  function handleStatus(e) {
    setStatus(e.target.value);
  }

  return (
    <>
      <div id="form" class="columns is-mobile is-centered is-vcentered">
        <div class="box has-text-centered has-background-grey-lighter	">
          <label
            class="label has-text-danger-dark is-size-6 is-family-sans-serif"
            style={{ display: className }}
          >
            Must fill out all the fields
          </label>
          <label class="label has-text-black-bis is-size-4 is-family-sans-serif">
            Add New Alert
          </label>
          <div class="columns is-mobile is-centered is-vcentered">
            <div class="column">
              <label class="label">Location Affected</label>
              <input
                class="input"
                type="text"
                placeholder="Enter name"
                onChange={(e) => setLocation(e.target.value)}
              />
            </div>
            <div class="column">
              <label class="label">Alert Details</label>
              <input
                class="input"
                type="text"
                placeholder="Enter Details"
                onChange={(e) => setAlert(e.target.value)}
              />
            </div>
          </div>
          <div class="columns is-mobile is-centered is-vcentered">
            <div class="column">
              <label class="label">Outbreak Status</label>
              <div class="select">
                <select onChange={handleStatus.bind(this)}>
                  <option></option>
                  <option>Active</option>
                  <option>Resolved</option>
                </select>
              </div>
            </div>
          </div>

          <div class="columns is-mobile is-centered is-vcentered">
            <div class="field is-grouped">
              <div class="control">
                <button class="button is-link" onClick={addAlert}>
                  Submit
                </button>
              </div>
              <div class="control">
                <button class="button is-link is-light">Cancel</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
