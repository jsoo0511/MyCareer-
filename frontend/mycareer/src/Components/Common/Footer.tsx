import React, { Component } from "react";
import "./Footer.scss";

class Footer extends Component {
  render() {
    return (
      <div className="footer-container">
        <div className="footer-text">
          (주) My Career / 마커 <br />
          인천광역시 계양구 둥그재산길 27 <br /> <br />
          askanything@mycareer.com / 010-1234-1234
        </div>
      </div>
    );
  }
}

export default Footer;
