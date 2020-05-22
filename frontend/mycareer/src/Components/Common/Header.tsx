import React, { Component } from "react";
import "./Header.scss";

class Header extends Component {
  render() {
    return (
      <div id="header-container">
        <div id="header-title">마커</div>
        <div id="header-login">로그인</div>
      </div>
    );
  }
}

export default Header;
