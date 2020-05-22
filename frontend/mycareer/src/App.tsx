import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Template1 from "./Components/Template1/Template1";
import Login from "./Components/User/Login";
<<<<<<< HEAD
import ProjectList from "./Components/Project/ProjectList";
=======
import Main from "./Components/Main/Main";
import UserInfo from "./Components/User/UserInfo";
>>>>>>> front/main

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Switch>
          <Route path="/Template1" exact component={Template1} />
          <Route path="/Login" exact component={Login} />
<<<<<<< HEAD
          <Route path="/ProjectList" exact component={ProjectList}/>
=======
          <Route path="/main" exact component={Main} />
          <Route path="/userInfo" exact component={UserInfo} />
>>>>>>> front/main
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
