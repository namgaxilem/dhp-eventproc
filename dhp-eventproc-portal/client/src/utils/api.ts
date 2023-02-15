import axios, { Axios } from 'axios'
import { config } from 'config/config'
import { LOGIN_ENDPOINT } from 'config/constants'

class RestClient {
    private client: Axios

    constructor(host: string) {
        this.client = axios.create({
            headers: {
                'Content-Type': 'application/json'
            },
            baseURL: host
        })
    }

    public get(endpoint: string, config?: object): Promise<any> {
        return this.client.get(endpoint, this.buildHeader(config))
            .then(this.handleSuccess)
            .catch(this.handleError)
    }

    public post(endpoint: string, data?: object, config?: object): Promise<any> {
        return this.client.post(endpoint, data, this.buildHeader(config))
            .then(this.handleSuccess)
            .catch(this.handleError)
    }

    public put(endpoint: string, data?: object, config?: object): Promise<any> {
        return this.client.put(endpoint, data, this.buildHeader(config))
            .then(this.handleSuccess)
            .catch(this.handleError)
    }

    public delete(endpoint: string, data?: object, config?: object): Promise<any> {
        return this.client.delete(endpoint, { ...this.buildHeader(config), data: data })
            .then(this.handleSuccess)
            .catch(this.handleError)
    }

    private buildHeader(c?: object): object {
        let cfg = {
            headers: {
                'Content-Type': 'application/json',
                // 'Authorization': ``
            },
            withCredentials: true
        }

        // if (user) {
        //   const userObj: User = JSON.parse(user)
        //   cfg.headers.Authorization = `Bearer ${userObj.accessToken}` || ''
        // }

        return { ...cfg, ...c }
    }

    private handleSuccess(res: any): Promise<any> {
        return Promise.resolve(res.data)
    }

    private handleError(res: any): Promise<any> {
        if (res.response.status === 401) {
            window.location.href = LOGIN_ENDPOINT;
            return Promise.reject('Unauthorized, trying login again.')
        }
        let error
        if (res.response)
            error = res.response.data.error
        else if (res.request)
            error = res.request
        else
            error = res.message
        if (res.response.status === 401) {
            window.location.href = window.location.href = LOGIN_ENDPOINT
        }
        return Promise.reject(error)
    }
}

const client = new RestClient(config.baseURL)
const loginClient = new RestClient(config.loginURL)

export {
    client,
    loginClient
}
