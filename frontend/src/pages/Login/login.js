// import React, { Component } from "react";
import React, { useState } from "react";
import { Route, useNavigate, Redirect } from "react-router-dom";
import InputField from "../../components/InputField/InputField";
import axios from "axios";
import "bulma/css/bulma.min.css";
import auth from "./auth";

async function getRole(uname, pw) {
  const role = axios.post(`http://127.0.0.1:8080/getRole`, {
    username: uname,
    password: pw,
  });
  const dataPromise = role.then((res) => {
    localStorage.setItem("userID", res.data[0].userID);
    localStorage.setItem("accessLevel", res.data[0].access);
    axios
      .post(`http://127.0.0.1:8080/login`, {
        username: uname,
        password: pw,
        role: res.data[0].uRole,
      })
      .then((res2) => {
        localStorage.setItem("token", res2.data.message);
      });
  });
  return dataPromise;
}

export default function Login() {
  var navigate = useNavigate();
  const [username, setUsername] = useState();
  const [password, setPassword] = useState();
  
  const signIn = async () => {
    getRole(username, password)
      .then(() => {
        auth.login(() => {
          localStorage.setItem("authenticated", 1);
          auth.authenticated = 1;
          navigate("/home");
        });
      })
      .catch((e) => {
        console.log(username,password);
        window.alert("Invalid username or password, please try again");
        auth.login(() => {
          localStorage.setItem("authenticated", 0);
          auth.authenticated = 0;
          navigate("/");
        });
      });
  };
  return (
    <div className="App">
      <div class="columns is-mobile is-centered is-vcentered">
        <div class="box has-text-centered has-background-grey-lighter	">
          <label class="label has-text-black-bis is-size-4 is-family-sans-serif">
            Vet Application
          </label>
          <div class="field">
            <input
              class="input"
              type="text"
              placeholder="username"
              // onChange={this.handleUsername}
              onChange={(e) => setUsername(e.target.value)}
            ></input>
          </div>
          <div class="field">
            <input
              class="input"
              type="text"
              // onChange={this.handlePassword}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="password"
            ></input>
          </div>
          <div class="columns">
            <div class="column">
              <button
                onClick={signIn}
                class="button has-background-success has-text-white-bis"
              >
                Sign in
              </button>
            </div>
            <div class="column">
              <button class="button has-background-dark	has-text-white-bis	">
                Forgot Password
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
