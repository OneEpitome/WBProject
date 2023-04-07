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

export default function ListItem({ onClickListButton, seatData }) {
  return (
    <Container>
      {
        seatData.map((data) => (
          <Button key={data.id} onClick={onClickListButton}>{data.sector}</Button>
        ))
      }
    </Container>
  );
}

