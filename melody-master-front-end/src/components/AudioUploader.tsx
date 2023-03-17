// import React, { useState } from 'react';
// import { Button, Form, Container } from 'react-bootstrap';
// import { useDispatch } from 'react-redux';
// import { processAudio } from './audioProcessingActions'; // You'll create this action later

// const AudioUploader: React.FC = () => {
//   const [selectedFile, setSelectedFile] = useState<File | null>(null);
//   const dispatch = useDispatch();

//   const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
//     if (event.target.files && event.target.files.length > 0) {
//       setSelectedFile(event.target.files[0]);
//     }
//   };

//   const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
//     event.preventDefault();
//     if (selectedFile) {
//       dispatch(processAudio(selectedFile));
//     }
//   };

//   return (
//     <Container>
//       <Form onSubmit={handleSubmit}>
//         <Form.Group controlId="formAudioFile">
//           <Form.Label>Upload audio file</Form.Label>
//           <Form.Control
//             type="file"
//             accept="audio/*"
//             onChange={handleFileChange}
//           />
//         </Form.Group>
//         <Button variant="primary" type="submit">
//           Process Audio
//         </Button>
//       </Form>
//     </Container>
//   );
// };

// export default AudioUploader;

// export {};