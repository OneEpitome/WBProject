import React from 'react';
import styled from 'styled-components';
import StyledLink from '../../UI/StyledLink';

const ListItem = styled.li`
  width: 90%;
  padding: 8px 12px;
  margin: 8px 12px;
  font-size: 24px;
  background-color: #F3F5F6;
  border-radius: 8px;
  cursor: pointer;
`

export default function PlaceListItem({ venue, onClickPlaceListItem }) {
  return (
    <StyledLink to={venue.link}>
      <ListItem onClick={onClickPlaceListItem}>
        <span>{venue.symbol} {venue.title}</span>
      </ListItem>
    </StyledLink>
  );
}