import React from 'react';
import styled from 'styled-components';
// import LoginButton from './LoginButton';
import SearchButton from './SearchButton/SearchButton';
import UploadButton from './UploadButton';

const Nav = styled.div`
  display: flex;
`

export default function Navbar() {
  return (
    <Nav>
      <SearchButton />
      <UploadButton />
      {/* <LoginButton /> */}
    </Nav>
  );
}

