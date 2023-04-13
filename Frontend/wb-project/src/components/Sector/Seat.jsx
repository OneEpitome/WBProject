import React, { useState } from 'react';
import styled from 'styled-components';
import SeatModal from './SeatModal';

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

export default function Seat({ children, top, left, backgroundColor, src, alt, title, content }) {
  const [isHovering, setIsHovering] = useState(false);

  const [visible, setVisible] = useState(false);
  const handleClickCancelButton = () => {
    console.log('clicked!!');
    setVisible(false);
  };

  return (
    <>
      <SeatSquare
        style={{
          top,
          left,
          backgroundColor,
        }}
        onMouseOver={(e) => {
          setIsHovering(true);
        }}
        onMouseOut={(e) => {
          setIsHovering(false);
        }}

        onClick={(e) => {
          // console.log(e.target.textContent);
          setVisible(true);
        }}
      >
        <SeatData style={isHovering ? { display: 'block' } : { display: 'none' }}>
          {children}
        </SeatData>
      </SeatSquare>
      {
        src &&
        <SeatModal
          visible={visible}
          onClickCancelButton={handleClickCancelButton}
          src={src}
          alt={alt}
          title={title}
          content={content}
        />
      }
    </>
  );
}