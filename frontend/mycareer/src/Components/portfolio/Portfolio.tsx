import React from "react";
import "./Portfolio.scss";

class Portfolio extends React.Component {
    copyURL = () => {
        console.log(window.location.href);
    };

    render() {
        return (
            <div className="portfolio">
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
                    <div className="copy">
                        <button onClick={this.copyURL}>주소 복사하기</button>
                    </div>
                    <div className="export">
                        <button>PDF로 내보내기</button>
                    </div>
                </div>

                <div className="personal">
                    <h1 className="userpf">누구의 포트폴리오</h1>
                    <div className="info">
                        personal info
                    </div>
                    <div className="profile">
                        <img className="profile-img" src="https://image.flaticon.com/icons/png/512/2092/2092392.png"></img>
                    </div>
                </div>

                <div className="pf">project</div>
                <div className="pf">project</div>
            </div>
        );
    }
}

export default Portfolio;
