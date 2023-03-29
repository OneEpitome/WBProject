import React from 'react';
import styled from 'styled-components';
import ListItem from './ListItem';

const Container = styled.div`
  width: 100%;
  height: 100%;
`;

const Button = styled.button`
  width: 100%;
  height: 40px;
  padding: 5px;
  font-size: 24px;
  border: none;
  background-color: black;
  color: white;
  border-radius: 20px;
  cursor: pointer;

  &:hover {
    background-color: lightgrey;
    color: black;
  }
`

export default function SeatList({ onClickListButton, seatData }) {
  return (
    <Container>
      <ListItem onClickListButton={onClickListButton} seatData={seatData} />
      <Button>선택된 좌석 보기</Button>
    </Container>
  );
}