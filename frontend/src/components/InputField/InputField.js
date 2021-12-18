import React from "react";
import "bulma/css/bulma.min.css";
import "./InputField.css";

export default function InputField(props) {
  return (
    <>
      <div class="field">
        <label>{props.name}</label>
        <input class="input" type="text" placeholder={props.place}></input>
      </div>
    </>
  );
}
