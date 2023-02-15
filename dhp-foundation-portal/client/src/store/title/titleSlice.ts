import { createSlice } from '@reduxjs/toolkit'

export const titleSlice = createSlice({
  name: 'title',
  initialState: {
    value: 'Data Flow'
  },
  reducers: {
    changeTitle: (state, action) => {
      state.value = action.payload;
      document.title = state.value;
    }
  }
})

// Action creators are generated for each case reducer function
export const { changeTitle } = titleSlice.actions

export default titleSlice.reducer