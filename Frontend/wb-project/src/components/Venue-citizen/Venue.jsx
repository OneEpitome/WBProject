import React, { useState } from 'react';
import Text from '../Text/Text';
import SeatList from './SeatList';
import styled from 'styled-components';
import { SEAT_DATA } from './SEAT_DATA';

import VenueImg from '../../images/venue/대전월드컵경기장/대전월드컵경기장.png';

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
  const [selectedSeat, setSelectedSeat] = useState(VenueImg);

  const onClickListButton = (e) => {
    const newSeat = e.target.innerText;
    const foundData = SEAT_DATA.find(data => data.text === newSeat).src;
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
        <SeatList onClickListButton={onClickListButton} />
      </Container>
    </Section>
  );
}