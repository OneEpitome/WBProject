import React, { useState } from 'react';
import { BiSearchAlt2 } from 'react-icons/bi';
import { Button } from '../Button';
import Modal from './SearchModal';
import { GiCancel } from 'react-icons/gi';
import SearchBox from './SearchBox';

export default function SearchButton() {
  const [visible, setVisible] = useState(false);
  const onClickPlaceListItem = () => {
    setVisible(false);
  };
  return (
    <>
      <Button onClick={() => setVisible(true)}>
        <BiSearchAlt2 />
      </Button>
      <Modal visible={visible} onClose={() => { setVisible(false) }}>
        <Button
          style={{
            fontSize: '30px',
            position: 'absolute',
            top: '10px',
            right: '10px',
          }}
        ><GiCancel onClick={() => setVisible(false)} /></Button>
        <SearchBox onClickPlaceListItem={onClickPlaceListItem} />
      </Modal>
    </>
  );
}