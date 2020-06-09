import React from "react";

import "./PersonalInfo.scss";
import cancelW from "../../img/close.png";

class PersonalInfo extends React.Component {
    state = {
        profile: "",
        base64: "",
        tools: Array<String>(),
        newtool: "",
    };

    fileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        // // console.log(e.target.files);
        const reader = new FileReader();

        reader.onloadend = () => {
            const base64 = reader.result;
            if (base64) {
                this.setState({
                    base64: base64.toString(),
                });
            }
        };
        if (e.target.files) {
            reader.readAsDataURL(e.target.files[0]);

            this.setState({
                profile: e.target.files[0],
            });
        }
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
        var Btools = this.state.tools.slice(num + 1, this.state.tools.length);
        // console.log(Btools)
        this.setState({
            tools: Ftools.concat(Btools),
        });
    };

    render() {
        return (
            <div className="personalInfo">
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
                </div>
                <div className="info">
                    <h2 className="info-title">인적 사항 수정</h2>
                    <br />
                    <div className="info-left">
                        <input className="text" type="text" placeholder="git hub 주소" />
                        <br />
                        <input
                            className="add-tool"
                            onChange={this.inputTool}
                            placeholder="보유 기술"
                            type="text"
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
                                                        className={idx + ""}
                                                        onClick={(e) => this.toolDelete(e)}
                                                    />
                                                </div>
                                            </div>
                                        </>
                                    );
                                })}
                                <br/>
                            </>
                        ) : (
                            <></>
                        )}
                        <input className="text" type="text" placeholder="email" />
                        <br />
                        <input className="text" type="text" placeholder="연락처" />
                        <br />
                    </div>
                    <div className="info-right">
                        <div className="pro-img">
                            {this.state.profile === "" ? (
                                <img
                                    className="profile-img"
                                    alt="profile"
                                    src="https://image.flaticon.com/icons/png/512/2092/2092392.png"
                                />
                            ) : (
                                <img
                                    className="profile-img"
                                    alt="profile"
                                    src={this.state.base64}
                                />
                            )}
                        </div>
                        <input className="profile-file" type="file" onChange={this.fileChange} />
                    </div>
                </div>
            </div>
        );
    }
}

export default PersonalInfo;
