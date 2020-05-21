import React from "react";
import Board from "@lourenci/react-kanban";
import "./ProjectList.scss";

const board = {
  columns: [
    {
      id: 1,
      title: "My Career",
      cards: [
        {
          id: 1,
          title: "ㅎㅎㅎ",
          description: "빅데이터 기반 장소 추천 서비스"
        },
        {
          id: 2,
          title: "Card title 2",
          description: "Card content"
        },
        {
          id: 3,
          title: "Card title 3",
          description: "Card content"
        }
      ]
    }
  ]
};

function UncontrolledBoard() {
  return (
    <Board
      allowRemoveLane
      allowRenameColumn
      allowRemoveCard
      onLaneRemove={console.log}
      onCardRemove={console.log}
      onLaneRename={console.log}
      initialBoard={board}
      allowAddCard={{ on: "top" }}
      onNewCardConfirm={draftCard => ({
        id: new Date().getTime(),
        ...draftCard
      })}
      onCardNew={console.log}
    />
  );
}

function ProjectList() {
  return (
    <>
      <UncontrolledBoard />
    </>
  );
}

export default ProjectList;