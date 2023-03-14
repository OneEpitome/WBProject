import React from 'react';
import styled from 'styled-components';

const Button = styled.button`
  font-size: large;
  outline: none;
  border: 1px solid black;
  border-radius: 20px;
  padding: 5px 10px;
  background-color: transparent;
  cursor: pointer;
  transition: all ease-in-out 200ms;

  &:hover{
    background-color: lightgrey;
    border: 1px solid lightgrey;
  }
`;

export default function ButtonListItem({ children }) {
  return (
    <Button>{children}</Button>
  );
}