import React from "react";
import "./PersonalInfo.scss";

class PersonalInfo extends React.Component {
    state = {
        profile: '',
        base64: ''
    }
    fileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        // // console.log(e.target.files);
        const reader = new FileReader()
    
        reader.onloadend = () => {
          const base64 = reader.result
          if (base64) {
            this.setState({
              base64: base64.toString()
            })
          }
        }
        if (e.target.files) {
          reader.readAsDataURL(e.target.files[0])
    
          this.setState({
            profile: e.target.files[0]
          })
        }
      }
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
                    <h2 className="info-title">인적 사항 수정</h2><br/>
                    <div className="info-left">
                        <input className="text" type="text" placeholder="git hub 주소" />
                        <input className="check" type="checkbox" />
                        <br />
                        <input className="text" type="text" placeholder="보유 기술" />
                        <input className="check" type="checkbox" />
                        <br />
                        <input className="text" type="text" placeholder="email" />
                        <input className="check" type="checkbox" />
                        <br />
                        <input className="text" type="text" placeholder="연락처" />
                        <input className="check" type="checkbox" />
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
                        <input className="check" type="checkbox" />
                        </div>
                </div>
            </div>
        );
    }
}

export default PersonalInfo;
