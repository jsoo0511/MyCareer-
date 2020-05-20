import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Template1 from "./Components/Template1/Template1";
import Login from "./Components/User/Login";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Switch>
          <Route path="/Template1" exact component={Template1} />
          <Route path="/Login" exact component={Login} />
        </Switch>
      </BrowserRouter>
    </div>

  );
}

export default App;
