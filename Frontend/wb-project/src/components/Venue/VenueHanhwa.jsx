import React, { useState } from 'react';
import Text from '../Text/Text';
import SeatList from './SeatList';
import { SEAT_DATA_Hanwha } from './SEAT_DATA/SEAT_DATA_Hanhwa';

import VenueImg from '../../images/venue/hanwha-eagles/한화생명이글스파크.png';
import { Container } from './UI/Container';
import { Section } from './UI/Section';

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