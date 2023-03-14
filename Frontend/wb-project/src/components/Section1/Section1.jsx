import React from 'react';
import { Section } from '../Section/Section';
import Text from '../Text/Text';

export default function Section1() {
  return (
    <Section>
      <Text size={48} strong={true}>예매할 자리를</Text>
      <Text size={48} strong={true}>미리 보고</Text>
      <Text size={48} strong={true}>싶을 때</Text>
    </Section>
  );
}