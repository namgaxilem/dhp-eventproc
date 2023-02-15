import Flow from 'types/Flow'
import { client } from 'utils/api'

const FLOW_ENDPOINT = '/flows'
const GRAPH_ENDPOINT = '/call-graph'

async function getFlows(page: number, pageSize: number): Promise<Flow[] | any> {
  try {
    const data = await client.get(FLOW_ENDPOINT + `?page=${page}&pageSize=${pageSize}`)
    return Promise.resolve(data)
  } catch (err) {
    return Promise.reject(err)
  }
}

async function callGraph(): Promise<Flow[] | any> {
  try {
    const data = await client.get(GRAPH_ENDPOINT)
    return Promise.resolve(data)
  } catch (err) {
    return Promise.reject(err)
  }
}

export {
  getFlows,
  callGraph
}

