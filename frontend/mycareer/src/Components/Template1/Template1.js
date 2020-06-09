import React from "react";
import { Redirect } from "react-router-dom";
import { Form, Upload, Input, Button, Row, Col } from "antd";
import { PlusOutlined, EnvironmentTwoTone } from "@ant-design/icons";
import axios from "axios";
import "./Template1.scss";

const layout = {
  labelCol: {
    span: 8,
  },
  wrapperCol: {
    span: 16,
  },
};

const tailLayout = {
  wrapperCol: {
    offset: 20,
    span: 5,
  },
};

const { TextArea } = Input;
function getBase64(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = (error) => reject(error);
  });
}

class PicturesWall extends React.Component {
  state = {
    previewVisible: false,
    previewImage: "",
    previewTitle: "",
    fileList: [],
    isWrite: false,
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
    if (this.state.isWrite) {
      return <Redirect to="/ProjectList" />;
    }
    const { previewVisible, previewImage, fileList, previewTitle } = this.state;
    const uploadButton = (
      <div>
        <PlusOutlined />
        <div className="ant-upload-text">Upload</div>
      </div>
    );

    const onFinish = (values) => {
      console.log(values);
      let form_data = new FormData();
      form_data.set("endDate", values.endDate);
      form_data.set("members", values.members);
      form_data.set("projectInfo", values.projectInfo);
      form_data.set("projectTitle", values.projectTitle);
      form_data.set("role", values.role);
      form_data.set("startDate", values.startDate);
      form_data.set("techs", values.techs);
      form_data.set("templateNo", 1);
      form_data.set("url", values.url);
      form_data.set("userNo", 1);

      for (let index = 0; index < fileList.length; index++) {
        if (index === 0) {
          form_data.append("files", this.state.fileList[0].originFileObj);
        } else {
          form_data.append(
            `photo${index + 1}`,
            this.state.fileList[index].originFileObj
          );
        }
      }
      axios
        .post(`http://13.124.227.192:8080/project/1`, form_data, {
          headers: {
            "content-type": "multipart/form-data",
          },
        })
        .then((response) => {
          console.log(response);
          this.setState({ isWrite: true });
        })
        .catch((err) => console.timeLog(err));
    };
    return (
      <div className="template1">
        <Row>
          <Col span={16}>
            <Form {...layout} name="validate_other" onFinish={onFinish}>
              <div className="fileSpace">
                <Form.Item name="files">
                  <Upload
                    action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                    listType="picture-card"
                    fileList={fileList}
                    onPreview={this.handlePreview}
                    onChange={this.handleChange}
                  >
                    {fileList.length >= 1 ? null : uploadButton}
                  </Upload>
                </Form.Item>
              </div>
              <div className="inputs">
                <Form.Item label="프로젝트명" name="projectTitle">
                  <Input />
                </Form.Item>
                <Form.Item label="프로젝트 시작" name="startDate">
                  <Input />
                </Form.Item>
                <Form.Item label="프로젝트 종료" name="endDate">
                  <Input />
                </Form.Item>
                <Form.Item label="프로젝트 인원" name="members">
                  <Input />
                </Form.Item>
                <Form.Item label="맡은 역할" name="role">
                  <Input />
                </Form.Item>
                <Form.Item label="사용한 기술" name="techs">
                  <Input />
                </Form.Item>
                <Form.Item label="프로젝트 설명" name="projectInfo">
                  <TextArea />
                </Form.Item>
                <Form.Item label="URL" name="url">
                  <Input />
                </Form.Item>
                <Form.Item {...tailLayout}>
                  <Button htmlType="submit">작성완료</Button>
                </Form.Item>
              </div>
            </Form>
          </Col>
        </Row>
      </div>
    );
  }
}

export default PicturesWall;
