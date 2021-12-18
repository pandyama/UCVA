import React, { Component } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import "../Home/home.css";
import p1 from "../Home/p1.jpg";
import p2 from "../Home/p2.jpg";
import p3 from "../Home/p3.jpg";
import p4 from "../Home/p4.jpg";
import p5 from "../Home/p5.jpg";

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      animals: [],
      images: [p1, p2, p3, p4, p5],
      searchedAnimal: ""
    };
    this.handleRequest = this.handleRequest.bind(this);
    this.handleAnimal = this.handleAnimal.bind(this);
    this.clickHandler = this.clickHandler.bind(this);
    this.isAvailable = this.isAvailable.bind(this);
    this.searchAnimal = this.searchAnimal.bind(this);
    this.setAnimal = this.setAnimal.bind(this);
  }

  handleRequest(e) {
    console.log("handle request");
    console.log(e);
  }

  handleAnimal(e) {
    console.log("handle animal" + e);
  }

  componentDidMount() {
    axios
      .get(`http://127.0.0.1:8080//allAnimals`)
      .then((res) => {
        console.log("here is the animal data:", res.data);
        console.log("Images are ");
        console.log(this.state.images);
        this.setState({ animals: res.data });
      })
      .catch((error) => {
        console.log(error);
      });
  }

  isAvailable(animal) {
    if (animal.borrowStatus === 0) {
      return "tag is-success is-medium";
    }
    return "tag is-danger is-medium";
  }

  healthStatus(animal) {
    if (animal.healthStatus === "Healthy") {
      return "tag is-success is-medium";
    }
    if (animal.healthStatus === "Sick") {
      return "tag is-danger is-medium";
    } else {
      return "tag is-dark is-medium";
    }
  }

  searchAnimal(e){
      this.setState({searchedAnimal: e.target.value});
  }
  setAnimal(){
    console.log(this.state.searchedAnimal);
    axios
      .post(`http://127.0.0.1:8080/searchAnimalByName`, {
        name: String(this.state.searchedAnimal),
      })
      .then((res) => {
        console.log("Getting individual animal details");
        console.log(res.data.length);
        if(res.data.length == 0){
            alert("Animal not found");
        }
        else{
            this.setState({ animals: res.data });
            this.forceUpdate();
        }
        // setAnimal(res.data[0]);
        
      })
      .catch((error) => {
        console.log(error);
      });
  }
    healthStatus(animal) {
        if (animal.healthStatus === "Healthy") {
            return "tag is-success is-medium";
        }
        if (animal.healthStatus === "Sick") {
            return "tag is-danger is-medium";
        } else {
            return "tag is-dark is-medium";
        }
    }

    clickHandler(animal) {
        console.log(animal);
    }

  clickHandler(animal) {
    console.log(animal);
  }

  render() {
    return (
      <div>
        <div class="flex-container">
          <input /*search bar*/
            class="input is-rounded"
            type="text"
            placeholder="Search Animal"
            onChange={this.searchAnimal}
          />
          {/* <div class="control"> */}
            <button class="button is-link is-rounded" onClick={this.setAnimal}>Search</button>
          {/* </div> */}
        </div>

        <div class="columns mt-2 is-multiline">
          {/* <div class="column" style={{ display: 'flex' }}> */}
          {this.state.animals.map((animal, i) => {
            return (
              <div key={i}>
                <div class="column" style={{ display: "flex" }}>
                  <div class="card has-background-warning">
                    {/* <figure class="image is-2by1"> */}
                    <img
                      width="200"
                      height="200"
                      // class="is-rounded"
                      // src="https://bulma.io/images/placeholders/64x64.png"
                      src={this.state.images[i + 1]}
                    />
                    {/* </figure> */}

                    <div class="card-content">
                      <div class="media-content">
                        <div class="columns">
                          <div class="column">
                            <p class="title is-4">{animal.aName}</p>
                            {/* <p class="title is-4">AnimalName</p> */}
                          </div>
                          <div class="column">
                            {/* <span class={this.isAvailable(animal)}>placeholder</span> */}
                            <span class={this.isAvailable(animal)}>
                              {animal.borrowStatus
                                ? "Not Available"
                                : "Available"}
                            </span>
                            {/* <span class="tag is-success is-medium">{isAvailable}</span> */}
                          </div>
                          <div class="column">
                            <span class={this.healthStatus(animal)}>
                              {animal.healthStatus == null
                                ? "Unknown Animal Health"
                                : animal.healthStatus}
                            </span>
                          </div>
                        </div>
                        <div class="c1">
                          <p class="title is-6">
                            {animal.age} years old | {animal.sex} |{" "}
                            {animal.breed}
                          </p>
                          {/* <p class="title is-6">horse years old | horse | horse</p> */}
                          <p></p>
                        </div>
                      </div>
                      <div class="content">
                        <div class="columns">
                          <div class="column">
                            <div class="card has-text-centered has-background-grey-lighter">
                              <header class="card-header has-background-white has-text-centered">
                                <p class="card-header-title">Diet</p>
                              </header>
                              <div class="card-content">
                                {/* <div class="content">{animal.diet}</div> */}
                                <div class="content">{animal.specialDiet}</div>
                              </div>
                            </div>
                          </div>
                          <div class="column">
                            <div class="card has-text-centered has-background-grey-lighter">
                              <header class="card-header has-text-centered has-background-white">
                                <p class="card-header-title">Instructions</p>
                              </header>
                              <div class="card-content">
                                {/* <div class="content">{animal.instructions}</div> */}
                                <div class="content">
                                  {animal.specialInstruction}
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <footer class="card-footer">
                        <div class="columns is-1">
                          <div class="column has-text-centered">
                            <button class="button is-link has-background-black-ter">
                              <Link
                                class="button is-link has-background-black-ter"
                                to={
                                  "/animalProfile?animalID=" + animal.animalID +
                                  "&name=" + animal.aName + "&age=" + animal.age + 
                                  "&breed=" + animal.breed +
                                  "&sex="  + animal.sex + 
                                  "&specialDiet="+animal.specialDiet+ 
                                  "&specialInstruction="+animal.specialInstruction + 
                                  "&healthStatus=" + animal.healthStatus
                                } 
                              >
                                View Profile
                              </Link>
                            </button>
                          </div>
                          <div class="column has-text-centered">
                            {animal.borrowStatus || animal.healthStatus != "Healthy" ? (
                              ""
                            ) : (
                              <button class="button is-link has-background-black-ter">
                                <Link
                                  class="button is-danger has-background-black-ter"
                                  to={
                                    "/borrowAnimal?animalID=" + animal.animalID
                                  }
                                >
                                  Request Animal
                                </Link>
                              </button>
                            )}
                            {/* <Link class = "button is-link has-background-black-ter" to={"/borrowAnimal?animalID="+animal.animalID}>Request Animal</Link> */}
                          </div>
                        </div>
                      </footer>
                    </div>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    );
  }
}
