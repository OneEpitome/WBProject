import axios from "axios";
import React from "react";
import styled from "styled-components";
import useForm from "../../hooks/useForm";
import { Section } from "../Section/Section";
import Text from "../Text/Text";
import Uploader from './Uploader';

const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px;
  width: 700px;
  margin: 10px;
`;

const ImgBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const InputBox = styled.div`
  display: flex;
  flex-direction: column;
`;

const Input = styled.input`
  margin: 5px;
  font-size: 32px;
  border: 1px solid black;
  border-radius: 10px;
  padding: 5px 10px;
  width: 500px;
`;

const Select = styled.select`
  margin: 5px;
  font-size: 32px;
  border: 1px solid black;
  border-radius: 10px;
  width: 520px;
  padding: 5px 10px;
`;

const WarningMessage = styled.span`
  color: red;
  font-weight: bold;
  font-size: 20px;
  margin: 0 20px;
`;

const Button = styled.button`
  outline: none;
  border: none;
  background-color: black;
  color: white;
  border-radius: 20px;
  padding: 10px 15px;
  margin: 10px;
  font-size: 24px;
  width: 530px;

  &:hover {
    cursor: pointer;
    background-color: grey;
    color: black;
  }
`;

const sleep = () => {
  return new Promise((resolve) => {
    setTimeout(() => resolve(), 500);
  });
};

export default function UploadForm() {
  const { isLoading, errors, handleChange, handleSubmit } = useForm({
    initialValues: {
      nickname: "",
      password: "",
      title: "",
      content: "",
      venue: "",
      sector: "",
      seatNumber: "",
      // seatId: "",
      // memberId: "1",
      imageFile: null,
    },

    onSubmit: async (values) => {
      await sleep();

      const url = "/api/reviews";
      const data = JSON.stringify(values);
      const config = { headers: { "Content-Type": "multipart/form-data" } };

      // FormData 객체를 생성하고 데이터를 추가합니다.
      const formData = new FormData();
      formData.append("nickname", values.nickname);
      formData.append("password", values.password);
      formData.append("title", values.title);
      formData.append("content", values.content);
      formData.append("venue", values.venue);
      formData.append("sector", values.sector);
      formData.append("seatNumber", values.seatNumber);
      // formData.append("seatId", values.seatId);
      // formData.append("memberId", values.memberId);
      formData.append("imageFile", values.imageFile); // 파일 추가

      axios
        .post(url, formData, config)
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err.response.data.message);
        });

      // alert(data);
      alert(data);
    },
    validate: ({ seatId, venue, sector, seatNumber, imageFile, nickname, password }) => {
      const errors = {};
      // if (!seatId) errors.seatId = "⬆️ 좌석 정보를 입력해주세요!";
      if (!venue) errors.venue = "⬆️ 경기장 혹은 공연장을 선택해주세요!";
      if (!sector) errors.sector = "⬆️ 섹터를 입력해주세요!";
      if (!seatNumber) errors.seatNumber = "⬆️ 좌석번호를 입력해주세요!";
      if (!imageFile) errors.imageFile = "⬆️ 좌석 이미지를 업로드해주세요!";
      if (!nickname) errors.nickname = "⬆️ 닉네임을 입력해주세요!";
      if (!password) errors.password = "⬆️ 비밀번호를 입력해주세요!";

      return errors;
    },
  });

  return (
    <Section
      style={{
        alignItems: "center",
      }}
    >
      <Text strong={true} size="32px">
        후기 작성하기
      </Text>
      <Form onSubmit={handleSubmit}>
        <ImgBox>
          <Uploader
            droppable
            type="file"
            name="imageFile"
            onHandleChange={handleChange}
          />
          <WarningMessage>{errors.imageFile}</WarningMessage>
        </ImgBox>
        <div style={{
          display: 'flex',
          width: '532px',
        }}>
          <InputBox>
            <Input
              type="text"
              name="nickname"
              placeholder="닉네임"
              onChange={handleChange}
              style={{
                width: '235px'
              }}
            />
            <WarningMessage>{errors.nickname}</WarningMessage>
          </InputBox>
          <InputBox>
            <Input
              type="password"
              name="password"
              placeholder="비밀번호"
              onChange={handleChange}
              style={{
                width: '235px'
              }}
            />
            <WarningMessage>{errors.password}</WarningMessage>
          </InputBox>
        </div>
        <InputBox>
          <Select
            name='venue'
            onChange={handleChange}
          >
            <option value="">경기장 혹은 공연장을 선택해주세요.</option>
            <option value="대전월드컵경기장">대전월드컵경기장</option>
            <option value="한화생명이글스파크" disabled>한화생명이글스파크</option>
            <option value="정심화홀" disabled>정심화홀</option>
          </Select>
          <WarningMessage>{errors.venue}</WarningMessage>
        </InputBox>
        <InputBox>
          <Input
            type='string'
            name='sector'
            placeholder='섹터를 입력해주세요.'
          />
          <WarningMessage>{errors.sector}</WarningMessage>
        </InputBox>
        <InputBox>
          <Input
            type="number"
            name="seatNumber"
            placeholder="좌석 번호를 입력해주세요."
            onChange={handleChange}
          />
          <WarningMessage>{errors.seatNumber}</WarningMessage>
        </InputBox>
        <InputBox>
          <Input
            type="text"
            name="title"
            placeholder="제목을 입력해주세요."
            onChange={handleChange}
          />
        </InputBox>
        <InputBox>
          <Input
            type="text"
            name="content"
            placeholder="간단한 후기를 입력해주세요."
            onChange={handleChange}
          />
        </InputBox>

        {/* <InputBox>
          hidden or disabled
          <Input
            type="text"
            name="memberId"
            value="멤버아이디받아와야함"
            disabled
            onChange={handleChange}
          />
        </InputBox> */}
        <Button type="submit" disabled={isLoading}>
          {isLoading ? "Loading.." : "공유하기"}
        </Button>
      </Form>
    </Section>
  );
}
