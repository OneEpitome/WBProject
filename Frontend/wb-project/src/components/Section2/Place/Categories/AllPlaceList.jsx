import React from 'react';
import { PlaceList } from '../PlaceList';
import PlaceListItem from '../PlaceListItem';

export default function AllPlaceList() {
  return (
    <PlaceList>
      <PlaceListItem
        src='	https://www.dhcfc.kr/images/2021/header_logo.png'
        alt='대전월드컵경기장'
        name='대전월드컵경기장'
        team='대전하나시티즌'
        isReady={true}
      />
      <PlaceListItem
        src='https://www.hanwhaeagles.co.kr/images/pages/eagles/img_eagles_ci_03.png'
        alt='한화생명이글스파크'
        name='한화생명이글스파크'
        team='한화이글스'
        isReady={false}
      />
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