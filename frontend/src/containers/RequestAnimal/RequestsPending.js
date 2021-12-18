import React, { Component } from "react";
import axios from "axios";
import "bulma/css/bulma.min.css";

export default class RequestsApproval extends Component {
    constructor(props) {
        super(props);
        this.state = {
            requests: [],
        };
    }
    componentDidMount() {
        axios.get(`http://127.0.0.1:8080/getRequests`).then((res) => {
            const allRequests = res.data;
            this.state.requests = allRequests;
            this.setState({ requests: allRequests });
        });
    }

    render() {
        this.list = this.state.requests;
        console.log(this.list);
        return (
            <table class="table">
                <thead>
                    <tr>
                        <th>
                            <h2 title="Position">Animal Name</h2>
                        </th>
                        <th>
                            <h2 title="Won">Who</h2>
                        </th>
                        <th>
                            <h2 title="Lost">Purpose</h2>
                        </th>
                        <th>
                            <h2 title="Goals for">Borrow Date</h2>
                        </th>
                        <th>
                            <h2 title="Goals against">Return Date</h2>
                        </th>
                        <th>
                            <h2 title="Goals against">Approved</h2>
                        </th>
                        {/* <th>
              <h2 title="Goals against"></h2>
            </th> */}
                    </tr>
                </thead>
                <tbody>
                    {this.list.map((requests, i) => {
                        return (
                            <tr key={i}>
                                <td>{requests.animalName}</td>
                                <td>{requests.borrowerID}</td>
                                <td>{requests.purpose}</td>
                                <td>{requests.borrowDate.slice(0, 10)}</td>
                                <td>{requests.returnDate.slice(0, 10)}</td>
                                <td>{String(requests.techApproved)}</td>
                                <button
                                    class="button is-success"
                                    onClick={() => {
                                        axios.post(
                                            "http://127.0.0.1:8080/approveAdmin",
                                            {
                                                position: i,
                                            }
                                        );
                                    }}
                                >
                                    Approve
                                </button>
                                <button
                                    class="button is-danger"
                                    onClick={() => {
                                        axios.post(
                                            "http://127.0.0.1:8080/cancelRequest",
                                            {
                                                position: i,
                                            }
                                        );
                                    }}
                                >
                                    Reject
                                </button>
                                {/* <td>{requests.adminApproved}</td> */}
                            </tr>
                        );
                    })}
                </tbody>
            </table>
            // </div>
        );
    }
}
