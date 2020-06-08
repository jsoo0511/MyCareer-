import React from "react";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";

import "./Portfolio.scss";
import profile from "../../img/programmer.png";

class Portfolio extends React.Component {
    state = {
        pdf: ""
    };

    copyURL = () => {
        console.log(window.location.href);
    };

    exportPDF = () => {
        document.documentElement.scrollTop = 0;
        const input = document.getElementById("portfolio");
        if(input){
            html2canvas(input)
            .then((canvas) => {
                const imgData = canvas.toDataURL('image/png');
                this.setState({
                    pdf: imgData
                })
                var imgW = 210 ;
                var pageH = imgW * 1.414;
                var imgH = canvas.height * imgW / canvas.width;
                var heightL = imgH;
                
                var pdf = new jsPDF();
                var position = 0;
                // var width = pdf.internal.pageSize.getWidth();
                // var height = pdf.internal.pageSize.getHeight();
                pdf.addImage(imgData, 'PNG', 0, position, imgW, imgH);
                heightL -= pageH;
                while(heightL >= 20) {
                    position = heightL - imgH;
                    pdf.addPage();
                    pdf.addImage(imgData, 'PNG', 0, position, imgW, imgH);
                    heightL -= pageH;
                }
                
                pdf.save("portfolio.pdf");
            })
        }
    }

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
                        <button onClick={this.exportPDF}>PDF로 내보내기</button>
                    </div>
                </div>
                <div id="portfolio">
                <div className="personal">
                    <h1 className="userpf">누구의 포트폴리오</h1>
                    <div className="info">
                        personal info
                    </div>
                    <div className="profile">
                        <img className="profile-img" src={profile}></img>
                    </div>
                </div>
                <div className="pf">project</div>
                <div className="pf">project</div>
                <div className="pf">project</div>
                <div className="pf">project</div>
                </div>
            </div>
        );
    }
}

export default Portfolio;
