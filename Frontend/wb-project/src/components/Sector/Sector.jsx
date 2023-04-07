import React from 'react';
import styled from 'styled-components';

const ImgContainer = styled.div`
  position: relative;
`;

const Img = styled.img`
  position:absolute;
  top:50px;
  left:50px;
  width: 1000px;
  height: 600px;
`;

export default function Sector({ src, children }) {
  return (
    <ImgContainer>
      <Img src={src} alt="" />
      {children}
    </ImgContainer>
  );
}