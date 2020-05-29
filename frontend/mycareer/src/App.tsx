import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Template1 from "./Components/Template1/Template1";
import Login from "./Components/User/Login";
import ProjectList from "./Components/Project/ProjectList";
import Main from "./Components/Main/Main";
import UserInfo from "./Components/User/UserInfo";
import TemplateList from "./Components/templateList/TemplateList";
import PersonalInfo from "./Components/personalInfo/PersonalInfo";
import Portfolio from "./Components/portfolio/Portfolio";
import Tem3 from "./Components/template3/Template3";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Switch>
          <Route path="/Template1" exact component={Template1} />
          <Route path="/Login" exact component={Login} />
          <Route path="/ProjectList" exact component={ProjectList}/>
          <Route path="/" exact component={Main} />
          <Route path="/userInfo" exact component={UserInfo} />
          <Route path="/tp-list" component={TemplateList} />
          <Route path="/personal" component={PersonalInfo} />
          <Route path="/portfolio" component={Portfolio} /> {/* 뒤에 user id를 붙이는 형식으로 수정 */}
          <Route path="/t3" component={Tem3} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;