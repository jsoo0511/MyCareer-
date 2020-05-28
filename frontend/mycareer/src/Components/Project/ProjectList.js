import React from "react";
import "./ProjectList.scss";
import { Row, Col, Button } from "antd";
import Sortable from "sortablejs";

class ProjectList extends React.Component {
  componentDidMount() {
      var el = document.getElementById('items');
      var sortable = Sortable.create(el);
  }

  render() {
    return (
      <>
        <Row className="ProjectRow">
          <Col span={12} offset={6}>
            <div className="MainDiv">
              <h2>Project List</h2>
              <br />
              <Row>
                <Col span={12}>
                  <Button>인적사항 수정</Button>
                  &nbsp;&nbsp;
                  <Button>회원정보 수정</Button>
                </Col>
                <Col span={6}></Col>
                <Col span={6}>
                  <Button type="primary">완성 포트폴리오 보기</Button>
                </Col>
              </Row>
              <div className="clearfix">
                  <Button id="addButton">새 프로젝트 추가</Button>
                <ul id="items">
                  <li>
                    <img src="https://www.simplesite.com/Images/FrontPage2017/Themes/4.png" />
                  </li>
                  &nbsp;
                  <li>
                    <img src="https://www.thebestdesigns.com/design-images/artoftea.com-1.jpg" />
                  </li>
                  &nbsp;
                  <li>
                    <img src="https://cdn.imweb.me/thumbnail/20200131/b13f5004d887a.png" />
                  </li>
                </ul>
              </div>
            </div>
          </Col>
        </Row>
      </>
    );
  }
}

export default ProjectList;
