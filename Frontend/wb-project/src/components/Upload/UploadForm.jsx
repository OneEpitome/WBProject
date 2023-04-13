import axios from "axios";
import React, { useState } from "react";
import styled from "styled-components";
import useForm from "../../hooks/useForm";
import { Section } from "../Section/Section";
import Text from "../Text/Text";

const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 1px solid black;
  border-radius: 20px;
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

const PreviewImg = styled.img`
  width: 100%;
  height: 400px;
`;

const Label = styled.label`
  background-color: #80a9db;
  color: white;
  padding: 5px 25px;
  border-radius: 20px;
  margin: 10px 0;
  font-size: 24px;

  &:hover {
    cursor: pointer;
  }
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
  const [previewImg, setPreviewImg] = useState(
    "https://www.pngall.com/wp-content/uploads/7/Gallery.png"
  );
  const insertImg = (e) => {
    const reader = new FileReader();
    const img = e.target.files[0];
    if (img) {
      reader.readAsDataURL(img);
    }

    reader.onloadend = () => {
      const previewImgUrl = reader.result;

      setPreviewImg(previewImgUrl);
    };
  };

  const { isLoading, errors, handleChange, handleSubmit } = useForm({
    initialValues: {
      title: "",
      content: "",
      seatId: "",
      memberId: "1",
      imageFile: null,
    },

    onSubmit: async (values) => {
      await sleep();

      const url = "/api/reviews";
      const data = JSON.stringify(values);
      const config = { headers: { "Content-Type": "multipart/form-data" } };

      // FormData 객체를 생성하고 데이터를 추가합니다.
      const formData = new FormData();
      formData.append("title", values.title);
      formData.append("content", values.content);
      formData.append("seatId", values.seatId);
      formData.append("memberId", values.memberId);
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
    validate: ({ seatId, imageFile }) => {
      const errors = {};
      if (!seatId) errors.seatId = "⬆️ 좌석 정보를 입력해주세요!";
      if (!imageFile) errors.imageFile = "⬆️ 좌석 이미지를 업로드해주세요!";
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
          <PreviewImg src={previewImg} alt="좌석 이미지" />
          <Input
            type="file"
            id="file"
            name="imageFile"
            style={{
              display: "none",
            }}
            onChange={(e) => {
              handleChange(e);
              insertImg(e);
            }}
          />
          <Label htmlFor="file">좌석 이미지 업로드 하기</Label>
          <WarningMessage>{errors.imageFile}</WarningMessage>
        </ImgBox>

        <InputBox>
          <Input
            type="number"
            name="seatId"
            placeholder="좌석 정보를 입력해주세요."
            onChange={handleChange}
          />
          <WarningMessage>{errors.seatId}</WarningMessage>
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
        <InputBox>
          {/* hidden or disabled */}
          <Input
            type="text"
            name="memberId"
            value="멤버아이디받아와야함"
            disabled
            onChange={handleChange}
          />
        </InputBox>
        <Button type="submit" disabled={isLoading}>
          {isLoading ? "Loading.." : "공유하기"}
        </Button>
      </Form>
    </Section>
  );
}
