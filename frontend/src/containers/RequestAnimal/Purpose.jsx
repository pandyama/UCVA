import React from "react";
import "react-datepicker/dist/react-datepicker.css";
import "./Request.css";
import "bulma/css/bulma.min.css";

function GetPurpose(props) {
    return (
        <div class="field">
            <label class="label">Borrow Purpose</label>
            <div class="control">
                <textarea
                    value={props.text}
                    onInput={(e) => props.handler(e.target.value)}
                    maxLength="50"
                    name="purpose"
                    class="textarea"
                    placeholder="Maximum 50 Characters"
                ></textarea>
            </div>
        </div>
    );
}

export default GetPurpose;
