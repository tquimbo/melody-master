// import { Dispatch } from 'redux';

// export const uploadAudio = (file: File) => async (dispatch: Dispatch) => {
//   const formData = new FormData();
//   formData.append('audio', file);

//   try {
//     // Replace this URL with the actual backend API endpoint
//     const response = await fetch('https://your-backend-api.com/upload_audio', {
//       method: 'POST',
//       body: formData,
//     });

//     if (response.ok) {
//       const data = await response.json();
//       // Dispatch success action with data here
//       dispatch({ type: 'UPLOAD_AUDIO_SUCCESS', payload: data });
//     } else {
//       // Dispatch error action here
//       dispatch({ type: 'UPLOAD_AUDIO_FAILURE' });
//     }
//   } catch (error) {
//     // Dispatch error action here
//     dispatch({ type: 'UPLOAD_AUDIO_FAILURE' });
//   }
// };

// export default uploadAudio;
// import { Dispatch } from 'redux';

// export const uploadAudio = (file: File) => (dispatch: Dispatch) => {
//   const formData = new FormData();
//   formData.append('audio', file);

//   dispatch({ type: 'UPLOAD_AUDIO_REQUEST' });

//   // Replace this URL with the actual backend API endpoint
//   return fetch('https://your-backend-api.com/upload_audio', {
//     method: 'POST',
//     body: formData,
//   })
//     .then(response => response.json())
//     .then(data => {
//       dispatch({ type: 'UPLOAD_AUDIO_SUCCESS', payload: data });
//     })
//     .catch(error => {
//       dispatch({ type: 'UPLOAD_AUDIO_FAILURE', payload: error.message });
//     });
// };


// export const uploadAudio = (file: File) => (dispatch: Dispatch) => {
//   const formData = new FormData();
//   formData.append('audio', file);

//   dispatch({ type: 'UPLOAD_AUDIO_REQUEST' });

//   // Replace this URL with the actual backend API endpoint
//   return fetch('https://your-backend-api.com/upload_audio', {
//     method: 'POST',
//     body: formData,
//   })
//     .then(response => response.json())
//     .then(responseData => {
//       dispatch({ type: 'UPLOAD_AUDIO_SUCCESS' });
//       return responseData;
//     })
//     .catch(error => {
//       dispatch({ type: 'UPLOAD_AUDIO_FAILURE', payload: error.message });
//       throw error;
//     });
// };

// export const uploadAudio = (file: File) => async (dispatch: Dispatch) => {
//   const formData = new FormData();
//   formData.append('audio', file);

//   dispatch({ type: 'UPLOAD_AUDIO_REQUEST' });

//   try {
//     const response = await fetch('https://your-backend-api.com/upload_audio', {
//       method: 'POST',
//       body: formData,
//     });
//     if (response.ok) {
//       const data = await response.json();
//       dispatch({ type: 'UPLOAD_AUDIO_SUCCESS', payload: data });
//     } else {
//       throw new Error('Upload failed');
//     }
//   } catch (error) {
//     dispatch({ type: 'UPLOAD_AUDIO_FAILURE', payload: error.message });
//     throw error;
//   }
// };

import { Dispatch } from 'redux';
import { isExpressionWithTypeArguments } from 'typescript';


const uploadAudio = (file) => async (dispatch) => {
  const formData = new FormData();
  formData.append('audio', file);

  dispatch({ type: 'UPLOAD_AUDIO_REQUEST' });

  try {
    const response = await fetch('https://your-backend-api.com/upload_audio', {
      method: 'POST',
      body: formData,
    });
    if (response.ok) {
      const data = await response.json();
      dispatch({ type: 'UPLOAD_AUDIO_SUCCESS', payload: data });
    } else {
      throw new Error('Upload failed');
    }
  } catch (error) {
    dispatch({ type: 'UPLOAD_AUDIO_FAILURE', payload: error.message });
    throw error;
  }
};

export { uploadAudio };