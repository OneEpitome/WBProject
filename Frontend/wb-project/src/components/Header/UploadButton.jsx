import React from 'react';
import { Button } from '../UI/Button';
import { FiUpload } from 'react-icons/fi';
import StyledLink from '../UI/StyledLink';

export default function UploadButton() {
  return (
    <StyledLink to='/upload'>
      <Button>
        <FiUpload />
      </Button>
    </StyledLink>
  );
}

