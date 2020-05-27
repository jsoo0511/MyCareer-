<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> branch 'backend' of https://lab.ssafy.com/s02-final/s02p31a103.git
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
<<<<<<< HEAD
=======
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
>>>>>>> ca6549c6110c323afd2e09c2a2d8c6582531f93f
=======
>>>>>>> branch 'backend' of https://lab.ssafy.com/s02-final/s02p31a103.git
