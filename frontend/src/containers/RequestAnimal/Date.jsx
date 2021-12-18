import React from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "./Request.css";
import "bulma/css/bulma.min.css";

function GetDate(props) {
    return (
        <div class="field">
            <label class="label">{props.label}</label>
            <div class="control has-icons-left has-icons-right">
                <DatePicker
                    name={props.name}
                    selectsRange={true}
                    startDate={props.startDate}
                    endDate={props.endDate}
                    onChange={(newRange) => props.setDateRange(newRange)}
                    isClearable={true}
                    minDate={new Date()}
                />
            </div>
        </div>
    );
}

export default GetDate;
