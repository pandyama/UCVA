import React, { useState, useEffect, Component } from "react";
import "./AnimalPrescription.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Timestamp from "../Timestamp";


async function sendPrescription(prescription) {
  axios
    .post(`http://127.0.0.1:8080/prescription`, {
      prescriptionDetail: prescription,
    })
    .then((res) => {
      console.log(res);
    })
    .catch((error) => {
      console.log(error);
    });
}

export default function AnimalPrescription() {
  var navigate = useNavigate();
  const [a_prescription, setPrescription] = useState();
  const [className, setClass] = useState();
  const [animalDetails, setAnimal] = useState([]);


  useEffect(() => {
    const queryParams = new URLSearchParams(window.location.search);
    console.log(queryParams);
    axios
      .post(`http://127.0.0.1:8080/searchAnimal`, {
        id: Number(queryParams.get("animalID")),
      })
      .then((res) => {
        setAnimal(res.data[0]);
      })
      .catch((error) => {
        console.log(error);
      });
    setClass("none");
  }, []);



  const p1 = () => {
    if (a_prescription == undefined) {
      setClass("inline");
    } else if (a_prescription.length > 1) {
      const timestamp = Timestamp.getCurrentTime();
      const queryParams = new URLSearchParams(window.location.search);
      var prescription = {
        time: String(timestamp),
        details: String(a_prescription),
        diagnosisID: Number(queryParams.get("diagnoseID")),
      };
      sendPrescription(prescription);
      setClass("none");
      navigate("/home");
    }

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
                <p class="title is-2">{animalDetails[0]}</p>

                <div class="c1">
                  <p class="title is-5">{animalDetails[1]}yrs old</p>
                </div>
                <div class="c1">
                  <p class="title is-5">{animalDetails[2]}</p>
                </div>
                <div class="c1">
                  <p class="title is-5">{animalDetails[3]}</p>
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
            <label
              class="label has-text-danger-dark is-size-6 is-family-sans-serif"
              style={{ display: className }}
            >
              Must fill out all the fields
            </label>
              <label class="label has-text-black-bis is-size-4 is-family-sans-serif">
                Enter Prescription Details
              </label>
              <div class="columns is-mobile is-centered is-vcentered">
                <div class="column">
                  <textarea
                    class="textarea is-medium"
                    rows="8"
                    placeholder="Prescription Details"
                    onChange={(e) => setPrescription(e.target.value)}
                  ></textarea>
                </div>
              </div>
              <div class="columns is-mobile is-centered is-vcentered">
                <div class="control">
                  <button class="button is-link" onClick={p1}>Send Prescription</button>
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
