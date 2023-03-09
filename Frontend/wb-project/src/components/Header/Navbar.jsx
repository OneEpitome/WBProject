import React from 'react';
import styled from 'styled-components';
import LoginButton from './LoginButton';
import SearchButton from './SearchButton';

const Nav = styled.div`
  display: flex;
`

export default function Navbar() {
  return (
    <Nav>
      <SearchButton />
      <LoginButton />
    </Nav>
  );
}

