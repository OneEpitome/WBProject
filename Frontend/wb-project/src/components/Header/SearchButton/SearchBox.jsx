import React, { useState } from 'react';
import styled from 'styled-components';
import { ImSearch } from 'react-icons/im'
import PlaceList from './PlaceList';
import emojiJson from '../../../data/emoji.json';

const Container = styled.div`
  display: flex;
  font-size: 24px;
  border: 3px solid black;
  border-radius: 15px;
  box-sizing: border-box;
  width: 60%;
  align-items: center;
  padding: 4px 6px;
  margin: 0 auto;
`

const Input = styled.input`
  width: 90%;
  padding: 10px 12px;
  border: none;
  font-size: 24px;
  font-weight: bolder;
`;

const Icon = styled.div`
  padding: 4px 4px 0 4px;
  font-size: 28px;
`;

export default function SearchBox() {
  const [keyword, setKeyword] = useState('');
  return (
    <>
      <Container>
        <Icon><ImSearch /></Icon>
        <Input
          placeholder='경기장 혹은 공연장을 입력해주세요.'
          onChange={(e) => { setKeyword(e.target.value) }}
        />
      </Container>
      <PlaceList emojis={emojiJson} keyword={keyword} />
    </>
  );
}