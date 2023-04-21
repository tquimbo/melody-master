// import React, { useState, useEffect } from 'react';
// import { Container, Row, Col, Form, Button } from 'react-bootstrap';

// interface User {
//   id: number;
//   firstName: string;
//   lastName: string;
//   email: string;
// }

// interface UserProfileProps {
//   user: User;
// }

// const UserProfile: React.FC<UserProfileProps> = ({ user }) => {
//   const [firstName, setFirstName] = useState(user.firstName);
//   const [lastName, setLastName] = useState(user.lastName);
//   const [email, setEmail] = useState(user.email);

//   const handleSave = () => {
//     // Make API call to update user profile
//   };

//   return (
//     <Container>
//       <Row>
//         <Col>
//           <h1>User Profile</h1>
//         </Col>
//       </Row>
//       <Row>
//         <Col md={6}>
//           <Form>
//             <Form.Group controlId="firstName">
//               <Form.Label>First Name</Form.Label>
//               <Form.Control type="text" value={firstName} onChange={(event) => setFirstName(event.target.value)} />
//             </Form.Group>
//             <Form.Group controlId="lastName">
//               <Form.Label>Last Name</Form.Label>
//               <Form.Control type="text" value={lastName} onChange={(event) => setLastName(event.target.value)} />
//             </Form.Group>
//             <Form.Group controlId="email">
//               <Form.Label>Email</Form.Label>
//               <Form.Control type="email" value={email} onChange={(event) => setEmail(event.target.value)} />
//             </Form.Group>
//             <Button variant="primary" onClick={handleSave}>Save Changes</Button>
//           </Form>
//         </Col>
//       </Row>
//     </Container>
//   );
// };

// export default UserProfile;
export default {};