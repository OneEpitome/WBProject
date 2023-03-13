import Text from '../components/Text/Text';

export default {
  title: 'Component/Text',
  component: Text,
  argTypes: {
    block: { control: 'boolean' },
    paragraph: { control: 'boolean' },
    size: { control: 'number' },
    strong: { control: 'boolean' },
    color: { control: 'color' },
  },
}

export const Default = (args) => {
  return (
    <Text {...args}>Text</Text>
  )
}