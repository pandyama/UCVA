import React from "react";
import { Navigate, Route } from "react-router-dom";
import { Outlet } from "react-router";
import auth from "../Login/auth";
import Home from "./home";

function Level2And3Route() {
  return (auth.isAuthenticated() == 1 && (auth.isAuthorized() == 2 || auth.isAuthorized() == 3))
  ? <Outlet /> : <Home />;
}

export default Level2And3Route;
