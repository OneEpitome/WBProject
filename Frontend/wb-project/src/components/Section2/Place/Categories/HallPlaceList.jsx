import React from 'react';
import { PlaceList } from '../PlaceList';
import PlaceListItem from '../PlaceListItem';

export default function HallPlaceList() {
  return (
    <PlaceList>
      <PlaceListItem
        src=''
        alt='충남대학교 정심화홀'
        name='충남대 정심화홀'
        team='충남대학교'
        isReady={false}
      />
    </PlaceList>
  );
}