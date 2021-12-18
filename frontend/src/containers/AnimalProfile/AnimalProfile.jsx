import React, { useState, useEffect, Component } from "react";
import "bulma/css/bulma.min.css";
import Info from "./Info";
import Health from "./Health";
import Status from "./Status";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Profile(props) {
    const queryParams = new URLSearchParams(window.location.search);
    var navigate = useNavigate();
    const [state, setState] = useState(0);
    const [comments, setComments] = useState([]);
    const [commentText, setCommentText] = useState("");
    // const comments = [
    //     { user: "John Doo", date: "April 2, 2021", text: "It's cute" },
    //     { user: "Emily Doo", date: "April 3, 2021", text: "Sooooo cute" },
    // ];
    const info = {
        Name: queryParams.get("name"),
        Sex: queryParams.get("sex"),
        Breed: queryParams.get("breed"),
        Age: queryParams.get("age"),
        HealthStatus: queryParams.get("healthStatus"),
        Diet: queryParams.get("specialDiet"),
        Instructions: queryParams.get("specialInstruction"),
    };
    const getAnimalComments = async () => {
        const payload = {
            animalID: queryParams.get("animalID"),
        };
        await axios
            .post("http://127.0.0.1:8080/animalComments", payload)
            .then((res) => {
                console.log(res);
                setComments(res.data);
            })
            .catch((err) => {
                console.log(err);
            });
    };

    useEffect(() => {
        getAnimalComments();
    }, []);

    const addComment = () => {
        axios
            .post("http://127.0.0.1:8080/postComment", {
                userID: localStorage.getItem("userID"),
                text: commentText,
                animalID: queryParams.get("animalID"),
            })
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            });
    };

    const navDiagnosis = () => {
        navigate("/diagnosis?animalID=" + queryParams.get("animalID"));
    };

    const navTreatment = () => {
        navigate("/treatment?animalID=" + queryParams.get("animalID"));
    };

    const navHealth = () => {
        console.log("navHealth being called");
        console.log(queryParams.get("animalID") + queryParams.get("name"));
        navigate("/updateHealth?animalID=" + queryParams.get("animalID")+ "&animalName=" + queryParams.get("name"));
    };
    //need name, age, breed special diet, special instruction, 

    return (
        <div class="container">
            <div class="columns">
                <div class="column is-three-fifths has-text-centered">
                    <figure class="image is-1by1">
                        <img
                            class="is-rounded"
                            src="https://bulma.io/images/placeholders/64x64.png"
                            alt="animalimage"
                        />
                    </figure>
                    <div>
                        {/* <button class="button is-link">Request Animal</button> */}
                        {/* <button class="button is-danger">Disease Alert</button> */}
                        <button
                            class="button is-warning"
                            onClick={navDiagnosis}
                        >
                            Diagnose
                        </button>
                        <button
                            class="button is-success"
                            onClick={navTreatment}
                        >
                            Request Treatment
                        </button>
                        <button
                            class="button is-link"
                            onClick={navHealth}
                        >
                            updateHealth
                        </button>
                    </div>
                </div>
                <div class="column is-three-fifths">
                    <div>
                        <button
                            class="button is-ghost is-active"
                            onClick={() => {
                                setState(0);
                            }}
                        >
                            Info
                        </button>
                        <button
                            class="button is-ghost is-active"
                            onClick={() => {
                                setState(1);
                            }}
                        >
                            Health
                        </button>
                        {/* <button
              class="button is-ghost is-active"
              onClick={() => {
                setState(2);
              }}
            >
              Status
            </button> */}
                        {state === 0 && (
                            <Info
                                name={info.Name}
                                sex={info.Sex}
                                breed={info.Breed}
                                age={info.Age}
                            />
                        )}
                        {state === 1 && (
                            <Health
                                health={info.HealthStatus}
                                diet={info.Diet}
                                instr={info.Instructions}
                            />
                        )}
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="columns">
                    <div class="column is-1">
                        <figure class="image is-64x64">
                            <img
                                class="is-rounded"
                                src="https://bulma.io/images/placeholders/64x64.png"
                                alt="profileimg"
                            />
                        </figure>
                    </div>
                    <div class="column is-9">
                        <input
                            class="input"
                            type="text"
                            placeholder="Type Comment"
                            value={commentText}
                            onInput={(e) => setCommentText(e.target.value)}
                        />
                    </div>
                    <div class="column">
                        <button
                            class="button is-link"
                            onClick={() => {
                                addComment();
                            }}
                        >
                            Comment
                        </button>
                    </div>
                </div>
                <hr />
                {comments.map((comment) => {
                    return (
                        <div class="columns">
                            <div class="column is-1">
                                <figure class="image is-64x64">
                                    <img
                                        class="is-rounded"
                                        src="https://bulma.io/images/placeholders/64x64.png"
                                        alt="profileimg"
                                    />
                                </figure>
                            </div>
                            <div class="column is-9">
                                <p>{comment.user}</p>
                                <p>{comment.text}</p>
                            </div>
                        </div>
                    );
                })}
            </div>
        </div>
    );
}

export default Profile;
