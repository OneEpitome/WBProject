import React from 'react';
import styled from 'styled-components';

const List = styled.li`
  position: relative;
  margin: 0 auto;
  width: 150px;
  height: 220px;
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
`;

const ImageBox = styled.div`
  text-align: center;
  width: 100%;
  height: 100%;
  border-bottom: 1px solid lightgray;
`

const Image = styled.img`
  width: 70%;
  height: 150px;
`;

const NotReady = styled.div`
  position: absolute;
  text-align: center;
  width: 100%;
  height: 100%;
  background-color:rgba( 255, 255, 255, 0.5 );
  border-radius: 10px;
`;

const Message = styled.span`
  font-size: 24px;
  position: absolute;
  top: 30%;
  right: 5px;
  background-color: #272424;
  color: red;
  font-weight: bolder;
  border-radius: 10px;
  padding: 4px 6px;
`

const Name = styled.h3`
  margin: 10px 0;
`;

const Team = styled.p`
  margin: 0;
`;

export default function Place({ src, alt, name, team, isReady }) {
  return (
    <List>
      <ImageBox>
        <Image
          src={src}
          alt={alt}
        >
        </Image>
      </ImageBox>
      {isReady || (
        <NotReady>
          <Message>
            서비스 <br></br>
            준비중입니다!
          </Message>
        </NotReady>
      )}
      <Name>{name}</Name>
      <Team>{team}</Team>
    </List>
  );
}