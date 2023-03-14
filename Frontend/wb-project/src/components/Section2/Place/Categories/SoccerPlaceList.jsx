import React from 'react';
import { PlaceList } from '../PlaceList';
import PlaceListItem from '../PlaceListItem';

export default function SoccerPlaceList() {
  return (
    <PlaceList>
      <PlaceListItem
        src='	https://www.dhcfc.kr/images/2021/header_logo.png'
        alt='대전월드컵경기장'
        name='대전월드컵경기장'
        team='대전하나시티즌'
        isReady={true}
      />
    </PlaceList>
  );
}