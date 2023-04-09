import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const Container = styled.div`
  height: 440px;
  overflow-y: scroll;
  border: solid 3px black;
  border-left: none;
`

const Button = styled(Link)`
  display: block;
  font-size: 20px;
  padding: 5px 40px;
  border: none;
  background-color: transparent;
  border-radius: 20px;
  cursor: pointer;

  text-decoration: none;
  color: black;

  &:hover {
    background-color: lightgrey;
  }
`;

const DetailContainer = styled.div`
  border: 2px solid #972a40;
  border-radius: 20px;
`;

export default function ListItem({ onClickListButton, onGetDetailData, seatData }) {
  const [selected, setSelected] = useState('');

  const onClickListButton2 = (e) => {
    setSelected(e.target.innerText);
  };

  const onClickDetailButton = (e) => {
    onGetDetailData(e.target.innerText);
  };

  return (
    <Container>
      {
        seatData.map((data) => {
          return (
            <div>
              <Button key={data.id}
                onClick={(e) => {
                  onClickListButton(e);
                  onClickListButton2(e);
                }}
              >{data.sector}</Button>
              {
                (data.id === selected) &&
                <DetailContainer>
                  {data.details.map((detail) => (
                    <Button
                      to={detail}
                      key={detail}
                      onClick={onClickDetailButton}
                    >{detail}</Button>
                  ))}
                </DetailContainer>
              }
            </div>
          )
        })
      }
    </Container >
  );
}