import React from 'react';

const Footer: React.FC = () => {
  return (
    <footer className="footer has-background-primary-blue has-text-white">
      © {new Date().getFullYear()} Learning Portal. All rights reserved.
    </footer>
  );
};

export default Footer;