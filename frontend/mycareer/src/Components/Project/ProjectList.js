import React from "react";
import "./ProjectList.scss";
import { Row, Col } from "antd";
import { Upload, Modal, Button } from "antd";
import { PlusOutlined } from "@ant-design/icons";

function getBase64(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = (error) => reject(error);
  });
}

class ProjectList extends React.Component {
  state = {
    previewVisible: false,
    previewImage: "",
    previewTitle: "",
    fileList: [
      {
        uid: "-1",
        name: "image.png",
        status: "done",
        url: "https://www.simplesite.com/Images/FrontPage2017/Themes/4.png",
      },
      {
        uid: "-2",
        name: "image.png",
        status: "done",
        url: "https://www.thebestdesigns.com/design-images/artoftea.com-1.jpg",
      },
      {
        uid: "-3",
        name: "image.png",
        status: "done",
        url: "https://cdn.imweb.me/thumbnail/20200131/b13f5004d887a.png",
      },
      {
        uid: "-4",
        name: "image.png",
        status: "done",
        url:
          "https://www.macobserver.com/imgs/tips/apple_site_no_shopping.jpg",
      },
    ],
  };

  handleCancel = () => this.setState({ previewVisible: false });

  handlePreview = async (file) => {
    if (!file.url && !file.preview) {
      file.preview = await getBase64(file.originFileObj);
    }

    this.setState({
      previewImage: file.url || file.preview,
      previewVisible: true,
      previewTitle:
        file.name || file.url.substring(file.url.lastIndexOf("/") + 1),
    });
  };

  handleChange = ({ fileList }) => this.setState({ fileList });

  render() {
    const { previewVisible, previewImage, fileList, previewTitle } = this.state;
    const uploadButton = (
      <div>
        <PlusOutlined />
        <div className="ant-upload-text">새 프로젝트 추가</div>
      </div>
    );
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
                <Upload
                  action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                  listType="picture-card"
                  fileList={fileList}
                  onPreview={this.handlePreview}
                  onChange={this.handleChange}
                >
                  {fileList.length >= 8 ? null : uploadButton}
                </Upload>
                <Modal
                  visible={previewVisible}
                  title={previewTitle}
                  footer={null}
                  onCancel={this.handleCancel}
                >
                  <img
                    alt="example"
                    style={{ width: "100%" }}
                    src={previewImage}
                  />
                </Modal>
              </div>
            </div>
          </Col>
        </Row>
      </>
    );
  }
}

export default ProjectList;
