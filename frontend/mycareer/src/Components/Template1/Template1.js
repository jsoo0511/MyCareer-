import React, { useState } from "react";
import { Checkbox, Row, Col, Button } from "antd";
import "./Template1.scss";
import Avatar from "./Avatar";

import {
  Form,
  Input,
  Radio,
  Select,
  Cascader,
  DatePicker,
  InputNumber,
  TreeSelect,
  Switch,
} from "antd";
import TextArea from "antd/lib/input/TextArea";

class Template1 extends React.Component {
  render() {
    return (
      <div className="template1">
        <Row>
          <Col span={16}>
            <div>
              <Form
                labelCol={{
                  span: 4,
                }}
                wrapperCol={{
                  span: 14,
                }}
                layout="horizontal"
                initialValues={{
                  size: 'large',
                }}
              >
                <Form.Item label="대표이미지">
                  <Avatar/>
                </Form.Item>
                <Form.Item label="프로젝트명">
                  <Input />
                </Form.Item>
                <Form.Item label="프로젝트 기간">
                  <DatePicker /> ~ <DatePicker />
                </Form.Item>
                <Form.Item label="프로젝트 소개">
                  <TextArea />
                </Form.Item>
                <Form.Item label="맡은 역할">
                  <Select>
                    <Select.Option value="Frontend">Frontend</Select.Option>
                    <Select.Option value="Backend">Backend</Select.Option>
                    <Select.Option value="Fullstack">Fullstack</Select.Option>
                  </Select>
                </Form.Item>
                <Form.Item label="나의 기여도">
                  <InputNumber /> %
                </Form.Item>
                <Form.Item label="사용한 기술">
                  <Button>Javascript</Button> <Button>ReactJS</Button> <Button>Typescript</Button>
                </Form.Item>
              </Form>
              
            </div>

          </Col>
          <Col span={8}>
            <Button>저장하기</Button>
          </Col>
        </Row>
      </div>
    );
  }
}

export default Template1;
