import { createSlice } from '@reduxjs/toolkit'

export const breadcrumbNameMapSlice = createSlice({
  name: 'breadcrumbNameMap',
  initialState: {
    value: {
      "/catalog": "Catalog",
      "/clusters": "Clusters",
      "/dataflows": "Data Flows",
      "/dataflows/user-requests": "User Requests",
      "/dataflows/deployments": "Deployments"
    }
  },
  reducers: {
    setBreadcrumbNameMap: (state, action) => {
      state.value = action.payload;
    }
  }
})

// Action creators are generated for each case reducer function
export const { setBreadcrumbNameMap } = breadcrumbNameMapSlice.actions

export default breadcrumbNameMapSlice.reducer