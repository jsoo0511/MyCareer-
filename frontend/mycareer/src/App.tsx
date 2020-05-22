import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Template1 from "./Components/Template1/Template1";
import Login from "./Components/User/Login";
import Main from "./Components/Main/Main";
import UserInfo from "./Components/User/UserInfo";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Switch>
          <Route path="/Template1" exact component={Template1} />
          <Route path="/Login" exact component={Login} />
          <Route path="/main" exact component={Main} />
          <Route path="/userInfo" exact component={UserInfo} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
