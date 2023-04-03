import axios from "axios";
import React, { useState } from "react";
import styled from "styled-components";
import useForm from "../../hooks/useForm";
import { Section } from "../Section/Section";

const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const sleep = () => {
  return new Promise((resolve) => {
    setTimeout(() => resolve(), 1000);
  });
};

export default function UploadForm() {
  const [previewImg, setPreviewImg] = useState('https://cdn-icons-png.flaticon.com/512/2271/2271664.png');
  const insertImg = (e) => {
    const reader = new FileReader();
    const img = e.target.files[0];
    if (img) {
      reader.readAsDataURL(img);
    }

    reader.onloadend = () => {
      const previewImgUrl = reader.result;

      setPreviewImg(previewImgUrl);
    }
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

      const url = "http://localhost:3000/api/review/save";
      const data = JSON.stringify(values);
      // const config = { headers: { "Content-Type": "multipart/form-data" } }; //before : application/json
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
    validate: ({ seatId }) => {
      const errors = {};
      if (!seatId) errors.seatId = "좌석 정보를 입력해주세요!";
      return errors;
    },
  });

  return (
    <Section>
      <Form onSubmit={handleSubmit}>
        <h1>후기 작성하기</h1>
        <div>
          <img
            src={previewImg}
            alt="좌석 이미지"
          />
          <label htmlFor="file">좌석 이미지 업로드 하기</label>
          <input
            type="file"
            id='file'
            name="imageFile"
            style={{
              display: 'none',
            }}
            onChange={(e) => {
              handleChange(e);
              insertImg(e);
            }}
          />
        </div>
        <div>
          <input
            type="number"
            name="seatId"
            placeholder="좌석 정보를 입력해주세요."
            onChange={handleChange}
          />
          {errors.seatId}
        </div>
        <div>
          <input
            type="text"
            name="title"
            placeholder="제목을 입력해주세요."
            onChange={handleChange}
          />
        </div>
        <div>
          <input
            type="text"
            name="content"
            placeholder="후기를 입력해주세요."
            onChange={handleChange}
          />
        </div>
        <div>
          {/* hidden or disabled */}
          <input
            type="text"
            name="memberId"
            value="멤버아이디받아와야함"
            disabled
            onChange={handleChange}
          />
        </div>
        <button type="submit" disabled={isLoading}>
          {isLoading ? "제출중.." : "제출하기"}
        </button>
      </Form>
    </Section>
  );
}
