import React from "react";
import { DatePicker } from "antd";

import "./Template3.scss";
import cancel from "../../img/cancel.png";
import cancelW from "../../img/close.png";

const { RangePicker } = DatePicker;

class Template3 extends React.Component {
    state = {
        value: "",
        newtool: "",
        tools: Array<String>(),
        base64: [],
        images: Array<File>(),
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

    toolDelete = (e: any) => {
        console.log(e.target.className);
        var num = e.target.className;
        num = num * 1;
        var Ftools = this.state.tools.slice(0, num);
        // console.log(num+1, this.state.tools.length)
        var Btools = this.state.tools.slice(num+1, this.state.tools.length);
        // console.log(Btools)
        this.setState({
            tools: Ftools.concat(Btools)
        })
    };

    inputImage = (e: React.ChangeEvent<HTMLInputElement>) => {
        console.log(e.target.className);
        if (e.target.className === "image") {
            console.log(e.target.files?.length);
            let imageNum = e.target.files?.length;
            if (e.target.files !== null && imageNum !== undefined) {
                if (imageNum + this.state.images.length > 5) {
                    alert("이미지는 최대 5개까지 추가할 수 있습니다.");
                } else {
                    var newImages = this.state.images;
                    for (var i = 0; i < imageNum; i++) {
                        let file = e.target.files[i];
                        newImages = newImages.concat(file);
                    }
                    for (var j = this.state.images.length; j < newImages.length; j++) {
                        this.changeImage(newImages[j]);
                        // console.log("input");
                    }
                    this.setState({
                        images: newImages,
                    });
                }
            }
        }
    };

    changeImage = (image: any) => {
        let reader = new FileReader();
        console.log("READ IMAGE");

        if (image) {
            reader.readAsDataURL(image);
        }
        reader.onloadend = (e) => {
            const base64 = reader.result;
            if (base64) {
                this.setState({
                    base64: [...this.state.base64, base64.toString()],
                });
            }
        };
    };

    imageDelete = (num: any) => {
        var Fimages = this.state.images.slice(0, num);
        var Bimages = this.state.images.slice(num + 1, this.state.images.length);
        var Fbase = this.state.base64.slice(0, num);
        var Bbase = this.state.base64.slice(num + 1, this.state.base64.length);

        this.setState({
            images: Fimages.concat(Bimages),
            base64: Fbase.concat(Bbase),
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
                        value={this.state.newtool ? this.state.newtool : ""}
                    ></input>
                    <button className="add-btn" onClick={this.addTool}>
                        +
                    </button>
                    {this.state.tools ? (
                        <>
                            {this.state.tools.map((tool, idx) => {
                                return (
                                    <>
                                        <div className="tool">
                                            {tool}
                                            <div className="tool-delete">
                                                <img
                                                    src={cancelW}
                                                    alt="delete"
                                                    className={idx+""}
                                                    onClick={(e) => this.toolDelete(e)}
                                                />
                                            </div>
                                        </div>
                                    </>
                                );
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
                    <div className="file-upload">
                        <label htmlFor="upload">파일 업로드</label>
                        <input
                            className="image"
                            type="file"
                            id="upload"
                            onChange={(e) => this.inputImage(e)}
                            multiple
                        ></input>
                    </div>
                    {this.state.images.length === 0 ? <><div className="preview"></div></> : <></>}
                    <div>
                        {this.state.base64[0] && (
                            <div className="preview">
                                <img
                                    className="image-delete"
                                    alt="delete"
                                    src={cancel}
                                    onClick={(e) => this.imageDelete(0)}
                                />
                                <img
                                    className="image-preview"
                                    alt="preview 0"
                                    src={this.state.base64[0]}
                                />
                            </div>
                        )}
                        {this.state.base64[1] && (
                            <div className="preview">
                                <img
                                    className="image-delete"
                                    alt="delete"
                                    src={cancel}
                                    onClick={(e) => this.imageDelete(1)}
                                />
                                <img
                                    className="image-preview"
                                    alt="preview 0"
                                    src={this.state.base64[1]}
                                />
                            </div>
                        )}
                        {this.state.base64[2] && (
                            <div className="preview">
                                <img
                                    className="image-delete"
                                    alt="delete"
                                    src={cancel}
                                    onClick={(e) => this.imageDelete(2)}
                                />
                                <img
                                    className="image-preview"
                                    alt="preview 0"
                                    src={this.state.base64[2]}
                                />
                            </div>
                        )}
                        {this.state.base64[3] && (
                            <div className="preview">
                                <img
                                    className="image-delete"
                                    alt="delete"
                                    src={cancel}
                                    onClick={(e) => this.imageDelete(3)}
                                />
                                <img
                                    className="image-preview"
                                    alt="preview 0"
                                    src={this.state.base64[3]}
                                />
                            </div>
                        )}
                        {this.state.base64[4] && (
                            <div className="preview">
                                <img
                                    className="image-delete"
                                    alt="delete"
                                    src={cancel}
                                    onClick={(e) => this.imageDelete(4)}
                                />
                                <img
                                    className="image-preview"
                                    alt="preview 0"
                                    src={this.state.base64[4]}
                                />
                            </div>
                        )}
                    </div>
                </div>
            </div>
        );
    }
}

export default Template3;
