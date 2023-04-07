import React, { useState } from 'react';
import styled from 'styled-components';

const SeatSquare = styled.div`
  position: absolute;
  width:10px;
  height:10px;
  background-color: black;
  &:hover {
    cursor:pointer;
  }
`;

const SeatData = styled.div`
  width:95px;
  font-weight: bold;
  border-radius: 20px;
  background-color: lightgray;
  display: none;
`;

export default function Seat({ top, left, children, }) {
  const [isHovering, setIsHovering] = useState(false);

  return (
    <SeatSquare
      style={{
        top,
        left
      }}
      onMouseOver={(e) => {
        setIsHovering(true);
      }}
      onMouseOut={(e) => {
        setIsHovering(false);
      }}

      onClick={(e) => console.log(e.target.textContent)}
    ><SeatData style={isHovering ? { display: 'block' } : { display: 'none' }}>{children}</SeatData></SeatSquare>
  );
}