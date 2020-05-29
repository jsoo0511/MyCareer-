import React from "react";
import "./TemplateList.scss";
import { Link } from "react-router-dom";

class TemplateList extends React.Component {
  render() {
    return (
      <>
        <div className="templateList">
          <div className="header">
            <h1>My Career</h1>
            <h4>import "your-new-project";</h4>
          </div>
          <div className="list">
            <Link to="/Template1">
              <div className="tp">
                <div className="tp-img">
                  <img
                    src="https://image.flaticon.com/icons/svg/888/888970.svg"
                    alt="tp"
                  />
                </div>
                <div className="tp-desc">
                  <h4>simple</h4>
                </div>
              </div>
            </Link>
            <div className="tp">
              <div className="tp-img">
                <img
                  src="https://image.flaticon.com/icons/svg/888/888970.svg"
                  alt="tp"
                />
              </div>
              <div className="tp-desc">
                <h4>템플릿2 어쩌구저쩌구</h4>
              </div>
            </div>
            <div className="tp">
              <div className="tp-img">
                <img
                  src="https://image.flaticon.com/icons/svg/888/888970.svg"
                  alt="tp"
                />
              </div>
              <div className="tp-desc">
                <h4>템플릿3 어쩌구저쩌구</h4>
              </div>
            </div>
            <div className="tp">
              <div className="tp-img">
                <img
                  src="https://image.flaticon.com/icons/svg/888/888970.svg"
                  alt="tp"
                />
              </div>
              <div className="tp-desc">
                <h4>템플릿4 어쩌구저쩌구</h4>
              </div>
            </div>
            <div className="tp">
              <div className="tp-img">
                <img
                  src="https://image.flaticon.com/icons/svg/888/888970.svg"
                  alt="tp"
                />
              </div>
              <div className="tp-desc">
                <h4>템플릿5 어쩌구저쩌구</h4>
              </div>
            </div>
            <div className="tp">
              <div className="tp-img">
                <img
                  src="https://image.flaticon.com/icons/svg/888/888970.svg"
                  alt="tp"
                />
              </div>
              <div className="tp-desc">
                <h4>템플릿6 어쩌구저쩌구</h4>
              </div>
            </div>
            <div className="tp">
              <div className="tp-img">
                <img
                  src="https://image.flaticon.com/icons/svg/888/888970.svg"
                  alt="tp"
                />
              </div>
              <div className="tp-desc">
                <h4>템플릿 어쩌구저쩌구</h4>
              </div>
            </div>
            <div className="tp">
              <div className="tp-img">
                <img
                  src="https://image.flaticon.com/icons/svg/888/888970.svg"
                  alt="tp"
                />
              </div>
              <div className="tp-desc">
                <h4>템플릿 어쩌구저쩌구</h4>
              </div>
            </div>
            <br />
            <br />
            <br />
            <br />
          </div>
        </div>
        <div className="tpList-footer">
          <div className="back">
            <button>
              <img
                className="back-icon"
                src="https://image.flaticon.com/icons/svg/2223/2223615.svg"
                alt="back-icon"
              ></img>
            </button>
          </div>
          <div className="tp-info">
            <h4>템플릿을 선택하세요</h4>
          </div>
        </div>
      </>
    );
  }
}

export default TemplateList;
