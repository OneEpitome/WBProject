import { Section } from '../Section/Section';
import { useState } from 'react';
import styled from 'styled-components';
import AllPlaceList from './Place/Categories/AllPlaceList';
import SoccerPlaceList from './Place/Categories/SoccerPlaceList';
import BaseBallPlaceList from './Place/Categories/BaseballPlaceList';
import HallPlaceList from './Place/Categories/HallPlaceList';

const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  padding: 0;
  margin: 8px 0;
`;

const Button = styled.button`
  font-size: large;
  outline: none;
  border: 1px solid black;
  border-radius: 20px;
  padding: 5px 10px;
  margin: 0 5px;
  background-color: transparent;
  cursor: pointer;
  transition: all ease-in-out 200ms;

  &:hover{
    background-color: lightgrey;
    border: 1px solid lightgrey;
  }
`;

export default function Section2() {
  const [category, setCategory] = useState('전체');

  const onClickButton = (e) => {
    const newCategory = e.target.textContent.split(' ')[1];
    setCategory(newCategory);
  }

  const selectCategory = {
    전체: <AllPlaceList />,
    축구장: <SoccerPlaceList />,
    야구장: <BaseBallPlaceList />,
    공연장: <HallPlaceList />,
  };

  return (
    <Section>
      <ButtonContainer>
        <Button onClick={onClickButton}>🤗 전체</Button>
        <Button onClick={onClickButton}>⚽️ 축구장</Button>
        <Button onClick={onClickButton}>⚾️ 야구장</Button>
        <Button onClick={onClickButton}>🏛️ 공연장</Button>
      </ButtonContainer>
      {selectCategory[category]}
    </Section >
  );
}