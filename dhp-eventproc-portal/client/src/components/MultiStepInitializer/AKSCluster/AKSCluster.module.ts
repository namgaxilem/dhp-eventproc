interface Props {
    isValidForm: (isValid: boolean, model?: AKSClusterModel) => void
    model: AKSClusterModel
}

export interface AKSClusterModel {
    clusterName: string
    namespace: string
    description: string
    clusterScope: string
    namespaceScope: string
    domainSuffix: string
    istioGateway: string
}

export default Props;
