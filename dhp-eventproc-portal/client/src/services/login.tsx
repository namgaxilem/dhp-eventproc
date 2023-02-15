import Flow from 'types/Flow'
import { loginClient as client } from 'utils/api'

const AZURE_LOGIN = '/oauth2/authorization/azure';

async function login(): Promise<Flow[] | any> {
  try {
    const data = await client.get(AZURE_LOGIN)
    return Promise.resolve(data)
  } catch (err) {
    return Promise.reject(err)
  }
}

export {
  login
}

