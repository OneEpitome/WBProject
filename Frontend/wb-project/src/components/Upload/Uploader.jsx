import React, { useRef, useState } from 'react';
import styled from 'styled-components';
import Text from '../Text/Text';

const Input = styled.input`
  display: none;
`;

const UploaderContainer = styled.div`
  display: inline-block;
  cursor: pointer;
`;

const PreviewImg = styled.img`
  object-fit: contain;
  width: 100%;
  height: 100%;
`;

export default function Uploader({ children, droppable, onHandleChange, type, name, accept, value, onChange, ...props }) {
  const [previewImg, setPreviewImg] = useState('');
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

  const inputRef = useRef(null);

  const handleChooseFile = () => {
    inputRef.current.click();
  };

  // const [dragging, setDragging] = useState(false);
  // const handleDragEnter = (e) => {
  //   if (!droppable) return;
  //   e.preventDefault();
  //   e.stopPropagation();

  //   if (e.dataTransfer.items && e.dataTransfer.items.length > 0) {
  //     setDragging(true);
  //   }
  // };

  // const handleDragLeave = (e) => {
  //   if (!droppable) return;
  //   e.preventDefault();
  //   e.stopPropagation();

  //   setDragging(false);
  // };

  // const handleDragOver = (e) => {
  //   if (!droppable) return;
  //   e.preventDefault();
  //   e.stopPropagation();
  // };

  // const handleFileDrop = (e) => {
  //   if (!droppable) return;
  //   e.preventDefault();
  //   e.stopPropagation();

  //   const files = e.dataTransfer.files;
  //   const changedFile = files[0];

  //   const reader = new FileReader();
  //   const img = changedFile;
  //   if (img) {
  //     reader.readAsDataURL(img);
  //   }

  //   reader.onloadend = () => {
  //     const previewImgUrl = reader.result;
  //     setPreviewImg(previewImgUrl);
  //   };

  //   const draggedFileData = { [e.dataTransfer.files[0].name]: e.dataTransfer.files[0] };
  //   onHandleChange(e, draggedFileData);

  //   setDragging(false);
  // };

  return (
    <UploaderContainer
      onClick={handleChooseFile}
      // onDrop={(e) => {
      //   handleFileDrop(e);
      // }}
      // onDragEnter={handleDragEnter}
      // onDragLeave={handleDragLeave}
      // onDragOver={handleDragOver}
      {...props}
    >
      <Input
        ref={inputRef}
        type={type}
        name={name}
        accept={accept}
        onChange={(e) => {
          insertImg(e);
          onHandleChange(e);
        }} />
      <div
        style={{
          width: 550,
          height: 400,
          marginBottom: 10,
          border: '4px dashed #aaa',
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          // borderColor: dragging ? 'blue' : '#aaaaaa'
        }}
      >
        {previewImg ?
          <PreviewImg src={previewImg} alt="좌석 이미지" /> :
          <Text
            block
            size={32}
            strong={true}
          >클릭해서 사진을 업로드 해주세요.</Text>
        }
      </div>
    </UploaderContainer>
  );
}