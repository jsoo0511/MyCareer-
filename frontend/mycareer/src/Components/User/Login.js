import React from "react";
import { Modal, Button } from "antd";
import { Form, Input, Tabs } from "antd";
import { InputNumber } from "antd";
import "antd/dist/antd.css";
import "./Login.scss";
import GitHubLogin from "./GitHubLogin";

import axios from "axios";
import { toQuery } from "./utils";

const client_id = `420f98f96d5639a39a20`;
const client_secret = `a1de767b054482f306b680628b42cafa5b7a8a88`;
const redirect_uri = `http://localhost:3000/`;

const onSuccess = async (response) => {
  const code = response.code;
  var req = new XMLHttpRequest();
  req.onreadystatechange = async function () {
    if (req.readyState == XMLHttpRequest.DONE) {
      console.log(req.responseText);
      const token = req.response.substring(17, 57);
      await axios
          .get(`https://api.github.com/user`, {
            headers: {
              Authorization: `token ${token}`,
            },
          })
          .then((res) => console.log(res));
    }
  };

  await req.open(
    "POST",
    "https://cors-anywhere.herokuapp.com/https://github.com/login/oauth/access_token",
    true
  );
  req.setRequestHeader("Accept", "application/json");
  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  req.send(
    `code=${code}&client_id=${client_id}&client_secret=${client_secret}`
  );

};
const onFailure = (response) => console.error(response);

// sign up
const validateMessages = {
  required: "${label} is required!",
  types: {
    email: "${label} is not validate email!",
    number: "${label} is not a validate number!",
  },
  number: {
    range: "${label} must be between ${min} and ${max}",
  },
};

// tabs
const { TabPane } = Tabs;

function callback(key) {
  console.log(key);
}

// sign in
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
    offset: 8,
    span: 16,
  },
};

class Login extends React.Component {
  // modal
  state = { visible: false };

  showModal = () => {
    this.setState({
      visible: true,
    });
  };

  handleOk = (e) => {
    console.log(e);
    this.setState({
      visible: false,
    });
  };

  handleCancel = (e) => {
    console.log(e);
    this.setState({
      visible: false,
    });
  };

  signInFinish = async (values) => {
    console.log("Success:", values);
    // 로그인 API 적용
    await axios
      .get(
        `http://13.124.227.192:8080/users/login?email=${values.email}&password=${values.password}`
      )
      .then((res) => console.log(res));
  };

  signInFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  signUpFinish = (values) => {
    console.log("Success:", values);
  };

  render() {
    return (
      <div>
        <Button type="primary" onClick={this.showModal}>
          로그인
        </Button>
        <Modal
          // title="Sign In ✨ Sign Up"
          visible={this.state.visible}
          // onOk={this.handleOk}
          okButtonProps={{ disabled: true }}
          onCancel={this.handleCancel}
        >
          <Tabs defaultActiveKey="1" onChange={callback}>
            <TabPane tab="로그인" key="1">
              <Form
                {...layout}
                name="basic"
                initialValues={{
                  remember: true,
                }}
                onFinish={this.signInFinish}
                onFinishFailed={this.signInFinishFailed}
              >
                <Form.Item
                  label="이메일"
                  name="email"
                  rules={[{ required: true, type: "email" }]}
                >
                  <Input />
                </Form.Item>

                <Form.Item
                  label="비밀번호"
                  name="password"
                  rules={[
                    {
                      required: true,
                      message: "Please input your password!",
                    },
                  ]}
                >
                  <Input.Password />
                </Form.Item>

                <Form.Item {...tailLayout}>
                  <Button type="primary" htmlType="submit">
                    로그인 하기
                  </Button>
                  {/* <img
                    class="signInGithub"
                    width="200px"
                    src="https://coderwall-assets-0.s3.amazonaws.com/uploads/picture/file/4363/github.png"
                    onClick={}
                  /> */}
                  <GitHubLogin
                    clientId="420f98f96d5639a39a20"
                    redirectUri="http://localhost:3000/"
                    onSuccess={onSuccess}
                    onFailure={onFailure}
                  />
                </Form.Item>
              </Form>
            </TabPane>
            <TabPane tab="회원가입" key="2">
              <Form
                {...layout}
                name="nest-messages"
                onFinish={this.signUpFinish}
                validateMessages={validateMessages}
              >
                <Form.Item
                  name={["user", "name"]}
                  label="이름"
                  rules={[{ required: true }]}
                >
                  <Input />
                </Form.Item>
                <Form.Item
                  name={["user", "email"]}
                  label="이메일"
                  rules={[{ type: "email" }]}
                >
                  <Input />
                </Form.Item>
                <Form.Item
                  name={["user", "age"]}
                  label="나이"
                  rules={[{ type: "number", min: 0, max: 99 }]}
                >
                  <InputNumber />
                </Form.Item>
                <Form.Item name={["user", "website"]} label="나의 GitHub 주소">
                  <Input />
                </Form.Item>
                <Form.Item name={["user", "introduction"]} label="내 소개">
                  <Input.TextArea />
                </Form.Item>

                <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 8 }}>
                  <Button type="primary" htmlType="submit">
                    가입하기
                  </Button>
                </Form.Item>
              </Form>
            </TabPane>
          </Tabs>
        </Modal>
      </div>
    );
  }
}

export default Login;
