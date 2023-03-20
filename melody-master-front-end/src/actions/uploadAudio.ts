import { Dispatch } from 'redux';

export const uploadAudio = (file: File) => async (dispatch: Dispatch) => {
  const formData = new FormData();
  formData.append('audio', file);

  try {
    // Replace this URL with the actual backend API endpoint
    const response = await fetch('https://your-backend-api.com/upload_audio', {
      method: 'POST',
      body: formData,
    });

    if (response.ok) {
      const data = await response.json();
      // Dispatch success action with data here
      dispatch({ type: 'UPLOAD_AUDIO_SUCCESS', payload: data });
    } else {
      // Dispatch error action here
      dispatch({ type: 'UPLOAD_AUDIO_FAILURE' });
    }
  } catch (error) {
    // Dispatch error action here
    dispatch({ type: 'UPLOAD_AUDIO_FAILURE' });
  }
};

export default uploadAudio;