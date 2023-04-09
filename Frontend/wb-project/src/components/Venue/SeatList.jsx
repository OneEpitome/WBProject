import React, { useState } from 'react';
import styled from 'styled-components';
import ListItem from './ListItem';
import { Link } from 'react-router-dom';

const Container = styled.div`
  width: 100%;
  height: 100%;
`;

const Button = styled(Link)`
  display: block;
  height: 40px;
  padding: 6px 0 0 0;
  font-size: 24px;
  border: none;
  background-color: black;
  color: white;
  border-radius: 20px;
  cursor: pointer;

  text-decoration: none;

  &:hover {
    background-color: lightgrey;
    color: black;
  }
`

export default function SeatList({ onClickListButton, seatData }) {
  const [selectedSector, setSelectedSector] = useState('');

  const getDetailData = (sector) => {
    setSelectedSector(sector);
  };

  return (
    <Container>
      <ListItem
        onClickListButton={onClickListButton}
        onGetDetailData={getDetailData}
        seatData={seatData}
      />
      <Button
        to={`/daejeon-worldcup/${selectedSector}`}
      >선택된 좌석 보기</Button>
    </Container>
  );
}