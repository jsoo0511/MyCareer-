import React, { Component } from "react";
import "./Header.scss";
import Login from "../User/Login";
class Header extends Component {
  render() {
    return (
      <div id="header-container">
        <div id="header-title">마커</div>
        <div id="header-login"><Login /></div>
      </div>
    );
  }
}

export default Header;