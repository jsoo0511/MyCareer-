import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Template1 from "./Components/Template1/Template1";
import Login from "./Components/User/Login";
<<<<<<< HEAD
import ProjectList from "./Components/Project/ProjectList";
<<<<<<< HEAD
=======
import Main from "./Components/Main/Main";
import UserInfo from "./Components/User/UserInfo";
>>>>>>> front/main

=======
import TemplateList from "./Components/templateList/TemplateList";
import PersonalInfo from "./Components/personalInfo/PersonalInfo";
import Portfolio from "./Components/portfolio/Portfolio";
>>>>>>> 11d30ed61bf7cf5bce661fba1c5b6b856706e7b6
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Switch>
          <Route path="/Template1" exact component={Template1} />
          <Route path="/Login" exact component={Login} />
<<<<<<< HEAD
          <Route path="/ProjectList" exact component={ProjectList}/>
<<<<<<< HEAD
=======
          <Route path="/main" exact component={Main} />
          <Route path="/userInfo" exact component={UserInfo} />
>>>>>>> front/main
=======
          <Route path="/tp-list" component={TemplateList} />
          <Route path="/personal" component={PersonalInfo} />
          <Route path="/portfolio" component={Portfolio} /> {/* 뒤에 user id를 붙이는 형식으로 수정 */}
>>>>>>> 11d30ed61bf7cf5bce661fba1c5b6b856706e7b6
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
