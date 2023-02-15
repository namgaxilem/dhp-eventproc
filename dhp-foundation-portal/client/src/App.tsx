import { useAuth } from "context/auth";
import DeployFlow from "pages/DeployFlow/DeployFlow";
import Initializer from "pages/Initializer/Initializer";
import RouteWithSidenav from "pages/RouteWithSidenav/RouteWithSidenav";
import React from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import "./App.less";

const NotFoundRedirect = () => <Navigate to="/" />;

export interface CustomRoute {
  path?: string;
  children?: React.ReactNode;
  exact?: boolean;
  element?: JSX.Element;
  breadcrumbName?: string;
}

const privateRoutes: CustomRoute[] = [
  { path: "/catalog/deploy-flow", exact: true, element: <DeployFlow /> },
  { path: "/environments/initializer", exact: true, element: <Initializer /> },
  { path: "/*", element: <RouteWithSidenav /> },
];

const App = () => {
  useAuth();

  return (
    <BrowserRouter>
      <Routes>
        {[...privateRoutes, { component: NotFoundRedirect }].map(
          (route, index) => (
            <Route key={index} {...route} />
          )
        )}
      </Routes>
    </BrowserRouter>
  );
};

export default App;
