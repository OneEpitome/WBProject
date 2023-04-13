import React from 'react';
import Modal from '../UI/Modal';
import { GiCancel } from 'react-icons/gi';
import { Button } from '../UI/Button';
import styled from 'styled-components';
import Text from '../Text/Text';

const Container = styled.div`
  margin-top: 15px;
  width: 100%;
  height: 100%;
  display: flex;
`;

const Image = styled.img`
  background-color: black;
  width: 700px;
  height: 100%;
  border-radius: 20px;
`;

const ContentContainer = styled.div`
  margin: 0 30px;
`;

const Content = styled.div`
  width: 350px;
  height: 80%;
  padding: 20px;
  margin: 20px 0 0 0;
  border: 3px solid lightgrey;
  border-radius: 20px;
`

export default function SeatModal({ visible, onClickCancelButton, src, alt, title, content }) {
  return (
    <Modal
      width='80%'
      visible={visible}
    >
      <Button
        style={{
          fontSize: '30px',
          position: 'absolute',
          top: '10px',
          right: '10px',
        }}
      ><GiCancel
          onClick={() => onClickCancelButton()}
        /></Button>

      <Container>
        <Image src={src} alt={alt} />
        <ContentContainer>
          <Text strong={true} size={38}>{title}</Text>
          <Content>{content}</Content>
        </ContentContainer>
      </Container>
    </Modal>
  );
}