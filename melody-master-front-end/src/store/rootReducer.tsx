// src/store/rootReducer.ts

import { combineReducers } from 'redux';
import { audioUploadReducer } from './audioUploadReducer';
// Import your individual reducers here, for example:
//import scoresReducer from './scoresReducer';

const rootReducer = combineReducers({
  // Add your individual reducers here, for example:
  audioUpload: audioUploadReducer,
  // scores: scoresReducer,
});

export default rootReducer;