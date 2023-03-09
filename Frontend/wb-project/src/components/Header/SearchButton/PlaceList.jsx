import styled from 'styled-components';
import PlaceListItem from './PlaceListItem';

const Container = styled.ul`
  width: 60%;
  padding: 0;
  margin: 0 auto;
  list-style: none;
`

const PlaceList = ({ emojis, keyword }) => {
  return (
    <Container>
      {
        emojis
          .filter(emoji => emoji.title.indexOf(keyword) >= 0 ||
            emoji.keywords.indexOf(keyword) >= 0
          )
          .slice(0, 5)
          .map(emoji =>
            (<PlaceListItem key={emoji.title} emoji={emoji} />)
          )
      }
    </Container>
  );
};

export default PlaceList;