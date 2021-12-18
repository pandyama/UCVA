import React from "react";

function HealthData(props) {
    return (
        <div>
            <p>Health: {props.health}</p>
            <p>Diet: {props.diet}</p>
            <p>Instructions: {props.instr}</p>
        </div>
    );
}

export default HealthData;
