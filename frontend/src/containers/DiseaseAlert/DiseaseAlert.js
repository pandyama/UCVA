// import React from "react";
import React, { Component } from "react";
import axios from "axios";
import "bulma/css/bulma.min.css";

export default class RequestsApproval extends Component {
  constructor(props) {
    super(props);
    this.state = {
      alerts: [] 
    };
  }

  componentDidMount() {
    axios.get(`http://127.0.0.1:8080/viewAlerts`)
.then((res) => {
        this.setState({alerts: res.data})
      })
      .catch((error) => {
        console.log(error);
      })
  };


  render() {
    this.list = this.state.alerts;
    return (
      <div id="form" class="columns is-mobile is-centered is-vcentered">
        <div class="box has-text-centered has-background-warning">
          <table class="table is-bordered is-fullwidth">
            <thead>
              <tr>
                <th>AlertID</th>
                <th>Care Attendant ID</th>
                <th>Reported Date</th>
                <th>Alert Details</th>
                <th>Location Affected</th>
                <th>Outbreak Status</th>
              </tr>
            </thead>
            <tbody>
              {this.list.map((alerts, i) => {
                return (
                  <tr key={i}>
                    <td>{alerts.alertID}</td>
                    <td>{alerts.careAttendantID}</td>
                    <td>{alerts.reportedDate}</td>
                    <td>{alerts.alertDetails}</td>
                    <td>{alerts.locationAffected}</td>
                    <td>{alerts.outbreakStatus}</td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}
