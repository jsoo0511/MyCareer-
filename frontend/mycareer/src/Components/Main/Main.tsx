import React, { Component } from "react";
import Header from "../Common/Header";
import "./Main.scss";

class Main extends Component {
  render() {
    return (
      <div id="main-container">
        <Header></Header>
        <div id="main-leftblock">
          <div id="main-headtext">
            간단하고 손쉽게 만들다 <br />
            당신의 포트폴리오, 마커
          </div>

          <div id="main-start">시작하기</div>
        </div>
        <img id="main-img" src={require("../../note.png")} alt="" />
      </div>
    );
  }
}

export default Main;
