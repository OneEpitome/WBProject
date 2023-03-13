import React from 'react';

export default function Text({ children, block, paragraph, size, strong, color, ...props }) {
  const Tag = block ? 'div' : paragraph ? 'p' : 'span';

  const fontStyle = {
    fontSize: size,
    fontWeight: strong ? 'bold' : undefined,
    color: color,
  };

  return (
    <Tag style={{ ...props.style, ...fontStyle }} {...props}>
      {children}
    </ Tag>
  );
}