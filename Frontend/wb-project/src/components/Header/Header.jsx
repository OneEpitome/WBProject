import React from 'react';
import { MdEventSeat } from 'react-icons/md';
import styled from 'styled-components'
import StyledLink from '../UI/StyledLink';
import Navbar from './Navbar';

const Container = styled.header`
  border-bottom: 1px solid lightgray;
`;

const Section = styled.section`
  margin: 0 150px;
  display: flex;
  justify-content: space-between;
  align-items: center;
`

const Title = styled.h1`
  display: flex;
  align-items: flex-end;
  letter-spacing: 1px;
  svg {
    padding-bottom: 2px;
    margin: 0px 4px;
  }

  &:hover {
    cursor: pointer;
  }
`

const QuestionMark = styled.span`
  font-size: 18px;
  margin-left: 2px;
`;

export default function Header() {
  return (
    <Container>
      <Section>
        <StyledLink to='/'>
          <Title>
            <MdEventSeat />
            <span>어디서볼</span>
            <QuestionMark>?</QuestionMark>
          </ Title>
        </StyledLink>
        <Navbar />
      </Section>
    </Container>
  );
}