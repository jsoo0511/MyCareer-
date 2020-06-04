import React from "react";
import { DatePicker } from "antd";

import "./Template3.scss";

const { RangePicker } = DatePicker;

class Template3 extends React.Component {
    state = {
        value: "",
        newtool: "",
        tools: Array<String>(),
        // tools:["1", "2"]
    };

    inputTool = (e: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({
            newtool: e.target.value,
        });
    };

    addTool = () => {
        var newtools = this.state.tools;
        newtools.push(this.state.newtool);
        this.setState({
            newtool: "",
            tools: newtools,
        });
    };

    render() {
        const { value } = this.state;

        return (
            <div className="template3">
                <div className="header">
                    <div className="back">
                        <button>
                            <img
                                className="back-icon"
                                src="https://image.flaticon.com/icons/svg/2223/2223615.svg"
                            ></img>
                        </button>
                    </div>
                    <div className="title">
                        <h2>My Career</h2>
                    </div>
                    <div className="save">
                        <button>저장하고 나가기</button>
                    </div>
                </div>{" "}
                <br />
                <div className="template-left">
                    <input className="name" type="text" placeholder="project name" /> <br />
                    <div
                        contentEditable="true"
                        className="summary"
                        suppressContentEditableWarning={true}
                    >
                        project summary - 프로젝트를 간단히 설명하세요.
                    </div>
                    <span className="prjDate">프로젝트 기간 </span> <RangePicker className="date" />{" "}
                    <br />
                    {/* <TextArea
                        value={value}
                        onChange={this.onChange}
                        placeholder="Controlled autosize"
                        autoSize={{ minRows: 3, maxRows: 5 }}
                    /> */}
                    <span className="tools">사용한 기술 </span>
                    <input
                        className="add-tool"
                        onChange={this.inputTool}
                        value={this.state.newtool? this.state.newtool : ""}
                    ></input>
                    <button className="add-btn" onClick={this.addTool}>
                        +
                    </button>
                    {this.state.tools ? (
                        <>
                            {this.state.tools.map((tool) => {
                                return <div className="tool">{tool}</div>;
                            })}
                        </>
                    ) : (
                        <></>
                    )}
                    <div
                        contentEditable="true"
                        className="function"
                        suppressContentEditableWarning={true}
                    >
                        project function - 프로젝트 기능을 입력하세요.
                        <br />
                        1. ~~기능
                    </div>{" "}
                    <br />
                    <div
                        contentEditable="true"
                        className="myWork"
                        suppressContentEditableWarning={true}
                    >
                        맡은 부분을 설명하세요.
                    </div>{" "}
                    <br />
                    <input className="gitURL" type="text" placeholder="Git URL을 입력하세요." />
                </div>
                <div className="template-right">
                    {/* 이미지 업로드 input file */}
                    <div className="img"></div>
                    <div className="img"></div>
                </div>
            </div>
        );
    }
}

export default Template3;
