import React from 'react';
import styled from 'styled-components';
import ButtonListItem from './ButtonListItem';

const Container = styled.ul`
  display: flex;
  justify-content: center;
  padding: 0;
`;

export default function ButtonList() {
  return (
    <Container>
      <ButtonListItem>🤗 전체</ButtonListItem>
      <ButtonListItem>⚽️ 축구장</ButtonListItem>
      <ButtonListItem>⚾️ 야구장</ButtonListItem>
      <ButtonListItem>🏛️ 공연장</ButtonListItem>
    </Container>
  );
}