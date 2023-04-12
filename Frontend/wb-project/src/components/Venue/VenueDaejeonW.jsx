import React, { useState } from 'react';
import Text from '../Text/Text';
import SeatList from './SeatList';
import { SEAT_DATA_DaejeonW } from './SEAT_DATA/SEAT_DATA_DaejeonW';

import VenueImg from '../../images/venue/대전월드컵경기장/대전월드컵경기장.png';
import { Container } from './UI/Container';
import { Section } from './UI/Section';

export default function VenueDaejeonW() {
  const seatData = SEAT_DATA_DaejeonW;
  const [selectedSeat, setSelectedSeat] = useState(VenueImg);

  const onClickListButton = (e) => {
    const newSeat = e.target.innerText;
    const foundData = seatData.find(data => data.sector === newSeat).src;
    setSelectedSeat(foundData);
  }

  return (
    <Section>
      <Text size={28} strong={true}>대전월드컵경기장</Text>
      <Container>
        <img
          src={selectedSeat}
          alt="대전월드컵경기장"
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