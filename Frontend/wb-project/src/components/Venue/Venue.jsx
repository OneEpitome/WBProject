import React from 'react';
import Text from '../Text/Text';
import SeatList from './SeatList';
import VenueImg from '../../image/venue/한화생명이글스파크.jpg';
import styled from 'styled-components';

const Section = styled.section`
  margin: 15px 150px;
  height: 600px;
`;

const Container = styled.div`
  width: 100%;
  height: 550px;
  display: flex;
  margin: 20px 0;
`

export default function Venue() {
  return (
    <Section>
      <Text size={28} strong={true}>한화생명이글스파크</Text>
      <Container>
        <img
          src={VenueImg}
          alt="한화생명이글스파크"
          style={{
            width: '65%',
            height: '80%',
            border: 'solid 3px black'
          }}
        />
        <SeatList />
      </Container>
    </Section>
  );
}