import React, { useState } from 'react';
import Text from '../Text/Text';
import SeatList from './SeatList';
import styled from 'styled-components';
import { SEAT_DATA_Hanwha } from './SEAT_DATA/SEAT_DATA_Hanhwa';

import VenueImg from '../../images/venue/한화생명이글스파크/한화생명이글스파크.png';

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

export default function VenueHanwha() {
  const seatData = SEAT_DATA_Hanwha;
  const [selectedSeat, setSelectedSeat] = useState(VenueImg);

  const onClickListButton = (e) => {
    const newSeat = e.target.innerText;
    const foundData = seatData.find(data => data.sector === newSeat).src;
    setSelectedSeat(foundData);
  }

  return (
    <Section>
      <Text size={28} strong={true}>한화생명이글스파크</Text>
      <Container>
        <img
          src={selectedSeat}
          alt="한화생명이글스파크"
          style={{
            width: '65%',
            height: '80%',
            border: 'solid 3px black'
          }}
        />
        <SeatList onClickListButton={onClickListButton} seatData={seatData} />
      </Container>
    </Section>
  );
}