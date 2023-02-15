import { configureStore } from '@reduxjs/toolkit';
import titleReducer from './title/titleSlice';
import breadcrumbReducer from './breadcrumb/breadcrumbSlice';

const store = configureStore({
  reducer: {
    title: titleReducer,
    breadcrumbNameMap: breadcrumbReducer,
  }
})
export default store;
export type RootStoreState = ReturnType<typeof store.getState>;