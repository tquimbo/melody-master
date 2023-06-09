// import React from 'react';


// const HomePage: React.FC = () => {
//   return (
 
//       <h1>Melody Master</h1>
//       <p>
//         Unleash your inner star and master your favorite songs!
//       </p>
   
//   );
// };

// export default HomePage;
import React from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';



const HomePage: React.FC = () => {
  return (
    <Container  style={{ backgroundColor: '#282c34'}}>
      <Row className="justify-content-center mt-5" style={{ color: 'white' }}>
        <Col className="text-center" md={6} style={{ color: 'white' }}>
        <Link to="/" style={{ textDecoration: 'none', color: 'white' }}>
            <h1>Melody Master</h1>
          </Link>
          <p>Unleash your inner star and master your favorite songs!</p>
         
        </Col>
      </Row>
    </Container>
  );
};

export default HomePage;