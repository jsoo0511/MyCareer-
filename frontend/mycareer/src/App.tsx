import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Template1 from "./Components/Template1/Template1";
import TemplateList from "./Components/templateList/TemplateList";
import PersonalInfo from "./Components/personalInfo/PersonalInfo";
import Portfolio from "./Components/portfolio/Portfolio";
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Switch>
          <Route path="/Template1" exact component={Template1} />
          <Route path="/tp-list" component={TemplateList} />
          <Route path="/personal" component={PersonalInfo} />
          <Route path="/portfolio" component={Portfolio} /> {/* 뒤에 user id를 붙이는 형식으로 수정 */}
        </Switch>
      </BrowserRouter>
    </div>

  );
}

export default App;
