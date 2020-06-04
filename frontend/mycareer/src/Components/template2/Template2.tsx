import React, { Component } from "react";
import Header from "../Common/Header";
import { Input, DatePicker } from "antd";
import "./Template2.scss";

const { RangePicker } = DatePicker;

class Template2 extends Component {
  state = {
    imgBase64: "",
    imgFile: null,
  };

  handleChangeFile = (event: any) => {
    let reader = new FileReader();

    reader.onloadend = () => {
      const base64 = reader.result;

      if (base64) {
        this.setState({ imgBase64: base64.toString() });
      }
    };

    if (event.target.files[0]) {
      reader.readAsDataURL(event.target.files[0]);
      this.setState({ imgFile: event.target.files[0] });
    }
  };

  handleRemove = () => {
    this.setState({
      imgBase64: "",
      imgFile: null,
    });
  };

  render() {
    return (
      <div id="container">
        <Header></Header>
        <div id="template2">
          <div id="user-profile">
            <div id="user-pic">
              {this.state.imgBase64 ? (
                <img
                  src={this.state.imgBase64}
                  onClick={this.handleRemove}
                ></img>
              ) : (
                <div></div>
              )}
            </div>

            <input
              type="file"
              id="imgFile"
              name="imgFile"
              accept="image/*"
              onChange={this.handleChangeFile}
            ></input>

            <table className="profile-table" id="table1">
              <tbody>
                <tr>
                  <th className="table-title">PERSONAL</th>
                  <td></td>
                </tr>
                <tr>
                  <th>이름</th>
                  <td>
                    <input type="text" />
                  </td>
                </tr>

                <tr>
                  <th>생년월일</th>
                  <td>
                    <input type="text" />
                  </td>
                </tr>

                <tr>
                  <th>이메일</th>
                  <td>
                    <input type="text" />
                  </td>
                </tr>

                <tr>
                  <th>전화번호</th>
                  <td>
                    <input type="text" />
                  </td>
                </tr>

                <tr>
                  <th>GitHub</th>
                  <td>
                    <input type="text" />
                  </td>
                </tr>
              </tbody>
            </table>

            <table className="profile-table" id="table2">
              <tbody>
                <tr>
                  <th className="table-title">SKILLS</th>
                  <td></td>
                </tr>
              </tbody>
            </table>
          </div>
          <div id="portfolio">
            <div id="portfolio-title">
              <input type="text" placeholder="Project Name ..." />
            </div>

            <div id="portfolio-summary">
              <div className="tab-title">프로젝트 개요</div>
              <textarea id="summary-text"> </textarea>
            </div>

            <div id="portfolio-dates">
              <div className="tab-title">프로젝트 기간</div>
              <RangePicker id="dates"></RangePicker>
            </div>

            <div id="portfolio-functions">
              <div className="tab-title">주요 기능</div>
              <textarea className="textarea"> </textarea>
            </div>

            <div id="portfolio-role">
              <div className="tab-title">맡은 역할</div>
              <textarea className="textarea"> </textarea>
            </div>

            <div id="portfolio-results">
              <div className="tab-title">결과물</div>
              <textarea className="textarea"> </textarea>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Template2;
