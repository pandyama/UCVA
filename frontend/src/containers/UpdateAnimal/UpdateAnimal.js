import React, { useState, Component } from "react";
import "./UpdateAnimal.css";
import axios from "axios";


async function sendUpdate(animal2){
  var receivedAnimal = animal2;
  axios.post(`http://127.0.0.1:8080/updateAnimal`,{
    animal: receivedAnimal
  })
  .then((res) => {
    console.log(res);
  })
  .catch((error) => {
    console.log(error);
  })
}


export default function UpdateAnimal() {
  const [a_age, setAge] = useState();
  const [a_weight, setWeight] = useState();
  const [a_color, setColor] = useState();
  const [a_location, setLocation] = useState();
  const [a_purpose, setPurpose] = useState();

  const updateAnimal = () => {
    var animal = {
      age: Number(a_age),
      weight: Number(a_weight),
      color: String(a_color),
      location: String(a_location),
      purpose: String(a_purpose),
    };
    sendUpdate(animal);
  };

  function handlePurpose(e) {
    // console.log(e.target.value);
    setPurpose(e.target.value);
  }

  return (
    <>
      <div id="form" class="columns is-mobile is-centered is-vcentered">
        <div class="box has-text-centered has-background-grey-lighter	">
          <label class="label has-text-black-bis is-size-4 is-family-sans-serif">
            Update Animal Info
          </label>
          <div class="columns is-mobile is-centered is-vcentered">
            <div class="column file">
              <label class="label"> Profile Image</label>
              <input class="file-input" type="file" name="resume" />
              <span class="file-cta">
                {/* <span class="file-icon">
                    <i class="fas fa-upload"></i>
                  </span> */}
                <span class="file-label">Choose an Image</span>
              </span>
            </div>
            <div class="column">
              <label class="label">Name</label>
              <input
                class="input is-danger"
                type="text"
                placeholder="Enter name"
                disabled
              />
            </div>
            <div class="column">
              <label class="label">BirthDate</label>
              <input class="input is-danger" type="date" disabled />
            </div>
            <div class="column">
              <label class="label">Sex</label>
              <div class="control">
                <label class="radio">
                  <input type="radio" name="answer" disabled />
                  Male
                </label>
                <label class="radio">
                  <input type="radio" name="answer" disabled />
                  Female
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
                class="input is-danger"
                type="text"
                placeholder="Enter breed"
                disabled
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
                class="input is-danger"
                type="text"
                placeholder="Enter species"
                disabled
              />
            </div>
            <div class="column">
              <label class="label">Permanent Location</label>
              <input
                class="input"
                placeholder="Location"
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
                class="input is-danger"
                type="number"
                placeholder="Enter tattoo"
                disabled
              />
            </div>
            <div class="column">
              <label class="label">City Tattoo</label>
              <input
                class="input is-danger"
                type="number"
                placeholder="Enter city Tattoo"
                disabled
              />
            </div>
            <div class="column">
              <label class="label">RFID</label>
              <input
                class="input is-danger"
                type="number"
                placeholder="Enter RFID"
                disabled
              />
            </div>
            <div class="column">
              <label class="label">Microchip</label>
              <input
                class="input is-danger"
                type="number"
                placeholder="Microchip"
                disabled
              />
            </div>
          </div>

          <div class="columns is-mobile is-centered is-vcentered">
            <div class="field is-grouped">
              <div class="control">
                <button class="button is-link" 
                 onClick={updateAnimal}>Submit</button>
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
