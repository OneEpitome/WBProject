import React from 'react';
import styled from 'styled-components';

const ListItem = styled.li`
  width: 90%;
  padding: 8px 12px;
  margin: 8px 12px;
  font-size: 24px;
  background-color: #F3F5F6;
  border-radius: 8px;
  cursor: pointer;
`

export default function PlaceListItem({ emoji }) {
  return (
    <ListItem>
      <span>{emoji.symbol} {emoji.title}</span>
    </ListItem>
  );
}