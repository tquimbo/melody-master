
// import React from 'react';
// import { Navbar, Nav } from 'react-bootstrap';
// import { Link } from 'react-router-dom';

// interface NavBarItemProps {
//   label: string;
//   path: string;
// }

// const NavBarItem: React.FC<NavBarItemProps> = ({ label, path }) => (
//   <Nav.Link as={Link} to={path}>{label}</Nav.Link>
// );

// const NavBar: React.FC = () => {
//   return (
//     <Navbar className="navbar-dark" style={{ backgroundColor: '#282c34', color: 'white' }} expand="lg">
//       {/* <Navbar.Brand href="/">Melody Master</Navbar.Brand> */}
//       <Navbar.Toggle aria-controls="basic-navbar-nav" />
//       <Navbar.Collapse id="basic-navbar-nav">
//         <Nav className="mr-auto">
//           <NavBarItem label="Submit a Song" path="/submit" />
//           <NavBarItem label="Choose a Song" path="/choose" />
//           <NavBarItem label="User Profile" path="/profile" />
//         </Nav>
//       </Navbar.Collapse>

          
         
//     </Navbar>

    
//   );
// };

// export default NavBar;
import React from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import { Link } from 'react-router-dom';

interface NavBarItemProps {
  label: string;
  path: string;
}

const NavBarItem: React.FC<NavBarItemProps> = ({ label, path }) => (
  <Nav.Link as={Link} to={path} className="text-white">{label}</Nav.Link>
);

// const NavBar: React.FC = () => {
//   return (
//     <Navbar className="navbar-dark"  expand="lg" style={{ backgroundColor: '#282c34', justifyContent: 'center' }}>
//       <Navbar.Toggle aria-controls="basic-navbar-nav" />
//       <Navbar.Collapse id="basic-navbar-nav">
//         <Nav className="mr-auto" style={{ justifyContent: 'center' }}>
//           <NavBarItem label="Submit a Song" path="/submit" />
//           <NavBarItem label="Choose a Song" path="/choose" />
//           <NavBarItem label="User Profile" path="/profile" />
//         </Nav>
//       </Navbar.Collapse>
//     </Navbar>
//   );
// };
const NavBar: React.FC = () => {
  return (
    <Navbar className="navbar-dark" expand="lg" style={{ backgroundColor: '#282c34', justifyContent: 'center', top: '50px', left: 0, right: 0, zIndex: 999 }}>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mx-auto" style={{ justifyContent: 'center' }}>
          <NavBarItem label="Submit a Song" path="/submit" />
          <NavBarItem label="Choose a Song" path="/choose" />
          <NavBarItem label="User Profile" path="/profile" />
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default NavBar;