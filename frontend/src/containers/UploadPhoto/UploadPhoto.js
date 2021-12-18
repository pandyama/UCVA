import React, { Component } from "react";
import "./UploadPhoto.css";

export default class UploadPhoto extends Component {
  render() {
    return (
      <>
        <div id="form" class="columns is-mobile is-centered is-vcentered">
          <div class="column is-half">
            <div class="box has-text-centered has-background-grey-lighter">
              <label class="label has-text-black-bis is-size-4 is-family-sans-serif">
                Add Animal Photo
              </label>
              <div class="columns is-mobile is-centered is-vcentered">
                <div class="column file">
                  <label class="label"> Drop files here, or click below:</label>
                  <input class="file-input" type="file" name="resume" />
                  <span class="file-cta">
                    {/* <span class="file-icon">
                    <i class="fas fa-upload"></i>
                  </span> */}
                    <span class="file-label">Choose an Image</span>
                  </span>
                </div>
              </div>

              <div class="columns is-mobile is-centered is-vcentered">
                You can upload files up to a maximum of 2GB
              </div>

              <div class="columns is-mobile is-centered is-vcentered">
                <div class="field is-grouped">
                  <div class="control">
                    <button class="button is-link">Submit</button>
                  </div>
                  <div class="control">
                    <button class="button is-link is-light">Cancel</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  }
}
