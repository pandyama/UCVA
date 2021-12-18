import React, { useState, useEffect, Component } from "react";
import "./AnimalDiagnosis.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Timestamp from "../Timestamp";
import { Link } from "react-router-dom";

async function sendDiagnosis(diagnosis) {
  axios
    .post(`http://127.0.0.1:8080/diagnose`, {
      diagnosisDetail: diagnosis,
    })
    .then((res) => {
      console.log(res);
    })
    .catch((error) => {
      console.log(error);
    });
}

var lst;
const populateData = (data) => {
  lst = data;
};

export default function AnimalDiagnosis() {
  var navigate = useNavigate();
  const [a_diagnosis, setDiagnosis] = useState();
  const [className, setClass] = useState();
  const [allDiagnosisList, setAll] = useState([]);
  const [animalDetails, setAnimal] = useState([]);
  const [animalID, setAnimalID] = useState([]);
  var navigate = useNavigate();

  useEffect(() => {
    const queryParams = new URLSearchParams(window.location.search);
    setAnimalID(queryParams.get("animalID"));
    axios
      .post(`http://127.0.0.1:8080/allDiagnosis`, {
        id: Number(queryParams.get("animalID")),
      })
      .then((res) => {
        var x = JSON.stringify(res.data);
        var js = JSON.parse(x);
        setAll(js);
        populateData(js);
      })
      .catch((error) => {
        console.log(error);
      });
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

  const d1 = () => {
    if (a_diagnosis == undefined) {
      setClass("inline");
    } else if (a_diagnosis.length > 1) {
      const timestamp = Timestamp.getCurrentTime();
      const queryParams = new URLSearchParams(window.location.search);
      var diagnosis = {
        userID: Number(localStorage.getItem("userID")),
        time: String(timestamp),
        details: String(a_diagnosis),
        animalID: Number(queryParams.get("animalID")),
      };
      sendDiagnosis(diagnosis);
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
              Enter Diagnosis Details
            </label>
            <div class="columns is-mobile is-centered is-vcentered">
              <div class="column">
                <textarea
                  class="textarea is-medium"
                  rows="8"
                  placeholder="Diagnosis Details"
                  onChange={(e) => setDiagnosis(e.target.value)}
                ></textarea>
              </div>
            </div>
            <div class="columns is-mobile is-centered is-vcentered">
              <div class="control">
                <button onClick={d1} class="button is-link">
                  Send Diagnosis
                </button>
              </div>
              <div class="control">
                <button class="button is-link is-light">Cancel</button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <table class="table">
        <thead>
          <tr>
            <th>
              <h2 title="Position">Diagnosis ID</h2>
            </th>
            <th>
              <h2 title="Won">Diagnosis Details</h2>
            </th>
            <th>
              <h2 title="Lost">Submitted On</h2>
            </th>
            <th>
              <h2 title="Lost">Add Prescription</h2>
            </th>
          </tr>
        </thead>
        <tbody>
          {allDiagnosisList.map((item, index) => {
            return (
              <tr key={index}>
                <td>{item.diagnosis[0]}</td>
                <td>{item.diagnosis[1]}</td>
                <td>{item.diagnosis[2]}</td>
                <button class="button is-success">
                  <Link
                    class="is-link has-text-white	"
                    to={"/prescription?diagnoseID=" + item.diagnosis[0]+"&animalID="+animalID}
                  >
                    Prescribe
                  </Link>
                </button>
              </tr>
            );
          })}
        </tbody>
      </table>
    </>
  );
}
