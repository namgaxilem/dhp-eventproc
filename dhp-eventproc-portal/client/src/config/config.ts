declare global {
  interface Window {
    env: any
  }
}
  
  // change with your own variables
// type EnvType = {
//   REACT_APP_COLOR: string,
//   REACT_APP_MAIN_TEXT: string,
//   REACT_APP_LINK_URL: string,
//   REACT_APP_LOGO_URL: string,
//   REACT_APP_CATALOG_URL: string
// }

const config = {
  // baseURL: '/api',
  baseURL: 'http://localhost:8080/api',
  loginURL: 'http://localhost:8080',
  // deploymentURL: 'http://localhost:8181/'
}


export { config }