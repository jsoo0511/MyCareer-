import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Template1 from "./Components/Template1/Template1";
import TemplateList from "./Components/templateList/TemplateList";
import PersonalInfo from "./Components/personalInfo/PersonalInfo";
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Switch>
          <Route path="/Template1" exact component={Template1} />
          <Route path="/tp-list" component={TemplateList} />
          <Route path="/personal" component={PersonalInfo} />
        </Switch>
      </BrowserRouter>
    </div>

  );
}

export default App;
