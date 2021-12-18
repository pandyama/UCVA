import React, { useState } from "react";
// import React, { useState } from "react";
import axios from "axios";


export default function UpdateHealth() {

    const [health, setHealth] = useState("");
    const queryParams = new URLSearchParams(window.location.search);
    const updateHealth = () => {
        const animalHealth = {
            animalID: Number(queryParams.get("animalID")),
            healthStatus: String(health)
        }
        axios.post("http://127.0.0.1:8080/updateHealth", animalHealth)
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            });
    };

    return (
        <>
            <div id="form" class="columns is-mobile is-centered">
                <div class="column">
                    <div class="card has-background-warning">
                        <div class="card-content">
                            <div class="media-content">
                                <div class="columns">
                                    <div class="column">
                                        <p class="title is-2">{queryParams.get("animalName")}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="column">
                    <div class="field">
                        <label class="label">Health</label>
                        <div class="control">
                            <div class="select">
                                <select
                                    name="health"
                                    value={health}
                                    onChange={(e) => {
                                        setHealth(e.target.value);
                                    }}
                                >
                                    <option value="Healthy">Healthy</option>
                                    <option value="Sick">Sick</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="column">
                    <div class="field">
                        <div class="control">
                            <button
                                class="button is-link"
                                onClick={() => {
                                    updateHealth();
                                }}>Submit</button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};