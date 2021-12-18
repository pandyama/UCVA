import React, { useState } from "react";
import "bulma/css/bulma.min.css";
import axios from "axios";

function Block(props) {
    const [email, setEmail] = useState("");
    const body = {
        email: email,
    };
    const sendBlockUserRequest = () => {
        axios
            .post("http://127.0.0.1:8080/blockUser", body)
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            });
    };
    return (
        <div class="column has-text-centered is-6">
            <label>User Email</label>
            <input
                class="input is-primary"
                type="text"
                placeholder="Enter User's Email"
                value={email}
                onInput={(e) => setEmail(e.target.value)}
            />
            <button
                class="button is-danger"
                onClick={() => {
                    sendBlockUserRequest();
                }}
            >
                Confirm Block
            </button>
        </div>
    );
}

export default Block;
