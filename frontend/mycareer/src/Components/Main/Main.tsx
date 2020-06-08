import React, { Component } from "react";
import Header from "../Common/Header";
import Footer from "../Common/Footer";
import "./Main.scss";
import { Link } from "react-router-dom";

class Main extends Component {
  render() {
    return (
      <div className="main-container">
        <Header />
        <div className="main-first-row">
          <div className="main-first-row-left">
            <img src={require("../../note.png")} alt="" />
          </div>
          <div className="main-first-row-right">
            <div className="main-first-row-right-text">
              간단하고 손쉽게 만들다 <br />
              당신의 포트폴리오, My Career
              <div className="main-first-row-right-sub-text">
                빠르고 손쉽게 디자인된 포트폴리오를 완성시켜 보세요.
              </div>
            </div>
            <Link to="/ProjectList">
              <div id="main-start">시작하기</div>
            </Link>
          </div>
        </div>
        <div className="main-rows">
          <div className="main-rows-inner">
            <div className="main-rows-texts">
              <div className="main-rows-main-text">
                편안하게 <br />
                글자만 입력하세요
              </div>
              <div className="main-rows-sub-text">
                디자인은 마커가 해드립니다 !
              </div>
              <Link to="/ProjectList">
                <div className="main-rows-start">시작하기</div>
              </Link>
            </div>

            <div className="main-rows-img">
              <img src={require("../../img/main1.PNG")} alt="" />
            </div>
          </div>
        </div>
        <div className="main-rows">
          <div className="main-rows-inner">
            <div className="main-rows-img">
              <img
                className="main-rows-img-second"
                src={require("../../img/main2.png")}
                alt=""
              />
            </div>
            <div className="main-rows-texts">
              <div className="main-rows-main-text">PDF파일로 준비하세요</div>
              <div className="main-rows-sub-text">
                클릭 한 번으로 편안하게 !
              </div>
              <Link to="/ProjectList">
                <div className="main-rows-start">시작하기</div>
              </Link>
            </div>
          </div>
        </div>
        <div className="main-rows">
          <div className="main-rows-inner">
            <div className="main-rows-texts">
              <div className="main-rows-main-text">설치가 필요없습니다</div>
              <div className="main-rows-sub-text">
                간편하게 바로 시작하세요 !
              </div>
              <Link to="/ProjectList">
                <div className="main-rows-start">시작하기</div>
              </Link>
            </div>
            <div className="main-rows-img">
              <img
                className="main-rows-img-third"
                src={require("../../img/main3.png")}
                alt=""
              />
            </div>
          </div>
        </div>

        <Footer />
      </div>
    );
  }
}

export default Main;
