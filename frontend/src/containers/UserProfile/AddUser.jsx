import React, { useState } from "react";
import "bulma/css/bulma.min.css";
import axios from "axios";

function Add(props) {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [role, setRole] = useState("student");
    const [password, setPassword] = useState("");
    const sendNewUserRequest = () => {
        const userData = {
            username: username,
            email: email,
            role: role,
            password: password,
        };
        axios
            .post("http://127.0.0.1:8080/addUser", userData)
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            });
    };
    return (
        <div class="column has-text-centered is-6">
            <div class="field">
                <label class="label">Username</label>
                <div class="control">
                    <input
                        class="input"
                        type="text"
                        placeholder="Enter Username"
                        name="username"
                        value={username}
                        onInput={(e) => setUsername(e.target.value)}
                    />
                </div>
            </div>
            <div class="field">
                <label class="label">Email</label>
                <div class="control">
                    <input
                        class="input"
                        type="text"
                        placeholder="Enter Email"
                        name="email"
                        value={email}
                        onInput={(e) => setEmail(e.target.value)}
                    />
                </div>
            </div>
            <div class="field">
                <label class="label">Role</label>
                <div class="control">
                    <div class="select">
                        <select
                            name="role"
                            value={role}
                            onChange={(e) => {
                                setRole(e.target.value);
                            }}
                        >
                            <option value="student">Student</option>
                            <option value="admin">Admin</option>
                            <option value="health_technician">
                                Health Technician
                            </option>
                            <option value="teaching_technician">
                                Teaching Technician
                            </option>
                            <option value="care_attendant">
                                Care Attendant
                            </option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="field">
                <label class="label">Password</label>
                <div class="control">
                    <input
                        class="input"
                        type="text"
                        placeholder="Enter User's Password"
                        name="password"
                        value={password}
                        onChange={(e) => {
                            setPassword(e.target.value);
                        }}
                    />
                </div>
            </div>
            <div class="field">
                <div class="control">
                    <button
                        class="button is-link"
                        onClick={() => {
                            sendNewUserRequest();
                        }}
                    >
                        Submit
                    </button>
                </div>
            </div>
        </div>
    );
}

export default Add;
