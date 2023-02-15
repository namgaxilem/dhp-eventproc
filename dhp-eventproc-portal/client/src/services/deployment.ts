import Flow from 'types/Flow'
import { client } from 'utils/api'

const DEPLOYMENT_ENDPOINT = '/deployment'
const USER_REQUEST_ENDPOINT = '/user-request'
const APPROVAL_ENDPOINT = '/approval'

async function getDeployment(): Promise<Flow[] | any> {
    try {
        const data = await client.get(DEPLOYMENT_ENDPOINT)
        return Promise.resolve(data)
    } catch (err) {
        return Promise.reject(err)
    }
}

async function postDeployment(body: any): Promise<Flow[] | any> {
    try {
        const data = await client.post(DEPLOYMENT_ENDPOINT, body)
        return Promise.resolve(data)
    } catch (err) {
        return Promise.reject(err)
    }
}

async function getUserRequest(id: number): Promise<Flow[] | any> {
    try {
        const data = await client.get(USER_REQUEST_ENDPOINT + `?id=${id}`)
        return Promise.resolve(data)
    } catch (err) {
        return Promise.reject(err)
    }
}

async function postUserRequest(body: any): Promise<Flow[] | any> {
    try {
        const data = await client.post(USER_REQUEST_ENDPOINT, body)
        return Promise.resolve(data)
    } catch (err) {
        return Promise.reject(err)
    }
}

async function approveRequest(requestId: string, body: any): Promise<Flow[] | any> {
    try {
        const data = await client.put(`${APPROVAL_ENDPOINT}/${requestId}`, body)
        return Promise.resolve(data)
    } catch (err) {
        return Promise.reject(err)
    }
}

export {
    postDeployment,
    getDeployment,
    postUserRequest,
    getUserRequest,
    approveRequest,
}

