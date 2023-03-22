import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
  height: 400px;
  overflow-y: scroll;
  border: solid 3px black;
  border-left: none;
  border-bottom: none;
`

const Button = styled.button`
  width: 100%;
  font-size: 20px;
  padding: 5px 40px;
  border: none;
  background-color: transparent;
  cursor: pointer;

  &:hover {
    background-color: lightgrey;
  }
`;

export default function ListItem({ onClickListButton }) {
  return (
    <Container>
      <Button onClick={onClickListButton}>1루 내야 지정석 1층</Button>
      <Button onClick={onClickListButton}>3루 내야 지정석 1층</Button>
      <Button onClick={onClickListButton}>중앙 가족석 5인</Button>
      <Button onClick={onClickListButton}>1루 내야 지정석 1층</Button>
      <Button onClick={onClickListButton}>3루 내야 지정석 1층</Button>
      <Button onClick={onClickListButton}>중앙 가족석 5인</Button>
      <Button onClick={onClickListButton}>1루 내야 지정석 1층</Button>
      <Button onClick={onClickListButton}>3루 내야 지정석 1층</Button>
      <Button onClick={onClickListButton}>중앙 가족석 5인</Button>
      <Button onClick={onClickListButton}>1루 내야 지정석 1층</Button>
      <Button onClick={onClickListButton}>3루 내야 지정석 1층</Button>
      <Button onClick={onClickListButton}>중앙 가족석 5인</Button>
      <Button onClick={onClickListButton}>1루 내야 지정석 1층</Button>
      <Button onClick={onClickListButton}>3루 내야 지정석 1층</Button>
      <Button onClick={onClickListButton}>중앙 가족석 5인</Button>
    </Container>
  );
}

