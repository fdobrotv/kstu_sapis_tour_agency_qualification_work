import { render, screen } from '@/test-utils';
import { CarMarks } from './CarMarks';

describe('CarMarks component', () => {
  it('has correct Next.js theming section link', () => {
    render(<CarMarks />);
    expect(screen.getByText('this guide')).toHaveAttribute(
      'href',
      'https://mantine.dev/guides/next/'
    );
  });
});
