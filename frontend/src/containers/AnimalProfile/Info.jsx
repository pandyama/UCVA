import React from "react";

function InfoDate(props) {
    return (
        <div>
            <p>Name: {props.name}</p>
            <p>Age: {props.age}</p>
            <p>Breed: {props.breed}</p>
            <p>Sex: {props.sex}</p>
        </div>
    );
}

export default InfoDate;
