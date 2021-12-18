import React, { useState, useEffect, Component } from "react";
import "./AddAnimal.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";

async function sendAnimal(animal2) {
  var receivedAnimal = animal2;
  axios
    .post(`http://127.0.0.1:8080/addAnimal`, {
      animal: receivedAnimal,
    })
    .then((res) => {
      console.log(res);
    })
    .catch((error) => {
      console.log(error);
    });
}

export default function AddAnimal() {
  var navigate = useNavigate();
  const [a_name, setName] = useState();
  const [a_dob, setDob] = useState();
  const [a_sex, setSex] = useState();
  const [a_age, setAge] = useState();
  const [a_breed, setBreed] = useState();
  const [a_weight, setWeight] = useState();
  const [a_color, setColor] = useState();
  const [a_species, setSpecies] = useState();
  const [a_location, setLocation] = useState();
  const [a_purpose, setPurpose] = useState();
  const [a_tattoo, setTattoo] = useState();
  const [a_cityTattoo, setCityTattoo] = useState();
  const [a_rfid, setRfid] = useState();
  const [a_microchip, setMicrochip] = useState();
  const [className, setClass] = useState();

  useEffect(() => {
    setClass("none");
  }, []);

  const addAnimal = () => {
    if (
      (a_name != undefined || a_name != "") &&
      (a_dob != undefined || a_dob != "") &&
      (a_sex != undefined || a_sex != "") &&
      (a_age != undefined || a_age != "") &&
      (a_breed != undefined || a_breed != "") &&
      (a_weight != undefined || a_weight != "") &&
      (a_color != undefined || a_color != "") &&
      (a_species != undefined || a_species != "") &&
      (a_location != undefined || a_location != "") &&
      (a_purpose != undefined || a_purpose != "") &&
      (a_tattoo != undefined || a_tattoo != undefined) &&
      (a_cityTattoo != undefined || a_cityTattoo != "") &&
      (a_rfid != undefined || a_rfid != "") &&
      (a_microchip != undefined || a_microchip != "")
    ) {
      var animal = {
        name: String(a_name),
        dob: String(a_dob),
        sex: String(a_sex),
        age: Number(a_age),
        breed: String(a_breed),
        weight: Number(a_weight),
        color: String(a_color),
        species: String(a_species),
        location: String(a_location),
        purpose: String(a_purpose),
        tattoo: Number(a_tattoo),
        cityTattoo: String(a_cityTattoo),
        rfid: Number(a_rfid),
        microchip: Number(a_microchip),
      };
      sendAnimal(animal);
      setClass("none");
      navigate("/home");
    } else {
      setClass("inline");
    }
  };

  const getSex = (e) => {
    setSex(e.target.value);
  };

  function handlePurpose(e) {
    setPurpose(e.target.value);
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
            Add Animal Info
          </label>
          <div class="columns is-mobile is-centered is-vcentered">
            <div class="column file">
              <label class="label"> Profile Image</label>
              <input class="file-input" type="file" name="resume" />
              <span class="file-cta">
                <span class="file-label">Choose an Image</span>
              </span>
            </div>
            <div class="column">
              <label class="label">Name</label>
              <input
                class="input"
                type="text"
                placeholder="Enter name"
                onChange={(e) => setName(e.target.value)}
              />
            </div>
            <div class="column">
              <label class="label">BirthDate</label>
              <input
                class="input"
                type="date"
                onChange={(e) => setDob(e.target.value)}
              />
            </div>
            <div class="column">
              <label class="label">Sex</label>
              <div class="control" onChange={getSex.bind(this)}>
                <label class="radio">
                  <input type="radio" value="M" name="answer" />M
                </label>
                <label class="radio">
                  <input type="radio" name="answer" value="F" />F
                </label>
              </div>
            </div>
            <div class="column">
              <label class="label">Age</label>
              <input
                class="input"
                type="number"
                placeholder="Enter age"
                onChange={(e) => setAge(e.target.value)}
              />
            </div>
            <div class="column">
              <label class="label">Breed</label>
              <input
                class="input"
                type="text"
                placeholder="Enter breed"
                onChange={(e) => setBreed(e.target.value)}
              />
            </div>
          </div>

          <div class="columns is-mobile is-centered is-vcentered">
            <div class="column">
              <label class="label">Weight</label>
              <input
                class="input"
                type="number"
                placeholder="Enter animal weight"
                onChange={(e) => setWeight(e.target.value)}
              />
            </div>
            <div class="column">
              <label class="label">Color</label>
              <input
                class="input"
                type="text"
                placeholder="Enter coat color"
                onChange={(e) => setColor(e.target.value)}
              />
            </div>
            <div class="column">
              <label class="label">Species</label>
              <input
                class="input"
                type="text"
                placeholder="Enter species"
                onChange={(e) => setSpecies(e.target.value)}
              />
            </div>
            <div class="column">
              <label class="label">Permanent Location</label>
              <input
                class="input"
                placeholder="Enter Location"
                type="text"
                onChange={(e) => setLocation(e.target.value)}
              />
            </div>
          </div>

          <div class="columns is-mobile is-centered is-vcentered">
            <div class="column">
              <label class="label">Purpose</label>
              <div class="select">
                <select onChange={handlePurpose.bind(this)}>
                  <option></option>
                  <option>Training</option>
                  <option>Teaching Assistant</option>
                  <option>Support</option>
                  <option>Medical</option>
                </select>
              </div>
            </div>
          </div>

          <div class="columns is-mobile is-centered is-vcentered">
            <div class="column">
              <label class="label">Tattoo Number</label>
              <input
                class="input"
                type="number"
                placeholder="Enter tattoo"
                onChange={(e) => setTattoo(e.target.value)}
              />
            </div>
            <div class="column">
              <label class="label">City Tattoo</label>
              <input
                class="input"
                type="text"
                placeholder="Enter city Tattoo"
                onChange={(e) => setCityTattoo(e.target.value)}
              />
            </div>
            <div class="column">
              <label class="label">RFID</label>
              <input
                class="input"
                type="number"
                placeholder="Enter RFID"
                onChange={(e) => setRfid(e.target.value)}
              />
            </div>
            <div class="column">
              <label class="label">Microchip</label>
              <input
                class="input"
                type="number"
                placeholder="Microchip"
                onChange={(e) => setMicrochip(e.target.value)}
              />
            </div>
          </div>

          <div class="columns is-mobile is-centered is-vcentered">
            <div class="field is-grouped">
              <div class="control">
                <button class="button is-link" onClick={addAnimal}>
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
