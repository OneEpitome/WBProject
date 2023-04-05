import React from 'react';
import { Section } from '../Section/Section';
import useForm from '../../hooks/useForm';
import axios from 'axios';

export default function SignInForm() {
  const { errors, handleChange, handleSubmit } = useForm({
    initialValues: {
      username: "",
      password: "",
      confirmPassword: "",
      nickname: "",
    },
    onSubmit: async (values) => {
      const url = 'http://localhost:3000/api/member/create';
      const data = JSON.stringify(values);
      const config = { headers: { "Content-Type": "application/json" } };

      const formData = new FormData();
      formData.append("username", values.nickname);
      formData.append("password", values.password);
      formData.append("nickname", values.nickname);

      axios
        .post(url, formData, config)
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err.response.data.message);
        });

      alert(data);
    },
    validate: ({ username, password, confirmPassword, nickname }) => {
      const errors = {};
      // 아이디 유효성 검사 (영문, 숫자 5-11자)
      if (!username) errors.username = '아이디를 입력해주세요!';
      if (username && username.length < 5) errors.usernameLength = '아이디는 5자 이상이여야 합니다!'


      // 비밀번호 유효성 검사 (숫자, 영문, 특수문자 조합 최소 8자)
      if (!password) errors.password = '비밀번호를 입력해주세요!';
      const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
      if (password && !passwordRegex.test(password)) errors.passwordRegex = '숫자, 영문, 특수문자 조합 8자 이상으로 입력해주세요!'

      // 비밀번호 확인 유효성 검사
      if (!confirmPassword) errors.confirmPassword = '비밀번호 재확인은 필수정보입니다!';
      if (confirmPassword && (password !== confirmPassword)) errors.notMatchPassword = '비밀번호가 일치하지 않습니다!';

      // 닉네임 유효성 검사
      if (!nickname) errors.nickname = '닉네임을 입력해주세요!';

      return errors;
    }
  })

  return (
    <Section>
      <form onSubmit={handleSubmit}>
        <div>
          <input type="text" placeholder='아이디 (영문, 숫자 5-11자)' maxLength={11} name='username' onChange={handleChange} />
          {errors.username}
          {errors.usernameLength}
        </div>
        <div>
          <input type="password" placeholder='비밀번호 (숫자, 영문, 특수문자 조합 최소 8자)' name='password' autoComplete='on' onChange={handleChange} />
          {errors.password}
          {errors.passwordRegex}
        </div>
        <div>
          <input type="password" placeholder='비밀번호 확인' name='confirmPassword' autoComplete='on' onChange={handleChange} />
          {errors.confirmPassword}
          {errors.notMatchPassword}
        </div>
        <div>
          <input type="text" placeholder='닉네임' name='nickname' onChange={handleChange} />
          {errors.nickname}
        </div>
        <button type='sumbit' >회원가입</button>
      </form>
    </Section>
  );
}