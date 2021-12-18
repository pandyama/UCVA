import React from "react";
import { Navigate, Route } from "react-router-dom";
import { Outlet } from "react-router";
import auth from "../Login/auth";
import Home from "./home";

function SecureRoute() {
  return auth.isAuthenticated() == 1 ? <Outlet /> : <Home />;
}

export default SecureRoute;
