// audioUploadReducer.ts
import { AnyAction } from 'redux';

interface AudioUploadState {
  isLoading: boolean;
  data: any;
  error: string | null;
}

const initialState: AudioUploadState = {
  isLoading: false,
  data: null,
  error: null,
};

export const audioUploadReducer = (state = initialState, action: AnyAction): AudioUploadState => {
  switch (action.type) {
    case 'UPLOAD_AUDIO_REQUEST':
      return { ...state, isLoading: true };
    case 'UPLOAD_AUDIO_SUCCESS':
      return { ...state, isLoading: false, data: action.payload, error: null };
    case 'UPLOAD_AUDIO_FAILURE':
      return { ...state, isLoading: false, error: action.payload };
    default:
      return state;
  }
};