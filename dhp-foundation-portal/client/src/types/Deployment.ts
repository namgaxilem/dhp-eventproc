interface Deployment {
    deploymentId: string,
    deploymentName: string,
    flowId: string,
    version: number,
    environmentId: string,
    deployedAt: number,
    deployedBy: string,
    state: 'PENDING' | 'SUCCESS' | 'IN_PROGRESS' | 'REJECTED' | 'CANCEL',
    approvedAt?: number,
    approvedBy?: string
}

export default Deployment;