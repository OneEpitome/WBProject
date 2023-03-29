import styled from 'styled-components';
import PlaceListItem from './PlaceListItem';

const Container = styled.ul`
  width: 60%;
  padding: 0;
  margin: 0 auto;
  list-style: none;
`

const PlaceList = ({ venues, keyword, onClickPlaceListItem }) => {
  return (
    <Container>
      {
        venues
          .filter(venue => venue.title.indexOf(keyword) >= 0 ||
            venue.keywords.indexOf(keyword) >= 0
          )
          .slice(0, 5)
          .map(venue =>
            (<PlaceListItem key={venue.title} venue={venue} onClickPlaceListItem={onClickPlaceListItem} />)
          )
      }
    </Container>
  );
};

export default PlaceList;