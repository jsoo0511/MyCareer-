import React, { Component } from "react";
import "./UserInfo.scss";

class UserInfo extends Component {
  render() {
    return (
      <div id="userinfo-container">
        <div id="userinfo-box">
          <p id="userinfo-title">회원정보수정</p>
          <table>
            <tbody>
              <tr>
                <th>아이디</th>
                <td>
                  <input type="text" disabled />
                </td>
              </tr>

              <tr>
                <th>이메일</th>
                <td>
                  <input type="text" />
                </td>
              </tr>

              <tr>
                <th>비밀번호</th>
                <td>
                  <input type="text" />
                </td>
              </tr>

              <tr>
                <th>이름</th>
                <td>
                  <input type="text" />
                </td>
              </tr>

              <tr>
                <th>성별</th>
                <td>
                  <input type="radio" id="male" name="gender" value="male" />
                  <label> 남자</label>
                  <input
                    type="radio"
                    id="female"
                    name="gender"
                    value="female"
                  />
                  <label> 여자</label>
                </td>
              </tr>

              <tr>
                <th>직업</th>
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
            </tbody>
          </table>

          <input id="submit-btn" type="submit" value="수정" />
        </div>
      </div>
    );
  }
}

export default UserInfo;
