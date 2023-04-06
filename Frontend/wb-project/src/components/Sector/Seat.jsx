import React from 'react';
import styled from 'styled-components';

const SeatSquare = styled.div`
  position: absolute;
  width:10px;
  height:10px;
  background-color: black;
  z-index:1000;
  &:hover {
    cursor:pointer;
  }
`;

export default function Seat({ top, left, children }) {
  return (
    <SeatSquare
      style={{
        top,
        left
      }}
      onClick={(e) => console.log(e.target.textContent)}
      onMouseOver={(e) => console.log(e.target.textContent)}
    >{children}</SeatSquare>
  );
}