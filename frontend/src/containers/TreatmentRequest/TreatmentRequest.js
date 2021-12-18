import React, { useState, Component } from "react";
import Timestamp from "../Timestamp";
import axios from "axios";

async function sendTreatment(treatment) {
  axios
    .post(`http://127.0.0.1:8080/treatmentRequest`, {
      treatmentDetails: treatment,
    })
    .then((res) => {
      console.log(res);
    })
    .catch((error) => {
      console.log(error);
    });
}

export default function TreatmentRequest() {
  const [t_status, setStatus] = useState();
  const [treatmentDetails, setTreatment] = useState();
  const queryParams = new URLSearchParams(window.location.search);

  const treatmentRequest = () => {
    const timestamp = Timestamp.getCurrentTime();
    setStatus("In Progress");
    var treatmentInfo = {
      userID: Number(localStorage.getItem("userID")),
      time: String(timestamp),
      details: String(treatmentDetails),
      status: String(t_status),
      animalID: Number(queryParams.get("animalID")),
    };
    sendTreatment(treatmentInfo);
  };

  return (
    <>
      <div id="form" class="columns is-mobile is-centered is-2">
        <div class="column">
          <div class="card has-background-warning">
            <figure class="image is-128x128">
              <img
                class="is-rounded"
                src="https://bulma.io/images/placeholders/128x128.png"
              />
            </figure>

            <div class="card-content">
              <div class="media-content">
                <p class="title is-2">Buddy</p>

                <div class="c1">
                  <p class="title is-5">2yrs old</p>
                </div>
                <div class="c1">
                  <p class="title is-5">MALE</p>
                </div>
                <div class="c1">
                  <p class="title is-5">Pomsky</p>
                </div>
              </div>
              <div class="content">
                <div class="columns">
                  <div class="column">
                    <div class="box has-text-centered">
                      <p class="title is-5">Diet</p>
                      <p class="subtitle">Liquid Diet only</p>
                    </div>
                  </div>
                  <div class="column">
                    <div class="box has-text-centered">
                      <p class="title is-5">Instructions</p>
                      <p class="subtitle">Has separation Anxiety</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="column">
          <div class="box has-text-centered has-background-grey-lighter">
            <label class="label has-text-black-bis is-size-4 is-family-sans-serif">
              Treatment Request Details
            </label>
            <div class="columns is-mobile is-centered is-vcentered">
              <div class="column">
                <textarea
                  class="textarea is-medium"
                  rows="8"
                  placeholder="Diagnosis Details"
                  onChange={(e) => setTreatment(e.target.value)}
                ></textarea>
              </div>
            </div>
            <div class="columns is-mobile is-centered is-vcentered">
              <div class="control">
                <button class="button is-link" onClick={treatmentRequest}>
                  Send Request
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
