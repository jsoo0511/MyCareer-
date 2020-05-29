import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Template1 from "./Components/Template1/Template1";
import Template2 from "./Components/template2/Template2";
import Login from "./Components/User/Login";
import ProjectList from "./Components/Project/ProjectList";
import Main from "./Components/Main/Main";
import UserInfo from "./Components/User/UserInfo";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Switch>
          <Route path="/Template1" exact component={Template1} />
          <Route path="/Login" exact component={Login} />
          <Route path="/ProjectList" exact component={ProjectList} />
          <Route path="/" exact component={Main} />
          <Route path="/userInfo" exact component={UserInfo} />
          <Route path="/template2" exact component={Template2} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
