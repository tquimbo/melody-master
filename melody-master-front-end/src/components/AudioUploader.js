// import React, { useState } from 'react';
// import { Button, Form, Container } from 'react-bootstrap';
// import { useDispatch } from 'react-redux';
// import { uploadAudio } from '../actions/uploadAudio'; // You'll create this action later




// const AudioUploader: React.FC = () => {
//   const [selectedFile, setSelectedFile] = useState<File | null>(null);
//   const dispatch = useDispatch();

//   const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
//     if (event.target.files && event.target.files.length > 0) {
//       setSelectedFile(event.target.files[0]);
//     }
//   };

//   // const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
//   //   event.preventDefault();
//   //   if (selectedFile) {
//   //     dispatch(uploadAudio(selectedFile));
//   //   }
//   // };
//   const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
//     event.preventDefault();
//     if (selectedFile) {
//       dispatch({ type: 'UPLOAD_AUDIO_REQUEST' });
//       try {
//         const response = await dispatch(uploadAudio(selectedFile));
//         dispatch({ type: 'UPLOAD_AUDIO_SUCCESS', payload: response });
//       } catch (error) {
//         dispatch({ type: 'UPLOAD_AUDIO_FAILURE', payload: error.message });
//       }
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
import React, { useState } from 'react';
import { Button, Form, Container } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import { uploadAudio } from '../actions/uploadAudio'; // You'll create this action later

const AudioUploader = () => {
  const [selectedFile, setSelectedFile] = useState(null);
  const dispatch = useDispatch();

  const handleFileChange = (event) => {
    if (event.target.files && event.target.files.length > 0) {
      setSelectedFile(event.target.files[0]);
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (selectedFile) {
      dispatch({ type: 'UPLOAD_AUDIO_REQUEST' });
      try {
        const response = await dispatch(uploadAudio(selectedFile));
        dispatch({ type: 'UPLOAD_AUDIO_SUCCESS', payload: response });
      } catch (error) {
        dispatch({ type: 'UPLOAD_AUDIO_FAILURE', payload: error.message });
      }
    }
  };

  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formAudioFile">
          <Form.Label>Upload audio file</Form.Label>
          <Form.Control
            type="file"
            accept="audio/*"
            onChange={handleFileChange}
          />
        </Form.Group>
        <Button variant="primary" type="submit">
          Process Audio
        </Button>
      </Form>
    </Container>
  );
};

export default AudioUploader;

export {};
