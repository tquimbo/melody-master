// src/store/index.ts

// import { configureStore } from '@reduxjs/toolkit';
// import rootReducer from './rootReducer';

// export const store = configureStore({
//   reducer: rootReducer,
// });

// export type RootState = ReturnType<typeof store.getState>;
// export type AppDispatch = typeof store.dispatch;

// export {};

// import { createStore, applyMiddleware } from 'redux';
// import thunkMiddleware from 'redux-thunk';
// import rootReducer from './rootReducer';

// const middleware = [thunkMiddleware];

// const store = createStore(rootReducer, applyMiddleware(...middleware));

// export type RootState = ReturnType<typeof store.getState>;
// export type AppDispatch = typeof store.dispatch;

// import { createStore, applyMiddleware } from 'redux';
// import thunkMiddleware from 'redux-thunk';
// import rootReducer from './rootReducer';

// const middleware = [thunkMiddleware];

// const store = createStore(rootReducer, applyMiddleware(...middleware));

// export const RootState = store.getState();
// export const AppDispatch = store.dispatch();
import { createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import rootReducer from './rootReducer';

const middleware = [thunkMiddleware];

export const store = createStore(rootReducer, applyMiddleware(...middleware));

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;