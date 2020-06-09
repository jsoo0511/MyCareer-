import React, { useState } from "react";
import { Checkbox, Row, Col, Button } from "antd";
import "./Template1.scss";
import { Upload, Modal, Rate } from "antd";

import { Form, Input, DatePicker } from "antd";
import TextArea from "antd/lib/input/TextArea";

const Template1 = () => {
  const onFinish = (values) => {
    console.log("Success:", values);
  };

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  return (
    <div className="template1">
      <Form name="basic" onFinish={onFinish} onFinishFailed={onFinishFailed}>
        <Row>
          <Col span={16}>
            <Form.Item name="photo">
              <Upload
                action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                listType="picture-card"
                fileList={fileList}
                onPreview={this.handlePreview}
                onChange={this.handleChange}
              >
                {fileList.length >= 3 ? null : uploadButton}
              </Upload>
            </Form.Item>

            <Form.Item label="프로젝트명">
              <Input />
            </Form.Item>
            <Form.Item label="프로젝트 기간">
              <DatePicker /> ~ <DatePicker />
            </Form.Item>
            <Form.Item label="프로젝트 인원">
              <Input />
            </Form.Item>
            <Form.Item label="맡은 역할">
              <Input />
            </Form.Item>
            <Form.Item label="사용한 기술">
              <Input />
            </Form.Item>
            <Form.Item label="프로젝트 설명">
              <TextArea />
            </Form.Item>
          </Col>

          <Col span={8}>
            <Form.Item>
              <Button type="primary" htmlType="submit">
                저장하기
              </Button>
            </Form.Item>
          </Col>
        </Row>
      </Form>
    </div>
  );
};

export default Template1;
