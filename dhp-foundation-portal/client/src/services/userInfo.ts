import Flow from 'types/Flow'
import { client } from 'utils/api'

const USER_INFO = '/user-info'

async function getUserInfo(): Promise<Flow[] | any> {
  try {
    const data = await client.get(USER_INFO)
    return Promise.resolve(data)
  } catch (err) {
    return Promise.reject(err)
  }
}

export {
  getUserInfo
}

