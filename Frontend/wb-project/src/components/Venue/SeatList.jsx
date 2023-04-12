import React from 'react';
import styled from 'styled-components';
import ListItem from './ListItem';

const Container = styled.div`
  width: 100%;
  height: 100%;
`;

export default function SeatList({ onClickListButton, seatData }) {
  return (
    <Container>
      <ListItem
        onClickListButton={onClickListButton}
        seatData={seatData}
      />
    </Container>
  );
}