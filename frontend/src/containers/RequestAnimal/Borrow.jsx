import React, { useState } from "react";
import "bulma/css/bulma.min.css";
import Date from "./Date";
import Purpose from "./Purpose";
import axios from "axios";

function BorrowAnimal() {
    const [purpose, setPurpose] = useState("");
    const [dateRange, setDateRange] = useState([null, null]);
    const [startDate, endDate] = dateRange;
    const sendAnimalRequest = () => {
        const queryParams = new URLSearchParams(window.location.search);
        axios
            .post("http://127.0.0.1:8080/requestAnimal", {
                animalID: queryParams.get("animalID"),
                fromDate: startDate.getTime(),
                toDate: endDate.getTime(),
                purpose: purpose,
                userID: localStorage.getItem("userID"),
            })
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            });
    };
    return (
        <div class="columns is-centered">
            <div class="column is-2">
                <div class="container">
                    <Date
                        label="Start Date - End Date"
                        name="date-input"
                        startDate={startDate}
                        endDate={endDate}
                        setDateRange={setDateRange}
                    />
                    <Purpose text={purpose} handler={setPurpose} />
                    <div class="field is-grouped">
                        <div class="control">
                            <button
                                class="button is-link"
                                onClick={sendAnimalRequest}
                            >
                                Request
                            </button>
                        </div>
                        <div class="control">
                            <button class="button is-link is-light">
                                Cancel
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default BorrowAnimal;
