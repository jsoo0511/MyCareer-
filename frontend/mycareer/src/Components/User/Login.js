import React from "react";
import { Modal, Button } from "antd";
import "antd/dist/antd.css";
import { Form, Input, Checkbox, Tabs } from "antd";
import { InputNumber } from "antd";

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

  signInFinish = (values) => {
    console.log("Success:", values);
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
        ✨ 로그인 & 회원가입 ✨
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
                  label="아이디"
                  name="username"
                  rules={[
                    {
                      required: true,
                      message: "Please input your username!",
                    },
                  ]}
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

                <Form.Item
                  {...tailLayout}
                  name="remember"
                  valuePropName="checked"
                >
                  <Checkbox>로그인 유지하기</Checkbox>
                </Form.Item>

                <Form.Item {...tailLayout}>
                  <Button type="primary" htmlType="submit">
                    로그인 하기
                  </Button>
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
