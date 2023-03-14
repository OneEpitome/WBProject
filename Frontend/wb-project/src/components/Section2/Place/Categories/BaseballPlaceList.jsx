import React from 'react';
import { PlaceList } from '../PlaceList';
import PlaceListItem from '../PlaceListItem';

export default function BaseBallPlaceList() {
  return (
    <PlaceList>
      <PlaceListItem
        src='https://www.hanwhaeagles.co.kr/images/pages/eagles/img_eagles_ci_03.png'
        alt='한화생명이글스파크'
        name='한화생명이글스파크'
        team='한화이글스'
        isReady={false}
      />
    </PlaceList>
  );
}