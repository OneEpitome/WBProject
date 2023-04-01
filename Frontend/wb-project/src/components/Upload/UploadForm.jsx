import axios from 'axios';
import React from 'react';
import styled from 'styled-components';
import useForm from '../../hooks/useForm';
import { Section } from '../Section/Section';

const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const sleep = () => {
  return new Promise((resolve) => {
    setTimeout(() => resolve(), 1000);
  })
}

export default function UploadForm() {
  const { isLoading, errors, handleChange, handleSubmit } = useForm({
    initialValues: {
      title: '',
      content: '',
      seatId: '',
      memberId: '1',
    },
    onSubmit: async (values) => {
      await sleep();

      const url = '';
      const data = JSON.stringify(values);
      const config = { "Content-Type": "application/json" };

      axios.post(url, data, config)
        .then(res => {
          console.log(res);
        }).catch(err => {
          console.log(err.response.data.message);
        });

      alert(data);
    },
    validate: ({ seatId }) => {
      const errors = {};
      if (!seatId) errors.seatId = '좌석 정보를 입력해주세요!';
      return errors;
    }
  });

  return (
    <Section>
      <Form onSubmit={handleSubmit}>
        <h1>후기 작성하기</h1>
        <div>
          <input type="file" onChange={handleChange} />
        </div>
        <div>
          <input
            type="text"
            name='seatId'
            placeholder='좌석 정보를 입력해주세요.'
            onChange={handleChange}
          />
          {errors.seatId}
        </div>
        <div>
          <input
            type="text"
            name='title'
            placeholder='제목을 입력해주세요.'
            onChange={handleChange}
          />
        </div>
        <div>
          <input
            type="text"
            name='content'
            placeholder='후기를 입력해주세요.'
            onChange={handleChange}
          />
        </div>
        <div>
          {/* hidden or disabled */}
          <input type="text" name='memberId' value='멤버아이디받아와야함' disabled onChange={handleChange} />
        </div>
        <button type='submit' disabled={isLoading}>{isLoading ? '제출중..' : '제출하기'}</button>
      </Form>
    </Section>
  );
}